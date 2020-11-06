package ru.armd.pbk.mappers.nsi;

import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.mappers.IVersionDomainMapper;
import ru.armd.pbk.domain.nsi.Equipment;
import ru.armd.pbk.domain.nsi.EquipmentShort;

import java.util.List;

/**
 * Мапер для операций с бортовым оборудованием.
 */
@IsMapper
public interface EquipmentMapper
	extends IVersionDomainMapper<Equipment> {

   /**
	* Получение бортового оборудования.
	*
	* @return
	*/
   List<EquipmentShort> getDomainsShort();
}
