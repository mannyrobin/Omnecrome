package ru.armd.pbk.enums.core;

/**
 * Аудит модуля nsi.
 */
public class NsiAuditType
	extends AuditType {

   public static final AuditType NSI_DEPARTMENT = new AuditType(1_001_01L, "Подразделения");
   public static final AuditType NSI_DEPARTMENT_PARK = new AuditType(1_001_02L, "Парки подразделений");

   public static final AuditType NSI_EMPLOYEE = new AuditType(1_001_03L, "Сотрудники");
   public static final AuditType NSI_EMPLOYEE_WORK_MODE = new AuditType(1_001_04L, "Режим работы сотрудника");
   //public static final AuditType NSI_EMPLOYEE_DEPARTMENT 	= new AuditType(1_001_05L, "");
   //public static final AuditType NSI_EMPLOYEE_USER			= new AuditType(1_001_06L, "");
   //public static final AuditType NSI_EMPLOYEE_PUSK			= new AuditType(1_001_07L, "");
   //public static final AuditType NSI_EMPLOYEE_CONT_CARD	= new AuditType(1_001_08L, "");
   //public static final AuditType NSI_EMPLOYEE_OFF_CARD		= new AuditType(1_001_09L, "");
   public static final AuditType NSI_EMPLOYEE_CALENDAR = new AuditType(1_001_10L, "Рабочий календарь сотрудника");
   public static final AuditType NSI_EMPLOYEE_DEPT_MOVE = new AuditType(1_001_11L, "Период работы сотрудника в департаменте");
   public static final AuditType NSI_EMPLOYEE_ABSENCE = new AuditType(1_001_12L, "Отсутсвия сотрудников");
   public static final AuditType NSI_EMPLOYEE_ABSENCE_TYPE = new AuditType(1_001_13L, "Типы отсутствий");

   public static final AuditType NSI_SHIFT = new AuditType(1_001_14L, "Смены");

   public static final AuditType NSI_CALENDAR = new AuditType(1_001_15L, "Календари");

   public static final AuditType NSI_TS = new AuditType(1_001_16L, "Транспортные средства");
   public static final AuditType NSI_TS_TYPE = new AuditType(1_001_17L, "Типы тс");
   public static final AuditType NSI_TS_CAPACITY = new AuditType(1_001_18L, "Вместимость тс");
   public static final AuditType NSI_TS_MODEL = new AuditType(1_001_19L, "Модели тс");

   public static final AuditType NSI_PARK = new AuditType(1_001_20L, "Эксплуатационные филиалы");

   public static final AuditType NSI_ROUTE = new AuditType(1_001_21L, "Маршруты");
   public static final AuditType NSI_ROUTE_KIND = new AuditType(1_001_22L, "Типы тс маршрутов");

   public static final AuditType NSI_TICKET = new AuditType(1_001_23L, "Билеты");
   public static final AuditType NSI_TICKET_TYPE = new AuditType(1_001_24L, "Типы билетов");

   public static final AuditType NSI_CONTACTLESS_CARD = new AuditType(1_001_25L, "БСК");
   public static final AuditType NSI_OFFICILA_CARD = new AuditType(1_001_26L, "СКК");
   public static final AuditType NSI_PUSK = new AuditType(1_001_27L, "ПУСКи");
   public static final AuditType NSI_DVR = new AuditType(1_001_28L, "Видео регистраторы");
   public static final AuditType NSI_SEX = new AuditType(1_001_29L, "Пол сотрудника");

   public static final AuditType NSI_EQUIPMENT = new AuditType(1_001_30L, "Бортовое оборудование");

   public static final AuditType NSI_DRIVER = new AuditType(1_001_31L, "Водители");

   public static final AuditType NSI_DISTRICT = new AuditType(1_001_32L, "Районы");
   //public static final AuditType NSI_DISTRICT_VENUE		= new AuditType(1_001_33L, "");
   public static final AuditType NSI_DISTRICT_ROUTE = new AuditType(1_001_34L, "Маршруты района");

   public static final AuditType NSI_COUNTY = new AuditType(1_001_35L, "Районы");

   public static final AuditType NSI_VENUE = new AuditType(1_001_36L, "Места встреч");
   public static final AuditType NSI_VENUE_ROUTE = new AuditType(1_001_37L, "Маршруты сопутствующие местам встреч");
   public static final AuditType NSI_VENUE_ROUTE_TYPE = new AuditType(1_001_38L, "Тип сопутствующего маршрута места встречи");

   public static final AuditType NSI_BSO = new AuditType(1_001_39L, "БСО");
   public static final AuditType NSI_BSO_NUMBER_RULE = new AuditType(1_001_40L, "Номера БСО");
   public static final AuditType NSI_BSO_TYPE = new AuditType(1_001_41L, "Типы БСО");

   public static final AuditType NSI_GKUOP = new AuditType(1_001_42L, "ГКУ ОП");

   public static final AuditType NSI_STOP = new AuditType(1_001_43L, "Остановки");
   public static final AuditType NSI_STOP_PLACE = new AuditType(1_001_43L, "Остановочные места");

   public static final AuditType NSI_BRANCH = new AuditType(1_001_44L, "Филиалы");

   public static final AuditType NSI_DUTY = new AuditType(1_001_45L, "Наряды");
   public static final AuditType NSI_ASDU_TELEMATICS = new AuditType(1_001_00L, "Анализ пассажиропотока");

   public static final AuditType NSI_ASKP_PUSK_CHECK = new AuditType(1_001_46L, "Данные от АФСКП по устройствам");

   public static final AuditType NSI_WORK_MODE = new AuditType(1_001_00L, "Режим работы");

   public static final AuditType NSI_DVR_RECORD = new AuditType(1_001_47L, "Запись с видеорегистратора");
   public static final AuditType NSI_DVR_STORE_PERIOD = new AuditType(1_001_48L, "Время хранения записи с видеорегистратора");
   public static final AuditType NSI_TS_WORK_DAY = new AuditType(1_001_49L, "День использования ТС");

   public static final AuditType NSI_ASKP_TICKET_CHECK = new AuditType(1_001_50L, "Данные от АФСКП по билетам");

   public static final AuditType NSI_PARK_DRIVER = new AuditType(1_001_51L, "Водители парка");

   public static final AuditType NSI_OPERATION_TYPES = new AuditType(1_001_52L, "Виды операций, выполняемые контролерами в рамках проведения билетного контроля");
   public static final AuditType NSI_BONUS = new AuditType(1_001_53L, "Премирование");
   public static final AuditType NSI_TICKET_BONUS = new AuditType(1_001_54L, "Премирование билета");

   public static final AuditType NSI_ASKP_CONTACTLESS_CARDS = new AuditType(1_001_55L, "Проходы по БСК контролера");

   public static final AuditType NSI_EMPLOYEE_LAST_UPDATE = new AuditType(1_001_56L, "Последнее обновление сотрудника");

   public static final AuditType NSI_CONTACTLESS_CARD_HISTORY = new AuditType(1_001_57L, "История БСК");
   public static final AuditType NSI_OFFICIAL_CARD_HISTORY = new AuditType(1_001_58L, "История СКК");
   public static final AuditType NSI_PUSK_HISTORY = new AuditType(1_001_59L, "История ПУсК");
   public static final AuditType NSI_DVR_HISTORY = new AuditType(1_001_60L, "История видео регистратора");

   public static final AuditType NSI_ANALYSIS_BY_ROUTES = new AuditType(1_001_61L, "Сравнительный анализ по маршрутам");
   public static final AuditType NSI_CHECKS_BY_HOURS = new AuditType(1_001_62L, "АСКП по часам");
   public static final AuditType NSI_CHECKS_BY_MOVES = new AuditType(1_001_63L, "АСКП по выходам");
   public static final AuditType NSI_CHECK_BY_STOPS = new AuditType(1_001_64L, "АСКП поостановочно");
   public static final AuditType NSI_STOP_INTERVALS_STAT = new AuditType(1_001_65L, "Статистические данные интервалы");

   public static final AuditType NSI_SHIFT_DEPARTMENT = new AuditType(1_001_66L, "Смены подразделения");
   
   public static final AuditType NSI_GRAFIC = new AuditType(Long.valueOf(100167L), "График");

   protected NsiAuditType(Long id, String name) {
	  super(id, name);
   }

}
