package ru.armd.pbk.mappers.nsi;

import org.apache.ibatis.annotations.Param;
import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.mappers.IDomainMapper;
import ru.armd.pbk.core.views.ISelectItem;
import ru.armd.pbk.domain.nsi.Shift;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Мапер для операций со сменами.
 */
@IsMapper
public interface ShiftMapper
	extends IDomainMapper<Shift> {

   /**
	* Получение списка смен для отображения в плановых комбобоксах.
	*
	* @param params параметры фильтрации.
	* @return список смен объектов.
	*/
   List<ISelectItem> getSelectItemsForPlan(Map<String, Object> params);

   /**
	* Выбрать смену по id расписания.
	*
	* @param scheduleId id расписания.
	* @return смену.
	*/
   Shift getByScheduleId(@Param("scheduleId") Long scheduleId);

   /**
	* Выбрать смену подразделения.
	* Учитывается резерв подразделения, а не смены.
	*
	* @param code     код смены
	* @param deptId   ИД подразделения
	* @param workDate дата
	* @return
	*/
   Shift getDepartmentShift(@Param("code") String code, @Param("deptId") Long deptId, @Param("workDate") Date workDate);

   /**
	* Получить смену задания. Время окончания рабочего дня смены
	* зависит от планового времени работы сотрудника.
	* Например, если смена начинается в 11:00, а заканчивается в 20:00 и
	* плановое время сотрудника 7 часов, то время окончания рабочего дня будет 19:00.
	*
	* @param taskId ИД задания
	* @return смена
	*/
   Shift getByTaskId(@Param("taskId") Long taskId);

}
