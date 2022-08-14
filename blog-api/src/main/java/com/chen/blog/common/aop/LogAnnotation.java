package com.chen.blog.common.aop;

import java.lang.annotation.*;

//Type表示可以放在类上面，Method可以放在方法上
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAnnotation {

    String module() default "";

    String operator() default "";
}
