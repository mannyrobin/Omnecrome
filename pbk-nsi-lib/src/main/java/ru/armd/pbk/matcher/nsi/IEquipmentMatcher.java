package ru.armd.pbk.matcher.nsi;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.armd.pbk.core.matcher.IDomainMatcher;
import ru.armd.pbk.domain.nsi.Equipment;
import ru.armd.pbk.dto.nsi.EquipmentDTO;

/**
 * Мапер для сопостовления различных типов сущности "бортовое оборудование".
 */
@Mapper
public interface IEquipmentMatcher
	extends IDomainMatcher<Equipment, EquipmentDTO> {

   IEquipmentMatcher INSTANCE = Mappers.getMapper(IEquipmentMatcher.class);
}