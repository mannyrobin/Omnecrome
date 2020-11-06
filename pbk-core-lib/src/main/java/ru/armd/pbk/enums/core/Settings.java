package ru.armd.pbk.enums.core;

/**
 * Настройки системы.
 */
public enum Settings {

   SYS_VERSION(1L),
   AUDIT_LEVEL(2L),

   VIS_EXCHANGE_ALLOW(100_000L),
   VIS_IMPORT_ALLOW(100_001L),
   VIS_EXPORT_ALLOW(100_002L),
   VIS_BSK_COUNT_DAY(100_003L),
   VIS_STOP_INTERVALS_D(100_004L),

   AD_URL(1_001L),
   AD_DOMAIN(1_002L),
   AD_ATTR_NAME(1_003L),
   AD_ATTR_EXPIRATION_DATE(1_004L),
   AD_ATTR_LDAP_CREATE_DATE(1_005L),

   MAX_LOGIN_ATTEMPT_FAILS(2001L),
   MAX_USER_BAN_PERIOD(2002L),
   CHECK_LOGIN_ATTEMPTS_PERIOD(2003L),
   EMPLOYEE_POSITION_NAME_FOR_PLAN_USE(2004L),
   CLEAN_TIME_LOGS(2005L),
   SHOW_DELETE(2006L),
   BSO_MAX_LENGTH(2007L),
   EMPLOYEE_MAX_NOT_FHD_PERIOD(2008L),
   ERROR_MESSAGE(2009L),
   PLAN_PERIOD(2_010L),
   AUTO_CLOSE_TASK_BY(2_011L),
   REPORT_WITH_VESB(2_012L);

   private Long id;

   /**
	* Конструктор.
	*
	* @param id id.
	*/
   private Settings(Long id) {
	  this.id = id;
   }

   public Long getId() {
	  return id;
   }

   /**
	* Получение элемента по id.
	*
	* @param id id.
	* @return
	*/
   public static Settings getEnumById(Long id) {
	  for (Settings value : values()) {
		 if (value.getId().equals(id)) {
			return value;
		 }
	  }
	  return null;
   }
}
