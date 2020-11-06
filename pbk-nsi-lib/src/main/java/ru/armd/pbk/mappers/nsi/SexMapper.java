package ru.armd.pbk.mappers.nsi;

import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.mappers.IDomainMapper;
import ru.armd.pbk.domain.nsi.Sex;

/**
 * Маппер "пола".
 *
 * @author nikita_chebotaryov
 */
@IsMapper
public interface SexMapper
	extends IDomainMapper<Sex> {

}
