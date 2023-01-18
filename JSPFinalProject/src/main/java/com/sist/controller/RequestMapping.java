package com.sist.controller;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(METHOD) 
/*
 * 	public class A ==> 
 * 	{
 * 		@RequestMapping("a.do")
 * 		public void disp()
 * 		{
 * 		}
 * 		
 * 		@ RequestMapping("a.do")
 * 		public void disp1()
 * 		{
 * 		}
 * 	}
 */
public @interface RequestMapping {
	public String value();
}
