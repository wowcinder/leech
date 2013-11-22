package com.voole.leech.gwt.util;

import javax.persistence.Entity;
import javax.validation.Validator;
import javax.validation.groups.Default;

import com.google.gwt.validation.client.GwtValidation;
import com.sun.codemodel.ClassType;
import com.sun.codemodel.JAnnotationArrayMember;
import com.sun.codemodel.JAnnotationUse;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.voole.leech.common.shared.groups.GWTChecks;
import com.voole.leech.gwt.client.common.BeanValidator;
import com.voole.leech.gwt.client.common.BeanValidatorFactory.needValidate;
import com.voole.leech.util.ClassScaner;

public class ValidatorGenerator {
	private static JCodeModelUtil jCodeModelUtil;
	private static JDefinedClass cinderValidator;

	public static void main(String[] args) throws Exception {
		jCodeModelUtil = new JCodeModelUtil();
		cinderValidator = getjCodeModel()._class(BeanValidator.class.getName(),
				ClassType.INTERFACE);
		cinderValidator._extends(Validator.class);
		JAnnotationUse gwtValidationAnnotate = cinderValidator
				.annotate(GwtValidation.class);
		gwtValidationAnnotate.paramArray("groups").param(Default.class)
				.param(GWTChecks.class);
		JAnnotationArrayMember gwtValidation = gwtValidationAnnotate
				.paramArray("value");

		ClassScaner scaner = new ClassScaner("xdata.etl.cinder.shared.entity",
				"xdata.etl.cinder.hbasemeta.shared.entity",
				"xdata.etl.cinder.logmodelmeta.shared.entity");

		for (Class<?> clazz : scaner.getClazzes()) {
			if (clazz.isAnnotationPresent(Entity.class)
					|| clazz.isAnnotationPresent(needValidate.class)) {
				gwtValidation.param(clazz);
			}
		}
		getjCodeModel().build(jCodeModelUtil.getMainJavaPath());
	}

	public static JCodeModel getjCodeModel() {
		return jCodeModelUtil.getjCodeModel();
	}
}
