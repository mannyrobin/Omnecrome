package ru.armd.pbk.enums.report.standard;

/**
 * Ячейки с данными для печатной формы отчёта "Работа контролеров на маршруте".
 */
public enum So8ValueCell {

   TASK_DATE(0),
   TS_CHECK_COUNT(1),
   EXEMPT_SKM_COUNT(2),
   EXEMPT_SKMO_COUNT(3),
   EXEMPT_VESB_COUNT(4),
   EXEMPT_OTHER_LPK_COUNT(5),
   EXEMPT_VALIDLESS_COUNT(6),
   TICKET_SOLD_COUNT(7),
   PROTOCOL_2500_COUNT(8),
   PROTOCOL_1000_COUNT(9),
   ORDINANCE_2500_COUNT(10),
   ORDINANCE_1000_COUNT(11),
   MGT_EMPLOYEE_COUNT(12),
   GKUOP_EMPLOYEE_COUNT(13),
   DELIVERY_OVD_COUNT(14);


   private final Integer col;

   So8ValueCell(Integer col) {
	  this.col = col;
   }

   public Integer getCol() {
	  return col;
   }
}
