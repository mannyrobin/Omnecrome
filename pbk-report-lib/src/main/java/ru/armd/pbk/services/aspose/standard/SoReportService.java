package ru.armd.pbk.services.aspose.standard;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.aspose.core.IReportType;
import ru.armd.pbk.aspose.core.ReportData;
import ru.armd.pbk.aspose.core.ReportFormat;
import ru.armd.pbk.aspose.report.standard.SoReportBeanData;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.core.services.IDomainService;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.exceptions.PBKAsposeReportException;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.services.*;
import ru.armd.pbk.services.reports.AsposeReportsService;
import ru.armd.pbk.utils.json.JsonGridData;
import ru.armd.pbk.views.report.SoView;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Сервис экспорта печатных форм стандартных отчётов.
 */
@Service
public class SoReportService
	extends AsposeReportsService {

   private static final Logger LOGGER = Logger.getLogger(SoReportService.class);

   private So1Service so1Service;
   private So2Service so2Service;
   private So3Service so3Service;
   private So4Service so4Service;
   private So5Service so5Service;
   private So6Service so6Service;
   private So7Service so7Service;
   private So8Service so8Service;
   private So9Service so9Service;
   private So10Service so10Service;
   private So11Service so11Service;
   private So12Service so12Service;
   private So13Service so13Service;
   private So14Service so14Service;
   private So15Service so15Service;
   private So16Service so16Service;
   private So17Service so17Service;
   private So18Service so18Service;
   private So19Service so19Service;
   private So20Service so20Service;
   private So21Service so21Service;
   private So22Service so22Service;
   private So23Service so23Service;
   private So24Service so24Service;
   private So25Service so25Service;

   /**
	* Конструктор по сервисам стандартных отчётов.
	*
	* @param so1Service  сервис отчёта 1
	* @param so2Service  сервис отчёта 2
	* @param so3Service  сервис отчёта 3
	* @param so4Service  сервис отчёта 4
	* @param so5Service  сервис отчёта 5
	* @param so6Service  сервис отчёта 6
	* @param so7Service  сервис отчёта 7
	* @param so8Service  сервис отчёта 8
	* @param so9Service  сервис отчёта 9
	* @param so10Service сервис отчёта 10
	* @param so11Service сервис отчёта 11
	* @param so12Service сервис отчёта 12
	* @param so13Service сервис отчёта 13
	* @param so14Service сервис отчёта 14
	* @param so15Service сервис отчёта 15
	* @param so16Service сервис отчёта 16
	* @param so17Service сервис отчёта 17
	* @param so18Service сервис отчёта 18
	* @param so19Service сервис отчёта 19
	*/
   @Autowired
   public SoReportService(So1Service so1Service,
						  So2Service so2Service,
						  So3Service so3Service,
						  So4Service so4Service,
						  So5Service so5Service,
						  So6Service so6Service,
						  So7Service so7Service,
						  So8Service so8Service,
						  So9Service so9Service,
						  So10Service so10Service,
						  So11Service so11Service,
						  So12Service so12Service,
						  So13Service so13Service,
						  So14Service so14Service,
						  So15Service so15Service,
						  So16Service so16Service,
						  So17Service so17Service,
						  So18Service so18Service,
						  So19Service so19Service,
						  So20Service so20Service,
						  So21Service so21Service,
						  So22Service so22Service,
						  So23Service so23Service,
						  So24Service so24Service,
						  So25Service so25Service
   ) {
	  this.so1Service = so1Service;
	  this.so2Service = so2Service;
	  this.so3Service = so3Service;
	  this.so4Service = so4Service;
	  this.so5Service = so5Service;
	  this.so6Service = so6Service;
	  this.so7Service = so7Service;
	  this.so8Service = so8Service;
	  this.so9Service = so9Service;
	  this.so10Service = so10Service;
	  this.so11Service = so11Service;
	  this.so12Service = so12Service;
	  this.so13Service = so13Service;
	  this.so14Service = so14Service;
	  this.so15Service = so15Service;
	  this.so16Service = so16Service;
	  this.so17Service = so17Service;
	  this.so18Service = so18Service;
	  this.so19Service = so19Service;
	  this.so20Service = so20Service;
	  this.so21Service = so21Service;
	  this.so22Service = so22Service;
	  this.so23Service = so23Service;
	  this.so24Service = so24Service;
	  this.so25Service = so25Service;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   protected Map<ReportFormat, File> generateReport(IReportType reportType, ReportData reportData)
	   throws PBKException {
	  return super.generateReport(reportType, reportData);
   }

   /**
	* Создать и заполнить печатную форму отчёта указанного типа по указанным параметрам в указанном формате.
	*
	* @param soNumber тип отчёта
	* @param params   параметры для выборки данных для отчёта
	* @param type     формат файла отчёта
	* @return набор пар "формат отчёта" - "файл отчёта"
	* @throws PBKAsposeReportException
	*/
   public Map<ReportFormat, File> generate(Integer soNumber, BaseGridViewParams params, IReportType type)
	   throws PBKAsposeReportException {
	  IDomainService<?, ?> service = getServiceBySoNumber(soNumber);
	  if (service instanceof SoService<?, ?>) {
		 JsonGridData views = getServiceBySoNumber(soNumber).getGridViews(params);
		 return generateReport(type, new ReportData(new SoReportBeanData(soNumber, (List<SoView>) views.getRows(), (SoView) ((SoService<?, ?>) service).getGridViewTotal(params.getFilter()))));
	  }
	  JsonGridData views = getServiceBySoNumber(soNumber).getGridViews(params);
	  return generateReport(type, new ReportData(new SoReportBeanData(soNumber, (List<SoView>) views.getRows())));
   }

   private IDomainService<BaseDomain, BaseDTO> getServiceBySoNumber(Integer number) {
	  switch (number) {
		 case 1:
			return so1Service;
		 case 2:
			return so2Service;
		 case 3:
			return so3Service;
		 case 4:
			return so4Service;
		 case 5:
			return so5Service;
		 case 6:
			return so6Service;
		 case 7:
			return so7Service;
		 case 8:
			return so8Service;
		 case 9:
			return so9Service;
		 case 10:
			return so10Service;
		 case 11:
			return so11Service;
		 case 12:
			return so12Service;
		 case 13:
			return so13Service;
		 case 14:
			return so14Service;
		 case 15:
			return so15Service;
		 case 16:
			return so16Service;
		 case 17:
			return so17Service;
		 case 18:
			return so18Service;
		 case 19:
			return so19Service;
		 case 20:
			return so20Service;
		 case 21:
			return so21Service;
		 case 22:
			return so22Service;
		 case 23:
			return so23Service;
		 case 24:
			return so24Service;
		 case 25:
		    return so25Service;
		  default:
			return null;
	  }
   }

}
