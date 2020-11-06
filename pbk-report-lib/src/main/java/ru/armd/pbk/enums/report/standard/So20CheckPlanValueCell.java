package ru.armd.pbk.enums.report.standard;

/**
 * Created by Yakov Volkov.
 */
public enum So20CheckPlanValueCell {

   ROUTE_NUMBER(0),
   CHECK_DATE(1),
   SHIFT(2),
   MOC(3);

   private final Integer col;

   So20CheckPlanValueCell(Integer col) {
	  this.col = col;
   }

   public Integer getCol() {
	  return col;
   }
}