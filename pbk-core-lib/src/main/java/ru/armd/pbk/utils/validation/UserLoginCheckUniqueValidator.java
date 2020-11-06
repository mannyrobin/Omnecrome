package ru.armd.pbk.utils.validation;

import org.springframework.beans.factory.annotation.Autowired;
import ru.armd.pbk.dto.core.UserDTO;
import ru.armd.pbk.services.core.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Валидатор для проверки уникальности логина.
 */
public class UserLoginCheckUniqueValidator
	implements ConstraintValidator<UserLoginCheckUnique, UserDTO> {

   @Autowired
   private UserService userService;

   @Override
   public void initialize(UserLoginCheckUnique constraintAnnotation) {
   }

   @Override
   public boolean isValid(UserDTO value, ConstraintValidatorContext context) {
	  if (value == null || value.getId() != null) {
		 return true;
	  }
	  return userService.getByLogin(value.getLogin()) == null;
   }
}
