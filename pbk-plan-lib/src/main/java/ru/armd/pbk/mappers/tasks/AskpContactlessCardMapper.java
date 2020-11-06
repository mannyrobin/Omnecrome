package ru.armd.pbk.mappers.tasks;

import org.apache.ibatis.annotations.Param;
import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.mappers.IDomainMapper;
import ru.armd.pbk.domain.tasks.AskpContactlessCard;

import java.util.Date;

/**
 * Маппер данных БСК от АСКП.
 */
@IsMapper
public interface AskpContactlessCardMapper
	extends IDomainMapper<AskpContactlessCard> {

   /**
	* Привязать "Проходы по БСК контролера"
	* к заданиям на дату {@code date}.
	*
	* @param date дата выполнения заданий.
	*/
   void updateTasks(@Param("date") Date date);

   /**
	* Получает заголовок вкладки "Проходы по БСК контролера" по id задания.
	*
	* @param taskId id задания
	* @return заголовок вкладки "Проходы по БСК контролера"
	*/
   String getTitle(@Param("taskId") Long taskId);

   /**
	* Привязать проверку БСК к заданию.
	*
	* @param taskId - ИД задания.
	* @return количество привзяных.
	*/
   int bind(@Param("taskId") Long taskId);

   /**
	* Получить количество уникальных маршрутов для задания.
	*
	* @param taskId ИД задания
	* @return
	*/
   int getUniqueRouteCount(@Param("taskId") Long taskId);
}
