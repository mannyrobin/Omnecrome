package ru.armd.pbk.mappers.nsi;

import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.mappers.IVersionDomainMapper;
import ru.armd.pbk.domain.nsi.Ticket;

/**
 * Мапер для операций с билетами.
 */
@IsMapper
public interface TicketMapper
	extends IVersionDomainMapper<Ticket> {

}
