package ru.armd.pbk.enums.report.standard;

/**
 * Ячейки с данными для печатной формы отчёта "Результаты контроля (мотивация)".
 */
public enum So6ValueCell {

   ID(0),
   TO_SDIK(1),
   SURNAME(2),
   NAME(3),
   PATRONUMIC(4),
   PERSONAL_NUMBER(5),
   FOR_PLAN_USE(6),
   SCHEDULE_NUMBER(7),
   PLAN_SHIFT_COUNT(8),
   FACT_SHIFT_COUNT(9),
   TOTAL_FACT_COUNT(10),
   TOTAL_PLAN_COUNT(11),
   FACT_SKM_COUNT(12),
   FACT_SKMO_COUNT(13),
   FACT_VESB_COUNT(14),
   FACT_VALIDLESS_COUNT(15),
   FACT_LPK_COUNT(16),
   FACT_SOLD_COUNT(17),
   FACT_STOWAWAY_COUNT(18),
   FACT_DELIVERY_COUNT(19),
   FACT_1000_COUNT(20),
   FACT_2500_COUNT(21),
   EXCESS_SKM_COUNT(22),
   UNDER_SKM(23),
   UNDER_SKM_VALUE(24),
   PLAN_SUBTR(25),
   PCNT(26);

   private final Integer col;

   So6ValueCell(Integer col) {
	  this.col = col;
   }

   public Integer getCol() {
	  return col;
   }
}
