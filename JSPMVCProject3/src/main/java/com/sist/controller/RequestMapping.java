package com.sist.controller;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME) //저장 기간 => 프로그램 종료시까지 유지
@Target(METHOD) // Method 구분
/*
 *  @RequestMapping("a") ==> if ==> index(찾기)
 * 	public void display()
 */
public @interface RequestMapping {
	public String value();

}
