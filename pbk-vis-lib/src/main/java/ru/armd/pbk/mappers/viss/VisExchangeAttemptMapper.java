package ru.armd.pbk.mappers.viss;

import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.mappers.IDomainMapper;
import ru.armd.pbk.domain.viss.VisExchangeAttempt;

/**
 * Маппер журнальной записи отправки обмена.
 */
@IsMapper
public interface VisExchangeAttemptMapper
	extends IDomainMapper<VisExchangeAttempt> {
}
