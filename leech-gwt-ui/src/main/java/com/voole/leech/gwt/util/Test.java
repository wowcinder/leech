/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.gwt.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author XuehuiHe
 * @date 2013年9月29日
 */
public class Test {
	public static void main(String[] args) throws SecurityException,
			NoSuchMethodException {
		ClassTypeHolder holder = new ClassTypeHolder(TestSub2.class);
		System.out.println(holder.getActualType(TestSub.class
				.getTypeParameters()[0]));
		System.out.println(TestSub.class.getTypeParameters()[0]
				.getGenericDeclaration());
		// JCodeModel jCodeModel = new JCodeModel();
		Type type = TestSub2.class.getSuperclass().getGenericSuperclass();
		if (type instanceof ParameterizedType) {
			ParameterizedType parameterizedType = ((ParameterizedType) type);
			System.out.println(parameterizedType.getActualTypeArguments()[0]);
		}
	}

	public static class ClassTypeHolder {
		private final Class<?> curr;
		private final ClassTypeHolder prev;
		private ClassTypeHolder next;
		private Type[] actualTypeArguments;
		private Map<String, Integer> typeVariableMap;

		public ClassTypeHolder(Class<?> curr) {
			this(curr, null);

		}

		@SuppressWarnings("unchecked")
		public ClassTypeHolder(Class<?> curr, ClassTypeHolder prev) {
			typeVariableMap = new HashMap<String, Integer>();
			Type genericSuperclass = curr.getGenericSuperclass();
			if (genericSuperclass instanceof ParameterizedType) {
				actualTypeArguments = ((ParameterizedType) genericSuperclass)
						.getActualTypeArguments();
			}
			int i = 0;
			for (TypeVariable<Class<?>> typeVariable : (TypeVariable[]) curr
					.getTypeParameters()) {
				typeVariableMap.put(typeVariable.getName(), i);
				i++;
			}

			this.curr = curr;
			this.prev = prev;
			if (curr.getSuperclass().equals(Object.class)) {
				next = null;
			} else {
				next = new ClassTypeHolder(curr.getSuperclass(), this);
			}
		}

		public Class<?> getActualType(TypeVariable<?> typeVariable) {
			if (!typeVariable.getGenericDeclaration().equals(this.curr)) {
				if (this.next != null) {
					return this.next.getActualType(typeVariable);
				} else {
					return Object.class;
				}
			}
			return findActualTypePrev(typeVariable);
		}

		protected Class<?> findActualTypeCurr(Integer index) {
			if (index != null && actualTypeArguments != null
					&& actualTypeArguments.length > index) {
				Type type = actualTypeArguments[index];
				if (type instanceof Class) {
					return (Class<?>) type;
				} else if (type instanceof TypeVariable) {
					return findActualTypePrev((TypeVariable<?>) type);
				}
			}
			return Object.class;
		}

		protected Class<?> findActualTypePrev(TypeVariable<?> typeVariable) {
			Integer index = typeVariableMap.get(typeVariable.getName());
			if (this.prev != null) {
				return this.prev.findActualTypeCurr(index);
			}
			return Object.class;
		}

	}
}
