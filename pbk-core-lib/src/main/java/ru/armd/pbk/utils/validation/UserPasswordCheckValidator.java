package ru.armd.pbk.utils.validation;

import org.springframework.beans.factory.annotation.Autowired;
import ru.armd.pbk.dto.core.UserDTO;
import ru.armd.pbk.services.core.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Валидатор для проверки совпадения пароля.
 */
public class UserPasswordCheckValidator
	implements ConstraintValidator<UserPasswordCheck, UserDTO> {

   @Autowired
   private UserService userService;

   @Override
   public void initialize(UserPasswordCheck constraintAnnotation) {
   }

   @Override
   public boolean isValid(UserDTO value, ConstraintValidatorContext context) {
	  return value.getPassword().equals(value.getPasswordRepeat());
   }
}
