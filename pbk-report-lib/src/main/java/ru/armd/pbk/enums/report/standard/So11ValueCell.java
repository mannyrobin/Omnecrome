package ru.armd.pbk.enums.report.standard;

/**
 * Ячейки с данными для печатной формы отчёта "Работа контролеров".
 */
public enum So11ValueCell {

   TO_SDIK(0),
   PLAN_EMPLOYEE_COUNT(1),
   FACT_EMPLOYEE_COUNT(2),
   EXEMPT_SKM_COUNT(3),
   EXEMPT_SKMO_COUNT(4),
   EXEMPT_VESB_COUNT(5),
   EXEMPT_LPK_COUNT(6),
   TICKET_SOLD_COUNT(7),
   TS_CHECK_COUNT(8),
   EXEMPT_VALIDLESS_COUNT(9),
   PLANT_STOWAWAY_COUNT(10),
   DELIVERY_OVD_COUNT(11),
   TOTAL_SKM_SKMO_VESB(12);

   private final Integer col;

   So11ValueCell(Integer col) {
	  this.col = col;
   }

   public Integer getCol() {
	  return col;
   }
}
