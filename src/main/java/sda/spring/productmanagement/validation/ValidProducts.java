package sda.spring.productmanagement.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE) // can be applied to classes
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ProductExistValidator.class)
public @interface ValidProducts {
    String message() default "One or more products do not exist";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
