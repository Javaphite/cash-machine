package ua.training.cashmachine.model.annotation;

import ua.training.cashmachine.model.entity.Role;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ServiceScope {

    String USER = "user";
    String SESSION ="session";

    String value();

    Role[] roles() default {Role.UNKNOWN_USER, Role.CASHIER, Role.SENIOR_CASHIER, Role.MERCHANDISER, Role.MANAGER};
}
