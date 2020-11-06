package ru.armd.pbk.repositories;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.authtoken.DepartmentAuthorization;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.core.views.BaseGridView;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.enums.core.AuditLevel;
import ru.armd.pbk.enums.core.AuditObjOperation;
import ru.armd.pbk.enums.core.ReportAuditType;
import ru.armd.pbk.enums.core.Settings;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.mappers.core.SettingsMapper;
import ru.armd.pbk.mappers.report.standard.So10Mapper;

import java.util.LinkedList;
import java.util.List;

/**
 * Репозиторий стандартного отчёта "Сводная форма по эффективности работы контролеров".
 */
@Repository
public class So10Repository
	extends BaseDomainRepository<BaseDomain> {

   public static final Logger LOGGER = Logger.getLogger(So10Repository.class);

   private So10Mapper mapper;

   @Autowired
   private SettingsMapper settingsMapper;

   @Autowired
   So10Repository(So10Mapper mapper) {
	  super(ReportAuditType.REPORT_STANDARD_SO10, mapper);
	  this.mapper = mapper;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   @DepartmentAuthorization
   public List<BaseGridView> getGridViews(BaseGridViewParams params) {

	  List<BaseGridView> views = null;
	  try {
		 views = getReportView(params);
		 views.get(0).setCnt(6L);
	  } catch (Exception e) {
		 String message = "Не удалось получить список gridView. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, params, message, e);
		 throw new PBKException(message, e);
	  }

	  return views;
   }

   protected List<BaseGridView> getReportView(BaseGridViewParams params) {
	  Boolean withVesb = settingsMapper.getById(Settings.REPORT_WITH_VESB.getId()).getValue().equals("Да");
	  List<BaseGridView> views = new LinkedList<BaseGridView>();

	  views.add(mapper.getFirstRow(params));
	  views.add(withVesb ? mapper.getSecondRowWithVesb(params) : mapper.getSecondRow(params));
	  views.add(withVesb ? mapper.getThridRowWithVesb(params) : mapper.getThridRow(params));
	  views.add(withVesb ? mapper.getFourthRowWithVesb(params) : mapper.getFourthRow(params));
	  views.add(mapper.getFifthRow(params));
	  views.add(mapper.getSixthRow(params));

	  return views;
   }

}
