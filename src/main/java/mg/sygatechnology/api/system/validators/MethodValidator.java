package mg.sygatechnology.api.system.validators;

import io.vertx.core.http.HttpMethod;
import mg.sygatechnology.api.system.annotations.Method;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MethodValidator implements ConstraintValidator<Method, HttpMethod> {

    HttpMethod method;

    @Override
    public void initialize(Method constraintAnnotation) {
        method = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(HttpMethod value, ConstraintValidatorContext context) {
        return true;
    }
}
