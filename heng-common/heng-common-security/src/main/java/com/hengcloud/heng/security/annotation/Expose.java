package com.hengcloud.heng.security.annotation;

import java.lang.annotation.*;

/**
 * 服务调用不鉴权注解
 *
 * @author liheng
 * @date 2021-02-4
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Expose {

	/**
	 * 是否AOP统一处理
	 * @return false, true
	 */
	boolean value() default true;

	/**
	 * 需要特殊判空的字段(预留)
	 * @return {}
	 */
	String[] field() default {};

}
