package ru.armd.pbk.enums.core;

import ru.armd.pbk.utils.StringUtils;

/**
 * Перечислимый тип, описывающий перечень уровней сообщений аудита.
 */
public enum AuditLevel {

   TRACE(1L, "Трассировка", "TRACE"),
   DEBUG(2L, "Отладка", "DEBUG"),
   INFO(3L, "Информация", "INFO"),
   WARNING(4L, "Предупреждение", "WARNING"),
   ERROR(5L, "Ошибка", "ERROR");

   private Long id;
   private String name;
   private String settingValue;

   /**
	* Конструктор.
	*
	* @param id   id.
	* @param name name.
	*/
   private AuditLevel(Long id, String name, String settingValue) {
	  this.id = id;
	  this.name = name;
	  this.settingValue = settingValue;
   }

   public Long getId() {
	  return id;
   }

   public String getName() {
	  return name;
   }

   public String getSettingValue() {
	  return settingValue;
   }

   /**
	* Возвращает возможность логирования до уровня переданного в параметрах.
	*
	* @param settingValue Уровень.
	* @return true - если воможно логировать.
	*/
   public boolean isEnable(String settingValue) {
	  AuditLevel settingLevel = null;
	  for (AuditLevel value : values()) {
		 if (!StringUtils.isBlank(settingValue) && settingValue.equalsIgnoreCase(value.getSettingValue())) {
			settingLevel = value;
			break;
		 }
	  }
	  if (settingLevel != null) {
		 return settingLevel.getId() <= this.getId();
	  }
	  return false;
   }

   /**
	* Получение элемента по id.
	*
	* @param id id.
	* @return
	*/
   public static AuditLevel getEnumById(Long id) {
	  for (AuditLevel value : values()) {
		 if (value.getId() == id) {
			return value;
		 }
	  }
	  return INFO;
   }
}
