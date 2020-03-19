package mg.sygatechnology.vertx.system.annotations;

import io.vertx.core.http.HttpMethod;
import mg.sygatechnology.vertx.system.validators.MethodValidator;

import javax.validation.Constraint;
import java.lang.annotation.*;

@Target( {ElementType.METHOD } )
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Constraint( validatedBy = MethodValidator.class )
public @interface Method {
    HttpMethod value();
}
