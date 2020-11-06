package ru.armd.pbk.enums.report.standard;

/**
 * Ячейки с данными для печатной формы отчёта "Результаты ПБК за период".
 */
public enum So14ValueCell {

   TO_SDIK(0),
   CUR_FACT_EMPLOYEE_TOTAL(1),
   CUR_EXEMPT_SKM_SKMO_VESB(2),
   CUR_EXEMPT_LPK_COUNT(3),
   CUR_EXEMPT_VALIDLESS_COUNT(4),
   CUR_PLANT_STOWAWAY_COUNT(5),
   CUR_DELIVERY_OVD_COUNT(6),
   CUR_TICKET_SOLD_COUNT(7),
   CUR_EMPLOYEE_TASK_COUNT(8),
   PREV_FACT_EMPLOYEE_TOTAL(9),
   PREV_EXEMPT_SKM_SKMO_VESB(10),
   PREV_EXEMPT_LPK_COUNT(11),
   PREV_EXEMPT_VALIDLESS_COUNT(12),
   PREV_PLANT_STOWAWAY_COUNT(13),
   PREV_DELIVERY_OVD_COUNT(14),
   PREV_TICKET_SOLD_COUNT(15),
   PREV_EMPLOYEE_TASK_COUNT(16);

   private final Integer col;

   So14ValueCell(Integer col) {
	  this.col = col;
   }

   public Integer getCol() {
	  return col;
   }
}
