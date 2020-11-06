package ru.armd.pbk.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.core.views.BaseGridView;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.repositories.So4Repository;
import ru.armd.pbk.utils.json.JsonGridData;
import ru.armd.pbk.views.report.So4View;

import java.util.Calendar;
import java.util.List;

/**
 * Сервис стандартного отчёта
 * "Ежедневная посменная численность контролёров ГУП "Мосгортранс" по
 * территориальному подразделению".
 */
@Service
public class So4Service
	extends SoService<BaseDomain, BaseDTO> {

   private static final Logger LOGGER = Logger.getLogger(So4Service.class);

   @Autowired
   So4Service(So4Repository repository) {
	  super(repository);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   public <Views extends BaseGridView, Params extends BaseGridViewParams> JsonGridData getGridViews(Params params) {
	  return super.getGridViews(params);
   }

   /**
	* Получить данные для строки таблицы "Среднее за период".
	*
	* @param totalView информация о строке таблицы "Итого"
	* @param size      кол-во записей в таблице
	* @return данные для строки таблицы "Среднее за период"
	*/
   public So4View getAverageTotal(So4View totalView, int size) {
	  So4View result = new So4View();
	  result.setTotalTitle("Среднее за период");
	  result.setFirstShiftCount(totalView.getFirstShiftCount() / size);
	  result.setSecondShiftCount(totalView.getSecondShiftCount() / size);
	  result.setDayCount(totalView.getDayCount() / size);
	  result.setNightCount(totalView.getNightCount() / size);
	  result.setTotalCount(totalView.getTotalCount() / size);
	  return result;
   }

   /**
	* Получить данные для строки таблицы "Среднее по будним дням за период".
	*
	* @param views строки таблицы
	* @return данные для строки таблицы "Среднее по будним дням за период"
	*/
   public So4View getAverageWeekDayTotal(List<So4View> views) {
	  So4View result = new So4View();
	  result.setTotalTitle("Среднее по будним дням за период");
	  result.setFirstShiftCount(0);
	  result.setSecondShiftCount(0);
	  result.setDayCount(0);
	  result.setNightCount(0);
	  result.setTotalCount(0);
	  int weekDaysRowCount = 0;
	  Calendar c = Calendar.getInstance();
	  int dayOfWeek = 0;
	  for (So4View view : views) {
		 c.setTime(view.getDate());
		 dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		 if (dayOfWeek > 1 && dayOfWeek < 7) {
			result.setFirstShiftCount(result.getFirstShiftCount() + view.getFirstShiftCount());
			result.setSecondShiftCount(result.getSecondShiftCount() + view.getSecondShiftCount());
			result.setDayCount(result.getDayCount() + view.getDayCount());
			result.setNightCount(result.getNightCount() + view.getNightCount());
			result.setTotalCount(result.getTotalCount() + view.getTotalCount());
			weekDaysRowCount++;
		 }
	  }
	  result.setFirstShiftCount(weekDaysRowCount > 0 ? result.getFirstShiftCount() / weekDaysRowCount : 0);
	  result.setSecondShiftCount(weekDaysRowCount > 0 ? result.getSecondShiftCount() / weekDaysRowCount : 0);
	  result.setDayCount(weekDaysRowCount > 0 ? result.getDayCount() / weekDaysRowCount : 0);
	  result.setNightCount(weekDaysRowCount > 0 ? result.getNightCount() / weekDaysRowCount : 0);
	  result.setTotalCount(weekDaysRowCount > 0 ? result.getTotalCount() / weekDaysRowCount : 0);
	  return result;
   }
}
