package ru.armd.pbk.services.nsi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.core.services.BaseDomainService;
import ru.armd.pbk.core.views.BaseSelectListParams;
import ru.armd.pbk.core.views.ISelectItem;
import ru.armd.pbk.domain.nsi.StopIntervalStat;
import ru.armd.pbk.repositories.nsi.StopIntervalsStatsRepository;

import java.util.List;

/**
 * Сервис статистических данные для Интервалов.
 */
@Service
public class StopIntervalsStatsService
	extends BaseDomainService<StopIntervalStat, BaseDTO> {

   private StopIntervalsStatsRepository repository;

   @Autowired
   StopIntervalsStatsService(StopIntervalsStatsRepository domainRepository) {
	  super(domainRepository);
	  this.repository = domainRepository;
   }

   /**
	* Получить список маршрутов.
	*
	* @param <Params>     тип параметров.
	* @param <SelectItem> тип элемента в списке.
	* @param params       параметры.
	* @return
	*/
   @Transactional
   public <SelectItem extends ISelectItem, Params extends BaseSelectListParams> List<SelectItem> getRouteSelectList(Params params) {
	  List<SelectItem> selectItemList = repository.getRouteSelectList(params);
	  return selectItemList;
   }

   /**
	* Получить список парков.
	*
	* @param <Params>     тип параметров.
	* @param <SelectItem> тип элемента в списке.
	* @param params       параметры.
	* @return
	*/
   @Transactional
   public <SelectItem extends ISelectItem, Params extends BaseSelectListParams> List<SelectItem> getParkSelectList(Params params) {
	  List<SelectItem> selectItemList = repository.getParkSelectList(params);
	  return selectItemList;
   }

   /**
	* Получить список выходов маршрутов.
	*
	* @param <Params>     тип параметров.
	* @param <SelectItem> тип элемента в списке.
	* @param params       параметры.
	* @return
	*/
   @Transactional
   public <SelectItem extends ISelectItem, Params extends BaseSelectListParams> List<SelectItem> getRouteMoveSelectList(Params params) {
	  List<SelectItem> selectItemList = repository.getRouteMoveSelectList(params);
	  return selectItemList;
   }

   /**
	* Получить список рейсов маршрутов.
	*
	* @param <Params>     тип параметров.
	* @param <SelectItem> тип элемента в списке.
	* @param params       параметры.
	* @return
	*/
   @Transactional
   public <SelectItem extends ISelectItem, Params extends BaseSelectListParams> List<SelectItem> getRouteTripSelectList(Params params) {
	  List<SelectItem> selectItemList = repository.getRouteTripSelectList(params);
	  return selectItemList;
   }

   /**
	* Получить список остановок.
	*
	* @param <Params>     тип параметров.
	* @param <SelectItem> тип элемента в списке.
	* @param params       параметры.
	* @return
	*/
   @Transactional
   public <SelectItem extends ISelectItem, Params extends BaseSelectListParams> List<SelectItem> getStopSelectList(Params params) {
	  List<SelectItem> selectItemList = repository.getStopSelectList(params);
	  return selectItemList;
   }

}
