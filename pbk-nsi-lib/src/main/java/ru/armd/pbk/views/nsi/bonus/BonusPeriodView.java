package ru.armd.pbk.views.nsi.bonus;

import ru.armd.pbk.core.views.BaseGridView;

/**
 * View для отображения коэфициента премирования.
 */
public class BonusPeriodView
	extends BaseGridView {

   private Long id;
   private int countFrom;
   private Integer countTo;
   private int percents;

   public Long getId() {
	  return id;
   }

   public void setId(Long id) {
	  this.id = id;
   }

   public int getCountFrom() {
	  return countFrom;
   }

   public void setCountFrom(int countFrom) {
	  this.countFrom = countFrom;
   }

   public Integer getCountTo() {
	  return countTo;
   }

   public void setCountTo(Integer countTo) {
	  this.countTo = countTo;
   }

   public int getPercents() {
	  return percents;
   }

   public void setPercents(int percents) {
	  this.percents = percents;
   }
}
