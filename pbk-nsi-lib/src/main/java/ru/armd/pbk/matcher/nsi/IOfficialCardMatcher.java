package ru.armd.pbk.matcher.nsi;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.armd.pbk.core.matcher.IDomainMatcher;
import ru.armd.pbk.domain.nsi.OfficialCard;
import ru.armd.pbk.dto.nsi.OfficialCardDTO;

/**
 * Мапер для сопостовления различных типов сущности "ССК".
 */
@Mapper
public interface IOfficialCardMatcher
	extends IDomainMatcher<OfficialCard, OfficialCardDTO> {

   IOfficialCardMatcher INSTANCE = Mappers.getMapper(IOfficialCardMatcher.class);

}
