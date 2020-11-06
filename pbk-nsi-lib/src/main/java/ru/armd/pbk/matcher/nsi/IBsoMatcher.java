package ru.armd.pbk.matcher.nsi;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.armd.pbk.core.matcher.IDomainMatcher;
import ru.armd.pbk.domain.nsi.bso.Bso;
import ru.armd.pbk.dto.nsi.bso.BsoDTO;

/**
 * Маппер для сопоставления различных типов сущности "БСО".
 */
@Mapper
public interface IBsoMatcher
	extends IDomainMatcher<Bso, BsoDTO> {

   IBsoMatcher INSTANCE = Mappers.getMapper(IBsoMatcher.class);
}
