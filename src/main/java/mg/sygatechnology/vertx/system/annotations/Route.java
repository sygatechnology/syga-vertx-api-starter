package mg.sygatechnology.vertx.system.annotations;

import mg.sygatechnology.vertx.system.validators.RouteValidator;

import javax.validation.Constraint;
import java.lang.annotation.*;

@Target( {ElementType.METHOD } )
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Constraint( validatedBy = RouteValidator.class )
public @interface Route {
    String value();
}
