package ru.armd.pbk.mappers.core;

import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.mappers.IDomainMapper;
import ru.armd.pbk.domain.core.AuditLog;

/**
 * Маппер результатов очистки логов аудита.
 */
@IsMapper
public interface AuditLogMapper
	extends IDomainMapper<AuditLog> {

}
