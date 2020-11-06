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
import ru.armd.pbk.mappers.report.standard.So8Mapper;
import ru.armd.pbk.views.report.So8View;

import java.util.List;

/**
 * Репозиторий стандартного отчёта "Работа контролеров на маршруте".
 */
@Repository
public class So8Repository
	extends SoRepository<BaseDomain> {

   public static final Logger LOGGER = Logger.getLogger(So8Repository.class);

   @Autowired
   private So8Mapper so8Mapper;

   @Autowired
   So8Repository(So8Mapper mapper) {
	  super(ReportAuditType.REPORT_STANDARD_SO8, mapper);
   }

   /**
	* Получает view отчёта "Работа контролеров на маршруте" по параметрам.
	*
	* @param params параметры
	* @return view отчёта
	*/
   @DepartmentAuthorization
   public List<So8View> getGridViews(BaseGridViewParams params) {
	  List<So8View> so8Views = null;
	  try {
		 so8Views = so8Mapper.getGridViews(params);
	  } catch (Exception e) {
		 String message = "Не удалось получить список для отображения расписания. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, ReportAuditType.REPORT_STANDARD_SO8, AuditObjOperation.SELECT, params, message, e);
		 throw new PBKException(message, e);
	  }
	  return so8Views;
   }
}
