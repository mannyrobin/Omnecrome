package ru.armd.pbk.enums.nsi;

/**
 * Справочник режимов работы.
 * - ВВДН;
 * - ВДНВ;
 * - ДНВВ;
 * - НВВД;
 * - В-ПтСб I;
 * - В-ПтСб II;
 * - В-СбВс I;
 * - В-СбВс II;
 * - В-ВсПн II;
 * - В-ВсПн II;
 * - Отпуск;
 * - Больничный.
 */
public enum WorkMode {

   HHDN(1L, "ВВДН"),
   HDNH(2L, "ВДНВ"),
   DNHH(3L, "ДНВВ"),
   NHHD(4L, "НВВД"),
   I_H_FriSat(5L, "В-ПтСб Первая"),
   II_H_FriSat(6L, "В-ПтСб Вторая"),
   I_H_SatSun_(7L, "В-СбВс Первая"),
   II_H_SatSun(8L, "В-СбВс Вторая"),
   I_H_SunMon(9L, "В-ВсПн Первая"),
   II_H_SunMon(10L, "В-ВсПн Вторая"),
   VACATION(11L, "Отпуск"),
   SICK(12L, "Больничный");

   private Long id;
   private String name;

   /**
	* Конструктор.
	*
	* @param id   id.
	* @param name name.
	*/
   private WorkMode(Long id, String name) {
	  this.id = id;
	  this.name = name;
   }

   public Long getId() {
	  return id;
   }

   public String getName() {
	  return name;
   }

   /**
	* Получение элемента по id.
	*
	* @param id id.
	* @return
	*/
   public static WorkMode getEnumById(Long id) {
	  for (WorkMode value : values()) {
		 if (value.getId() == id) {
			return value;
		 }
	  }
	  return null;
   }

}
