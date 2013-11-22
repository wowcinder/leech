/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.gwt.util;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.sencha.gxt.dnd.core.client.TreeDropTarget;
import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JInvocation;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JType;
import com.sun.codemodel.JVar;
import com.voole.leech.gwt.client.ui.logmodelmeta.c.tree.CTypeLogModelColumnTree;
import com.voole.leech.logmodelmeta.shared.entity.c.CTypeLogModelColumn;

/**
 * @author XuehuiHe
 * @date 2013年9月23日
 */
public class TreeDropTargetTestUtil {
	private static JCodeModelUtil jCodeModelUtil;
	private static JDefinedClass treeTarget;

	public static JCodeModel getjCodeModel() {
		return jCodeModelUtil.getjCodeModel();
	}

	public static void main(String[] args) throws JClassAlreadyExistsException,
			IOException, ClassNotFoundException {
		jCodeModelUtil = new JCodeModelUtil();
		treeTarget = getjCodeModel()
				._class("xdata.etl.cinder.gwt.client.ui.logmodelmeta.c.tree.CTypeLogModelColumnTreeDropTarget2");
		treeTarget._extends(getjCodeModel().ref(TreeDropTarget.class).narrow(
				CTypeLogModelColumn.class));
		JMethod constructor = treeTarget.constructor(JMod.PUBLIC);
		JVar treePar = constructor.param(
				getjCodeModel().ref(CTypeLogModelColumnTree.class), "tree");

		constructor.body().add(JExpr.invoke("super").arg(treePar));

		for (Method method : getNoPrivateMethods(TreeDropTarget.class)) {
			writeMethod(method);
		}

		getjCodeModel().build(jCodeModelUtil.getMainJavaPath());
	}

	public static void writeMethod(Method method) throws ClassNotFoundException {
		JMethod jMethod = treeTarget.method(getJMod(method),
				jCodeModelUtil.getJType(method.getGenericReturnType()),
				method.getName());
		List<JType> types = getArgTypes(method);
		for (int i = 0; i < types.size(); i++) {
			jMethod.param(types.get(i), "arg" + i);
		}

		jMethod.body()
				.add(JExpr.invoke("log").arg(JExpr.lit(method.getName())));
		JInvocation invokeSuper = JExpr._super().invoke(method.getName());
		for (int i = 0; i < types.size(); i++) {
			invokeSuper.arg(JExpr.ref("arg" + i));
		}
		if (method.getReturnType() != void.class) {
			jMethod.body()._return(invokeSuper);
		} else {
			jMethod.body().add(invokeSuper);
		}

	}

	public static List<JType> getArgTypes(Method method)
			throws ClassNotFoundException {
		List<JType> types = new ArrayList<JType>();

		for (Type paraClass : method.getGenericParameterTypes()) {
			types.add(jCodeModelUtil.getJType(paraClass));
		}
		return types;
	}

	public static int getJMod(Method method) {
		int modifer = method.getModifiers();
		int jMod = 0;
		if (Modifier.isPublic(modifer)) {
			jMod += JMod.PUBLIC;
		} else {
			jMod += JMod.PROTECTED;
		}
		return jMod;
	}

	public static List<Method> getNoPrivateMethods(Class<?> clazz) {
		List<Method> methods = new ArrayList<Method>();
		for (Method method : clazz.getDeclaredMethods()) {
			int modifer = method.getModifiers();
			if (!Modifier.isStatic(modifer)
					&& !Modifier.isNative(modifer)
					&& (Modifier.isPublic(modifer) || Modifier
							.isProtected(modifer))) {
				methods.add(method);
			}
		}
		// if (clazz.getSuperclass() != null
		// && clazz.getSuperclass() != Object.class) {
		// methods.addAll(getNoPrivateMethods(clazz.getSuperclass()));
		// }
		return methods;
	}
}
