package ru.armd.pbk.components.viss.gtfs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import ru.armd.pbk.components.viss.IVisExecutor;
import ru.armd.pbk.components.viss.core.BaseExchangeProcessor;
import ru.armd.pbk.components.viss.gtfs.providers.GtfFilesystemProvider;
import ru.armd.pbk.components.viss.gtfs.providers.GtfsCalendarProvider;
import ru.armd.pbk.components.viss.gtfs.providers.GtfsChangeEquipmentProvider;
import ru.armd.pbk.components.viss.gtfs.providers.GtfsChangeStopTimesProvider;
import ru.armd.pbk.components.viss.gtfs.providers.GtfsDepotProvider;
import ru.armd.pbk.components.viss.gtfs.providers.GtfsEquipmentProvider;
import ru.armd.pbk.components.viss.gtfs.providers.GtfsImpactCodeProvider;
import ru.armd.pbk.components.viss.gtfs.providers.GtfsImpactProvider;
import ru.armd.pbk.components.viss.gtfs.providers.GtfsReplaceVehicleProvider;
import ru.armd.pbk.components.viss.gtfs.providers.GtfsRouteProvider;
import ru.armd.pbk.components.viss.gtfs.providers.GtfsStopTimesProvider;
import ru.armd.pbk.components.viss.gtfs.providers.GtfsStopsProvider;
import ru.armd.pbk.components.viss.gtfs.providers.GtfsTripsProvider;
import ru.armd.pbk.components.viss.gtfs.providers.GtfsTripsStopsProvider;
import ru.armd.pbk.components.viss.gtfs.providers.GtfsVehicleProvider;
import ru.armd.pbk.components.viss.gtfs.providers.GtfsVehicleTaskProvider;
import ru.armd.pbk.domain.viss.VisExchangeConfig;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.enums.viss.VisExchangeObjects;
import ru.armd.pbk.enums.viss.VisExchangeOperations;

import java.util.Date;

/**
 * Процессор для обмена с ГТФС.
 */
