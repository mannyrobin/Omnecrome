package ru.armd.pbk.mappers.report.standard;

import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.mappers.IDomainMapper;

/**
 * Маппер стандартного отчёта "Сводный сравнительный анализ данных пассажиропотока (АСКП/АСМ-ПП)".
 */
@IsMapper
public interface So17Mapper
	extends IDomainMapper<BaseDomain> {
}
