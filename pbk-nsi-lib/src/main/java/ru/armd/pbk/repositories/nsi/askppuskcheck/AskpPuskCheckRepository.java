package ru.armd.pbk.repositories.nsi.askppuskcheck;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.core.views.BaseGridView;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.domain.nsi.askppuskcheck.AskpPuskCheck;
import ru.armd.pbk.enums.core.AuditLevel;
import ru.armd.pbk.enums.core.AuditObjOperation;
import ru.armd.pbk.enums.core.NsiAuditType;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.mappers.nsi.askppuskcheck.AskpPuskCheckMapper;

import java.util.List;

/**
 * Репозиторий данных от АСКП.
 */
@Repository
public class AskpPuskCheckRepository
	extends BaseDomainRepository<AskpPuskCheck> {

   public static final Logger LOGGER = Logger.getLogger(AskpPuskCheckRepository.class);

   private AskpPuskCheckMapper mapper;

   @Autowired
   AskpPuskCheckRepository(AskpPuskCheckMapper mapper) {
	  super(NsiAuditType.NSI_ASKP_PUSK_CHECK, mapper);
	  this.mapper = mapper;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   public <Views extends BaseGridView, Params extends BaseGridViewParams> List<Views> getGridViews(Params params) {
	  String isRouteOnly = "isRouteOnly";
	  String isHoursOnly = "isHoursOnly";
	  String isBindOnly = "isBindOnly";
	  List<Views> views = null;

	  try {
		 preapreParams(params);

		 if (params.getFilter().containsKey(isRouteOnly) && ((String) params.getFilter().get(isRouteOnly)).equals("1")) {
			views = mapper.getGridViewsByRoutes(params);
		 } else if (params.getFilter().containsKey(isRouteOnly) && ((String) params.getFilter().get(isRouteOnly)).equals("0")) {
			views = mapper.getGridViewsByRoutesDetails(params);
		 } else if (params.getFilter().containsKey(isHoursOnly) && ((String) params.getFilter().get(isHoursOnly)).equals("1")) {
			views = mapper.getGridViewsByHours(params);
		 } else if (params.getFilter().containsKey(isHoursOnly) && ((String) params.getFilter().get(isHoursOnly)).equals("0")) {
			views = mapper.getGridViewsByHoursDetails(params);
		 } else if (params.getFilter().containsKey(isBindOnly)) {
			views = mapper.getGridViewsByBind(params);
		 }
	  } catch (Exception e) {
		 String message = "Не удалось получить список gridView. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, params, message, e);
		 throw new PBKException(message, e);
	  }
	  return views;
   }

   /**
	* Привязать проверку ПУсК к заданию.
	*
	* @param taskId - ИД задания.
	* @return количество привзяных.
	*/
   public int bind(Long taskId) {
	  try {
		 return mapper.bind(taskId);
	  } catch (Exception e) {
		 String message = "Не удалось привязать проверку ПУсК к заданию. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.OTHER, null, message, e);
		 throw new PBKException(message, e);
	  }
   }

   /**
	* Подготовка параметров, для получения списка gridView.
	*
	* @param <Params> тип параметров.
	* @param params   параметры.
	*/
   private <Params extends BaseGridViewParams> void preapreParams(Params params) {
	  String startTimeFrom = "startTimeFrom";
	  String startTimeTo = "startTimeTo";
	  String endTimeFrom = "endTimeFrom";
	  String endTimeTo = "endTimeTo";

	  if (params.getFilter().containsKey(startTimeFrom)) {
		 String str = (String) params.getFilter().get(startTimeFrom);
		 params.getFilter().put(startTimeFrom, Integer.valueOf(str.substring(0, 2)) * 60 + Integer.valueOf(str.substring(3, 5)));
	  }
	  if (params.getFilter().containsKey(startTimeTo)) {
		 String str = (String) params.getFilter().get(startTimeTo);
		 params.getFilter().put(startTimeTo, Integer.valueOf(str.substring(0, 2)) * 60 + Integer.valueOf(str.substring(3, 5)));
	  }
	  if (params.getFilter().containsKey(endTimeFrom)) {
		 String str = (String) params.getFilter().get(endTimeFrom);
		 params.getFilter().put(endTimeFrom, Integer.valueOf(str.substring(0, 2)) * 60 + Integer.valueOf(str.substring(3, 5)));
	  }
	  if (params.getFilter().containsKey(endTimeTo)) {
		 String str = (String) params.getFilter().get(endTimeTo);
		 params.getFilter().put(endTimeTo, Integer.valueOf(str.substring(0, 2)) * 60 + Integer.valueOf(str.substring(3, 5)));
	  }
   }
}
