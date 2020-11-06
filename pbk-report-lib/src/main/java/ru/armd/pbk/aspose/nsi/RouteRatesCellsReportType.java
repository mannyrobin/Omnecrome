package ru.armd.pbk.aspose.nsi;

import ru.armd.pbk.aspose.core.AbstractReportProcessor;
import ru.armd.pbk.aspose.core.AbstractReportType;
import ru.armd.pbk.aspose.core.IReportType;
import ru.armd.pbk.aspose.core.ReportFormat;
import ru.armd.pbk.aspose.plan.RouteRateCellReportProcessor;

/**
 * Created by Yakov Volkov.
 */
public class RouteRatesCellsReportType
	extends AbstractReportType
	implements IReportType {

   public RouteRatesCellsReportType() {
	  super("pbk_route_rate.xlsx", "Рейтинги маршрутов ",
		  "RT_TEMP_Pbk_Route_Rate.xlsx", "pbk_route_rate",
		  ReportFormat.XLSX, ReportFormat.XLSX);
   }

   @Override
   public AbstractReportProcessor instantiateProcessor() {
	  return new RouteRateCellReportProcessor();
   }

}
