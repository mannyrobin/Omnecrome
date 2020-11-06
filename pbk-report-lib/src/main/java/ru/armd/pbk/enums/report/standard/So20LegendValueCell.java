package ru.armd.pbk.enums.report.standard;

/**
 * Created by Yakov Volkov.
 */
public enum So20LegendValueCell {

   ROUTE_NUMBER(0),
   NUMBER(1),
   SCM(2),
   SCMO(3),
   VESB(4),
   OTHER(5);

   private final Integer col;

   So20LegendValueCell(Integer col) {
	  this.col = col;
   }

   public Integer getCol() {
	  return col;
   }
}
