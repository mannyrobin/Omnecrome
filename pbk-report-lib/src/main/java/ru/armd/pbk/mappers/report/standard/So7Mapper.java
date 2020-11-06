package ru.armd.pbk.mappers.report.standard;

import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.mappers.IDomainMapper;

/**
 * Маппер стандартного отчёта "Количество перевезенных пассажиров по маршрутам".
 */
@IsMapper
public interface So7Mapper
	extends IDomainMapper<BaseDomain> {
}
