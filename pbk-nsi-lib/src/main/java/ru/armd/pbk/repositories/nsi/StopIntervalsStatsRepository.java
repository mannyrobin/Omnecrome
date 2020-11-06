package ru.armd.pbk.repositories.nsi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.core.views.BaseSelectListParams;
import ru.armd.pbk.core.views.ISelectItem;
import ru.armd.pbk.domain.nsi.StopIntervalStat;
import ru.armd.pbk.enums.core.AuditLevel;
import ru.armd.pbk.enums.core.AuditObjOperation;
import ru.armd.pbk.enums.core.NsiAuditType;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.mappers.nsi.StopIntervalsStatsMapper;

import java.util.List;

/**
 * Репозиторий статистических данные для Интервалов.
 */
@Repository
public class StopIntervalsStatsRepository
	extends BaseDomainRepository<StopIntervalStat> {

   private StopIntervalsStatsMapper mapper;

   @Autowired
   StopIntervalsStatsRepository(StopIntervalsStatsMapper mapper) {
	  super(NsiAuditType.NSI_STOP_INTERVALS_STAT, mapper);
	  this.mapper = mapper;
   }

   /**
	* Получить список маршрутов.
	*
	* @param <Params>     тип параметров.
	* @param <SelectItem> тип элемента в списке.
	* @param params       параметры.
	* @return
	*/
   public <SelectItem extends ISelectItem, Params extends BaseSelectListParams> List<SelectItem> getRouteSelectList(Params params) {
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
	* Получить список парков.
	*
	* @param <Params>     тип параметров.
	* @param <SelectItem> тип элемента в списке.
	* @param params       параметры.
	* @return
	*/
   public <SelectItem extends ISelectItem, Params extends BaseSelectListParams> List<SelectItem> getParkSelectList(Params params) {
	  List<SelectItem> sList = null;
	  try {
		 sList = mapper.getParkSelectList(params.getFilter());
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
	* @param <Params>     тип параметров.
	* @param <SelectItem> тип элемента в списке.
	* @param params       параметры.
	* @return
	*/
   public <SelectItem extends ISelectItem, Params extends BaseSelectListParams> List<SelectItem> getRouteMoveSelectList(Params params) {
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
	* @param <Params>     тип параметров.
	* @param <SelectItem> тип элемента в списке.
	* @param params       параметры.
	* @return
	*/
   public <SelectItem extends ISelectItem, Params extends BaseSelectListParams> List<SelectItem> getRouteTripSelectList(Params params) {
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
	* Получить список остановок.
	*
	* @param <Params>     тип параметров.
	* @param <SelectItem> тип элемента в списке.
	* @param params       параметры.
	* @return
	*/
   public <SelectItem extends ISelectItem, Params extends BaseSelectListParams> List<SelectItem> getStopSelectList(Params params) {
	  List<SelectItem> sList = null;
	  try {
		 sList = mapper.getStopSelectList(params.getFilter());
	  } catch (Exception e) {
		 String message = "Не удалось получить список SelectItems. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, null, message, e);
		 throw new PBKException(message, e);
	  }
	  return sList;
   }
}
