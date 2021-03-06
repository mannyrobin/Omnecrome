package ru.armd.pbk.enums.viss;

/**
 * Типы запросов к ВИС.
 */
public enum VisExchangeObjects {

   ASDU_GTFS_DRIVER(40_001L, "Запрос на обновление Водителей"),
   ASDU_GTFS_VENICLE(40_002L, "Запрос на обновление ТС"),
   ASDU_GTFS_EQUIPMENT(40_003L, "Запрос на обновление бортового оборудования ТС"),
   ASDU_GTFS_STOP(40_004L, "Запрос на обновление остановочных пунктов"),
   ASDU_GTFS_TELEMATIC(40_005L, "Запрос на обновление телематики"),
   ASDU_GTFS_TRIP(40_006L, "Запрос на обновление рейсов"),
   ASDU_GTFS_PLANTRIP(40_007L, "Запрос на обновление плановых рейсов"),
   GIS_MGT_OBJECTS(60_000L, "Запрос на обновление ГИС МГТ"),
   EASU_FHD_DEPARTMENT(70_000L, "Запрос на обновление ЕАСУ ФХД подразделений"),
   EASU_FHD_TRIP_ORDER(70_001L, "Запрос на обновление нарядов"),
   EASU_FHD_TRIP_SCHEDULE(70_002L, "Запрос на обновление расписания"),
   EASU_FHD_EMPLOYEE(70_003L, "Запрос на обновление ЕАСУ ФХД сотрудников"),
   EASU_FHD_WORK_MODE(70_004L, "Запрос на обновление ЕАСУ ФХД баланса рабочего времени сотрудников"),
   EASU_FHD_VENICLE(70_005L, "Запрос на обновление ЕАСУ ФХД транспортных средств"),
   EASU_FHD_SKK(70_006L, "Запрос на обновление ЕАСУ ФХД СКК"),
   EASU_FHD_BSK(70_007L, "Запрос на обновление ЕАСУ ФХД БСК"),
   DVR_RECORD(80_000L, "Запрос на обновление записей с видеорегистраторов"),
   DVR_DVR(80_001L, "Запрос на обновление вдеорегистраторов"),
   DVR_RECORD_MOK1(80_002L, "Запрос на обновление записей с видеорегистраторов для МОК 1"),
   DVR_RECORD_MOK2(80_003L, "Запрос на обновление записей с видеорегистраторов для МОК 2"),
   DVR_RECORD_MOK3(80_004L, "Запрос на обновление записей с видеорегистраторов для МОК 3"),
   DVR_RECORD_MOK4(80_005L, "Запрос на обновление записей с видеорегистраторов для МОК 4"),
   DVR_RECORD_MOK5(80_006L, "Запрос на обновление записей с видеорегистраторов для МОК 5"),
   GKUOP_EMPLOYEE(90_000L, "Запрос на обновление ГКУ ОП сотрудников"),
   ASKP_TICKET(100_000L, "Запрос на обновление АСКП Билеты"),
   ASKP_TICKET_CHECK(100_001L, "Запрос на обновление АСКП Результата билетного контроля"),
   ASKP_PUSK_CHECK(100_002L, "Запрос на обновление АСКП Данных о проходах пассажиров"),
   ASKP_CONTACTLESS_CARD(100_003L, "Запрос на обновление АСКП Данных о проходах по БСК контролера"),
   ASMPP_PASSENGERS(110_001L, "Запрос на обновление данных о проходах пассажиров АСМПП"),

   GTFS_CHANGE_EQUIPMENT(120_001L, "Запрос на обновление данных о перестановке бортового оборудования"),
   GTFS_CHANGE_STOP_TIMES(120_002L, "Запрос на обновление оперативных изменений поостановочных расписаний"),
   GTFS_EQUIPMENT(120_003L, "Запрос на обновление справочника бортового оборудования транспортных средств"),
   GTFS_IMPACT(120_004L, "Запрос на обновление данных об управляющих воздействий"),
   GTFS_IMPACT_CODE(120_005L, "Запрос на обновление данных о кодах управляющих воздействий"),
   GTFS_REPLACE_VEHICLE(120_006L, "Запрос на обновление данных о замене ТС на маршруте"),
   GTFS_STOPS(120_007L, "Запрос на обновление спарочника остановочных пунктов"),
   GTFS_STOP_TIMES(120_008L, "Запрос на обновление справочника поостановочных расписаний"),
   GTFS_TRIPS(120_009L, "Запрос на обновление справочника рейсов"),
   GTFS_TRIPS_STOPS(120_010L, "Запрос на обновление справочника рейсов с последовательностями остановок из ЭРМ"),
   GTFS_VEHICLE_TASK(120_011L, "Запрос на обновление справочника назначений ТС на маршрут"),
   GTFS_DEPOTS(120_013L, "Запрос на обновление справочника автотранспортных предприятий"),
   GTFS_VEHICLES(120_014L, "Запрос на обновление справочника транспортных средств"),
   GTFS_CALENDAR(120_015L, "Запрос на обновление справочника периодов действий маршрутов"),
   GTFS_ROUTE(120_016L, "Запрос на обновление справочника маршрутов"),
   GTFS_FILESYSTEM(120_017L, "Запрос ГТФС из файловой системы"),

   ASMPP_STOPS(130_001L, "Запрос на обновление остановок"),
   PUSK(140_001L,"Отправка данных о сотрудниках, НЗ и БСО в ПУСК");

   private Long id;
   private String name;

   /**
	* Конструктор.
	*
	* @param id   id.
	* @param name name.
	*/
   private VisExchangeObjects(Long id, String name) {
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
   public static VisExchangeObjects getEnumById(Long id) {
	  for (VisExchangeObjects value : values()) {
		 if (value.getId() == id) {
			return value;
		 }
	  }
	  return null;
   }
}
