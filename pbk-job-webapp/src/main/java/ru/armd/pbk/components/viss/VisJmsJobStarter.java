package ru.armd.pbk.components.viss;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import ru.armd.pbk.components.viss.asdu.AsduExchangeProcessor;
import ru.armd.pbk.components.viss.askp.AskpExchangeProcessor;
import ru.armd.pbk.components.viss.askp.AskpExecutorStarter;
import ru.armd.pbk.components.viss.asmpp.AsmppExchangeProcessor;
import ru.armd.pbk.components.viss.dvr.DvrExchangeProcessor;
import ru.armd.pbk.components.viss.easu.EasuFhdProcessor;
import ru.armd.pbk.components.viss.gismgt.GisMgtProcessor;
import ru.armd.pbk.components.viss.gkuop.GkuopExchangeProcessor;
import ru.armd.pbk.components.viss.gtfs.GtfsExchangeProcessor;
import ru.armd.pbk.dto.viss.JmsVisExchangeStartMessage;
import ru.armd.pbk.enums.viss.VisExchangeObjects;

/**
 * Запускает обмен с вис при получении jms сообщения.
 */
@Component
public class VisJmsJobStarter {

   @Autowired
   private AsduExchangeProcessor asduExchangeProcessor;

   @Autowired
   private GisMgtProcessor gisMgtUpdateDbProcessor;

   @Autowired
   private EasuFhdProcessor easuFhdProcessor;

   @Autowired
   private DvrExchangeProcessor dvrExchangeProcessor;

   @Autowired
   private GkuopExchangeProcessor gkuopExchangeProcessor;

   @Autowired
   private AskpExchangeProcessor askpExchangeProcessor;

   @Autowired
   private AskpExecutorStarter askpExecutorStarter;

   @Autowired
   private GtfsExchangeProcessor gtfsExchangeProcessor;

   @Autowired
   private AsmppExchangeProcessor asmppExchangeProcessor;

   public VisJmsJobStarter() {
	  // Устанавливаем разрешение на пакет для сериализации
	  System.setProperty("org.apache.activemq.SERIALIZABLE_PACKAGES", "*");
   }

