package ru.armd.pbk.enums.report.standard;

/**
 * Created by Yakov Volkov.
 */
public enum So20EndCheckValueCell {


   NUMBER(0),

   CHECK_DATETIME(1),
   SHIFT(2),
   FIO(3),
   MOC(4),
   SCM(5),
   SCMO(6),
   VESB(7),
   OTHER(8),

   ROUTE_NUMBER(9),
   MOVE_CODE(10);

   private final Integer col;

   So20EndCheckValueCell(Integer col) {
	  this.col = col;
   }

   public Integer getCol() {
	  return col;
   }
}
