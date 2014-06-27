package de.hybris.platform.cuppytrail.constraints;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


/**
 * The annotated element must not be empty or <code>null</code>. Supported type is {@link String}. <code>null</code>
 * elements are considered <b>invalid</b>.
 */
@Target(
{ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = NotEmptyCuppyValidator.class)
@Documented
public @interface NotEmptyCuppy
{
	String message() default "{de.hybris.platform.cuppytrail.constraints.NotEmptyCuppy.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}