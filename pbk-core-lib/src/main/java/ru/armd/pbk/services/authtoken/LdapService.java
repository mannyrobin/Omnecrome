package ru.armd.pbk.services.authtoken;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.armd.pbk.core.components.BaseComponent;
import ru.armd.pbk.domain.core.Role;
import ru.armd.pbk.domain.core.UserRole;
import ru.armd.pbk.dto.core.UserDTO;
import ru.armd.pbk.dto.core.UserRoleDTO;
import ru.armd.pbk.enums.core.AuditLevel;
import ru.armd.pbk.enums.core.AuditType;
import ru.armd.pbk.enums.core.Settings;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.services.core.RoleService;
import ru.armd.pbk.services.core.UserRoleService;
import ru.armd.pbk.services.core.UserService;
import ru.armd.pbk.utils.date.DateUtils;

import javax.naming.AuthenticationException;
import javax.naming.CommunicationException;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Сервис по работе с LDAP-сервером.
 */
@Component
public class LdapService
	extends BaseComponent {

   public static final Logger LOGGER = Logger.getLogger(LdapService.class);

   private static final String LDAP_USER_PATTERN = "{LdapUser}";
   private static final String ERROR_MESSAGE_ID = "ERROR_MESSAGE";

   /**
	* Атрибуты пользователя, которые заполняются из ldap.
	*/
   public enum UserAttributes {
	  name,
	  expirationDate,
	  ldapCreateDate
   }

   ;

	/*
	 * Описание редактируемых полей в AD:
	 * 
	 * c Country or Region, eg. 'US' cn Common Name co Country company Company
	 * or Organization Name" department Department description Description
	 * displayName Display Name facsimileTelephoneNumber Fax Number givenName
	 * First Name homePhone Home Phone initials Initials ipPhone IP Phone Number
	 * l City mail E-Mail Address memberOf Group Membership middleName Middle
	 * Name mobile Mobile Number pager Pager Number physicalDeliveryOfficeName
	 * Office Location postalCode ZIP/Postal Code postOfficeBox Post Office Box
	 * sAMAccountName Logon Name (pre-Windows 2000) sn Last Name st
	 * State/Province streetAddress Street Address telephoneNumber Telephone
	 * Number title Job Title userPrincipalName Logon Name, eg.
	 * username@ldap.domain.com wWWHomePage Web Page Address
	 *
	 * 
	 * Служебные поля AD:
	 *
	 * whenChanged whenCreated accountExpires logonCount lastLogon lastLogoff
	 */

   private boolean inited = false;

   private String ldapURL = null;
   private String ldapDomain = null;
   private String ldapDomainDC = null;
   private String ldapSearchFilter = null;
   private String ldapSearchBase = null;
   private List<String> ldapSearchAttributes = null;
   private Map<UserAttributes, String> ldapAttributesMapping = null;

   @Autowired
   private UserService userService;

   @Autowired
   private RoleService roleService;

   @Autowired
   private UserRoleService userRoleService;

   @Autowired
   private StandardPasswordEncoder encoder;

   /**
	* Конструктор выставляет параметры по умолчанию.
	*/
   public LdapService() {

	  // Настройки по умолчанию
	  ldapSearchFilter = "(& (userPrincipalName={LdapUser})(objectClass=user))";
	  ldapSearchBase = ""; // например, cn=users
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   /**
	* Авторизоваться в ldap и создать/обновить пользователя в БД PBK.
	*
	* @param login    логин.
	* @param password пароль.
	*/
   @Transactional
   public void login(String login, String password) {

	  Map<String, String> attributes;
	  try {
		 attributes = parseAttributes(ldapAuthenticate(login, password));
	  } catch (PBKException e) {
		 logAudit(AuditLevel.ERROR, AuditType.LDAP, null, null, e.getMessage(), e);
		 throw e;
	  }

	  try {
		 UserDTO user = userService.getByLogin(login);

		 // Формируем dto пользователя
		 UserDTO dto = new UserDTO();
		 if (user != null) {
			dto.setId(user.getId());
		 } else {
			dto.setPassword(password);
		 }
		 dto.setLogin(login);
		 dto.setIsLdap(true);
		 dto.setIsActive(true);
		 for (UserAttributes key : ldapAttributesMapping.keySet()) {
			String name = ldapAttributesMapping.get(key);
			if (name == null || name.length() == 0) {
			   continue;
			}

			String value = attributes.get(name);
			if (value == null) {
			   continue;
			}

			if (UserAttributes.name.equals(key)) {
			   dto.setName(value);
			} else if (UserAttributes.expirationDate.equals(key)) {
			   dto.setExpirationDate(parseDate(name, value));
			} else if (UserAttributes.ldapCreateDate.equals(key)) {
			   dto.setLdapCreateDate(parseDate(name, value));
			}
		 }

		 // Обновить информацию о пользователе
		 if (user == null || !user.equals(dto)) {
			dto = userService.saveDTO(dto);
		 }

		 // Обновить пароль
		 if (user == null || !userService.checkPass(password, user.getId())) {
			dto.setPassword(password);
			dto.setReportsAuth(login + "@" + ldapDomain + ":" + password);
			userService.changePassword(dto);
		 }

		 // Обновить роли пользователя
		 String memberOf = attributes.get("memberOf");
		 if (memberOf != null) {
			// Парсинг групп
			String[] parts = memberOf.split(";");
			Set<String> groups = new HashSet<String>();
			for (int it = 0; it < parts.length; it++) {
			   String[] parts2 = parts[it].split(",");
			   if (parts2.length > 0 && parts2[0].startsWith("CN=") && parts2[0].length() > 3) {
				  groups.add(parts2[0].substring(3));
			   }
			}

			// Выбрать роли из БД
			List<Role> allRoles = roleService.getDomains(null);
			Set<Long> userRolesSet = new HashSet<Long>();
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("userId", dto.getId());
			List<UserRole> userRoles = userRoleService.getDomains(params);
			for (UserRole ur : userRoles) {
			   userRolesSet.add(ur.getRoleId());
			}

			// Добавить новые роли
			for (Role role : allRoles) {
			   if (role.getLdapRole() != null && groups.contains(role.getLdapRole())) {
				  if (!userRolesSet.contains(role.getId())) {
					 UserRoleDTO urDto = new UserRoleDTO();
					 urDto.setUserId(dto.getId());
					 urDto.setRoleId(role.getId());
					 userRoleService.saveDTO(urDto);
				  } else {
					 userRolesSet.remove(role.getId());
				  }
			   }
			}

			// Удалить старые роли
			if (userRolesSet.size() > 0) {
			   List<Long> ids = new ArrayList<Long>();
			   for (UserRole ur : userRoles) {
				  if (userRolesSet.contains(ur.getRoleId())) {
					 ids.add(ur.getId());
				  }
			   }
			   userRoleService.delete(ids);
			}
		 }
	  } catch (PBKException e) {
		 logAudit(AuditLevel.ERROR, AuditType.LDAP, null, null, e.getMessage(), e);
		 throw e;
	  } catch (Throwable t) {
		 String errorMessage = ". " + settingsMapper.getByCode(ERROR_MESSAGE_ID).getValue();
		 PBKException e = new PBKException("Ошибка создания или обновления пользователя:  " + t.getMessage() + errorMessage, t);
		 logAudit(AuditLevel.ERROR, AuditType.LDAP, null, null, e.getMessage(), e);
		 throw e;
	  }
   }

   private Attributes ldapAuthenticate(String user, String password) {

	  init();

	  DirContext ldapContext = null;
	  try {
		 String ldapUser = user + "@" + ldapDomain;

		 Hashtable<String, String> ldapEnv = new Hashtable<String, String>(10);
		 ldapEnv.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		 ldapEnv.put(Context.PROVIDER_URL, ldapURL);
		 ldapEnv.put(Context.SECURITY_AUTHENTICATION, "simple"); // simple,
		 // ssl
		 ldapEnv.put(Context.SECURITY_PRINCIPAL, ldapUser);
		 ldapEnv.put(Context.SECURITY_CREDENTIALS, password);
		 ldapContext = new InitialDirContext(ldapEnv);

		 SearchControls searchCtls = new SearchControls();
		 if (ldapSearchAttributes != null && ldapSearchAttributes.size() > 0) {
			searchCtls.setReturningAttributes(ldapSearchAttributes.toArray(new String[0]));
		 }
		 searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
		 searchCtls.setTimeLimit(10000);

		 String searchFilter = replace(ldapSearchFilter, LDAP_USER_PATTERN, ldapUser);

		 int count = 0;
		 Attributes attrs = null;
		 NamingEnumeration<SearchResult> answer = ldapContext.search(ldapSearchBase, searchFilter, searchCtls);
		 while (answer.hasMoreElements()) {
			SearchResult result = (SearchResult) answer.next();

			count++;
			attrs = result.getAttributes();
		 }

		 if (count == 1) {
			return attrs;
		 } else {
			String errorMessage = ". " + settingsMapper.getByCode(ERROR_MESSAGE_ID).getValue();
			throw new PBKException("Ошибка аутентификации на ldap-сервере, найдено " + count
				+ " аккаунтов для пользователя " + ldapUser + errorMessage);
		 }
	  } catch (Throwable t) {
		 throw makePBKException(t);
	  } finally {
		 try {
			if (ldapContext != null) {
			   ldapContext.close();
			}
		 } catch (Throwable t) {
			LOGGER.error(t);
		 }
	  }
   }

   private PBKException makePBKException(Throwable t) {

	  String errorMessage = ". " + settingsMapper.getByCode(ERROR_MESSAGE_ID).getValue();
	  if (t instanceof PBKException) {
		 return (PBKException) t;
	  } else if (t instanceof CommunicationException) {
		 return new PBKException("Ошибка подключения к серверу LDAP: " + t.getMessage() + errorMessage, t);
	  } else if (t instanceof NamingException && !(t instanceof AuthenticationException)) {
		 return new PBKException("Некорректное имя сервера LDAP: " + t.getMessage() + errorMessage, t);
	  } else if (t instanceof AuthenticationException && t.getMessage() != null) {
		 // [LDAP: error code 49 - 80090308: LdapErr: DSID-0C0903A9, comment:
		 // AcceptSecurityContext error, data 52e, v1db0
		 String[] tokens = t.getMessage().split(", ");
		 String data = null;
		 for (String token : tokens) {
			if (token.startsWith("data ")) {
			   data = token.substring(5);
			}
		 }

		 // invalid credentials​
		 if ("52e".equals(data)) {
			return new PBKException("Неверные учетные данные. Код ошибки: 52e", t);
		 }
		 // user not found
		 if ("525".equals(data)) {
			return new PBKException("Пользователь не найден. Код ошибки: 525", t);
		 }
		 // not permitted to logon at this time​
		 if ("530".equals(data)) {
			return new PBKException("В данный момент аутентификация невозможна. Код ошибки: 530" + errorMessage, t);
		 }
		 // not permitted to logon at this workstation​
		 if ("531".equals(data)) {
			return new PBKException("На данной рабочей станции аутентификация невозможна. Код ошибки: 531" + errorMessage, t);
		 }
		 // password expired
		 if ("532".equals(data)) {
			return new PBKException("Срок действия пароля истек. Код ошибки: 532" + errorMessage, t);
		 }
		 // account disabled
		 if ("533".equals(data)) {
			return new PBKException("Пользователь отключен. Код ошибки: 533" + errorMessage, t);
		 }
		 // account expired
		 if ("701".equals(data)) {
			return new PBKException("Доступ прекращен. Код ошибки: 701" + errorMessage, t);
		 }
		 // user must reset password
		 if ("773".equals(data)) {
			return new PBKException("Необходимо изменить пароль. Код ошибки: 773" + errorMessage, t);
		 }
		 // user account locked
		 if ("775".equals(data)) {
			return new PBKException("Пользователь заблокирован. Код ошибки: 775" + errorMessage, t);
		 }
	  }

	  return new PBKException("Ошибка аутентификации: " + t.getMessage() + errorMessage, t);
   }

   @SuppressWarnings("unused")
   private boolean ldapTestConnection() {
	  String testUserId = "uid_ABCDEFGHIJKLMNOP";
	  String testPassword = "pwd_ABCDEFGHIJKLMNOP";

	  try {
		 ldapAuthenticate(testUserId, testPassword);
	  } catch (PBKException e) {
		 // Проверяем оригинальное исключение
		 if (!(e.getCause() instanceof AuthenticationException) || e.getCause().getMessage() == null) {
			throw e;
		 }
		 // Проверяем распарсеный код ошибки
		 if (e.getMessage().endsWith("52e")) {
			return true;
		 }

		 throw e;
	  }

	  return true;
   }

   private Map<String, String> parseAttributes(Attributes attributes) {

	  HashMap<String, String> parsedAttributes = new HashMap<String, String>();
	  try {
		 NamingEnumeration<?> eAttributes = attributes.getAll();

		 if (eAttributes != null) {
			while (eAttributes.hasMore()) {
			   Object oItem = eAttributes.next();
			   if (oItem instanceof Attribute) {
				  Attribute att = (Attribute) oItem;
				  if (att.getID() == null || att.getID().length() == 0) {
					 continue;
				  }
				  if (att.size() > 1 && att.getAll() != null) {
					 StringBuffer sb = new StringBuffer();
					 for (int it = 0; it < att.size(); it++) {
						sb.append(att.get(it).toString());
						sb.append(";");
					 }
					 parsedAttributes.put(att.getID(), sb.toString());
				  } else if (att.get() != null) {
					 parsedAttributes.put(att.getID(), att.get().toString());
				  }
			   }
			}
		 }
	  } catch (Throwable t) {
		 String errorMessage = ". " + settingsMapper.getByCode(ERROR_MESSAGE_ID).getValue();
		 throw new PBKException("Невозможно распарсить ответ ldap-сервера" + errorMessage, t);
	  }

	  return parsedAttributes;
   }

   private void init()
	   throws PBKException {
	  if (inited) {
		 return;
	  }

	  try {
		 setLdapSettings(getSettingStringValue(Settings.AD_URL), getSettingStringValue(Settings.AD_DOMAIN));

		 ldapAttributesMapping = new HashMap<UserAttributes, String>();
		 String attr = getSettingStringValue(Settings.AD_ATTR_NAME);
		 if (attr != null && attr.length() > 0) {
			ldapAttributesMapping.put(UserAttributes.name, attr);
		 }
		 attr = getSettingStringValue(Settings.AD_ATTR_EXPIRATION_DATE);
		 if (attr != null && attr.length() > 0) {
			ldapAttributesMapping.put(UserAttributes.expirationDate, attr);
		 }
		 attr = getSettingStringValue(Settings.AD_ATTR_LDAP_CREATE_DATE);
		 if (attr != null && attr.length() > 0) {
			ldapAttributesMapping.put(UserAttributes.ldapCreateDate, attr);
		 }

		 ldapSearchAttributes = new ArrayList<String>(ldapAttributesMapping.values());
		 ldapSearchAttributes.add("memberOf");

		 inited = true;
	  } catch (Throwable t) {
		 String errorMessage = ". " + settingsMapper.getByCode(ERROR_MESSAGE_ID).getValue();
		 throw new PBKException("Невозможно прочитать настройки ldap-сервера" + errorMessage, t);
	  }
   }

   private void setLdapSettings(String url, String domain) {
	  ldapDomain = domain;
	  ldapDomainDC = getDomainDC(ldapDomain);
	  ldapURL = url + "/" + ldapDomainDC;
   }

   private static String getDomainDC(String domain) {
	  StringBuilder sb = new StringBuilder(domain.length() + 20);
	  sb.append("dc=");
	  int start = 0;
	  int found = 0;
	  while ((found = domain.indexOf('.', start)) != -1) {
		 sb.append(domain.substring(start, found));
		 sb.append(",dc=");
		 start = found + 1;
	  }
	  sb.append(domain.substring(start, domain.length()));

	  return sb.toString();
   }

   private static String replace(String str, String substr, String replace) {
	  int index = 0;
	  while ((index = str.indexOf(substr)) != -1) {
		 str = str.substring(0, index) + replace + str.substring(index + substr.length());
	  }

	  return str;
   }

   private Date parseDate(String name, String value) {
	  try {
		 Date date = null;
		 if ("accountExpires".equals(name)) {
			date = new Date((new Long(value) - 0x19db1ded53e8000L) / 10000);
		 } else {
			String[] parts = value.split("\\.");
			String dateTimePart = parts[0];
			String timeZonePart = "+0" + parts[1].substring(0, parts[1].length() - 1) + "00";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssZ");
			date = sdf.parse(dateTimePart + timeZonePart);
		 }

		 Date end = DateUtils.getVersionEndDate();
		 if (date.after(end)) {
			date = end;
		 } else if (date.before(new Date(0))) {
			date = new Date(0);
		 }

		 return date;
	  } catch (Throwable t) {
		 String errorMessage = ". " + settingsMapper.getByCode(ERROR_MESSAGE_ID).getValue();
		 throw new PBKException("Ошибка перобразования данных:  " + t.getMessage() + errorMessage, t);
	  }
   }

}