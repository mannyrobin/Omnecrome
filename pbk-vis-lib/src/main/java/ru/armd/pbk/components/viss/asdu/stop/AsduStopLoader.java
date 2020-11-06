package ru.armd.pbk.components.viss.asdu.stop;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.components.viss.core.loaders.BaseCsvDomainLoader;
import ru.armd.pbk.domain.viss.asdu.AsduStop;
import ru.armd.pbk.enums.core.AuditType;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.repositories.viss.asdu.AsduStopRepository;

/**
 * Лоадер для водителей.
 */
@Component
public class AsduStopLoader
	extends BaseCsvDomainLoader<AsduStop> {

   private static final Logger LOGGER = Logger.getLogger(AsduStopLoader.class);

   private AsduStopRepository asduStopRepository;

   @Autowired
   private StopLoader stopLoader;

   @Autowired
   AsduStopLoader(AsduStopRepository asduStopRepository) {
	  super(asduStopRepository);
	  this.asduStopRepository = asduStopRepository;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   public AuditType getAuditType() {
	  return VisAuditType.VIS_ASDU_STOP;
   }

   @Override
   protected AsduStop createDomain(String[] fields) {
	  // STOP_ID;STOP_CODE;STOP_NAME;STOP_DESC;STOP_LAT;STOP_LON;ZONE_ID;STOP_URL;LOCATION_TYPE;PARENT_STATION;STOP_TIMEZONE;WHEELCHAIR_BOARDING;STOP_POLYGON;
	  AsduStop stop = new AsduStop();
	  stop.setAsduStopId(getLongValue(fields[0]));
	  stop.setStopCode(getStringValue(fields[1]));
	  stop.setStopName(getStringValue(fields[2]));
	  stop.setStopDesc(getStringValue(fields[3]));
	  stop.setStopLat(getDoubleValue(fields[4]));
	  stop.setStopLon(getDoubleValue(fields[5]));
	  stop.setZoneId(getLongValue(fields[6]));
	  stop.setStopUrl(getStringValue(fields[7]));
	  stop.setLocationType(getStringValue(fields[8]));
	  stop.setParentStation(getStringValue(fields[9]));
	  stop.setStopTimezone(getStringValue(fields[10]));
	  stop.setWheelchairBoarding(getStringValue(fields[11]));
	  stop.setStopPolygon(getStringValue(fields[12]));

	  return stop;
   }

	/* TODO: решили пока не связывать ASDU_STOPS и STOP_HISTS
	@Override
	protected void doProcessFields(String[] fields) {
		AsduStop asduStop = createDomain(fields);
		importDomain(asduStop);
		stopLoader.importArray(fields, new Long[] { asduStop.getId() });
	}*/

   @Override
   protected AsduStop getExistedDomain(AsduStop newDomain) {
	  return asduStopRepository.getByAsduStopId(newDomain.getAsduStopId());
   }
}
