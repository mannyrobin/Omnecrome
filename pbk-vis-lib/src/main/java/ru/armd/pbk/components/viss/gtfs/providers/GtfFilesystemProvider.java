package ru.armd.pbk.components.viss.gtfs.providers;

import armd.lightRest.client.BaseRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.components.viss.core.BaseExchangeProvider;
import ru.armd.pbk.components.viss.gtfs.loaders.GtfsCalendarLoader;
import ru.armd.pbk.components.viss.gtfs.loaders.GtfsDepotLoader;
import ru.armd.pbk.components.viss.gtfs.loaders.GtfsEquipmentLoader;
import ru.armd.pbk.components.viss.gtfs.loaders.GtfsStopTimesLoader;
import ru.armd.pbk.components.viss.gtfs.loaders.GtfsTripsLoader;
import ru.armd.pbk.components.viss.gtfs.loaders.GtfsTripsStopsLoader;
import ru.armd.pbk.components.viss.gtfs.loaders.GtfsVehicleTaskLoader;
import ru.armd.pbk.components.viss.gtfs.loaders.GtfsVehiclesLoader;
import ru.armd.pbk.domain.viss.VisExchange;
import ru.armd.pbk.domain.viss.VisExchangeConfig;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.enums.viss.VisExchangeObjects;
import ru.armd.pbk.enums.viss.Viss;
import ru.armd.pbk.repositories.viss.VisExchangeConfigRepository;
import ru.armd.pbk.utils.ImportResult;

import java.io.InputStream;

@Component
public class GtfFilesystemProvider
	extends BaseExchangeProvider {

   @Autowired
   private GtfsCalendarLoader gtfsCalendarLoader;
   @Autowired
   private GtfsDepotLoader gtfsDepotLoader;
   @Autowired
   private GtfsStopTimesLoader gtfsStopTimesLoader;
   @Autowired
   private GtfsTripsStopsLoader gtfsTripsStopsLoader;
   @Autowired
   private GtfsTripsLoader gtfsTripsLoader;
   @Autowired
   private GtfsEquipmentLoader gtfsEquipmentLoader;
   @Autowired
   private GtfsVehiclesLoader gtfsVehiclesLoader;
   @Autowired
   private GtfsVehicleTaskLoader gtfsVehicleTaskLoader;


   @Autowired
   private VisExchangeConfigRepository repository;

   public GtfFilesystemProvider() {
	  super(Viss.GTFS, VisAuditType.VIS_GTFS);
   }

   @Override
   protected ImportResult<?> importStream(VisExchange visExchange, InputStream is) {
	  VisExchangeConfig config = repository.getById(visExchange.getConfigId());
	  if (VisExchangeObjects.GTFS_CALENDAR.getId().equals(config.getExchangeObjectId())) {
		 return gtfsCalendarLoader.importFile(is, visExchange.getWorkDate());
	  } else if (VisExchangeObjects.GTFS_DEPOTS.getId().equals(config.getExchangeObjectId())) {
		 return gtfsDepotLoader.importFile(is, visExchange.getWorkDate());
	  } else if (VisExchangeObjects.GTFS_STOP_TIMES.getId().equals(config.getExchangeObjectId())) {
		 return gtfsStopTimesLoader.importFile(is, visExchange.getWorkDate());
	  } else if (VisExchangeObjects.GTFS_TRIPS_STOPS.getId().equals(config.getExchangeObjectId())) {
		 return gtfsTripsStopsLoader.importFile(is, visExchange.getWorkDate());
	  } else if (VisExchangeObjects.GTFS_TRIPS.getId().equals(config.getExchangeObjectId())) {
		 return gtfsTripsLoader.importFile(is, visExchange.getWorkDate());
	  } else if (VisExchangeObjects.GTFS_EQUIPMENT.getId().equals(config.getExchangeObjectId())) {
		 return gtfsEquipmentLoader.importFile(is, visExchange.getWorkDate());
	  } else if (VisExchangeObjects.GTFS_VEHICLES.getId().equals(config.getExchangeObjectId())) {
		 return gtfsVehiclesLoader.importFile(is, visExchange.getWorkDate());
	  } else if (VisExchangeObjects.GTFS_VEHICLE_TASK.getId().equals(config.getExchangeObjectId())) {
		 return gtfsVehicleTaskLoader.importFile(is, visExchange.getWorkDate());
	  }
	  return null;
   }

   @Override
   protected boolean checkFileName(VisExchange visExchange, String name) {
	  VisExchangeConfig config = repository.getById(visExchange.getConfigId());
	  if (VisExchangeObjects.GTFS_CALENDAR.getId().equals(config.getExchangeObjectId())) {
		 return name.equals("GTFS_CALENDAR.ZIP");
	  } else if (VisExchangeObjects.GTFS_DEPOTS.getId().equals(config.getExchangeObjectId())) {
		 return name.equals("GTFS_DEPOTS.ZIP");
	  } else if (VisExchangeObjects.GTFS_STOP_TIMES.getId().equals(config.getExchangeObjectId())) {
		 return name.equals("GTFS_TRIPS_STOPS.ZIP");
	  } else if (VisExchangeObjects.GTFS_TRIPS_STOPS.getId().equals(config.getExchangeObjectId())) {
		 return name.equals("GTFS_TRIPS_STOPS.ZIP");
	  } else if (VisExchangeObjects.GTFS_TRIPS.getId().equals(config.getExchangeObjectId())) {
		 return name.equals("GTFS_TRIPS.ZIP");
	  } else if (VisExchangeObjects.GTFS_EQUIPMENT.getId().equals(config.getExchangeObjectId())) {
		 return name.equals("GTFS_EQUIPMENT.ZIP");
	  } else if (VisExchangeObjects.GTFS_VEHICLES.getId().equals(config.getExchangeObjectId())) {
		 return name.equals("GTFS_VEHICLES.ZIP");
	  } else if (VisExchangeObjects.GTFS_VEHICLE_TASK.getId().equals(config.getExchangeObjectId())) {
		 return name.equals("GTFS_VEHICLE_TASK.ZIP");
	  }
	  return false;
   }

   @Override
   protected boolean processAsZip(VisExchange visExchange, BaseRestClient client) {
	  return true;
   }
}
