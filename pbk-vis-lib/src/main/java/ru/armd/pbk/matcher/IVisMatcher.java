package ru.armd.pbk.matcher;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.armd.pbk.core.matcher.IDomainMatcher;
import ru.armd.pbk.domain.viss.Vis;
import ru.armd.pbk.dto.viss.VisDTO;

/**
 * Сопоставление сущностей ВИС.
 */
@Mapper
public interface IVisMatcher
	extends IDomainMatcher<Vis, VisDTO> {

   IVisMatcher INSTANCE = Mappers.getMapper(IVisMatcher.class);

}
