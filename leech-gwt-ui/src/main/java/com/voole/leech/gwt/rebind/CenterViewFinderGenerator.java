package com.voole.leech.gwt.rebind;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.ext.Generator;
import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.TypeOracle;
import com.google.gwt.dev.util.Name;
import com.google.gwt.user.rebind.ClassSourceFileComposerFactory;
import com.google.gwt.user.rebind.SourceWriter;
import com.voole.leech.gwt.client.ui.CenterView;
import com.voole.leech.gwt.client.ui.CenterView.CenterViewConfig;
import com.voole.leech.shared.annotations.MenuToken;

public class CenterViewFinderGenerator extends Generator {

	@Override
	public String generate(TreeLogger logger, GeneratorContext context,
			String typeName) throws UnableToCompleteException {

		TypeOracle oracle = context.getTypeOracle();
		JClassType toGenerate = oracle.findType(typeName).isClass();

		// Get the name of the new type
		String packageName = toGenerate.getPackage().getName();
		String simpleSourceName = toGenerate.getName().replace('.', '_')
				+ "Impl";
		PrintWriter pw = context.tryCreate(logger, packageName,
				simpleSourceName);
		if (pw == null) {
			return packageName + "." + simpleSourceName;
		}

		JClassType[] types = oracle.getTypes();

		Map<String, JClassType> tokenToClass = new HashMap<String, JClassType>();
		Map<String, String> tokenToLabel = new HashMap<String, String>();

		for (JClassType jClassType : types) {
			MenuToken menuToken = jClassType.getAnnotation(MenuToken.class);
			if (menuToken != null) {
				tokenToClass.put(menuToken.token(), jClassType);
				tokenToLabel.put(menuToken.token(), menuToken.name());
			}
		}

		ClassSourceFileComposerFactory factory = new ClassSourceFileComposerFactory(
				packageName, simpleSourceName);
		factory.setSuperclass(typeName);
		factory.addImport(Name.getSourceNameForClass(GWT.class));
		factory.addImport(Name.getSourceNameForClass(CenterView.class));
		factory.addImport(Name.getSourceNameForClass(CenterViewConfig.class));
		SourceWriter sw = factory.createSourceWriter(context, pw);

		sw.println("public CenterView findCenterView(String token) {");
		sw.println("CenterView m = null;");
		sw.indent();
		for (String token : tokenToClass.keySet()) {
			JClassType jClassType = tokenToClass.get(token);
			if (token == null) {
				token = "";
			}
			sw.println("if(\"%1$s\".equals(token)){", token);
			sw.println("CenterViewConfig viewConfig = new CenterViewConfig(\"%1$s\",\"%2$s\");",token,tokenToLabel.get(token));
			sw.println("m = new %1$s();", jClassType.getQualifiedSourceName());
			sw.println("m.setCenterViewConfig(viewConfig);", token);
//			sw.println("m.setLabel(\"%1$s\");", tokenToLabel.get(token));
//			sw.println("m.setEventBus(eventBus);");
			sw.println("return m;}");
		}
		sw.outdent();
		sw.println("return null;");
		sw.println("}");

		sw.commit(logger);

		return factory.getCreatedClassName();
	}

}
