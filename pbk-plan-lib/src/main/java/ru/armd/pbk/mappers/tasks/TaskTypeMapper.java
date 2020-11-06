package ru.armd.pbk.mappers.tasks;

import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.mappers.IDomainMapper;

/**
 * Маппер типов заданий.
 */
@IsMapper
public interface TaskTypeMapper
	extends IDomainMapper<BaseDomain> {

}
