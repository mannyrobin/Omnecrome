package ru.armd.pbk.mappers.viss.asdu;

import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.mappers.IDomainMapper;
import ru.armd.pbk.domain.viss.asdu.AsduPlanTrip;

import java.util.List;

/**
 * Маппер для нарядов ЕАСУ.
 */
@IsMapper
public interface AsduPlanTripMapper
	extends IDomainMapper<AsduPlanTrip> {

   /**
	* Множественная вставка объектов {@code list}
	* в таблицу.
	*
	* @param list объекты для вставки.
	*/
   void insertBulk(List<AsduPlanTrip> list);
}
