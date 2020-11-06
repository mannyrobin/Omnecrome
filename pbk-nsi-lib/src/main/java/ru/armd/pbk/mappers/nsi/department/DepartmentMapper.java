package ru.armd.pbk.mappers.nsi.department;

import org.apache.ibatis.annotations.Param;
import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.mappers.IVersionDomainMapper;
import ru.armd.pbk.core.views.SelectItem;
import ru.armd.pbk.domain.nsi.department.Department;

import java.util.List;
import java.util.Map;

/**
 * Маппер департамента.
 */
@IsMapper
public interface DepartmentMapper
	extends IVersionDomainMapper<Department> {

   /**
	* Получение списка подразделений.
	*
	* @param params параметры
	* @return
	*/
   List<SelectItem> getSelectItemsByName(Map<String, Object> params);

   /**
	* Получение подразделения по id расписания.
	*
	* @param scheduleId id расписания
	* @return подразделение
	*/
   Department getDepartmentByScheduleId(@Param("scheduleId") Long scheduleId);

   /**
	* Получает список подразделений для фильтра карт.
	*
	* @param params параметры
	* @return список подразделений
	*/
   List<SelectItem> getSelectItemForMap(Map<String, Object> params);

   /**
	* Получает подразделение по id места встречи.
	*
	* @param venueId id места встречи
	* @return подразделение
	*/
   Department getByVenueId(@Param("id") Long venueId);
}
