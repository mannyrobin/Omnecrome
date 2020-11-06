package ru.armd.pbk.enums.core;

/**
 * Аудит модуля vis.
 */
public class VisAuditType
	extends AuditType {

   public static final AuditType VIS_VIS = new AuditType(2_000_01L, "ВИС");
   public static final AuditType VIS_EXCHANGE = new AuditType(2_000_02L, "Журнал записей обмена");
   public static final AuditType VIS_EXCHANGE_ATTEMPT = new AuditType(2_000_03L, "Журнал записей попыток обмена");
   public static final AuditType VIS_EXCHANGE_RESULT = new AuditType(2_000_04L, "Журнал записей результатов обмена");
   public static final AuditType VIS_EXCHANGE_CONFIG = new AuditType(2_000_05L, "Конфиги обмена");
   public static final AuditType VIS_EXCHANGE_TYPE = new AuditType(2_000_06L, "Типы взаимодейсвия с ВИС");
   public static final AuditType VIS_EXCHANGE_STATE = new AuditType(2_000_07L, "Статусы взаимодействия с ВИС");
   public static final AuditType VIS_REQUEST_STATE = new AuditType(2_000_08L, "Статусы запросов ВИС");
   public static final AuditType VIS_RESPONSE_STATE = new AuditType(2_000_09L, "Статусы ответов ВИС");
   public static final AuditType VIS_TRANSPORT_TYPE = new AuditType(2_000_10L, "Виды транспорта ВИС");
   public static final AuditType VIS_OPERATION_TYPE = new AuditType(2_000_11L, "Типы операций с ВИС");
   public static final AuditType VIS_FILE = new AuditType(2_000_12L, "Файлы ВИС");

   public static final AuditType VIS_EASUFHD = new AuditType(2_002_01L, "ЕАСУ ФХД");
   public static final AuditType VIS_EASUFHD_TRIP_ORDER = new AuditType(2_002_02L, "ВИС ЕАСУ ФХД Наряд");
   public static final AuditType VIS_EASUFHD_TRIP_SCHEDULE = new AuditType(2_002_03L, "ВИС ЕАСУ ФХД Расписание");
   public static final AuditType VIS_EASUFHD_DEPARTMENT = new AuditType(2_002_04L, "ВИС ЕАСУ ФХД Подразделение");
   public static final AuditType VIS_EASUFHD_EMPLOYEE = new AuditType(2_002_05L, "ВИС ЕАСУ ФХД Сотрудник");
   public static final AuditType VIS_EASUFHD_WORK_MODE = new AuditType(2_002_06L, "ВИС ЕАСУ ФХД Баланс рабочего времени сотрудника");
   public static final AuditType VIS_EASUFHD_VENICLE = new AuditType(2_002_07L, "ВИС ЕАСУ ФХД Транспортные средства");
   public static final AuditType VIS_EASUFHD_SKK = new AuditType(2_002_08L, "ВИС ЕАСУ ФХД СКК");
   public static final AuditType VIS_EASUFHD_BSK = new AuditType(2_002_09L, "ВИС ЕАСУ ФХД БСК");

   public static final AuditType VIS_ASDU = new AuditType(2_004_01L, "ВИС АСДУ");
   public static final AuditType VIS_ASDU_DRIVER = new AuditType(2_004_02L, "ВИС АСДУ-НГПТ");
   public static final AuditType VIS_ASDU_VENICLE = new AuditType(2_004_03L, "ВИС АСДУ-НГПТ импорт списка ТС");
   public static final AuditType VIS_ASDU_EQUIPMENT = new AuditType(2_004_04L, "ВИС АСДУ-НГПТ бортовое оборудование ТС");
   public static final AuditType VIS_ASDU_STOP = new AuditType(2_004_05L, "ВИС АСДУ-НГПТ остановочные пункты");
   public static final AuditType VIS_ASDU_TELEMATIC = new AuditType(2_004_06L, "ВИС АСДУ-НГПТ телематика");
   public static final AuditType VIS_ASDU_TRIP = new AuditType(2_004_07L, "ВИС АСДУ-НГПТ рейсы");
   public static final AuditType VIS_ASDU_PLANTRIP = new AuditType(2_004_08L, "ВИС АСДУ-НГПТ плановые рейсы");
   public static final AuditType VIS_ASDU_STOPINTERVALS = new AuditType(2_004_09L, "ВИС АСДУ-НГПТ межостановочные интервалы");

   public static final AuditType VIS_GKUOP = new AuditType(2_005_01L, "ВИС ГКУ ОП");
   public static final AuditType VIS_GKUOP_EMPLOYEE = new AuditType(2_005_02L, "ВИС ГКУ ОП Сотрудники");

   public static final AuditType VIS_GISMGT_UPDATE = new AuditType(2_006_01L, "ВИС ГИС МГТ импорт");
   public static final AuditType VIS_GISMGT_ROUTE = new AuditType(2_006_02L, "ВИС ГИС МГТ Маршруты");
   public static final AuditType VIS_GISMGT_ROUTE_TS_KIND = new AuditType(2_006_03L, "ВИС ГИС МГТ ТС Тип Маршрута");
   public static final AuditType VIS_GISMGT_TS_KIND = new AuditType(2_006_04L, "ВИС ГИС МГТ ТС Типы");
   public static final AuditType VIS_GISMGT_REGION = new AuditType(2_006_05L, "ВИС ГИС МГТ Районы");
   public static final AuditType VIS_GISMGT_DISTRICT = new AuditType(2_006_06L, "ВИС ГИС МГТ Округа");
   public static final AuditType VIS_GISMGT_STOP = new AuditType(2_006_07L, "ВИС ГИС МГТ Остановочный пункты");
   public static final AuditType VIS_GISMGT_PARK = new AuditType(2_006_08L, "ВИС ГИС МГТ Парки");
   public static final AuditType VIS_GISMGT_AGENCY = new AuditType(2_006_09L, "ВИС ГИС МГТ Перевозчик");
   public static final AuditType VIS_GISMGT_DIRECTION = new AuditType(2_006_10L, "ВИС ГИС МГТ Направление движения");
   public static final AuditType VIS_GISMGT_RNRT = new AuditType(2_006_11L, "ВИС ГИС МГТ Типы нулевых рейсов");
   public static final AuditType VIS_GISMGT_P2A = new AuditType(2_006_12L, "ВИС ГИС МГТ Связь Парки - Перевозчики");
   public static final AuditType VIS_GISMGT_P2R = new AuditType(2_006_13L, "ВИС ГИС МГТ Связь Парки - Маршрут");
   public static final AuditType VIS_GISMGT_ROUTE_KIND = new AuditType(2_006_14L, "ВИС ГИС МГТ Вид маршрута");
   public static final AuditType VIS_GISMGT_RNR = new AuditType(2_006_15L, "ВИС ГИС МГТ Нулевой рейс");
   public static final AuditType VIS_GISMGT_ROUTE_STATE = new AuditType(2_006_16L, "ВИС ГИС МГТ Статус маршрута ЭРМ");
   public static final AuditType VIS_GISMGT_RT2SP = new AuditType(2_006_17L, "ВИС ГИС МГТ Связь Траектории маршуртов-Места остановки");
   public static final AuditType VIS_GISMGT_ROUTE_TRAJ = new AuditType(2_006_18L, "ВИС ГИС МГТ Траектория маршрута");
   public static final AuditType VIS_GISMGT_ROUTE_TRAJ_TYPE = new AuditType(2_006_19L, "ВИС ГИС МГТ Тип траектории маршрута");
   public static final AuditType VIS_GISMGT_ROUTE_TRNS_KIND = new AuditType(2_006_20L, "ВИС ГИС МГТ Вид сообщения");
   public static final AuditType VIS_GISMGT_ROUTE_VAR = new AuditType(2_006_21L, "ВИС ГИС МГТ Вариант маршрута");
   public static final AuditType VIS_GISMGT_ROUTE_VAR_TYPE = new AuditType(2_006_22L, "ВИС ГИС МГТ Состояние варианта маршрута");
   public static final AuditType VIS_GISMGT_STOP_PLACE = new AuditType(2_006_23L, "ВИС ГИС МГТ Место остановки транспорта");
   public static final AuditType VIS_GISMGT_STOP_STATE = new AuditType(2_006_24L, "ВИС ГИС МГТ Состояние остановочного пункта");
   public static final AuditType VIS_GISMGT_TER_STATION = new AuditType(2_006_25L, "ВИС ГИС МГТ Конечная станция");
   public static final AuditType VIS_GISMGT_VEHICLE_TYPE = new AuditType(2_006_26L, "ВИС ГИС МГТ Тип подвижного состава");
   public static final AuditType VIS_GISMGT_RR = new AuditType(2_006_27L, "ВИС ГИС МГТ Производственный рейс");
   public static final AuditType VIS_GISMGT_RR_TYPE = new AuditType(2_006_28L, "ВИС ИС МГТ Тип производственный рейса");
   public static final AuditType VIS_GISMGT_STOP_MODE = new AuditType(2_006_29L, "ВИС ГИС МГТ Режим работы остановки для маршрута");
   public static final AuditType VIS_GISMGT_REGION_EGKO = new AuditType(2_006_29L, "ВИС ГИС МГТ Территория района");
   public static final AuditType VIS_GISMGT_DISTRICT_EGKO = new AuditType(2_006_29L, "ВИС ГИС МГТ Территория округа");

   public static final AuditType VIS_DVR = new AuditType(2_007_01L, "ВИС ДОЗОР ПРО");
   public static final AuditType VIS_DVR_RECORD = new AuditType(2_007_02L, "ВИС ДОЗОР ПРО Записи с видеорегистраторов");
   public static final AuditType VIS_DVR_DVR = new AuditType(2_007_03L, "ВИС ДОЗОР ПРО Видеорегистраторы");

   public static final AuditType VIS_ASKP = new AuditType(2_008_01L, "ВИС АСКП");
   public static final AuditType VIS_ASKP_TICKET = new AuditType(2_008_02L, "ВИС АСКП Билеты");
   public static final AuditType VIS_ASKP_PUSK_CHECKS = new AuditType(2_008_03L, "ВИС АСКП Результат билетного контроля");
   public static final AuditType VIS_ASKP_TICKET_CHECKS = new AuditType(2_008_04L, "ВИС АСКП Данные о проходах пассажиров");
   public static final AuditType VIS_ASKP_CONTACTLESS_CARDS = new AuditType(2_008_05L, "ВИС АСКП Проходы по БСК контролера");

   public static final AuditType VIS_GTFS = new AuditType(2_009_00L, "ВИС ГТФС");
   public static final AuditType VIS_GTFS_CHANGE_EQUIPMENT = new AuditType(2_009_01L, "ВИС ГТФС Перестановка бортового оборудования");
   public static final AuditType VIS_GTFS_CHANGE_STOP_TIMES = new AuditType(2_009_02L, "ВИС ГТФС Изменений поостановочных расписаний");
   public static final AuditType VIS_GTFS_EQUIPMENT = new AuditType(2_009_03L, "ВИС ГТФС Справочника бортового оборудования транспортных средств");
   public static final AuditType VIS_GTFS_IMPACT = new AuditType(2_009_04L, "ВИС ГТФС Управляющие воздействия");
   public static final AuditType VIS_GTFS_IMPACT_CODE = new AuditType(2_009_05L, "ВИС ГТФС Коды управляющих воздействий");
   public static final AuditType VIS_GTFS_REPLACE_VEHICLE = new AuditType(2_009_06L, "ВИС ГТФС Замена ТС на маршруте");
   public static final AuditType VIS_GTFS_STOPS = new AuditType(2_009_07L, "ВИС ГТФС Справочник остановочных пунктов");
   public static final AuditType VIS_GTFS_STOP_TIMES = new AuditType(2_009_08L, "ВИС ГТФС Справочник поостановочных расписаний");
   public static final AuditType VIS_GTFS_TRIPS = new AuditType(2_009_09L, "ВИС ГТФС Справочник рейсов");
   public static final AuditType VIS_GTFS_TRIPS_STOPS = new AuditType(2_009_10L, "ВИС ГТФС Справочник рейсов с последовательностями остановок из ЭРМ");
   public static final AuditType VIS_GTFS_VEHICLE_TASK = new AuditType(2_009_11L, "ВИС ГТФС Справочник назначений ТС на маршрут");
   public static final AuditType VIS_GTFS_STS = new AuditType(2_009_12L, "ВИС ГТФС Справочник поостановочных расписаний v2");
   public static final AuditType VIS_GTFS_DEPOTS = new AuditType(2_009_13L, "ВИС ГТФС Справочник автотранспортных предприятий");
   public static final AuditType VIS_GTFS_VEHICLES = new AuditType(2_009_14L, "ВИС ГТФС Справочник транспортных средств");
   public static final AuditType VIS_GTFS_CALENDAR = new AuditType(2_009_15L, "ВИС ГТФС Справочник периодов действий маршрутов");
   public static final AuditType VIS_GTFS_ROUTE = new AuditType(2_009_16L, "ВИС ГТФС Маршрутов");

   public static final AuditType VIS_ASMPP = new AuditType(2_010_00L, "ВИС АСМПП");
   public static final AuditType VIS_ASMPP_STOPS = new AuditType(2_010_01L, "ВИС АСМПП Остановки");


   protected VisAuditType(Long id, String name) {
	  super(id, name);
   }
}
