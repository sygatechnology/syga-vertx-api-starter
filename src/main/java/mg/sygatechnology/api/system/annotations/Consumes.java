package mg.sygatechnology.api.system.annotations;

import java.lang.annotation.*;

@Target( {ElementType.METHOD } )
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Consumes {
    String value() default "application/text";
}
