package ru.armd.pbk.mappers.core;

import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.mappers.IDomainMapper;
import ru.armd.pbk.domain.core.Setting;

/**
 * Маппер с методами для работы с настройками.
 */
@IsMapper
public interface SettingsMapper
	extends IDomainMapper<Setting> {

}
