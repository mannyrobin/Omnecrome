package ru.armd.pbk.utils.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Аннотация проверки ГКУ ОП.
 */
@Target( {ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = GkuopValidator.class)
@Documented
public @interface IGkuopValidator {

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
