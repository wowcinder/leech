package com.voole.leech.gwt.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sun.codemodel.ClassType;
import com.sun.codemodel.JClass;
import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JVar;
import com.voole.leech.gwt.client.util.PropertyUtils;
import com.voole.leech.util.ClassScaner;

public class PropertiesGenerator {
	private static JCodeModelUtil jCodeModelUtil;
	private static JDefinedClass propertyUtils;

	public static void main(String[] args) throws Exception {
		jCodeModelUtil = new JCodeModelUtil();
		propertyUtils = getjCodeModel()._class(PropertyUtils.class.getName());

		ClassScaner scaner = new ClassScaner("xdata.etl.cinder.shared.entity",
				"xdata.etl.cinder.hbasemeta.shared.entity",
				"xdata.etl.cinder.logmodelmeta.shared.entity");
		for (Class<?> clazz : scaner.getClazzes()) {
			if (clazz.isAnnotationPresent(Entity.class)
					&& !Modifier.isAbstract(clazz.getModifiers())) {

				System.out.println(clazz.getName());
				generatePropertyClass(clazz);
			}
		}
		getjCodeModel().build(jCodeModelUtil.getMainJavaPath());
	}

	private static void generatePropertyClass(Class<?> clazz)
			throws JClassAlreadyExistsException, ClassNotFoundException,
			SecurityException, NoSuchFieldException {
		JDefinedClass dc = getjCodeModel()._class(
				"xdata.etl.cinder.gwt.client.property." + clazz.getSimpleName()
						+ "Property", ClassType.INTERFACE);
		JDefinedClass dc2 = getjCodeModel()._class(
				"xdata.etl.cinder.gwt.client.gridcolumn."
						+ clazz.getSimpleName() + "ColumnConfig");
		dc._extends(getjCodeModel().ref(PropertyAccess.class).narrow(
				jCodeModelUtil.getJType(clazz)));
		for (Method method : clazz.getMethods()) {
			if (isDoGeneratePropertyClass(method)) {
				generatePropertyMethod(dc, method, clazz, dc2);
			}
		}
		writeGwtInstance(dc);
	}

	private static void writeGwtInstance(JDefinedClass dc)
			throws JClassAlreadyExistsException {
		propertyUtils.field(JMod.PUBLIC + JMod.STATIC + JMod.FINAL, dc,
				dc.name(), getjCodeModel().ref(GWT.class)
						.staticInvoke("create").arg(JExpr.dotclass(dc)));
	}

	public static boolean isId(Method method, String name, Class<?> clazz) {
		if (method.isAnnotationPresent(Id.class)) {
			return true;
		}
		Field field = getField(name, clazz);
		if (field != null && field.isAnnotationPresent(Id.class)) {
			return true;
		}
		return false;
	}

	public static Field getField(String name, Class<?> clazz) {
		Field field = null;
		try {
			field = clazz.getDeclaredField(name);
		} catch (SecurityException e) {
		} catch (NoSuchFieldException e) {
		}
		if (field == null && !Object.class.equals(clazz.getSuperclass())) {
			field = getField(name, clazz.getSuperclass());
		}
		return field;
	}

	public static void generatePropertyMethod(JDefinedClass dc, Method method,
			Class<?> clazz, JDefinedClass dc2) throws ClassNotFoundException,
			SecurityException, NoSuchFieldException {
		String name = method.getName().replace("get", "");
		name = name.substring(0, 1).toLowerCase() + name.substring(1);
		if (isId(method, name, clazz)) {
			JMethod jMethod = dc.method(
					JMod.PUBLIC,
					getjCodeModel().ref(ModelKeyProvider.class).narrow(
							jCodeModelUtil.getJType(clazz)), "key");
			jMethod.annotate(Path.class).param("value", name);
		}
		dc.method(
				JMod.PUBLIC,
				getjCodeModel()
						.ref(ValueProvider.class)
						.narrow(jCodeModelUtil.getJType(clazz))
						.narrow(jCodeModelUtil.getJType(clazz,
								method.getGenericReturnType())), name);
		JClass columnConfigClass = getjCodeModel()
				.ref(ColumnConfig.class)
				.narrow(jCodeModelUtil.getJType(clazz))
				.narrow(jCodeModelUtil.getJType(clazz,
						method.getGenericReturnType()));
		JMethod jMethod = dc2.method(JMod.PUBLIC + JMod.STATIC,
				columnConfigClass, name);
		JVar jVar = jMethod.body().decl(
				columnConfigClass,
				name,
				JExpr._new(columnConfigClass)
						.arg(propertyUtils.staticRef(dc.name()).invoke(name))
						.arg(JExpr.lit(200)).arg(name));
		jMethod.body().invoke(jVar, "setSortable").arg(JExpr.lit(false));
		jMethod.body().invoke(jVar, "setMenuDisabled").arg(JExpr.lit(true));
		jMethod.body()._return(jVar);
	}

	private static boolean isDoGeneratePropertyClass(Method method) {
		Class<?> clazz = method.getDeclaringClass();
		return method.getName().startsWith("get")
				&& (clazz.isAnnotationPresent(Entity.class) || clazz
						.isAnnotationPresent(MappedSuperclass.class))
				&& method.getParameterTypes().length == 0;
	}

	public static JCodeModel getjCodeModel() {
		return jCodeModelUtil.getjCodeModel();
	}
}
