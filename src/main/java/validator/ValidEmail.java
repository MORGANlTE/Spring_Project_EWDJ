package validator;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

@Documented
@Constraint(validatedBy = EmailConstraintValidator.class)
@Target({METHOD, FIELD})
@Retention(RUNTIME)
public @interface ValidEmail {

    String message() default "you must include a valid email";
    Class<?>[] groups() default{};
    Class<? extends Payload>[] payload() default {};
}