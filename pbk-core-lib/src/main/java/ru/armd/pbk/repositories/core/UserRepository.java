package ru.armd.pbk.repositories.core;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.IHasCreater;
import ru.armd.pbk.core.IHasUpdater;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.core.views.BaseSelectListParams;
import ru.armd.pbk.core.views.ISelectItem;
import ru.armd.pbk.core.views.SelectItem;
import ru.armd.pbk.domain.core.Role;
import ru.armd.pbk.domain.core.User;
import ru.armd.pbk.domain.core.UserExtended;
import ru.armd.pbk.domain.core.UserRole;
import ru.armd.pbk.enums.core.AuditLevel;
import ru.armd.pbk.enums.core.AuditObjOperation;
import ru.armd.pbk.enums.core.AuditType;
import ru.armd.pbk.enums.core.CoreAuditType;
import ru.armd.pbk.enums.core.EmbeddedRole;
import ru.armd.pbk.enums.core.EmbeddedUser;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.mappers.core.UserMapper;

import java.util.List;

/**
 * Репозиторий пользователей.
 */
@Repository
public class UserRepository
	extends BaseDomainRepository<User> {

   public static final Logger LOGGER = Logger.getLogger(UserRepository.class);

   private UserMapper userMapper;

   private UserRoleRepository userRoleRepo;

   private RoleRepository roleRepo;

   /**
	* Создаёт репозиторий для пользователей.
	*
	* @param mapper       маппер пользователей.
	* @param userRoleRepo репозиторий связок пользователей и ролей.
	* @param roleRepo     репозиторий ролей.
	*/
   @Autowired
   UserRepository(UserMapper mapper, UserRoleRepository userRoleRepo, RoleRepository roleRepo) {
	  super(CoreAuditType.CORE_USER, mapper);
	  this.userMapper = mapper;
	  this.userRoleRepo = userRoleRepo;
	  this.roleRepo = roleRepo;
   }

   /**
	* Меняет пароль пользователю.
	*
	* @param user пользователь с новым паролем внутри.
	*/
   public void changePassword(User user) {
	  initUpdaterInfo(user);
	  ((UserMapper) domainMapper).changePassword(user);
   }

   /**
	* Получение департамента пользователя.
	*
	* @param login логин пользователя.
	* @return
	*/
   public Long getDepartmentId(String login) {
	  try {
		 return userMapper.getDepartmentId(login);
	  } catch (Exception e) {
		 String message = "Не удалось получить идентификатор департамента по логину. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, null, message, e);
		 throw new PBKException(message, e);
	  }
   }

   /**
	* Возвращает пользователя по логину.
	*
	* @param login логин.
	* @return
	*/
   public User getByLogin(String login) {
	  try {
		 return userMapper.getByLogin(login);
	  } catch (Exception e) {
		 String message = "Не удалось получить пользователя по логину. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, null, message, e);
		 throw new PBKException(message, e);
	  }
   }

   /**
	* Получение пользователя по логину с приджоиненой дополнительной информацией.
	* В результате список, потому что метод возвращает именно произведение нескольких таблиц.
	*
	* @param login логин.
	* @return
	*/
   public List<UserExtended> getByLoginExtended(String login) {
	  try {
		 return userMapper.getByLoginExtended(login);
	  } catch (Exception e) {
		 String message = "Не удалось получить пользователя по логину. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, null, message, e);
		 throw new PBKException(message, e);
	  }
   }

   @Override
   public User save(User domain) {
	  boolean isNewUser = domain.getId() == null;
	  User result = super.save(domain);
	  if (isNewUser && !domain.isLdap()) {
		 Role role = roleRepo.getById(EmbeddedRole.PBK_USERS_ROLE.getId());
		 if (role == null) {
			throw new PBKException("В БД отсутвует роль по умолчанию");
		 }
		 UserRole userRole = new UserRole();
		 userRole.setRoleId(role.getId());
		 userRole.setUserId(result.getId());
		 initCreaterInfo(userRole);
		 userRoleRepo.save(userRole);
	  }
	  return result;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   protected IHasCreater initCreaterInfo(IHasCreater object) {
	  try {
		 super.initCreaterInfo(object);
	  } catch (PBKException e) {
		 object.setCreateUserId(EmbeddedUser.SYSTEM.getId());
	  }
	  return object;
   }

   @Override
   protected IHasUpdater initUpdaterInfo(IHasUpdater object) {
	  try {
		 super.initUpdaterInfo(object);
	  } catch (PBKException e) {
		 object.setUpdateUserId(EmbeddedUser.SYSTEM.getId());
	  }
	  return object;
   }

   /**
	* Получение списка пользователей для отображкения в комбобоксах сотрудников.
	*
	* @param params Параметры фильтрации.
	* @return Список пользователей.
	*/
   public List<ISelectItem> getSelectItemsForEmployee(BaseSelectListParams params) {
	  List<ISelectItem> sList = null;
	  try {
		 sList = userMapper.getSelectItemsForEmployee(params.getFilter());
		 sList.add(0, new SelectItem(null, "Не выбран"));
	  } catch (Exception e) {
		 String message = "Не удалось получить список пользователей. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, null, message, e);
		 throw new PBKException(message, e);
	  }
	  return sList;
   }

   /**
	* Обновляет поля связанные с блокировкой пользователя.
	*
	* @param user пользователь с обновлённым статусом.
	*/
   public void updateUserStatus(User user) {
	  try {
		 initUpdaterInfo(user);
		 userMapper.updateUserStatus(user);
		 StringBuilder builder = new StringBuilder();
		 builder.append("Пользователь: ");
		 builder.append(user.getLogin());
		 if (user.getIsActive()) {
			builder.append(" разблокирован.");
			logAudit(AuditLevel.INFO, AuditType.UNLOCK, AuditObjOperation.OTHER, null, builder.toString(), null);
		 } else {
			builder.append(" заблокирован.");
			logAudit(AuditLevel.INFO, AuditType.LOCK, AuditObjOperation.OTHER, null, builder.toString(), null);
		 }
	  } catch (Exception e) {
		 String message = "Не удалось обновить статус пользователя. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.UPDATE, null, message, e);
		 throw new PBKException(message, e);
	  }
   }

   /**
	* Проверка подключения к БД.
	*/
   public boolean checkDBConnection() {
	  try {
		 userMapper.checkDBConnection();
		 return true;
	  } catch (Exception e) {
		 return false;
	  }
   }

}
