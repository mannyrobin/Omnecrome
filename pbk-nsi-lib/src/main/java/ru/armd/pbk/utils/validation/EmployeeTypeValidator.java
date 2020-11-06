package ru.armd.pbk.utils.validation;

import ru.armd.pbk.enums.nsi.EmployeeType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Валидатор типа сотрудника, проверяет, что сотрудник относится внутренним.
 */
public class EmployeeTypeValidator
	implements ConstraintValidator<IsEmployeeTypeValid, EmployeeType> {

   @Override
   public void initialize(IsEmployeeTypeValid isEmployeeTypeValid) {
   }

   @Override
   public boolean isValid(EmployeeType employeeType, ConstraintValidatorContext constraintValidatorContext) {
	  return employeeType != null && employeeType.equals(EmployeeType.PBK);
   }
}
