package ru.armd.pbk.matcher.nsi;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.armd.pbk.core.matcher.IDomainMatcher;
import ru.armd.pbk.domain.nsi.employee.Employee;
import ru.armd.pbk.dto.nsi.employee.EmployeeDTO;

/**
 * Мапер для сопостовления различных типов сущностей сотрудников.
 */
@Mapper
public interface IEmployeeMatcher
	extends IDomainMatcher<Employee, EmployeeDTO> {

   IEmployeeMatcher INSTANCE = Mappers.getMapper(IEmployeeMatcher.class);
}
