package ru.armd.pbk.enums.bsos;

/**
 * Ячейки печатной формы акта об изъятии, содержащие его параметры.
 */
public enum BsoValueCell {

   ACT_NUMBER("G1"),
   FIO("F25"),
   PERSONAL_NUMBER("L25"),
   YEAR("F4");

   private final String pos;

   BsoValueCell(String pos) {
	  this.pos = pos;
   }

   public String getPos() {
	  return pos;
   }
}
