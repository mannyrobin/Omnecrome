package ru.armd.pbk.repositories.viss.asmpp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.core.views.BaseSelectListParams;
import ru.armd.pbk.core.views.ISelectItem;
import ru.armd.pbk.domain.viss.asmpp.AsmppStop;
import ru.armd.pbk.enums.core.AuditLevel;
import ru.armd.pbk.enums.core.AuditObjOperation;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.mappers.viss.asmpp.AsmppStopMapper;

import java.util.List;

/**
 * Репозиторий остановок АСМПП.
 */
@Repository
public class AsmppStopRepository
	extends BaseDomainRepository<AsmppStop> {

   private AsmppStopMapper mapper;

   @Autowired
   AsmppStopRepository(AsmppStopMapper mapper) {
	  super(VisAuditType.VIS_ASMPP_STOPS, mapper);
	  this.mapper = mapper;
   }

   /**
	* Получить список маршрутов.
	*
	* @param <SelectItem> тип элемента в возвращаемом списке.
	* @param <Params>     тип параметров.
	* @param params       параметры.
	* @return
	*/
   public <SelectItem extends ISelectItem, Params extends BaseSelectListParams> List<SelectItem> getRouteSelectList(
	   Params params) {
	  List<SelectItem> sList = null;
	  try {
		 sList = mapper.getRouteSelectList(params.getFilter());
	  } catch (Exception e) {
		 String message = "Не удалось получить список SelectItems. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, null, message, e);
		 throw new PBKException(message, e);
	  }
	  return sList;
   }

   /**
	* Получить список выходов маршрутов.
	*
	* @param <SelectItem> тип элемента в возвращаемом списке.
	* @param <Params>     тип параметров.
	* @param params       параметры.
	* @return
	*/
   public <SelectItem extends ISelectItem, Params extends BaseSelectListParams> List<SelectItem> getRouteMoveSelectList(
	   Params params) {
	  List<SelectItem> sList = null;
	  try {
		 sList = mapper.getRouteMoveSelectList(params.getFilter());
	  } catch (Exception e) {
		 String message = "Не удалось получить список SelectItems. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, null, message, e);
		 throw new PBKException(message, e);
	  }
	  return sList;
   }

   /**
	* Получить список рейсов маршрутов.
	*
	* @param <SelectItem> тип элемента в возвращаемом списке.
	* @param <Params>     тип параметров.
	* @param params       параметры.
	* @return
	*/
   public <SelectItem extends ISelectItem, Params extends BaseSelectListParams> List<SelectItem> getRouteTripSelectList(
	   Params params) {
	  List<SelectItem> sList = null;
	  try {
		 sList = mapper.getRouteTripSelectList(params.getFilter());
	  } catch (Exception e) {
		 String message = "Не удалось получить список SelectItems. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, null, message, e);
		 throw new PBKException(message, e);
	  }
	  return sList;
   }

   /**
	* Получить список номеров рейсов маршрутов.
	*
	* @param <SelectItem> тип элемента в возвращаемом списке.
	* @param <Params>     тип параметров.
	* @param params       параметры.
	* @return
	*/
   public <SelectItem extends ISelectItem, Params extends BaseSelectListParams> List<SelectItem> getRouteTripNumSelectList(
	   Params params) {
	  List<SelectItem> sList = null;
	  try {
		 sList = mapper.getRouteTripNumSelectList(params.getFilter());
	  } catch (Exception e) {
		 String message = "Не удалось получить список SelectItems. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, null, message, e);
		 throw new PBKException(message, e);
	  }
	  return sList;
   }
}