   @JmsListener(destination = "VisExchange")
   public void processMessage(JmsVisExchangeStartMessage pbkMessage) {
	  if (VisExchangeObjects.ASDU_GTFS_DRIVER.equals(pbkMessage.getObjType())) {
		 asduExchangeProcessor.doImport(VisExchangeObjects.ASDU_GTFS_DRIVER, pbkMessage.getParameter(), pbkMessage.getStart(), pbkMessage.getEnd(), true);
	  } else if (VisExchangeObjects.ASDU_GTFS_VENICLE.equals(pbkMessage.getObjType())) {
		 asduExchangeProcessor.doImport(VisExchangeObjects.ASDU_GTFS_VENICLE, pbkMessage.getParameter(), pbkMessage.getStart(), pbkMessage.getEnd(), true);
	  } else if (VisExchangeObjects.ASDU_GTFS_EQUIPMENT.equals(pbkMessage.getObjType())) {
		 asduExchangeProcessor.doImport(VisExchangeObjects.ASDU_GTFS_EQUIPMENT, pbkMessage.getParameter(), pbkMessage.getStart(), pbkMessage.getEnd(), true);
	  } else if (VisExchangeObjects.ASDU_GTFS_STOP.equals(pbkMessage.getObjType())) {
		 asduExchangeProcessor.doImport(VisExchangeObjects.ASDU_GTFS_STOP, pbkMessage.getParameter(), pbkMessage.getStart(), pbkMessage.getEnd(), true);
	  } else if (VisExchangeObjects.ASDU_GTFS_TELEMATIC.equals(pbkMessage.getObjType())) {
		 asduExchangeProcessor.doImport(VisExchangeObjects.ASDU_GTFS_TELEMATIC, pbkMessage.getParameter(), pbkMessage.getStart(), pbkMessage.getEnd(), true);
	  } else if (VisExchangeObjects.ASDU_GTFS_TRIP.equals(pbkMessage.getObjType())) {
		 asduExchangeProcessor.doImport(VisExchangeObjects.ASDU_GTFS_TRIP, pbkMessage.getParameter(), pbkMessage.getStart(), pbkMessage.getEnd(), true);
	  } else if (VisExchangeObjects.ASDU_GTFS_PLANTRIP.equals(pbkMessage.getObjType())) {
		 asduExchangeProcessor.doImport(VisExchangeObjects.ASDU_GTFS_PLANTRIP, pbkMessage.getParameter(), pbkMessage.getStart(), pbkMessage.getEnd(), true);
	  } else if (VisExchangeObjects.GIS_MGT_OBJECTS.equals(pbkMessage.getObjType())) {
		 gisMgtUpdateDbProcessor.doImport(VisExchangeObjects.GIS_MGT_OBJECTS, pbkMessage.getParameter(), pbkMessage.getStart(), pbkMessage.getEnd(), true);
	  } else if (VisExchangeObjects.EASU_FHD_DEPARTMENT.equals(pbkMessage.getObjType())) {
		 easuFhdProcessor.doImport(VisExchangeObjects.EASU_FHD_DEPARTMENT, pbkMessage.getParameter(), pbkMessage.getStart(), pbkMessage.getEnd(), true);
	  } else if (VisExchangeObjects.EASU_FHD_EMPLOYEE.equals(pbkMessage.getObjType())) {
		 easuFhdProcessor.doImport(VisExchangeObjects.EASU_FHD_EMPLOYEE, pbkMessage.getParameter(), pbkMessage.getStart(), pbkMessage.getEnd(), true);
	  } else if (VisExchangeObjects.EASU_FHD_TRIP_ORDER.equals(pbkMessage.getObjType())) {
		 easuFhdProcessor.doImport(VisExchangeObjects.EASU_FHD_TRIP_ORDER, pbkMessage.getParameter(), pbkMessage.getStart(), pbkMessage.getEnd(), true);
	  } else if (VisExchangeObjects.EASU_FHD_TRIP_SCHEDULE.equals(pbkMessage.getObjType())) {
		 easuFhdProcessor.doImport(VisExchangeObjects.EASU_FHD_TRIP_SCHEDULE, pbkMessage.getParameter(), pbkMessage.getStart(), pbkMessage.getEnd(), true);
	  } else if (VisExchangeObjects.EASU_FHD_WORK_MODE.equals(pbkMessage.getObjType())) {
		 easuFhdProcessor.doImport(VisExchangeObjects.EASU_FHD_WORK_MODE, pbkMessage.getParameter(), pbkMessage.getStart(), pbkMessage.getEnd(), true);
	  } else if (VisExchangeObjects.EASU_FHD_VENICLE.equals(pbkMessage.getObjType())) {
		 easuFhdProcessor.doImport(VisExchangeObjects.EASU_FHD_VENICLE, pbkMessage.getParameter(), pbkMessage.getStart(), pbkMessage.getEnd(), true);
	  } else if (VisExchangeObjects.EASU_FHD_SKK.equals(pbkMessage.getObjType())) {
		 easuFhdProcessor.doImport(VisExchangeObjects.EASU_FHD_SKK, pbkMessage.getParameter(), pbkMessage.getStart(), pbkMessage.getEnd(), true);
	  } else if (VisExchangeObjects.EASU_FHD_BSK.equals(pbkMessage.getObjType())) {
		 easuFhdProcessor.doImport(VisExchangeObjects.EASU_FHD_BSK, pbkMessage.getParameter(), pbkMessage.getStart(), pbkMessage.getEnd(), true);
	  } else if (VisExchangeObjects.DVR_RECORD_MOK1.equals(pbkMessage.getObjType())) {
		 dvrExchangeProcessor.doImport(VisExchangeObjects.DVR_RECORD_MOK1, pbkMessage.getParameter(), pbkMessage.getStart(), pbkMessage.getEnd(), true);
	  } else if (VisExchangeObjects.DVR_RECORD_MOK2.equals(pbkMessage.getObjType())) {
		 dvrExchangeProcessor.doImport(VisExchangeObjects.DVR_RECORD_MOK2, pbkMessage.getParameter(), pbkMessage.getStart(), pbkMessage.getEnd(), true);
	  } else if (VisExchangeObjects.DVR_RECORD_MOK3.equals(pbkMessage.getObjType())) {
		 dvrExchangeProcessor.doImport(VisExchangeObjects.DVR_RECORD_MOK3, pbkMessage.getParameter(), pbkMessage.getStart(), pbkMessage.getEnd(), true);
	  } else if (VisExchangeObjects.DVR_RECORD_MOK4.equals(pbkMessage.getObjType())) {
		 dvrExchangeProcessor.doImport(VisExchangeObjects.DVR_RECORD_MOK4, pbkMessage.getParameter(), pbkMessage.getStart(), pbkMessage.getEnd(), true);
	  } else if (VisExchangeObjects.DVR_RECORD_MOK5.equals(pbkMessage.getObjType())) {
		 dvrExchangeProcessor.doImport(VisExchangeObjects.DVR_RECORD_MOK5, pbkMessage.getParameter(), pbkMessage.getStart(), pbkMessage.getEnd(), true);
	  } else if (VisExchangeObjects.DVR_DVR.equals(pbkMessage.getObjType())) {
		 dvrExchangeProcessor.doImport(VisExchangeObjects.DVR_DVR, pbkMessage.getParameter(), pbkMessage.getStart(), pbkMessage.getEnd(), true);
	  } else if (VisExchangeObjects.GKUOP_EMPLOYEE.equals(pbkMessage.getObjType())) {
		 gkuopExchangeProcessor.doImport(VisExchangeObjects.GKUOP_EMPLOYEE, pbkMessage.getParameter(), pbkMessage.getStart(), pbkMessage.getEnd(), true);
	  } else if (VisExchangeObjects.ASKP_TICKET.equals(pbkMessage.getObjType())) {
		 askpExecutorStarter.doImport(VisExchangeObjects.ASKP_TICKET, pbkMessage.getParameter(), pbkMessage.getStart(), pbkMessage.getEnd(), true);
	  } else if (VisExchangeObjects.ASKP_TICKET_CHECK.equals(pbkMessage.getObjType())) {
		 askpExecutorStarter.doImport(VisExchangeObjects.ASKP_TICKET_CHECK, pbkMessage.getParameter(), pbkMessage.getStart(), pbkMessage.getEnd(), true);
	  } else if (VisExchangeObjects.ASKP_PUSK_CHECK.equals(pbkMessage.getObjType())) {
		 askpExecutorStarter.doImport(VisExchangeObjects.ASKP_PUSK_CHECK, pbkMessage.getParameter(), pbkMessage.getStart(), pbkMessage.getEnd(), true);
	  } else if (VisExchangeObjects.ASKP_CONTACTLESS_CARD.equals(pbkMessage.getObjType())) {
		 askpExecutorStarter.doImport(VisExchangeObjects.ASKP_CONTACTLESS_CARD, pbkMessage.getParameter(), pbkMessage.getStart(), pbkMessage.getEnd(), true);
	  } else if (VisExchangeObjects.GTFS_CHANGE_EQUIPMENT.equals(pbkMessage.getObjType())) {
		 gtfsExchangeProcessor.doImport(VisExchangeObjects.GTFS_CHANGE_EQUIPMENT, pbkMessage.getParameter(), pbkMessage.getStart(), pbkMessage.getEnd(), true);
	  } else if (VisExchangeObjects.GTFS_CHANGE_STOP_TIMES.equals(pbkMessage.getObjType())) {
		 gtfsExchangeProcessor.doImport(VisExchangeObjects.GTFS_CHANGE_STOP_TIMES, pbkMessage.getParameter(), pbkMessage.getStart(), pbkMessage.getEnd(), true);
	  } else if (VisExchangeObjects.GTFS_EQUIPMENT.equals(pbkMessage.getObjType())) {
		 gtfsExchangeProcessor.doImport(VisExchangeObjects.GTFS_EQUIPMENT, pbkMessage.getParameter(), pbkMessage.getStart(), pbkMessage.getEnd(), true);
	  } else if (VisExchangeObjects.GTFS_IMPACT.equals(pbkMessage.getObjType())) {
		 gtfsExchangeProcessor.doImport(VisExchangeObjects.GTFS_IMPACT, pbkMessage.getParameter(), pbkMessage.getStart(), pbkMessage.getEnd(), true);
	  } else if (VisExchangeObjects.GTFS_IMPACT_CODE.equals(pbkMessage.getObjType())) {
		 gtfsExchangeProcessor.doImport(VisExchangeObjects.GTFS_IMPACT_CODE, pbkMessage.getParameter(), pbkMessage.getStart(), pbkMessage.getEnd(), true);
	  } else if (VisExchangeObjects.GTFS_REPLACE_VEHICLE.equals(pbkMessage.getObjType())) {
		 gtfsExchangeProcessor.doImport(VisExchangeObjects.GTFS_REPLACE_VEHICLE, pbkMessage.getParameter(), pbkMessage.getStart(), pbkMessage.getEnd(), true);
	  } else if (VisExchangeObjects.GTFS_STOPS.equals(pbkMessage.getObjType())) {
		 gtfsExchangeProcessor.doImport(VisExchangeObjects.GTFS_STOPS, pbkMessage.getParameter(), pbkMessage.getStart(), pbkMessage.getEnd(), true);
	  } else if (VisExchangeObjects.GTFS_STOP_TIMES.equals(pbkMessage.getObjType())) {
		 gtfsExchangeProcessor.doImport(VisExchangeObjects.GTFS_STOP_TIMES, pbkMessage.getParameter(), pbkMessage.getStart(), pbkMessage.getEnd(), true);
	  } else if (VisExchangeObjects.GTFS_TRIPS.equals(pbkMessage.getObjType())) {
		 gtfsExchangeProcessor.doImport(VisExchangeObjects.GTFS_TRIPS, pbkMessage.getParameter(), pbkMessage.getStart(), pbkMessage.getEnd(), true);
	  } else if (VisExchangeObjects.GTFS_TRIPS_STOPS.equals(pbkMessage.getObjType())) {
		 gtfsExchangeProcessor.doImport(VisExchangeObjects.GTFS_TRIPS_STOPS, pbkMessage.getParameter(), pbkMessage.getStart(), pbkMessage.getEnd(), true);
	  } else if (VisExchangeObjects.GTFS_VEHICLE_TASK.equals(pbkMessage.getObjType())) {
		 gtfsExchangeProcessor.doImport(VisExchangeObjects.GTFS_VEHICLE_TASK, pbkMessage.getParameter(), pbkMessage.getStart(), pbkMessage.getEnd(), true);
	  } else if (VisExchangeObjects.GTFS_DEPOTS.equals(pbkMessage.getObjType())) {
		 gtfsExchangeProcessor.doImport(VisExchangeObjects.GTFS_DEPOTS, pbkMessage.getParameter(), pbkMessage.getStart(), pbkMessage.getEnd(), true);
	  } else if (VisExchangeObjects.GTFS_CALENDAR.equals(pbkMessage.getObjType())) {
		 gtfsExchangeProcessor.doImport(VisExchangeObjects.GTFS_CALENDAR, pbkMessage.getParameter(), pbkMessage.getStart(), pbkMessage.getEnd(), true);
	  } else if (VisExchangeObjects.GTFS_VEHICLES.equals(pbkMessage.getObjType())) {
		 gtfsExchangeProcessor.doImport(VisExchangeObjects.GTFS_VEHICLES, pbkMessage.getParameter(), pbkMessage.getStart(), pbkMessage.getEnd(), true);
	  } else if (VisExchangeObjects.GTFS_ROUTE.equals(pbkMessage.getObjType())) {
		 gtfsExchangeProcessor.doImport(VisExchangeObjects.GTFS_ROUTE, pbkMessage.getParameter(), pbkMessage.getStart(), pbkMessage.getEnd(), true);
	  } else if (VisExchangeObjects.ASMPP_STOPS.equals(pbkMessage.getObjType())) {
		 asmppExchangeProcessor.doImport(VisExchangeObjects.ASMPP_STOPS, pbkMessage.getParameter(), pbkMessage.getStart(), pbkMessage.getEnd(), true);
	  } else if (VisExchangeObjects.GTFS_FILESYSTEM.equals(pbkMessage.getObjType())) {
		 gtfsExchangeProcessor.doImport(VisExchangeObjects.GTFS_FILESYSTEM, pbkMessage.getParameter(), pbkMessage.getStart(), pbkMessage.getEnd(), true);
	  }
   }
}
