package ru.armd.pbk.components.viss.asdu.plantrip;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.components.viss.core.loaders.BaseCsvDomainLoader;
import ru.armd.pbk.domain.viss.asdu.AsduPlanTrip;
import ru.armd.pbk.enums.core.AuditType;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.repositories.viss.asdu.AsduPlanTripRepository;

import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

/**
 * Лоадер для водителей.
 */
@Component
public class AsduPlanTripLoader
	extends BaseCsvDomainLoader<AsduPlanTrip> {

   private static final Logger LOGGER = Logger.getLogger(AsduPlanTripLoader.class);

   private AsduPlanTripRepository repository;

   private List<AsduPlanTrip> cache = new LinkedList<AsduPlanTrip>();

   @Autowired
   AsduPlanTripLoader(AsduPlanTripRepository repository) {
	  super(repository);
	  this.repository = repository;
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
   protected AsduPlanTrip createDomain(String[] fields) {
	  // DATE;MR_CODE;DEPOT_NUMBER;SHIFT_NUM;ROUTE_NUM;ORDER_NUM;STOP_ID;ERM_ID;TRIP_ID;GRAFIC;
	  AsduPlanTrip plantrip = new AsduPlanTrip();
	  plantrip.setDate(getDateValue(fields[0], new SimpleDateFormat("yyyyMMdd")));
	  plantrip.setMrCode(getStringValue(fields[1]));
	  plantrip.setDepotNumber(getStringValue(fields[2]));
	  plantrip.setShiftNum(getLongValue(fields[3]));
	  plantrip.setRouteNum(getLongValue(fields[4]));
	  plantrip.setOrderNum(getLongValue(fields[5]));
	  plantrip.setStopId(getLongValue(fields[6]));
	  plantrip.setErmId(getLongValue(fields[7]));
	  plantrip.setTripId(getLongValue(fields[8]));
	  plantrip.setGrafic(getLongValue(fields[9]));

	  return plantrip;
   }

   @Override
   protected AsduPlanTrip getExistedDomain(AsduPlanTrip newDomain) {
	  return null;
   }

   @Override
   protected void doProcessFields(String[] fields) {
	  cache.add(createDomain(fields));
	  if (cache.size() > 100) {// 210 макисмум
		 repository.insertBulk(cache);
		 cache.clear();
	  }
   }
}
