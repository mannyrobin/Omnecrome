package ru.armd.pbk.enums.report.standard;

/**
 * Ячейки с данными для печатной формы отчёта "Сводные данные по работе контролеров по подразделениям".
 */
public enum So9ValueCell {

   EMPLOYEE(0),
   PERSONAL_NUMBER(1),
   EXEMPT_SKM_COUNT(2),
   EXEMPT_SKMO_COUNT(3),
   EXEMPT_VESB_COUNT(4),
   LPK_COUNT(5),
   //PB_COUNT(6),
   TS_CHECK_COUNT(6),
   EXEMPT_VALIDLESS_COUNT(7),
   PLANT_STOWAWAY_COUNT(8),
   DELIVERY_OVD_COUNT(9),
   ORDINANCE_1000_COUNT(10),
   ORDINANCE_2500_COUNT(11),
   PROTOCOL_1000_COUNT(12),
   PROTOCOL_2500_COUNT(13),
   TICKET_SOLD_COUNT(14);

   private final Integer col;

   So9ValueCell(Integer col) {
	  this.col = col;
   }

   public Integer getCol() {
	  return col;
   }
}
