package com.voole.leech.gwt.util;

import java.io.File;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.HashMap;
import java.util.Map;

import com.sun.codemodel.JClass;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JType;
import com.voole.leech.logmodelmeta.shared.entity.c.CTypeLogModelColumn;

public class JCodeModelUtil {
	private final JCodeModel jCodeModel;
	private final Map<Class<?>, ClassTypeHolder> holderMap;

	public JCodeModelUtil() {
		jCodeModel = new JCodeModel();
		holderMap = new HashMap<Class<?>, JCodeModelUtil.ClassTypeHolder>();
	}

	public JCodeModel getjCodeModel() {
		return jCodeModel;
	}

	public File getMainJavaPath() {
		String path = JCodeModelUtil.class.getClassLoader().getResource("")
				.getFile();
		File file = new File(path);
		file = file.getParentFile().getParentFile();
		path = file.getAbsolutePath() + File.separator + "src" + File.separator
				+ "main" + File.separator + "java";
		file = new File(path);
		return file;
	}

	public ClassTypeHolder getClassHolder(Class<?> holder) {
		if (!holderMap.containsKey(holder)) {
			holderMap.put(holder, new ClassTypeHolder(holder));
		}
		return holderMap.get(holder);
	}

	public JType getJType(Class<?> holder, Type type)
			throws ClassNotFoundException {
		if (type instanceof ParameterizedType) {
			Type[] parameters = ((ParameterizedType) type)
					.getActualTypeArguments();
			JClass jClass = jCodeModel
					.ref((Class<?>) ((ParameterizedType) type).getRawType());
			for (Type type2 : parameters) {
				jClass = jClass.narrow((JClass) getJType(holder, type2));
			}
			return jClass;
		} else if (type instanceof GenericArrayType) {
			GenericArrayType arrayType = (GenericArrayType) type;
			return getJType(arrayType.getGenericComponentType()).array();
		} else if (type instanceof WildcardType) {
			return jCodeModel.wildcard();
		} else if (type instanceof TypeVariable) {
			return jCodeModel.ref(getClassHolder(holder).getActualType(
					(TypeVariable<?>) type));
		} else {
			return jCodeModel.parseType(((Class<?>) type).getName());
		}
	}

	public JType getJType(Type type) throws ClassNotFoundException {
		if (type instanceof ParameterizedType) {
			Type[] parameters = ((ParameterizedType) type)
					.getActualTypeArguments();
			JClass jClass = jCodeModel
					.ref((Class<?>) ((ParameterizedType) type).getRawType());
			for (Type type2 : parameters) {
				jClass = jClass.narrow((JClass) getJType(type2));
			}
			return jClass;
		} else if (type instanceof GenericArrayType) {
			GenericArrayType arrayType = (GenericArrayType) type;
			return getJType(arrayType.getGenericComponentType()).array();
		} else if (type instanceof WildcardType) {
			return jCodeModel.wildcard();
			// return jCodeModel.ref(Object.class);
		} else if (type instanceof TypeVariable) {
			// TypeVariable tv = (TypeVariable) type;
			return jCodeModel.ref(CTypeLogModelColumn.class);
		} else {
			return jCodeModel.parseType(((Class<?>) type).getName());
		}
	}

	public class ClassTypeHolder {
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
