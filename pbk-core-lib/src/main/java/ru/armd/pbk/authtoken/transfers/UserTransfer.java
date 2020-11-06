package ru.armd.pbk.authtoken.transfers;

import java.util.Map;
import java.util.Set;

/**
 * Транспортный объект для отправки клиенту информации о пользователе.
 */
public class UserTransfer {

   private final Long userId;

   private final String name;

   private final String login;

   private final Boolean isLdapUser;

   private final Set<String> roleNames;

   private final Map<String, Boolean> rolesPermissions;

   private final String reportsAuth;

   /**
	* Коструктор трансфера принимает всю необходимую информацию о пользователе.
	*
	* @param userId           идентификатор пользователя.
	* @param userName         имя пользователя.
	* @param isLdapUser       тип пользователя(наш или внешний).
	* @param rolesPermissions список прав ролей пользователя.
	* @param roleNames        список имён ролей пользователя.
	*/
   public UserTransfer(Long userId, String login, String userName, Boolean isLdapUser, Map<String, Boolean> rolesPermissions, Set<String> roleNames, String reportsAuth) {
	  this.userId = userId;
	  this.login = login;
	  this.name = userName;
	  this.isLdapUser = isLdapUser;
	  this.rolesPermissions = rolesPermissions;
	  this.roleNames = roleNames;
	  this.reportsAuth = reportsAuth;
   }

   public String getName() {
	  return this.name;
   }

   public Map<String, Boolean> getRolesPermissions() {
	  return this.rolesPermissions;
   }

   public Long getUserId() {
	  return userId;
   }

   public Boolean getIsLdapUser() {
	  return isLdapUser;
   }

   public String getLogin() {
	  return login;
   }

   public Set<String> getRoleNames() {
	  return roleNames;
   }

   public String getReportsAuth() {
	  return reportsAuth;
   }
}