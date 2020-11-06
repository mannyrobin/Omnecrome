package ru.armd.pbk.mappers.nsi.employee;

import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.mappers.IDomainMapper;
import ru.armd.pbk.domain.nsi.employee.EmployeeWorkMode;

/**
 * Маппер для работы с сущностью "Рабочий режим сотрудника".
 */
@IsMapper
public interface EmployeeWorkModeMapper
	extends IDomainMapper<EmployeeWorkMode> {

}
