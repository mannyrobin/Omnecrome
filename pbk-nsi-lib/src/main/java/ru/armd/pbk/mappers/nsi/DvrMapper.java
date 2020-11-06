package ru.armd.pbk.mappers.nsi;

import org.apache.ibatis.annotations.Param;
import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.mappers.IDeviceOwnersHistoryMapper;
import ru.armd.pbk.core.views.ISelectItem;
import ru.armd.pbk.domain.nsi.Dvr;

import java.util.List;
import java.util.Map;

/**
 * Маппер видеорегистраторов.
 */
@IsMapper
public interface DvrMapper
	extends IDeviceOwnersHistoryMapper<Dvr> {

   /**
	* Получение списка видеорегистраторов для отображкения в комбобоксах сотрудников.
	*
	* @param params Параметры фильтрации.
	* @return Список видеорегистраторов.
	*/
   List<ISelectItem> getSelectItemsForEmployee(Map<String, Object> params);

   /**
	* Обновить сотрудников при удалении устройства.
	*
	* @param dvrId ID
	*/
   void updateWithEmployee(@Param("dvrId") Long dvrId);
}
