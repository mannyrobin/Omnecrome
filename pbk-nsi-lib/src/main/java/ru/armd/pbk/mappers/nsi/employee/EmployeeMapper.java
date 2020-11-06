package ru.armd.pbk.mappers.nsi.employee;

import org.apache.ibatis.annotations.Param;
import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.mappers.IVersionDomainMapper;
import ru.armd.pbk.core.views.ISelectItem;
import ru.armd.pbk.domain.nsi.employee.Employee;
import ru.armd.pbk.domain.nsi.employee.PuskPlanningEmployee;
import ru.armd.pbk.views.nsi.route.RouteSelectItem;

import java.util.List;
import java.util.Map;

/**
 * Маппер для сотрудников.
 */
@IsMapper
public interface EmployeeMapper
	extends IVersionDomainMapper<Employee> {

	Employee getPhotoByDomainId(@Param("id") Long id);

	void deletePhoto(@Param("id") Long id);

	void updatePhoto(@Param("id") Long id, @Param("photo") byte[] photo);
   /**
	* Получение сотрудника по ИД задания.
	*
	* @param taskId ИД задания
	* @return
	*/
   Employee getEmployeeByTaskId(Long taskId);

   /**
	* Получение сотрудника по устройству.
	*
	* @param contId ИД БСК
	* @param puskId ИД ПУсК
	* @param offId  ИД СКК
	* @param dvrId  ИД видеорегистратора
	* @return
	*/
   Employee getEmployeeByDeviceId(@Param("contId") Long contId, @Param("puskId") Long puskId,
								  @Param("offId") Long offId, @Param("dvrId") Long dvrId);

   /**
	* Получение сотрудника по номеру СКК.
	*
	* @param cardNumber - номер СКК.
	* @return сотрудник.
	*/
   Employee getEmployeeBySKKCardNumber(@Param("cardNumber") String cardNumber);

   /**
	* Проверяет возможность восстановления версии сотрудника (проверяет
	* используются ли устрайства из данной версии).
	*
	* @param params параметры
	* @return истина, если устройства уже используются
	*/
   boolean checkEmployeeVersion(Map<String, Object> params);

   /**
	* Получение предпоследней актуальной версии сотрудника,
	* используется в востановлении сотрудника после увольнения.
	*
	* @param headId индефикатор сотрудника
	* @return
	*/
   Employee getLastButOneFireActual(@Param("domainId") Long headId);

   /**
	* Возвращает список сотрудников МГТ, работающих в тот же день.
	*
	* @param taskId базовое задание
	* @return
	*/
   List<RouteSelectItem> getEmployeesOnDate(@Param("taskId") Long taskId);

   /**
	* Возвращает список сотрудников МГТ, для поля подписант.
	*
	* @param <SelectItem> тип элемента списка.
	* @param params       параметры.
	* @return
	*/
   <SelectItem extends ISelectItem> List<SelectItem> getSelectItemsForSign(Map<String, Object> params);

	List<PuskPlanningEmployee> getPlanningEmployeeForPusk();
}