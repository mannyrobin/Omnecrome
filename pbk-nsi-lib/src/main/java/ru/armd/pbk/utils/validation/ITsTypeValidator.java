package ru.armd.pbk.utils.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация проверки уникальности Кода Типа ТС.
 */
@Target( {ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TsTypeValidator.class)
@Documented
public @interface ITsTypeValidator {
   /**
	* Сообщение по умолчанию.
	*/
   String message() default "";

   /**
	* Группы.
	*/
   Class<?>[] groups() default {};

   /**
	* Payload type that can be attached to a given constraint declaration.
	*/
   Class<? extends Payload>[] payload() default {};
}
