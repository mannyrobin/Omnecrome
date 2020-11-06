package ru.armd.pbk.services.aspose.nsi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.aspose.core.IReportType;
import ru.armd.pbk.aspose.core.ReportData;
import ru.armd.pbk.aspose.core.ReportFormat;
import ru.armd.pbk.aspose.plan.RouteRateReportBeanData;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.exceptions.PBKAsposeReportException;
import ru.armd.pbk.matcher.plans.PlanRouteMatcher;
import ru.armd.pbk.repositories.plans.routes.PlanRouteRepository;
import ru.armd.pbk.services.IReportService;
import ru.armd.pbk.services.reports.AsposeReportsService;
import ru.armd.pbk.views.plans.routes.PlanRouteView;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Yakov Volkov.
 */
@Service
public class RouteRateReportService
	extends AsposeReportsService
	implements IReportService {

   @Autowired
   private PlanRouteRepository repository;

   @Override
   public Map<ReportFormat, File> generate(IReportType type, Map<String, Object> params)
	   throws PBKAsposeReportException {
	  BaseGridViewParams baseGridViewParams = new BaseGridViewParams(1L, Long.MAX_VALUE,
		  (String) params.get("sidx"), params.containsKey("sord") ? (String) params.get("sord") : "asc", params);

	  List<PlanRouteView> planRouteViews = PlanRouteMatcher.INSTANCE.getPlanRouteViews(repository.getPlanRoutes(baseGridViewParams));

	  Date startDate = (Date) baseGridViewParams.getFilter().get("dateFrom");
	  Date endDate = (Date) baseGridViewParams.getFilter().get("dateTo");
	  return generateReport(type, new ReportData(new RouteRateReportBeanData(planRouteViews, startDate, endDate)));
   }
}
