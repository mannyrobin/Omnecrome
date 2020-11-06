package ru.armd.pbk.matcher.nsi;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.armd.pbk.core.matcher.IDomainMatcher;
import ru.armd.pbk.domain.nsi.Ticket;
import ru.armd.pbk.dto.nsi.TicketDTO;

/**
 * Мапер для сопостовления различных типов сущности "билет".
 */
@Mapper
public interface ITicketMatcher
	extends IDomainMatcher<Ticket, TicketDTO> {

   ITicketMatcher INSTANCE = Mappers.getMapper(ITicketMatcher.class);
}