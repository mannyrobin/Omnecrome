package ru.armd.pbk.mappers.report.standard;

import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.mappers.IDomainMapper;

/**
 * Маппер стандартного отчёта "Сравнительный анализ данных пассажиропотока (АСКП/АСМ-ПП) поостановочно".
 */
@IsMapper
public interface So16Mapper
	extends IDomainMapper<BaseDomain> {
}
