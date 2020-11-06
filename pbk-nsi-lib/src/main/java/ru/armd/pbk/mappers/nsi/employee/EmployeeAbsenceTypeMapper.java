package ru.armd.pbk.mappers.nsi.employee;

import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.mappers.IDomainMapper;
import ru.armd.pbk.domain.nsi.employee.EmployeeAbsenceType;

/**
 * Маппер для работы с сущностью "Тип отсутсвия сотрудника".
 */
@IsMapper
public interface EmployeeAbsenceTypeMapper
	extends IDomainMapper<EmployeeAbsenceType> {

}
