package ru.armd.pbk.views.nsi.askp;

import ru.armd.pbk.core.views.BaseGridView;

/**
 * Данные для заполнения полей печатной формы стандартного отчёта "Сводный сравнительный анализ данных
 * пассажиропотока (АСКП/АСМ-ПП)".
 */
public class AskpAnalysisByRoutesView
	extends BaseGridView {

   private String routeNumber;
   private Integer askpPassengerCount;
   private Integer asmppPassengerCount;
   private Integer diffs;

   public String getRouteNumber() {
	  return routeNumber;
   }

   public void setRouteNumber(String routeNumber) {
	  this.routeNumber = routeNumber;
   }

   public Integer getAskpPassengerCount() {
	  return askpPassengerCount;
   }

   public void setAskpPassengerCount(Integer askpPassengerCount) {
	  this.askpPassengerCount = askpPassengerCount;
   }

   public Integer getAsmppPassengerCount() {
	  return asmppPassengerCount;
   }

   public void setAsmppPassengerCount(Integer asmppPassengerCount) {
	  this.asmppPassengerCount = asmppPassengerCount;
   }

   public Integer getDiffs() {
	  return diffs;
   }

   public void setDiffs(Integer diffs) {
	  this.diffs = diffs;
   }
}
