package com.analysis.data.support.annotation;

import java.lang.annotation.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@Bean
@Scope("browserScope")
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ThreadScopeBean {}
