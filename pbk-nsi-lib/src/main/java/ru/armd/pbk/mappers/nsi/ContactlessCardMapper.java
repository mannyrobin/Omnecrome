package ru.armd.pbk.mappers.nsi;

import org.apache.ibatis.annotations.Param;
import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.mappers.IDeviceOwnersHistoryMapper;
import ru.armd.pbk.core.views.ISelectItem;
import ru.armd.pbk.domain.nsi.ContactlessCard;

import java.util.List;
import java.util.Map;

/**
 * Мапер для операций с БСК.
 */
@IsMapper
public interface ContactlessCardMapper
	extends IDeviceOwnersHistoryMapper<ContactlessCard> {

   /**
	* Получение списка БСК для отображкения в комбобоксах сотрудников.
	*
	* @param params Параметры фильтрации.
	* @return Список БСК.
	*/
   List<ISelectItem> getSelectItemsForEmployee(Map<String, Object> params);

   /**
	* Обновить сотрудников при удалении устройства.
	*
	* @param contCardId ID
	*/
   void updateWithEmployee(@Param("contCardId") Long contCardId);
}
