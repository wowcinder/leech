package com.voole.leech.util;

import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;

import com.voole.leech.common.shared.groups.RpcChecks;

public class CinderValidator {
	public static final Validator validator = Validation
			.buildDefaultValidatorFactory().getValidator();

	public static <T> void validate(T t) {
		Set<ConstraintViolation<T>> violations = validator.validate(t,
				new Class<?>[] { Default.class, RpcChecks.class });
		if (!violations.isEmpty()) {
			Set<ConstraintViolation<?>> temp = new HashSet<ConstraintViolation<?>>(
					violations);
			throw new ConstraintViolationException(temp);
		}
	}

}
