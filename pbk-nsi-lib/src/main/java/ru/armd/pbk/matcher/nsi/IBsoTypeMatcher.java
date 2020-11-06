package ru.armd.pbk.matcher.nsi;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.armd.pbk.core.matcher.IDomainMatcher;
import ru.armd.pbk.domain.nsi.bsotype.BsoType;
import ru.armd.pbk.dto.nsi.bsotype.BsoTypeDTO;

/**
 * Маппер для сопоставления различных типов сущности "тип БСО".
 */
@Mapper
public interface IBsoTypeMatcher
	extends IDomainMatcher<BsoType, BsoTypeDTO> {

   IBsoTypeMatcher INSTANCE = Mappers.getMapper(IBsoTypeMatcher.class);
}