@Component
public class GtfsExchangeProcessor
	extends BaseExchangeProcessor {

   @Autowired
   private GtfsChangeEquipmentProvider gtfsChangeEquipmentProvider;

   @Autowired
   private GtfsChangeStopTimesProvider gtfsChangeStopTimesProvider;

   @Autowired
   private GtfsEquipmentProvider gtfsEquipmentProvider;

   @Autowired
   private GtfsImpactProvider gtfsImpactProvider;

   @Autowired
   private GtfsImpactCodeProvider gtfsImpactCodeProvider;

   @Autowired
   private GtfsReplaceVehicleProvider gtfsReplaceVehicleProvider;

   @Autowired
   private GtfsStopsProvider gtfsStopsProvider;

   @Autowired
   private GtfsStopTimesProvider gtfsStopTimesProvider;

   @Autowired
   private GtfsTripsProvider gtfsTripsProvider;

   @Autowired
   private GtfsTripsStopsProvider gtfsTripsStopsProvider;

   @Autowired
   private GtfsVehicleTaskProvider gtfsVehicleTaskProvider;

   @Autowired
   private GtfsDepotProvider gtfsDepotProvider;

   @Autowired
   private GtfsCalendarProvider gtfsCalendarProvider;

   @Autowired
   private GtfsVehicleProvider gtfsVehicleProvider;

   @Autowired
   private GtfsRouteProvider gtfsRouteProvider;

   @Autowired
   private GtfFilesystemProvider gtfFilesystemProvider;

   GtfsExchangeProcessor() {
	  super(VisAuditType.VIS_GTFS);
   }

   /**
	* Метод, запускающий процесс информационного обмена с ВИС ЕАСУ ФХД.
	*
	* @param visExchangeObject - Тип обмена с ВИС.
	* @param parameter         - параметр.
	* @param start             - дата начала периуда.
	* @param end               - дата окончания периуда.
	* @param force             - флаг принудительного запуска.
	*/
   @Async(IVisExecutor.GTFS_EXECUTOR)
   public void doImport(VisExchangeObjects visExchangeObject, String parameter, Date start, Date end, boolean force) {
	  doProcess(visExchangeObject, VisExchangeOperations.IMPORT, parameter, start, end, force);
   }

   @Override
   protected void doExchange(VisExchangeConfig visExchangeConfig, String parameter, Date workDate, boolean force) {
	  if (VisExchangeObjects.GTFS_CHANGE_EQUIPMENT.getId().equals(visExchangeConfig.getExchangeObjectId())) {
		 gtfsChangeEquipmentProvider.doExchange(visExchangeConfig, parameter, workDate, force);
	  } else if (VisExchangeObjects.GTFS_CHANGE_STOP_TIMES.getId().equals(visExchangeConfig.getExchangeObjectId())) {
		 gtfsChangeStopTimesProvider.doExchange(visExchangeConfig, parameter, workDate, force);
	  } else if (VisExchangeObjects.GTFS_EQUIPMENT.getId().equals(visExchangeConfig.getExchangeObjectId())) {
		 gtfsEquipmentProvider.doExchange(visExchangeConfig, parameter, workDate, force);
	  } else if (VisExchangeObjects.GTFS_IMPACT.getId().equals(visExchangeConfig.getExchangeObjectId())) {
		 gtfsImpactProvider.doExchange(visExchangeConfig, parameter, workDate, force);
	  } else if (VisExchangeObjects.GTFS_IMPACT_CODE.getId().equals(visExchangeConfig.getExchangeObjectId())) {
		 gtfsImpactCodeProvider.doExchange(visExchangeConfig, parameter, workDate, force);
	  } else if (VisExchangeObjects.GTFS_REPLACE_VEHICLE.getId().equals(visExchangeConfig.getExchangeObjectId())) {
		 gtfsReplaceVehicleProvider.doExchange(visExchangeConfig, parameter, workDate, force);
	  } else if (VisExchangeObjects.GTFS_STOPS.getId().equals(visExchangeConfig.getExchangeObjectId())) {
		 gtfsStopsProvider.doExchange(visExchangeConfig, parameter, workDate, force);
	  } else if (VisExchangeObjects.GTFS_STOP_TIMES.getId().equals(visExchangeConfig.getExchangeObjectId())) {
		 gtfsStopTimesProvider.doExchange(visExchangeConfig, parameter, workDate, force);
	  } else if (VisExchangeObjects.GTFS_TRIPS.getId().equals(visExchangeConfig.getExchangeObjectId())) {
		 gtfsTripsProvider.doExchange(visExchangeConfig, parameter, workDate, force);
	  } else if (VisExchangeObjects.GTFS_TRIPS_STOPS.getId().equals(visExchangeConfig.getExchangeObjectId())) {
		 gtfsTripsStopsProvider.doExchange(visExchangeConfig, parameter, workDate, force);
	  } else if (VisExchangeObjects.GTFS_VEHICLE_TASK.getId().equals(visExchangeConfig.getExchangeObjectId())) {
		 gtfsVehicleTaskProvider.doExchange(visExchangeConfig, parameter, workDate, force);
	  } else if (VisExchangeObjects.GTFS_DEPOTS.getId().equals(visExchangeConfig.getExchangeObjectId())) {
		 gtfsDepotProvider.doExchange(visExchangeConfig, parameter, workDate, force);
	  } else if (VisExchangeObjects.GTFS_VEHICLES.getId().equals(visExchangeConfig.getExchangeObjectId())) {
		 gtfsVehicleProvider.doExchange(visExchangeConfig, parameter, workDate, force);
	  } else if (VisExchangeObjects.GTFS_CALENDAR.getId().equals(visExchangeConfig.getExchangeObjectId())) {
		 gtfsCalendarProvider.doExchange(visExchangeConfig, parameter, workDate, force);
	  } else if (VisExchangeObjects.GTFS_ROUTE.getId().equals(visExchangeConfig.getExchangeObjectId())) {
		 gtfsRouteProvider.doExchange(visExchangeConfig, parameter, workDate, force);
	  } else if (VisExchangeObjects.GTFS_FILESYSTEM.getId().equals(visExchangeConfig.getExchangeObjectId())) {
		 gtfFilesystemProvider.doExchange(visExchangeConfig, parameter, workDate, force);
	  }
   }

}
