package ru.armd.pbk.services.viss.asmpp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.core.services.BaseDomainService;
import ru.armd.pbk.core.views.BaseSelectListParams;
import ru.armd.pbk.core.views.ISelectItem;
import ru.armd.pbk.domain.viss.asmpp.AsmppStop;
import ru.armd.pbk.repositories.viss.asmpp.AsmppStopRepository;

import java.util.List;

/**
 * Сервис остановок АСМПП.
 */
@Service
public class AsmppStopsService
	extends BaseDomainService<AsmppStop, BaseDTO> {

   private AsmppStopRepository repository;

   @Autowired
   AsmppStopsService(AsmppStopRepository domainRepository) {
	  super(domainRepository);
	  this.repository = domainRepository;
   }

   /**
	* Получить список маршрутов.
	*
	* @param <SelectItem> тип элемента в возвращаемом списке.
	* @param <Params>     тип параметров.
	* @param params       параметры.
	* @return
	*/
   @Transactional
   public <SelectItem extends ISelectItem, Params extends BaseSelectListParams> List<SelectItem> getRouteSelectList(
	   Params params) {
	  List<SelectItem> selectItemList = repository.getRouteSelectList(params);
	  return selectItemList;
   }

   /**
	* Получить список выходов маршрутов.
	*
	* @param <SelectItem> тип элемента в возвращаемом списке.
	* @param <Params>     тип параметров.
	* @param params       параметры.
	* @return
	*/
   @Transactional
   public <SelectItem extends ISelectItem, Params extends BaseSelectListParams> List<SelectItem> getRouteMoveSelectList(
	   Params params) {
	  List<SelectItem> selectItemList = repository.getRouteMoveSelectList(params);
	  return selectItemList;
   }

   /**
	* Получить список рейсов маршрутов.
	*
	* @param <SelectItem> тип элемента в возвращаемом списке.
	* @param <Params>     тип параметров.
	* @param params       параметры.
	* @return
	*/
   @Transactional
   public <SelectItem extends ISelectItem, Params extends BaseSelectListParams> List<SelectItem> getRouteTripSelectList(
	   Params params) {
	  List<SelectItem> selectItemList = repository.getRouteTripSelectList(params);
	  return selectItemList;
   }

   /**
	* Получить список номеров рейсов маршрутов.
	*
	* @param <SelectItem> тип элемента в возвращаемом списке.
	* @param <Params>     тип параметров.
	* @param params       параметры.
	* @return
	*/
   @Transactional
   public <SelectItem extends ISelectItem, Params extends BaseSelectListParams> List<SelectItem> getRouteTripNumSelectList(
	   Params params) {
	  List<SelectItem> selectItemList = repository.getRouteTripNumSelectList(params);
	  return selectItemList;
   }
}
