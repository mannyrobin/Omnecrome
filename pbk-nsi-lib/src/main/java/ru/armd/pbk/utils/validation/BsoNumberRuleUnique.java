package ru.armd.pbk.utils.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Аннотация валидации уникальности правила формирования номера БСО.
 */
@Target( {ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = BsoNumberRuleUniqueValidator.class)
@Documented
public @interface BsoNumberRuleUnique {

   /**
	* Сообщение по умолчанию.
	*
	* @return
	*/
   String message() default "";

   /**
	* Группы.
	*
	* @return
	*/
   Class<?>[] groups() default {};

   /**
	* Payload type that can be attached to a given constraint declaration.
	*
	* @return
	*/
   Class<? extends Payload>[] payload() default {};
}
