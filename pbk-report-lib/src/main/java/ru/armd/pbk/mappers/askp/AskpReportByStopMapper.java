package ru.armd.pbk.mappers.askp;

import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.mappers.IDomainMapper;

/**
 * Маппер стандартного отчёта "Сводный сравнительный анализ данных пассажиропотока (АСКП/АСМ-ПП)".
 */
@IsMapper
public interface AskpReportByStopMapper
	extends IDomainMapper<BaseDomain> {
}
