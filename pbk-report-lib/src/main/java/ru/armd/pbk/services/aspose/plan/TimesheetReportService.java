package ru.armd.pbk.services.aspose.plan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.aspose.core.IReportType;
import ru.armd.pbk.aspose.core.ReportData;
import ru.armd.pbk.aspose.core.ReportFormat;
import ru.armd.pbk.aspose.plan.TimesheetReportBeanData;
import ru.armd.pbk.mappers.plan.TimesheetReportMapper;
import ru.armd.pbk.repositories.nsi.department.DepartmentRepository;
import ru.armd.pbk.services.reports.AsposeReportsService;

import java.io.File;
import java.util.Date;
import java.util.Map;

@Service
public class TimesheetReportService
	extends AsposeReportsService {

   @Autowired
   private TimesheetReportMapper mapper;

   @Autowired
   private DepartmentRepository departmentRepository;

   public Map<ReportFormat, File> generate(IReportType type, Long deptId, Date dateFrom, Date dateTo) {
	  return generateReport(type,
		  new ReportData(new TimesheetReportBeanData(dateFrom, dateTo,
			  departmentRepository.getActual(deptId).getName(),
			  mapper.getTimesheetReportView(deptId, dateFrom, dateTo))));
   }

}
