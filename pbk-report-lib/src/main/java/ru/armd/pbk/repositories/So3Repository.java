package ru.armd.pbk.repositories;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.enums.core.AuditLevel;
import ru.armd.pbk.enums.core.AuditObjOperation;
import ru.armd.pbk.enums.core.ReportAuditType;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.mappers.report.standard.So3Mapper;
import ru.armd.pbk.views.report.So3View;

import java.util.List;

/**
 * Репозиторий стандартного отчёта "Количество бригад по местам встречи".
 */
@Repository
public class So3Repository
	extends SoRepository<BaseDomain> {

   public static final Logger LOGGER = Logger.getLogger(So3Repository.class);

   @Autowired
   So3Repository(So3Mapper mapper) {
	  super(ReportAuditType.REPORT_STANDARD_SO3, mapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @SuppressWarnings("unchecked")
   @Override
   public List<So3View> getGridViews(BaseGridViewParams params) {
	  List<So3View> views = null;
	  try {
		 views = getDomainMapper().getGridViews(params);
		 if (views != null) {
			for (So3View view : views) {
			   if (view.isAgree()) {
				  int taskCount = ((So3Mapper) getDomainMapper()).getBrigadeTaskCount(view.getId());
				  if (view.getBrigadeCount() < taskCount) {
					 view.setBrigadeType("есть свободные контролеры");
				  } else if (view.getBrigadeCount() > taskCount) {
					 view.setBrigadeType("неполная");
				  } else {
					 view.setBrigadeType("полная");
				  }
			   } else {
				  int diff = ((So3Mapper) getDomainMapper()).getDifferenceForBrigadeState(view.getDeptId(),
					  view.getDate(), view.getShiftId());
				  if (diff > 0) {
					 view.setBrigadeType("есть свободные контролеры (несогласованная)");
				  } else if (diff < 0) {
					 view.setBrigadeType("неполная (несогласованная)");
				  } else {
					 view.setBrigadeType("полная (несогласованная)");
				  }
			   }
			}
		 }
	  } catch (Exception e) {
		 String message = "Не удалось получить список gridView. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, params, message, e);
		 throw new PBKException(message, e);
	  }
	  return views;
   }
}
