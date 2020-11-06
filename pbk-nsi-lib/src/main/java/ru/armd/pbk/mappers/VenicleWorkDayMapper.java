package ru.armd.pbk.mappers;

import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.mappers.IDomainMapper;
import ru.armd.pbk.domain.nsi.VenicleWorkDay;

/**
 * Маппер для работы с информацией об выходах ТС.
 */
@IsMapper
public interface VenicleWorkDayMapper
	extends IDomainMapper<VenicleWorkDay> {

}
