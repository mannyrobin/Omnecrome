package ru.armd.pbk.views.report;

/**
 * Данные для заполнения полей печатной формы стандартного отчёта "Сводная форма по
 * эффективности работы контролеров".
 */
public class So10View
	extends SoView {

   private String criterion;
   private Double curPeriod;
   private Double prevPeriod;
   private Double total;

   public String getCriterion() {
	  return criterion;
   }

   public void setCriterion(String criterion) {
	  this.criterion = criterion;
   }

   public Double getCurPeriod() {
	  return curPeriod;
   }

   public void setCurPeriod(Double curPeriod) {
	  this.curPeriod = curPeriod;
   }

   public Double getPrevPeriod() {
	  return prevPeriod;
   }

   public void setPrevPeriod(Double prevPeriod) {
	  this.prevPeriod = prevPeriod;
   }

   public Double getTotal() {
	  return total;
   }

   public void setTotal(Double total) {
	  this.total = total;
   }
}
