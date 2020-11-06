package ru.armd.pbk.repositories.unionanalysis;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.core.views.BaseGridView;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.enums.core.ReportAuditType;
import ru.armd.pbk.enums.nsi.WorkDayType;
import ru.armd.pbk.mappers.unionanalysis.UnionAnalysisByStopMapper;
import ru.armd.pbk.views.unionanalysis.PassengersCountByWorkModeView;
import ru.armd.pbk.views.unionanalysis.UnionAnalysisByStopView;
import ru.armd.pbk.views.unionanalysis.UnionByStopView;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Репозиторий сводного анализа по остановкам.
 */
@Repository
public class UnionAnalysisByStopRepository
	extends BaseDomainRepository<BaseDomain> {

   public static final Logger LOGGER = Logger.getLogger(UnionAnalysisByStopRepository.class);

   @Autowired
   UnionAnalysisByStopRepository(UnionAnalysisByStopMapper mapper) {
	  super(ReportAuditType.UNION_ANALYSIS_BY_STOP, mapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @SuppressWarnings("unchecked")
   @Override
   public <Views extends BaseGridView, Params extends BaseGridViewParams> List<Views> getGridViews(Params params) {
	  if (params.getFilter().get("routeId") == null) {
		 return (List<Views>) new LinkedList<UnionAnalysisByStopView>();
	  }
	  LinkedHashMap<String, UnionAnalysisByStopView> cache = new LinkedHashMap<String, UnionAnalysisByStopView>();
	  List<UnionByStopView> views = super.getGridViews(params);
	  for (UnionByStopView view : views) {
		 processView(cache, view);
	  }
	  List<UnionAnalysisByStopView> result = new LinkedList<UnionAnalysisByStopView>(cache.values());
	  if (result.size() > 0) {
		 result.get(0).setCnt(Long.valueOf(result.size()));
	  }
	  return (List<Views>) result;

   }


   private void processView(LinkedHashMap<String, UnionAnalysisByStopView> cache, UnionByStopView view) {
	  String key = getKey(view);
	  UnionAnalysisByStopView value = cache.containsKey(key) ? cache.get(key) : createUnionAnalysisByStopView(view);
	  if (WorkDayType.WORK_DAY.getId().equals(view.getWorkDayTypeId())) {
		 value.setWorkday(createPassengerView(view));
	  } else {
		 value.setHoliday(createPassengerView(view));
	  }
	  cache.put(key, value);
   }

   private UnionAnalysisByStopView createUnionAnalysisByStopView(UnionByStopView view) {
	  UnionAnalysisByStopView result = new UnionAnalysisByStopView();
	  result.setCnt(view.getCnt());
	  result.setDirection(view.getDirection());
	  result.setRouteVariant(view.getTripId());
	  result.setOrderNum(view.getStopSequence());
	  result.setStopName(view.getStopName());
	  return result;
   }

   private PassengersCountByWorkModeView createPassengerView(UnionByStopView view) {
	  PassengersCountByWorkModeView result = new PassengersCountByWorkModeView();
	  result.setAskpPassengersCount(view.getAskpPassengersCount());
	  result.setAsmppPassengersIncludedCount(view.getAsmppPassengersIncludedCount());
	  result.setAsmppPassengersLeftCount(view.getAsmppPassengersLeftCount());
	  result.setAsmppPassengersTransportedCount(view.getAsmppPassengersTransportedCount());
	  return result;
   }

   private String getKey(UnionByStopView view) {
	  return String.format("%d:%d:%s:%d:%d", view.getRouteId(), view.getTripId()
		  , view.getDirection(), view.getStopSequence(), view.getStopId());
   }
}
