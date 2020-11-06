package ru.armd.pbk.components.viss.asmpp.loaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.components.viss.core.loaders.BaseCsvDomainLoader;
import ru.armd.pbk.components.viss.core.loaders.BaseCsvMultipleDomainLoader;
import ru.armd.pbk.core.repositories.IDomainRepository;
import ru.armd.pbk.domain.dvr.DvrRecord;
import ru.armd.pbk.domain.nsi.Equipment;
import ru.armd.pbk.domain.nsi.Ticket;
import ru.armd.pbk.domain.viss.asmpp.AsmppStop;
import ru.armd.pbk.repositories.viss.asmpp.AsmppStopRepository;
import ru.armd.pbk.utils.ImportResult;
import ru.armd.pbk.utils.date.DateUtils;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Загрузчик для остановок из АСМПП.
 */
@Component
public class AsmppStopLoader
	extends BaseCsvDomainLoader<AsmppStop> {

   private static final Integer CACHE_COUNT = 100;
   private DateFormat df = new SimpleDateFormat("HH:mm:ss");
	private DateFormat workDateDf = new SimpleDateFormat("yyyy-MM-dd");

   @Autowired
   private AsmppStopRepository asmppStopRepository;

   @Autowired
   AsmppStopLoader(IDomainRepository<AsmppStop> domainRepository) {
	  super(domainRepository, true);
   }

   @Override
   protected AsmppStop createDomain(String[] fields) {
	  AsmppStop domain = new AsmppStop();
	  domain.setWorkDate(getDateValue(fields[0], workDateDf));
	  domain.setRouteId(getIntegerValue(fields[2]));
	  domain.setRouteNum(getIntegerValue(fields[5]));
	  domain.setGrafic(getIntegerValue(fields[6]));
	  domain.setDirection(getStringValue(fields[7]));
	  domain.setStopSequence(getIntegerValue(fields[8]));
	  domain.setStopId(getIntegerValue(fields[9]));
	  domain.setStopName(getStringValue(fields[10]));
	  domain.setTime(getDateValue(fields[11], df));
	  domain.setPassengersincluded(getIntegerValue(fields[12]));
	  domain.setPassengersleft(getIntegerValue(fields[13]));
	  domain.setTransported(getIntegerValue(fields[14]));
	  domain.setTripId(getIntegerValue(fields[15]));
	  domain.setDepotId(getIntegerValue(fields[16]));
	  return domain;
   }

	@Override
	protected AsmppStop getExistedDomain(AsmppStop newDomain) {
		List<AsmppStop> domains = asmppStopRepository.getDomains(getRecordParams(newDomain));
		if (domains.size() > 0) {
			return domains.get(0);
		} else return null;
	}

	protected Map<String, Object> getRecordParams(AsmppStop newDomain) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("workDate", newDomain.getWorkDate());
		params.put("routeId", newDomain.getRouteId());
		params.put("routeNum", newDomain.getRouteNum());
		params.put("grafic", newDomain.getGrafic());
		params.put("direction", newDomain.getDirection());
		params.put("stopSequence", newDomain.getStopSequence());
		params.put("stopId", newDomain.getStopId());
		params.put("stopName", newDomain.getStopName());
		params.put("passengersIncluded", newDomain.getPassengersincluded());
		params.put("passengersLeft", newDomain.getPassengersleft());
		params.put("transported", newDomain.getTransported());
		params.put("tripId", newDomain.getTripId());
		params.put("depotId", newDomain.getDepotId());
		params.put("time", newDomain.getTime());
		return params;
	}

	@Override
	protected AsmppStop importDomain(AsmppStop newDomain) {
		AsmppStop existedDomain = getExistedDomain(newDomain);
		if (existedDomain == null) {
			return save(newDomain);
		} else {
			if (!existedDomain.equals(newDomain)) {
				updateDomain(newDomain, existedDomain);
				return save(newDomain);
			}
		}
		return null;
	}
}
