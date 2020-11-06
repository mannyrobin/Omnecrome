package ru.armd.pbk.components.viss;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.armd.pbk.components.jms.JmsMessageListener;
import ru.armd.pbk.components.viss.asdu.AsduExchangeProcessor;
import ru.armd.pbk.components.viss.askp.AskpExchangeProcessor;
import ru.armd.pbk.components.viss.asmpp.AsmppExchangeProcessor;
import ru.armd.pbk.components.viss.dvr.DvrExchangeProcessor;
import ru.armd.pbk.components.viss.easu.EasuFhdProcessor;
import ru.armd.pbk.components.viss.gismgt.GisMgtProcessor;
import ru.armd.pbk.components.viss.gkuop.GkuopExchangeProcessor;
import ru.armd.pbk.components.viss.gtfs.GtfsExchangeProcessor;
import ru.armd.pbk.core.IHasLogger;
import ru.armd.pbk.enums.core.Settings;
import ru.armd.pbk.enums.viss.VisExchangeObjects;
import ru.armd.pbk.repositories.core.SettingsRepository;
import ru.armd.pbk.repositories.nsi.calendar.CalendarRepository;
import ru.armd.pbk.utils.date.DateUtils;

import java.util.Calendar;
import java.util.Date;

/**
 * Класс отвечающий за запуск запланированных задач, которые запускаются по
 * расписанию.
 */
