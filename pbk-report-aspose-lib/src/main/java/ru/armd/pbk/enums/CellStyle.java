package ru.armd.pbk.enums;

/**
 * Стили форматирования ячеек таблицы Aspose
 */
public enum CellStyle {

   TEXT(49),
   INT(1),
   DOUBLE_2(2);

   private final int styleNumber;

   CellStyle(int styleNumber) {
	  this.styleNumber = styleNumber;
   }

   public int getStyleNumber() {
	  return styleNumber;
   }
}
