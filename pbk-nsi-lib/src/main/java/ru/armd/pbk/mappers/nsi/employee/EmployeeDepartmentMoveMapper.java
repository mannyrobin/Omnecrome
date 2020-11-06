package ru.armd.pbk.mappers.nsi.employee;

import org.apache.ibatis.annotations.Param;
import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.mappers.IDomainMapper;
import ru.armd.pbk.domain.nsi.employee.EmployeeDepartmentMove;

/**
 * Маппер для работы с сущностью "Подразделения сотрудника".
 */
@IsMapper
public interface EmployeeDepartmentMoveMapper
	extends IDomainMapper<EmployeeDepartmentMove> {

   /**
	* Получение домена по выбранному подразделения и сотруднику.
	*
	* @param employeeId ИД сотрудника.
	* @param deptId     ИД подразделения.
	* @return
	*/
   EmployeeDepartmentMove getEmplDeptMoveByEmployeeAndDept(@Param("employeeId") Long employeeId, @Param("deptId") Long deptId);

   /**
	* Получение последнего подразделения, в котором работает сотрудник.
	*
	* @param employeeId ИД сотрудника.
	* @return
	*/
   EmployeeDepartmentMove getLastDepartmentMove(@Param("employeeId") Long employeeId);
}