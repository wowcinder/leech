package com.voole.leech.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.codec.digest.DigestUtils;

public class AuthorizeSystemAnnotations {
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.TYPE)
	@Inherited
	public static @interface AuthorizeGroupAnnotation {
		String value();
	}

	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.METHOD)
	public static @interface AuthorizeAnnotation {
		String group() default "";

		String value();
	}

	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.METHOD)
	public static @interface AuthorizeAnnotations {
		AuthorizeAnnotation[] value();
	}
	
	public static class TupleUtil{
		public static Set<String> getTokens(Tuple tuple) {
			return getTokens(tuple.getTargetClass(), tuple.getInvokeMethod());
		}

		public static Set<String> getTokens(Class<?> targetClass,
				Method invokeMethod) {
			Set<String> set = new HashSet<String>();
			AuthorizeAnnotations authorizeAnnotations = invokeMethod
					.getAnnotation(AuthorizeAnnotations.class);
			AuthorizeAnnotation authorizeAnnotation = invokeMethod
					.getAnnotation(AuthorizeAnnotation.class);
			AuthorizeGroupAnnotation authorizeGroupAnnotation = targetClass
					.getAnnotation(AuthorizeGroupAnnotation.class);
			if ((authorizeAnnotations == null && authorizeAnnotation == null)
					|| authorizeGroupAnnotation == null) {
				return set;
			}
			if (authorizeAnnotation != null) {
				set.add(getToken(authorizeGroupAnnotation, authorizeAnnotation));
			}
			if (authorizeAnnotations != null) {
				set.addAll(getTokens(authorizeGroupAnnotation,
						authorizeAnnotations));
			}
			return set;
		}

		public static Set<String> getTokens(
				AuthorizeGroupAnnotation authorizeGroupAnnotation,
				AuthorizeAnnotations authorizeAnnotations) {
			Set<String> set = new HashSet<String>();
			for (AuthorizeAnnotation authorizeAnnotation : authorizeAnnotations
					.value()) {
				set.add(getToken(authorizeGroupAnnotation, authorizeAnnotation));
			}
			return set;
		}

		public static String getToken(
				AuthorizeGroupAnnotation authorizeGroupAnnotation,
				AuthorizeAnnotation authorizeAnnotation) {
			String authorizeGroupName = authorizeGroupAnnotation.value();
			String realAuthorizeGroupName = authorizeAnnotation.group();
			if (realAuthorizeGroupName == null
					|| realAuthorizeGroupName.length() == 0) {
				realAuthorizeGroupName = authorizeGroupName;
			}
			return getToken(realAuthorizeGroupName, authorizeAnnotation.value());
		}

		public static String getToken(String authorizeGroupName,
				String authorizeName) {
			return DigestUtils.md5Hex(DigestUtils.md5Hex(authorizeGroupName)
					+ DigestUtils.md5Hex(authorizeName));
		}
	}

	public static class Tuple {
		private final Method invokeMethod;
		private final Class<?> targetClass;

		public Tuple(Class<?> targetClass, Method invokeMethod) {
			this.targetClass = targetClass;
			this.invokeMethod = invokeMethod;
		}

		public Method getInvokeMethod() {
			return invokeMethod;
		}

		public Class<?> getTargetClass() {
			return targetClass;
		}

		@Override
		public int hashCode() {
			return invokeMethod.hashCode() + 3 * targetClass.hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == null) {
				return false;
			}
			if (obj == this) {
				return true;
			}
			if (!(obj instanceof Tuple)) {
				return false;
			}
			Tuple that = (Tuple) obj;
			return this.invokeMethod.equals(that.invokeMethod)
					&& this.targetClass.equals(that.targetClass);
		}
	}

}
