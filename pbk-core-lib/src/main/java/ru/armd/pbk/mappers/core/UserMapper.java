package ru.armd.pbk.mappers.core;

import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.mappers.IDomainMapper;
import ru.armd.pbk.core.views.ISelectItem;
import ru.armd.pbk.domain.core.User;
import ru.armd.pbk.domain.core.UserExtended;

import java.util.List;
import java.util.Map;

/**
 * Маппер для работы с пользователями.
 */
@IsMapper
public interface UserMapper
	extends IDomainMapper<User> {

   /**
	* Получение пользователя по логину.
	*
	* @param login логин пользователя.
	* @return
	*/
   User getByLogin(String login);

   /**
	* Получение пользователя по логину с приджоиненой дополнительной информацией.
	* В результате список, потому что метод возвращает именно произведение нескольких таблиц.
	*
	* @param login логин пользователя.
	* @return
	*/
   List<UserExtended> getByLoginExtended(String login);

   /**
	* Получение департамента пользователя.
	*
	* @param login логин пользователя.
	* @return
	*/
   Long getDepartmentId(String login);

   /**
	* Получение списка пользователей для отображкения в комбобоксах сотрудников.
	*
	* @param params Параметры фильтрации.
	* @return Список пользователей.
	*/
   List<ISelectItem> getSelectItemsForEmployee(Map<String, Object> params);

   /**
	* Меняет пароль пользователя.
	*
	* @param user пользователь с новым паролем внутри.
	*/
   void changePassword(User user);

   /**
	* Обновляет поля связанные с активацией пользователя и попытками входа в систему.
	*
	* @param user пользователь.
	*/
   void updateUserStatus(User user);

   /**
	* Проверка подключения к БД.
	*/
   void checkDBConnection();
}