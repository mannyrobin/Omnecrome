package ru.armd.pbk.matcher;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.armd.pbk.core.matcher.IDomainMatcher;
import ru.armd.pbk.domain.viss.VisExchangeObject;
import ru.armd.pbk.dto.viss.VisExchangeObjectDTO;

/**
 * Сопоставление сущностей типа обмена с ВИС.
 */
@Mapper
public interface IVisExchangeObjectMatcher
	extends IDomainMatcher<VisExchangeObject, VisExchangeObjectDTO> {

   IVisExchangeObjectMatcher INSTANCE = Mappers.getMapper(IVisExchangeObjectMatcher.class);

}
