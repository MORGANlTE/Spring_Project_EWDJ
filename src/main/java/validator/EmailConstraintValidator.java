package validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailConstraintValidator 
    implements ConstraintValidator<ValidEmail,String>
{
    @Override
    public void initialize(ValidEmail constraintAnnotation) {}

    @Override
    public boolean isValid(String value, 
                                 ConstraintValidatorContext context) {
        
        return (value.contains("@"));
    }
}