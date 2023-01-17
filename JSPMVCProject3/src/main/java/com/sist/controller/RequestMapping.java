package com.sist.controller;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME) //���� �Ⱓ => ���α׷� ����ñ��� ����
@Target(METHOD) // Method ����
/*
 *  @RequestMapping("a") ==> if ==> index(ã��)
 * 	public void display()
 */
public @interface RequestMapping {
	public String value();

}
