package ru.armd.pbk.enums.core;

/**
 * Константы для аудита отчётов.
 */
public class ReportAuditType
	extends AuditType {

   public static final AuditType REPORT_REPORT =
	   new AuditType(4_001_00L, "Отчётность");
   public static final AuditType REPORT_STANDARD =
	   new AuditType(4_001_01L, "Стандартные отчёты");
   public static final AuditType REPORT_STANDARD_SO1 =
	   new AuditType(4_001_02L, "График работы контролеров по территориальному подразделению");
   public static final AuditType REPORT_STANDARD_SO2 =
	   new AuditType(4_001_03L, "Табель фактически отработанного времени");
   public static final AuditType REPORT_STANDARD_SO3 =
	   new AuditType(4_001_04L, "Количество бригад по местам встречи");
   public static final AuditType REPORT_STANDARD_SO4 =
	   new AuditType(4_001_05L, "Ежедневная посменная численность контролёров " +
		   "ГУП \"Мосгортранс\" по территориальному подразделению");
   public static final AuditType REPORT_STANDARD_SO5 =
	   new AuditType(4_001_06L, "Посменная численность контролёров ГУП \"Мосгортранс\"" +
		   " и среднее значение численности за период");
   public static final AuditType REPORT_STANDARD_SO6 =
	   new AuditType(4_001_07L, "Результаты контроля (мотивация)");
   public static final AuditType REPORT_STANDARD_SO7 =
	   new AuditType(4_001_08L, "Количество перевезенных пассажиров по маршрутам");
   public static final AuditType REPORT_STANDARD_SO8 =
	   new AuditType(4_001_09L, "Работа контролеров на маршруте");
   public static final AuditType REPORT_STANDARD_SO9 =
	   new AuditType(4_001_10L, "Сводные данные по работе контролеров по подразделениям");
   public static final AuditType REPORT_STANDARD_SO10 =
	   new AuditType(4_001_11L, "Сводная форма по эффективности работы контролеров");
   public static final AuditType REPORT_STANDARD_SO11 =
	   new AuditType(4_001_12L, "Работа контролеров");
   public static final AuditType REPORT_STANDARD_SO12 =
	   new AuditType(4_001_13L, "Совместный график по местам встреч");
   public static final AuditType REPORT_STANDARD_SO13 =
	   new AuditType(4_001_14L, "Маршруты и выходы");
   public static final AuditType REPORT_STANDARD_SO14 =
	   new AuditType(4_001_15L, "Результаты ПБК за период");
   public static final AuditType REPORT_STANDARD_SO15 =
	   new AuditType(4_001_16L, "Итоги ПБК по контролеру по данным АСУ ПБК");
   public static final AuditType REPORT_STANDARD_SO16 =
	   new AuditType(4_001_17L, "Сравнительный анализ данных пассажиропотока " +
		   "(АСКП/АСМ-ПП) поостановочно");
   public static final AuditType REPORT_STANDARD_SO17 =
	   new AuditType(4_001_18L, "Сводный сравнительный анализ данных пассажиропотока (АСКП/АСМ-ПП)");
   public static final AuditType REPORT_STANDARD_SO18 =
	   new AuditType(4_001_19L, "Сопоставительный анализ данных по наряд-заданию и из АСКП");
   public static final AuditType REPORT_STANDARD_SO19 =
	   new AuditType(4_001_20L, "Количество совместных бригад с ГКУ ОП");
   public static final AuditType REPORT_STANDARD_SO20 =
	   new AuditType(4_001_21L, "Статистика проверок маршрута");
   public static final AuditType REPORT_STANDARD_SO21 =
	   new AuditType(4_001_22L, "Проходы по БСК контролера");
   public static final AuditType REPORT_STANDARD_SO22 =
	   new AuditType(4_001_23L, "Сверка с ГКУ ОП");
   public static final AuditType REPORT_STANDARD_SO23 =
	   new AuditType(4_001_24L, "Сводные данные по работе контролеров за период");
	public static final AuditType REPORT_STANDARD_SO24 =
			new AuditType(4_001_25L, "Сводные данные по наряд заданиям");
	public static final AuditType REPORT_STANDARD_SO25 =
			new AuditType(4_001_26L, "Cписок маршрутов АСМ-ПП");
   public static final AuditType UNION_ANALYSIS_BY_ROUTE =
	   new AuditType(4_002_01L, "Сводный сравнительный анализ ПП по маршрутам");
   public static final AuditType UNION_ANALYSIS_BY_STOP =
	   new AuditType(4_002_02L, "Сводный сравнительный анализ ПП по остановкам");
   public static final AuditType ASKP_REPORT_BY_STOP = new
	   AuditType(4_002_03L, "Отчет «Пассажиропоток по остановке»");
   public static final AuditType UNION_ANALYSIS_BY_MOVE_ROUTE =
	   new AuditType(4_002_01L, "Аналитика по маршрутам и выходам");
   public static final AuditType UNION_ANALYSIS_BY_TRIP_MOVE_ROUTE =
	   new AuditType(4_002_02L, "Аналитика по рейсам маршрута-выхода");
   public static final AuditType UNION_ANALYSIS_BY_TRIP_STOP =
	   new AuditType(4_002_01L, "Аналитика по остановкам рейса");
   
   protected ReportAuditType(Long id, String name) {
	  super(id, name);
   }
}
