package ru.armd.pbk.mappers.tasks;

import org.apache.ibatis.annotations.Param;
import ru.armd.pbk.annotations.mappers.IsMapper;

import java.util.List;

/**
 * Маппер для получения информации о маршрутах необходимой для печатной формы задания.
 */
@IsMapper
public interface TaskRouteReportMapper {

   /**
	* Получает все номера маршрутов для задания.
	*
	* @param taskId id задания
	* @return список номеров маршрутов
	*/
   List<String> getRouteNumbersByTaskId(@Param("taskId") Long taskId);
}
