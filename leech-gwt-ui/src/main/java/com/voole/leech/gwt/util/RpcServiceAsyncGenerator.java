package com.voole.leech.gwt.util;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.sun.codemodel.ClassType;
import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JType;
import com.voole.leech.gwt.client.util.RpcServiceUtils;
import com.voole.leech.util.ClassScaner;

public class RpcServiceAsyncGenerator {

	private static JCodeModelUtil jCodeModelUtil;
	private static JDefinedClass rpcServiceUtils;

	public static void main(String[] args) throws Exception {
		jCodeModelUtil = new JCodeModelUtil();
		rpcServiceUtils = getjCodeModel()._class(
				RpcServiceUtils.class.getName());

		ClassScaner scaner = new ClassScaner(
				"xdata.etl.cinder.gwt.client.service");

		for (Class<?> clazz : scaner.getClazzes()) {
			if (RemoteService.class.isAssignableFrom(clazz)) {
				generateAsyncClass(clazz);
			}
		}
		getjCodeModel().build(jCodeModelUtil.getMainJavaPath());
	}

	private static void generateAsyncClass(Class<?> clazz)
			throws JClassAlreadyExistsException, IOException, ClassNotFoundException {
		String name = clazz.getSimpleName() + "Async";
		String packagee = clazz.getPackage().getName();
		JDefinedClass dc = getjCodeModel()._class(packagee + "." + name,
				ClassType.INTERFACE);
		for (Method method : clazz.getMethods()) {
			generateAsyncMethod(dc, method);
		}
		writeGwtInstance(clazz, dc);
	}

	private static void writeGwtInstance(Class<?> clazz, JDefinedClass dc)
			throws JClassAlreadyExistsException {
		rpcServiceUtils.field(
				JMod.PUBLIC + JMod.STATIC + JMod.FINAL,
				dc,
				clazz.getSimpleName(),
				getjCodeModel().ref(GWT.class).staticInvoke("create")
						.arg(JExpr.dotclass(getjCodeModel().ref(clazz))));
	}

	public static void generateAsyncMethod(JDefinedClass dc, Method method)
			throws IOException, ClassNotFoundException {
		JMethod jMethod = dc.method(JMod.PUBLIC, void.class, method.getName());
		Type[] genericParameterTypes = method.getGenericParameterTypes();
		for (int i = 0; i < genericParameterTypes.length; i++) {
			Type parameterType = genericParameterTypes[i];
			String parameterName = "arg" + i;
			jMethod.param(jCodeModelUtil.getJType(parameterType), parameterName);
		}
		Type type = method.getGenericReturnType();
		JType returnClass = jCodeModelUtil.getJType(type);
		jMethod.param(
				getjCodeModel().ref(AsyncCallback.class).narrow(returnClass),
				"callback");
	}

	public static JCodeModel getjCodeModel() {
		return jCodeModelUtil.getjCodeModel();
	}
}
