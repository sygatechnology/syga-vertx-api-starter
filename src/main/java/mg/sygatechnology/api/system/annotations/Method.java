package mg.sygatechnology.api.system.annotations;

import io.vertx.core.http.HttpMethod;
import mg.sygatechnology.api.system.validators.MethodValidator;

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
