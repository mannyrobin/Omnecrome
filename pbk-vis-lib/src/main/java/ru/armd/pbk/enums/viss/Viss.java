package ru.armd.pbk.enums.viss;

/**
 * Справочник Внешних Информационных Систем (ВИС).
 */
public enum Viss {

   PBK(1L, "ПБК"),
   EASU_FHD(2L, "ЕАСУ_ФХД"),
   ASKP(3L, "АСКП"),
   ASDU(4L, "АСДУ"),
   GKUOP(5L, "ГКУ ОП"),
   GIS_MGT(6L, "ГИС МГТ"),
   DVR(7L, "ДОЗОР ПРО"),
   ASMPP(8L, "АСМПП"),
   GTFS(9L, "ГТФС");

   private Long id;
   private String name;

   /**
	* Конструктор.
	*
	* @param id   id.
	* @param name name.
	*/
   private Viss(Long id, String name) {
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
   public static Viss getEnumById(Long id) {
	  for (Viss value : values()) {
		 if (value.getId() == id) {
			return value;
		 }
	  }
	  return null;
   }
}
