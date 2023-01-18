package com.sist.controller;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(TYPE)
//클래스 구분 ==> Model 여부 => Spring에서 모델 클래스 (@Controller)
public @interface Controller {

}
