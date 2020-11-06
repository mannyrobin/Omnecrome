package ru.armd.pbk.mappers.dvr;

import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.mappers.IDomainMapper;
import ru.armd.pbk.domain.dvr.DvrStorePeriod;

/**
 * Маппер для периуда хранения записи с видеорегистратора.
 */
@IsMapper
public interface DvrStorePeriodMapper
	extends IDomainMapper<DvrStorePeriod> {

}
