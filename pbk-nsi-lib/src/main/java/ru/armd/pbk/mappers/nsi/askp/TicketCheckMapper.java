package ru.armd.pbk.mappers.nsi.askp;

import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.mappers.IDomainMapper;
import ru.armd.pbk.domain.nsi.askp.TicketCheck;

/**
 * Маппер для работы с проверками билетов.
 */
@IsMapper
public interface TicketCheckMapper
	extends IDomainMapper<TicketCheck> {

}
