package ru.armd.pbk.repositories.askp;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.core.views.BaseGridView;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.enums.core.ReportAuditType;
import ru.armd.pbk.mappers.askp.AskpReportByStopMapper;
import ru.armd.pbk.repositories.So11Repository;
import ru.armd.pbk.views.askp.AskpByRouteView;
import ru.armd.pbk.views.askp.AskpByStopView;
import ru.armd.pbk.views.askp.AskpReportByStopView;

import java.util.LinkedList;
import java.util.List;

/**
 * Репозиторий АСКП по остановкам.
 */
@Repository
public class AskpReportByStopRepository
	extends BaseDomainRepository<BaseDomain> {

   public static final Logger LOGGER = Logger.getLogger(So11Repository.class);

   @Autowired
   AskpReportByStopRepository(AskpReportByStopMapper mapper) {
	  super(ReportAuditType.ASKP_REPORT_BY_STOP, mapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @SuppressWarnings("unchecked")
   @Override
   public <Views extends BaseGridView, Params extends BaseGridViewParams> List<Views> getGridViews(Params params) {
	  List<AskpByStopView> result = new LinkedList<AskpByStopView>();
	  if (!params.getFilter().containsKey("stopIds")) {
		 return (List<Views>) result;
	  }
	  List<AskpReportByStopView> views = super.getGridViews(params);
	  if (views.size() > 0) {
		 AskpReportByStopView prev = views.get(0);
		 List<AskpByRouteView> routes = new LinkedList<AskpByRouteView>();
		 for (AskpReportByStopView view : views) {
			if (!view.getStopId().equals(prev.getStopId())) {
			   result.add(createStopView(prev, new LinkedList<AskpByRouteView>(routes)));
			   routes.clear();
			}
			routes.add(createRouteView(view));
			prev = view;
		 }
		 result.add(createStopView(prev, new LinkedList<AskpByRouteView>(routes)));
	  }
	  if (result.size() > 0) {
		 result.get(0).setCnt(Long.valueOf(result.size()));
	  }
	  return (List<Views>) result;
   }

   private AskpByStopView createStopView(AskpReportByStopView view, List<AskpByRouteView> routes) {
	  AskpByStopView result = new AskpByStopView();
	  result.setStopId(view.getStopId());
	  result.setStopName(view.getStopName());
	  routes.add(createTotalRouteView(routes));
	  result.setRoutes(routes);
	  return result;
   }

   private AskpByRouteView createRouteView(AskpReportByStopView view) {
	  AskpByRouteView result = new AskpByRouteView();
	  result.setRouteNumber(view.getRouteNumber());
	  result.setTicketCount(view.getTicketCount());
	  result.setTicketCountFree(view.getTicketCountFree());
	  result.setTicketCountPaid(view.getTicketCountPaid());
	  return result;
   }

   private AskpByRouteView createTotalRouteView(List<AskpByRouteView> views) {
	  AskpByRouteView result = new AskpByRouteView();
	  result.setRouteNumber("Итого");
	  for (AskpByRouteView view : views) {
		 result.setTicketCount(result.getTicketCount() + view.getTicketCount());
		 result.setTicketCountFree(result.getTicketCountFree() + view.getTicketCountFree());
		 result.setTicketCountPaid(result.getTicketCountPaid() + view.getTicketCountPaid());
	  }
	  return result;
   }
}
