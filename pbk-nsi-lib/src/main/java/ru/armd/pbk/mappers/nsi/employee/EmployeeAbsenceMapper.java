package ru.armd.pbk.mappers.nsi.employee;

import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.mappers.IDomainMapper;
import ru.armd.pbk.domain.nsi.employee.EmployeeAbsence;

/**
 * Маппер для работы с сущностью "Отсутсвие сотрудника".
 */
@IsMapper
public interface EmployeeAbsenceMapper
	extends IDomainMapper<EmployeeAbsence> {

}