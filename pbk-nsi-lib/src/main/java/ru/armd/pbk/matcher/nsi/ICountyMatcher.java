package ru.armd.pbk.matcher.nsi;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.armd.pbk.core.matcher.IDomainMatcher;
import ru.armd.pbk.domain.nsi.County;
import ru.armd.pbk.dto.nsi.CountyDTO;

/**
 * Мапер для сопостовления различных типов сущности "Округ".
 */
@Mapper
public interface ICountyMatcher
	extends IDomainMatcher<County, CountyDTO> {

   ICountyMatcher INSTANCE = Mappers.getMapper(ICountyMatcher.class);

}
