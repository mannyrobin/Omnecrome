package ru.armd.pbk.services.core;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.armd.pbk.core.services.BaseDomainService;
import ru.armd.pbk.core.views.BaseGridView;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.core.views.BaseSelectListParams;
import ru.armd.pbk.core.views.ISelectItem;
import ru.armd.pbk.domain.core.User;
import ru.armd.pbk.dto.core.UserDTO;
import ru.armd.pbk.enums.core.Settings;
import ru.armd.pbk.matcher.core.IUserMatcher;
import ru.armd.pbk.repositories.core.UserRepository;
import ru.armd.pbk.utils.json.JsonGridData;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Сервис пользователей.
 */
@Service
public class UserService
	extends BaseDomainService<User, UserDTO>
	implements InitializingBean {

   private static final Logger LOGGER = Logger.getLogger(UserService.class);

   private static final String PASS_STUB = "passwordstub1";
   private static final long MS_IN_MIN = 60000L;

   private UserRepository userRepository;

   @Autowired
   private UserRoleService userRoleService;

   @Autowired
   private StandardPasswordEncoder encoder;

   private int maxLoginAttemptFails;
   private long maxBanPeriod;
   private long checkAttemptPeriod;

   @Autowired
   UserService(UserRepository repository) {
	  super(repository);
	  this.userRepository = repository;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   /**
	* Получить пользователя по логину.
	*
	* @param login логин.
	* @return пользователь.
	*/
   public UserDTO getByLogin(String login) {
	  return toDTO(((UserRepository) domainRepository).getByLogin(login));
   }

   @Transactional
   @Override
   public <Views extends BaseGridView, Params extends BaseGridViewParams> JsonGridData getGridViews(Params params) {
	  if (params.getOrderBy() == null) {
		 params.setOrderBy("createDate");
		 params.setOrderByAsc(false);
	  }
	  return super.getGridViews(params);
   }

   @Override
   public UserDTO toDTO(User domain) {
	  if (domain == null) {
		 return null;
	  }
	  UserDTO dto = IUserMatcher.INSTANCE.toDTO(domain);
	  dto.setPassword(PASS_STUB);
	  dto.setPasswordRepeat(PASS_STUB);
	  return dto;
   }

   @Override
   public User toDomain(UserDTO dto) {
	  if (dto == null) {
		 return null;
	  }
	  if (dto.getId() == null && dto.getPassword() != null) {
		 dto.setPassword(encoder.encode(dto.getPassword()));
	  }
	  System.out.print(dto.getExpirationDate());
	  if (dto.getExpirationDate() == null) {
		 dto.setExpirationDate(new GregorianCalendar(2100, 0, 1).getTime());
		 System.out.print(dto.getExpirationDate());
	  }
	  return IUserMatcher.INSTANCE.toDomain(dto);
   }

   /**
	* Изменение пароля.
	*
	* @param changePassDto пароль.
	*/
   @Transactional
   public void changePassword(UserDTO changePassDto) {
	  User user = toDomain(changePassDto);
	  user.setPassword(encoder.encode(user.getPassword()));
	  if (user.getReportsAuth() != null) {
		 user.setReportsAuth(new String(Base64.encodeBase64(user.getReportsAuth().getBytes())));
	  }
	  ((UserRepository) domainRepository).changePassword(user);
   }

   /**
	* Получение списка пользователей для отображкения в комбобоксах
	* сотрудников.
	*
	* @param params Параметры фильтрации.
	* @return Список пользователей.
	*/
   @Transactional
   public List<ISelectItem> getSelectItemsForEmployee(BaseSelectListParams params) {
	  List<ISelectItem> selectItemList = userRepository.getSelectItemsForEmployee(params);
	  return selectItemList;
   }

   /**
	* Попытка логина пользователя.
	*
	* @param userDto пользователь
	*/
   @Transactional
   public void processBadLoginAttempt(UserDTO userDto) {
	  if (userDto.getLoginAttemptDate() == null || (userDto.getLoginAttemptDate() != null
		  && System.currentTimeMillis() - userDto.getLoginAttemptDate().getTime() > checkAttemptPeriod)) {
		 userDto.setLoginAttemptCount(1);
	  } else {
		 userDto.setLoginAttemptCount(userDto.getLoginAttemptCount() + 1);
	  }
	  userDto.setLoginAttemptDate(new Date());
	  if (userDto.getLoginAttemptCount() == maxLoginAttemptFails) {
		 userDto.setIsActive(false);
	  }
	  User user = toDomain(userDto);
	  userRepository.updateUserStatus(user);
   }

   /**
	* Разблокировка пользователя.
	*
	* @param userDto ДТО пользователя.
	*/
   @Transactional
   public void unlockUser(UserDTO userDto) {
	  userDto.setLoginAttemptCount(0);
	  userDto.setIsActive(true);
	  userDto.setLoginAttemptDate(null);
	  User domain = toDomain(userDto);
	  userRepository.updateUserStatus(domain);
   }

   /**
	* Проверка пользователя с установкой разблокировки.
	*
	* @param user пользователь
	*/
   @Transactional
   public void checkAndUpdateUserActiveStatus(UserDTO user) {
	  if (!user.getIsActive()) {
		 if (user.getLoginAttemptDate() == null
			 || System.currentTimeMillis() - user.getLoginAttemptDate().getTime() > maxBanPeriod) {
			unlockUser(user);
		 }
	  }
   }

   /**
	* Проверка пароля.
	*
	* @param pass   пароль
	* @param userId ИД
	* @return
	*/
   @Transactional
   public boolean checkPass(String pass, Long userId) {
	  User user = userRepository.getById(userId);
	  if (user == null) {
		 return false;
	  }
	  return encoder.matches(pass, user.getPassword());
   }

   @Override
   public void afterPropertiesSet()
	   throws Exception {
	  this.maxBanPeriod = Long.parseLong(getSettingValue(Settings.MAX_USER_BAN_PERIOD, "30")) * MS_IN_MIN;
	  this.maxLoginAttemptFails = Integer.parseInt(getSettingValue(Settings.MAX_LOGIN_ATTEMPT_FAILS, "5"));
	  this.checkAttemptPeriod = Long.parseLong(getSettingValue(Settings.CHECK_LOGIN_ATTEMPTS_PERIOD, "50")) * MS_IN_MIN;
   }

   /**
	* Проверка подключения к БД.
	*/
   public boolean checkDBConnection() {
	  return userRepository.checkDBConnection();
   }
}
