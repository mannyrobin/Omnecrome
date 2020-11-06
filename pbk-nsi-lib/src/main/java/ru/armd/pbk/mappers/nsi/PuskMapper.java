package ru.armd.pbk.mappers.nsi;

import org.apache.ibatis.annotations.Param;
import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.mappers.IDeviceOwnersHistoryMapper;
import ru.armd.pbk.core.views.ISelectItem;
import ru.armd.pbk.domain.nsi.Pusk;

import java.util.List;
import java.util.Map;

/**
 * Мапер для операций с ПУсКами.
 */
@IsMapper
public interface PuskMapper
	extends IDeviceOwnersHistoryMapper<Pusk> {

   /**
	* Получение списка ПУсКов для отображкения в комбобоксах сотрудников.
	*
	* @param params Параметры фильтрации.
	* @return Список ПУсКов.
	*/
   List<ISelectItem> getSelectItemsForEmployee(Map<String, Object> params);

   /**
	* Обновить сотрудников при удалении устройства.
	*
	* @param puskId ID
	*/
   void updateWithEmployee(@Param("puskId") Long puskId);
}
