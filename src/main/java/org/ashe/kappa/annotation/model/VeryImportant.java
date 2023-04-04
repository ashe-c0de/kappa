package org.ashe.kappa.annotation.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.CONSTRUCTOR}) // use position
@Retention(RetentionPolicy.RUNTIME) // retention policy 保留策略
public @interface VeryImportant {
}