@Component
public class VisScheduler
	implements IHasLogger {

   public static final Logger LOGGER = Logger.getLogger(VisScheduler.class);

   @Autowired
   private AsduExchangeProcessor asduExchangeProcessor;

   @Autowired
   private GisMgtProcessor gisMgtUpdateDbProcessor;

   @Autowired
   private EasuFhdProcessor easuFhdProcessor;

   @Autowired
   private DvrExchangeProcessor dvrExchangeProcessor;

   @Autowired
   private GkuopExchangeProcessor gkoupExchangeProcessor;

   @Autowired
   private JmsMessageListener jmsMessageListener;

   @Autowired
   private AskpExchangeProcessor askpExchangeProcessor;

   @Autowired
   private SettingsRepository settingsRepository;

   @Autowired
   private GtfsExchangeProcessor gtfsExchangeProcessor;

   @Autowired
   private AsmppExchangeProcessor asmppExchangeProcessor;

   @Autowired
   private CalendarRepository calendarRepository;

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   /**
	* Вызывает процесс обновления справочника сотрудников ГКУ ОП по расписанию.
	*/
   @Scheduled(cron = "0 0 18 * * ?")
   public void doGkuopEmployeeImport() {
	  gkoupExchangeProcessor.doImport(VisExchangeObjects.GKUOP_EMPLOYEE, null, null, null, false);
   }

   /**
	* Вызывает процесс обновления справочника водителей по расписанию.
	*/
   @Scheduled(cron = "0 0 19 * * ?")
   public void doAsduDriverImport() {
	  asduExchangeProcessor.doImport(VisExchangeObjects.ASDU_GTFS_DRIVER, null, null, null, false);
   }


   /**
	* Вызывает процесс обновления справочника транспортных средств по расписанию.
	*/
   @Scheduled(cron = "0 0 19 * * ?")
   public void doAsduVenicleImport() {
	  asduExchangeProcessor.doImport(VisExchangeObjects.ASDU_GTFS_VENICLE, null, null, null, false);
   }

   /**
	* Вызывает процесс обновления справочника бортового оборудования по расписанию.
	*/
   @Scheduled(cron = "0 0 21 * * ?")
   public void doAsduEquipmentImport() {
	  asduExchangeProcessor.doImport(VisExchangeObjects.ASDU_GTFS_EQUIPMENT, null, null, null, false);
   }

   /**
	* Вызывает процесс обновления справочника остоновочных пунктов по расписанию.
	*/
   @Scheduled(cron = "0 0 18 * * ?")
   public void doAsduStopImport() {
	  Date workDate = DateUtils.addDays(new Date(), -1);
	  asduExchangeProcessor.doImport(VisExchangeObjects.ASDU_GTFS_STOP, null, workDate, workDate, false);
   }

   /**
	* Вызывает процесс обновления справочника водителей по расписанию.
	*/
   @Scheduled(cron = "0 0 22 * * ?")
   public void doAsduTelematicImport() {
	  Date workDate = DateUtils.addDays(new Date(), -1);
	  asduExchangeProcessor.doImport(VisExchangeObjects.ASDU_GTFS_TELEMATIC, null, workDate, workDate, false);
   }

   /**
	* Вызывает процесс обновления плановх расписаний по расписанию.
	*/
   @Scheduled(cron = "0 0 22 * * ?")
   public void doAsduPlanTripImport() {
	  Date workDate = DateUtils.addDays(new Date(), -1);
	  asduExchangeProcessor.doImport(VisExchangeObjects.ASDU_GTFS_PLANTRIP, null, workDate, workDate, false);
   }

   /**
	* Вызывает процесс обновления справочника нарядов по расписанию.
	*/
   //@Scheduled(cron = "0 0 9 * * ?")
   public void doEasuTripSchedule() {
	  easuFhdProcessor.doImport(VisExchangeObjects.EASU_FHD_TRIP_SCHEDULE, null, null, null, false);
   }

   /**
	* Вызывает процесс обновления справочника нарядов по расписанию.
	*/
   @Scheduled(cron = "0 0 20 * * ?")
   public void doEasuTripOrderImport() {
	  easuFhdProcessor.doImport(VisExchangeObjects.EASU_FHD_TRIP_ORDER, null, null, null, false);
   }

   /**
	* Вызывает процесс обновления справочника подразделений по расписанию.
	*/
   @Scheduled(cron = "0 10 3 * * ?")
   public void doEasuFhdDepartmentImport() {
	  easuFhdProcessor.doImport(VisExchangeObjects.EASU_FHD_DEPARTMENT, null, null, null, false);
   }

   /**
	* Вызывает процесс обновления справочника транспортных средств.
	*/
   @Scheduled(cron = "0 0 18 * * ?")
   public void doEasuFhdVenicleImport() {
	  easuFhdProcessor.doImport(VisExchangeObjects.EASU_FHD_VENICLE, null, null, null, false);
   }

   /**
	* Вызывает процесс обновления справочника БСК по расписанию.
	*/
   @Scheduled(cron = "0 0 18 * * ?")
   public void doBskImport() {
	  easuFhdProcessor.doImport(VisExchangeObjects.EASU_FHD_BSK, null, null, null, false);
   }

   /**
	* Вызывает процесс обновления справочника СКК по расписанию.
	*/
   @Scheduled(cron = "0 0 18 * * ?")
   public void doSkkImport() {
	  easuFhdProcessor.doImport(VisExchangeObjects.EASU_FHD_SKK, null, null, null, false);
   }

   /**
	* Вызывает процесс обновления справочника записей с видеорегистраторов по расписанию.
	*/
   @Scheduled(cron = "0 0 16 * * ?")
   public void doDvrRecordImport() {
	  Date workDate = DateUtils.addDays(new Date(), -1);
	  Date startDate = DateUtils.addDays(workDate, -7);
	  dvrExchangeProcessor.doImport(VisExchangeObjects.DVR_RECORD_MOK1, null, startDate, workDate, true);
	  dvrExchangeProcessor.doImport(VisExchangeObjects.DVR_RECORD_MOK2, null, startDate, workDate, true);
	  dvrExchangeProcessor.doImport(VisExchangeObjects.DVR_RECORD_MOK3, null, startDate, workDate, true);
	  dvrExchangeProcessor.doImport(VisExchangeObjects.DVR_RECORD_MOK4, null, startDate, workDate, true);
	  dvrExchangeProcessor.doImport(VisExchangeObjects.DVR_RECORD_MOK5, null, startDate, workDate, true);
   }

   /**
	* Запускается по расписанию. Вызывает обработчик обновления БД ГИС МГТ.
	*/
   @Scheduled(cron = "0 0 18 * * ?")
   public void doGisMgtUpdateDB() {
	  gisMgtUpdateDbProcessor.doImport(VisExchangeObjects.GIS_MGT_OBJECTS, null, null, null, false);
   }

   /**
	* Вызывает процесс обновления проверок ПУсК по расписанию.
	*/
   @Scheduled(cron = "0 30 22 * * ?")
   public void doPuskCheckImport() {
	  Date workDate = DateUtils.addDays(new Date(), -1);
	  askpExchangeProcessor.doImport(VisExchangeObjects.ASKP_PUSK_CHECK, null, workDate, workDate, false);
   }

   /**
	* Вызывает процесс обновления проверок билетов по расписанию.
	*/
   @Scheduled(cron = "0 0 22 * * ?")
   public void doTicketCheckImport() {
	  Date workDate = DateUtils.addDays(new Date(), -1);
	  askpExchangeProcessor.doImport(VisExchangeObjects.ASKP_TICKET_CHECK, null, workDate, workDate, false);
   }

   /**
	* Вызывает процесс обновления справочника билетов по расписанию.
	*/
   @Scheduled(cron = "0 30 21 * * ?")
   public void doTicketImport() {
	  Date workDate = DateUtils.addDays(new Date(), -1);
	  askpExchangeProcessor.doImport(VisExchangeObjects.ASKP_TICKET, null, workDate, workDate, false);
   }

   /**
	* Вызывает процесс обновления справочника по расписанию.
	*/
   @Scheduled(cron = "0 0 23 * * ?")
   public void doContaclessCarImport() {
	  try {
		 Date endDate = DateUtils.addDays(DateUtils.shiftToDayStart(new Date()), -1);
		 Date startDate = DateUtils.addDays(endDate,
			 -Integer.parseInt(settingsRepository.getById(Settings.VIS_BSK_COUNT_DAY.getId()).getValue()));

		 askpExchangeProcessor.doImport(VisExchangeObjects.ASKP_CONTACTLESS_CARD, null, startDate, endDate, false);
	  } catch (Exception e) {
		 getLogger().error("Ошибка запуска процесса обновления справочника проходов БСК по расписанию", e);
	  }
   }

   /**
	* Вызывает процесс обновления ГТФС.
	*/
   @Scheduled(cron = "0 50 2 * * ?")
   public void doGtfsImport() {
	  Date workDate = DateUtils.addDays(new Date(), -1);
	  gtfsExchangeProcessor.doImport(VisExchangeObjects.GTFS_CHANGE_EQUIPMENT, null, workDate, workDate, false);
	  gtfsExchangeProcessor.doImport(VisExchangeObjects.GTFS_CHANGE_STOP_TIMES, null, workDate, workDate, false);
	  gtfsExchangeProcessor.doImport(VisExchangeObjects.GTFS_EQUIPMENT, null, workDate, workDate, false);
	  gtfsExchangeProcessor.doImport(VisExchangeObjects.GTFS_IMPACT, null, workDate, workDate, false);
	  gtfsExchangeProcessor.doImport(VisExchangeObjects.GTFS_IMPACT_CODE, null, workDate, workDate, false);
	  gtfsExchangeProcessor.doImport(VisExchangeObjects.GTFS_REPLACE_VEHICLE, null, workDate, workDate, false);
	  gtfsExchangeProcessor.doImport(VisExchangeObjects.GTFS_STOPS, null, workDate, workDate, false);
	  gtfsExchangeProcessor.doImport(VisExchangeObjects.GTFS_STOP_TIMES, null, workDate, workDate, false);
	  gtfsExchangeProcessor.doImport(VisExchangeObjects.GTFS_TRIPS, null, workDate, workDate, false);
	  gtfsExchangeProcessor.doImport(VisExchangeObjects.GTFS_TRIPS_STOPS, null, workDate, workDate, false);
	  gtfsExchangeProcessor.doImport(VisExchangeObjects.GTFS_VEHICLE_TASK, null, workDate, workDate, false);
	  gtfsExchangeProcessor.doImport(VisExchangeObjects.GTFS_DEPOTS, null, workDate, workDate, false);
	  gtfsExchangeProcessor.doImport(VisExchangeObjects.GTFS_CALENDAR, null, workDate, workDate, false);
	  gtfsExchangeProcessor.doImport(VisExchangeObjects.GTFS_VEHICLES, null, workDate, workDate, false);
	  gtfsExchangeProcessor.doImport(VisExchangeObjects.GTFS_ROUTE, null, workDate, workDate, false);

   }

   /**
	* Вызывает процесс ежедневного обновления АСМПП.
	*/
   @Scheduled(cron = "0 0 6 * * ?")
   public void doAsmppImportDay() {
	  Date startDate = DateUtils.addDays(new Date(), -5);
	  Date endDate = DateUtils.addDays(new Date(), -4);
	  asmppExchangeProcessor.doImport(VisExchangeObjects.ASMPP_STOPS, null, startDate, endDate, true);
   }

	/**
	 * Вызывает процесс ежедневного обновления АСМПП.
	 */
	@Scheduled(cron = "0 30 5 * * ?")
	public void doAsmppImportDayMinus15() {
		Date workDate = DateUtils.addDays(new Date(), -15);
		asmppExchangeProcessor.doImport(VisExchangeObjects.ASMPP_STOPS, null, workDate, workDate, true);
	}

	/**
	 * Вызывает процесс ежедневного обновления АСМПП.
	 */
	@Scheduled(cron = "0 0 5 * * ?")
	public void doAsmppImportDayMinus30() {
		Date workDate = DateUtils.addDays(new Date(), -30);
		asmppExchangeProcessor.doImport(VisExchangeObjects.ASMPP_STOPS, null, workDate, workDate, true);
	}


/*
   //TODO если я забыл удалить этот метод - надо удалить.
	@Scheduled(cron = "0 33 12 * * ?")
	public void doAsmppImportDay1111() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2017,
				Calendar.OCTOBER, 10);
		Date workDate = calendar.getTime();
		asmppExchangeProcessor.doImport(VisExchangeObjects.ASMPP_STOPS, null, workDate, workDate, false);
	}*/

   /**
	* Вызывает процесс перезаливки данных АСМПП за предыдущий месяц
	*/
   @Scheduled(cron = "0 30 3 * * ?")
   public void doAsmppImportMonth() {
	  try {
		 Date firstDayInCurrentMonth = DateUtils.getFirstDateInCurrentMonth();
		 int countWorkDays = calendarRepository.getCountWorkDays(firstDayInCurrentMonth, new Date());
		 // В 6-ой рабочий день месяца запускается синхронизация
		 // TODO: 17/07/2017 Частоту синхронизации можно было бы вынести в настройки АСУ
		 if (countWorkDays == 5) {
			asmppExchangeProcessor.doImport(
				VisExchangeObjects.ASMPP_STOPS,
				null,
				DateUtils.getFirstDateInPrevMonth(),
				DateUtils.getLastDateInPrevMonth(),
				true
			);
		 }
	  } catch (Exception e) {
		 getLogger().error("Ошибка запуска процесса перезаливки данных АСМПП за предыдущий месяц", e);
	  }
   }
}
