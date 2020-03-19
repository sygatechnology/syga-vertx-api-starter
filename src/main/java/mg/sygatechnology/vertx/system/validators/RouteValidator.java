package mg.sygatechnology.vertx.system.validators;

import mg.sygatechnology.vertx.system.annotations.Route;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RouteValidator implements ConstraintValidator<Route, String> {

    private String path;

    @Override
    public void initialize(Route route) {
        path = route.value();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && path != null && value.startsWith("/");
    }
}
