package ru.armd.pbk.views.report;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.armd.pbk.utils.json.HyphenSepratedDateSerializer;

import java.util.Date;

/**
 * View для сводных данных по дате (для использования в отчёте "Посменная
 * численность контролёров ГУП "Мосгортранс" и среднее значение численности за
 * период").
 */
public class So5DaySummariesView
	extends SoView {

   @JsonSerialize(using = HyphenSepratedDateSerializer.class)
   private Date date;
   private int firstShift;
   private int secondShift;
   private int total;

   public Date getDate() {
	  return date;
   }

   public void setDate(Date date) {
	  this.date = date;
   }

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
