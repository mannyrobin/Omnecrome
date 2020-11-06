package ru.armd.pbk.matcher.nsi;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.armd.pbk.core.matcher.IDomainMatcher;
import ru.armd.pbk.domain.nsi.ContactlessCard;
import ru.armd.pbk.dto.nsi.ContactlessCardDTO;

/**
 * Мапер для сопостовления различных типов сущности "БСК".
 */
@Mapper
public interface IContactlessCardMatcher
	extends IDomainMatcher<ContactlessCard, ContactlessCardDTO> {

   IContactlessCardMatcher INSTANCE = Mappers.getMapper(IContactlessCardMatcher.class);

}
