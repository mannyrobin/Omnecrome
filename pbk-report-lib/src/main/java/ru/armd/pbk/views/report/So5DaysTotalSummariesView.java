package ru.armd.pbk.views.report;

/**
 * View для сводных данных итого за период (для использования в отчёте
 * "Посменная численность контролёров ГУП "Мосгортранс" и среднее значение
 * численности за период").
 */
public class So5DaysTotalSummariesView
	extends SoView {

   private int firstShift;
   private int secondShift;
   private int total;

   public int getFirstShift() {
	  return firstShift;
   }

   public void setFirstShift(int firstShift) {
	  this.firstShift = firstShift;
   }

   public int getSecondShift() {
	  return secondShift;
   }

   public void setSecondShift(int secondShift) {
	  this.secondShift = secondShift;
   }

   public int getTotal() {
	  return total;
   }

   public void setTotal(int total) {
	  this.total = total;
   }
}
