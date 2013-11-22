package com.voole.leech.gwt.client.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;

import com.google.gwt.core.client.GWT;
import com.google.gwt.validation.client.AbstractGwtValidatorFactory;
import com.google.gwt.validation.client.impl.AbstractGwtValidator;
import com.voole.leech.common.shared.groups.GWTChecks;

public final class BeanValidatorFactory extends AbstractGwtValidatorFactory {
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.TYPE)
	public static @interface needValidate {

	}

	private static Validator validator;

	@Override
	public AbstractGwtValidator createValidator() {
		return GWT.create(BeanValidator.class);
	}

	public static Validator getCannaValidator() {
		if (validator == null) {
			validator = Validation.buildDefaultValidatorFactory()
					.getValidator();
		}
		return validator;
	}

	public static <T> Set<ConstraintViolation<T>> validate(T t) {
		return getCannaValidator().validate(t,
				new Class<?>[] { Default.class, GWTChecks.class });
	}

}
