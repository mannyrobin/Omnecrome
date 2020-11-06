package ru.armd.pbk.repositories;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.authtoken.DepartmentAuthorization;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.enums.core.AuditLevel;
import ru.armd.pbk.enums.core.AuditObjOperation;
import ru.armd.pbk.enums.core.ReportAuditType;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.mappers.report.standard.So6Mapper;
import ru.armd.pbk.views.report.So6View;

import java.util.List;

/**
 * Репозиторий стандартного отчёта "Результаты контроля (мотивация)".
 */
@Repository
public class So6Repository
	extends SoRepository<BaseDomain> {

   public static final Logger LOGGER = Logger.getLogger(So6Repository.class);

   @Autowired
   private So6Mapper so6Mapper;

   @Autowired
   So6Repository(So6Mapper mapper) {
	  super(ReportAuditType.REPORT_STANDARD_SO6, mapper);
   }

   /**
	* Получает список view отчёта "Результаты контроля (мотивация)" по параметрам.
	*
	* @param params параметры
	* @return список view отчёта
	*/
   @DepartmentAuthorization
   public List<So6View> getGridViews(BaseGridViewParams params) {
	  List<So6View> so6Views = null;
	  try {
		 so6Views = so6Mapper.getGridViews(params);
	  } catch (Exception e) {
		 String message = "Не удалось получить список для отображения расписания. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, ReportAuditType.REPORT_STANDARD_SO6, AuditObjOperation.SELECT, params, message, e);
		 throw new PBKException(message, e);
	  }
	  return so6Views;
   }
}
