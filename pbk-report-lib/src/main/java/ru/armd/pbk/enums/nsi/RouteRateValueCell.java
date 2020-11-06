package ru.armd.pbk.enums.nsi;

/**
 * Created by Yakov Volkov.
 */
public enum RouteRateValueCell {

   ROUTE_NUMBER(0),
   TRANSPORT_TYPE(1);

   private final Integer col;

   RouteRateValueCell(Integer col) {
	  this.col = col;
   }

   public Integer getCol() {
	  return col;
   }
}
