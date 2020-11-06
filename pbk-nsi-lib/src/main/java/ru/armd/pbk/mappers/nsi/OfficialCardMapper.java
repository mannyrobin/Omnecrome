package ru.armd.pbk.mappers.nsi;

import org.apache.ibatis.annotations.Param;
import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.mappers.IDeviceOwnersHistoryMapper;
import ru.armd.pbk.core.views.ISelectItem;
import ru.armd.pbk.domain.nsi.OfficialCard;

import java.util.List;
import java.util.Map;

/**
 * Мапер для операций с ССК.
 */
@IsMapper
public interface OfficialCardMapper
	extends IDeviceOwnersHistoryMapper<OfficialCard> {

   /**
	* Получение списка ССК для отображкения в комбобоксах сотрудников.
	*
	* @param params Параметры фильтрации.
	* @return Список ССК.
	*/
   List<ISelectItem> getSelectItemsForEmployee(Map<String, Object> params);

   /**
	* Обновить сотрудников при удалении устройства.
	*
	* @param offCardId ID
	*/
   void updateWithEmployee(@Param("offCardId") Long offCardId);
}
