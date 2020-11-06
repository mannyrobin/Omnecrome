package ru.armd.pbk.components.viss.asdu.trip;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.components.viss.core.loaders.BaseCsvDomainLoader;
import ru.armd.pbk.domain.viss.asdu.AsduTrip;
import ru.armd.pbk.enums.core.AuditType;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.repositories.viss.asdu.AsduTripRepository;

/**
 * Лоадер для водителей.
 */
@Component
public class AsduTripLoader
	extends BaseCsvDomainLoader<AsduTrip> {

   private static final Logger LOGGER = Logger.getLogger(AsduTripLoader.class);

   private AsduTripRepository asduTripRepository;

   @Autowired
   AsduTripLoader(AsduTripRepository asduTripRepository) {
	  super(asduTripRepository);
	  this.asduTripRepository = asduTripRepository;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   public AuditType getAuditType() {
	  return VisAuditType.VIS_ASDU_TRIP;
   }

   @Override
   protected AsduTrip createDomain(String[] fields) {
	  // ROUTE_ID;SERVICE_ID;TRIP_ID;TRIP_HEADSIGN;TRIP_SHORT_NAME;DIRECTION_ID;BLOCK_ID;SHAPE_ID;WHEELCHAIR_ACCESSIBLE;ROUTE_VAR;
	  AsduTrip trip = new AsduTrip();
	  trip.setAsduRouteId(getLongValue(fields[0]));
	  trip.setServiceId(getLongValue(fields[1]));
	  trip.setAsduTripId(getLongValue(fields[2]));
	  trip.setTripHeadsign(getStringValue(fields[3]));
	  trip.setTripShortName(getStringValue(fields[4]));
	  trip.setDirectionId(getLongValue(fields[5]));
	  trip.setBlockId(getLongValue(fields[6]));
	  trip.setShapeId(getLongValue(fields[7]));
	  trip.setWheelchairAccessible(getStringValue(fields[8]));
	  trip.setRouteVar(getStringValue(fields[9]));

	  return trip;
   }

   @Override
   protected AsduTrip getExistedDomain(AsduTrip newDomain) {
	  return asduTripRepository.getByAsduTripId(newDomain.getAsduTripId());
   }
}
