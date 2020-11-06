package ru.armd.pbk.mappers.viss.asmpp;

import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.mappers.IDomainMapper;
import ru.armd.pbk.core.views.ISelectItem;
import ru.armd.pbk.domain.viss.asmpp.AsmppStop;

import java.util.List;
import java.util.Map;

/**
 * Маппер остановок АСМПП.
 */
@IsMapper
public interface AsmppStopMapper
	extends IDomainMapper<AsmppStop> {

   /**
	* Получить список маршрутов из АСМПП.
	*
	* @param <SelectItem> тип возращаемого элемента.
	* @param params       параметры.
	* @return
	*/
   <SelectItem extends ISelectItem> List<SelectItem> getRouteSelectList(Map<String, Object> params);

   /**
	* Получить список выходов маршрутов из АСМПП.
	*
	* @param <SelectItem> тип возращаемого элемента.
	* @param params       параметры.
	* @return
	*/
   <SelectItem extends ISelectItem> List<SelectItem> getRouteMoveSelectList(Map<String, Object> params);

   /**
	* Получить список рейсов маршрутов из АСМПП.
	*
	* @param <SelectItem> тип возращаемого элемента.
	* @param params       параметры.
	* @return
	*/
   <SelectItem extends ISelectItem> List<SelectItem> getRouteTripSelectList(Map<String, Object> params);

   /**
	* Получить список номеров рейсов маршрутов из АСМПП.
	*
	* @param <SelectItem> тип возращаемого элемента.
	* @param params       параметры.
	* @return
	*/
   <SelectItem extends ISelectItem> List<SelectItem> getRouteTripNumSelectList(Map<String, Object> params);

}
