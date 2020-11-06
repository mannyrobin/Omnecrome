package ru.armd.pbk.mappers.nsi.employee;

import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.mappers.IDomainMapper;
import ru.armd.pbk.domain.nsi.employee.EmployeeLastUpdate;

/**
 * Маппер для работы с сущностью "последнее обновление сотрудника".
 * Данный маппер содержит методы, необходимые для определения, что
 * данного сотрудника необходимо уволить.
 */
@IsMapper
public interface EmployeeLastUpdateMapper
	extends IDomainMapper<EmployeeLastUpdate> {

}
