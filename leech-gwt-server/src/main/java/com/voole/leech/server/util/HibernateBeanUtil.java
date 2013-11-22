/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.server.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;

import org.hibernate.Hibernate;
import org.hibernate.collection.AbstractPersistentCollection;

/**
 * @author XuehuiHe
 * @date 2013年8月11日
 * 
 */
public class HibernateBeanUtil {

	private final static Map<Class<?>, List<Field>> columnFieldMap = new HashMap<Class<?>, List<Field>>();
	private HashSet<Object> hasDealed;

	public HibernateBeanUtil() {
		hasDealed = new HashSet<Object>();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void dealBean(Object t) {
		if (t == null) {
			return;
		}
		if (hasDealed.contains(t)) {
			return;
		}
		hasDealed.add(t);
		if (t instanceof Collection) {
			for (Object o : (Collection<?>) t) {
				dealBean(o);
			}
		} else if (t instanceof Object[]) {
			for (Object o : (Object[]) t) {
				dealBean(o);
			}
		} else {
			if (!t.getClass().isAnnotationPresent(Entity.class)) {
				return;
			}
			List<Field> fields = getColumnFields(t.getClass());

			try {
				for (Field field : fields) {
					field.setAccessible(true);
					Object o = field.get(t);
					if (o == null) {
						continue;
					}
					if (Hibernate.isInitialized(o) && hasDealed.contains(o)) {
						continue;
					}
					Class<?> oClazz = o.getClass();
					if (oClazz.isPrimitive() || isWrapperType(oClazz)) {

					} else if (!Hibernate.isInitialized(o)) {
						field.set(t, null);
					} else if (Hibernate.isInitialized(o)
							&& o instanceof AbstractPersistentCollection) {
						if (field.getType().isArray()) {
							// TODO
						} else if (List.class.isAssignableFrom(field.getType())) {
							List list = new ArrayList();
							list.addAll((Collection<?>) o);
							field.set(t, list);
						} else if (Set.class.isAssignableFrom(field.getType())) {
							Set set = new HashSet();
							set.addAll((Collection<?>) o);
							field.set(t, set);
						} else {
							// TODO
						}
						dealBean(field.get(t));

					} else {
						dealBean(o);
					}

				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}

		}
	}

	private static final Set<Class<?>> WRAPPER_TYPES = getWrapperTypes();

	public static boolean isWrapperType(Class<?> clazz) {
		return WRAPPER_TYPES.contains(clazz);
	}

	private static Set<Class<?>> getWrapperTypes() {
		Set<Class<?>> ret = new HashSet<Class<?>>();
		ret.add(Boolean.class);
		ret.add(Character.class);
		ret.add(Byte.class);
		ret.add(Short.class);
		ret.add(Integer.class);
		ret.add(Long.class);
		ret.add(Float.class);
		ret.add(Double.class);
		ret.add(Void.class);
		ret.add(String.class);
		ret.add(Date.class);
		return ret;
	}

	protected static List<Field> getColumnFields(Class<?> clazz) {
		if (!columnFieldMap.containsKey(clazz)) {
			fillMap(clazz);
		}
		return columnFieldMap.get(clazz);
	}

	protected static synchronized void fillMap(Class<?> clazz) {
		if (columnFieldMap.containsKey(clazz)) {
			return;
		}
		if (!clazz.isAnnotationPresent(Entity.class)) {
			columnFieldMap.put(clazz, null);
			return;
		}
		columnFieldMap.put(clazz, getFields(clazz));
	}

	protected static List<Field> getFields(Class<?> clazz) {
		List<Field> fields = new ArrayList<Field>();
		if (clazz.isAnnotationPresent(Entity.class)
				|| clazz.isAnnotationPresent(MappedSuperclass.class)) {
			for (Field field : clazz.getDeclaredFields()) {
				int mod = field.getModifiers();
				if (!Modifier.isStatic(mod) && !Modifier.isTransient(mod)) {
					fields.add(field);
				}
			}
		}
		Class<?> superClazz = clazz.getSuperclass();
		if (!superClazz.equals(Object.class)) {
			fields.addAll(getFields(clazz.getSuperclass()));
		}
		return fields;
	}

}
