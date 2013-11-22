package com.voole.leech.server.init;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.voole.leech.service.MenuService;
import com.voole.leech.shared.annotations.MenuToken;
import com.voole.leech.util.ClassScaner;

@Component
public class MenuInit implements InitializingBean {
	private ClassScaner scanner;
	@Autowired
	private MenuService menuService;

	public MenuInit() {
		try {
			scanner = new ClassScaner("xdata.etl.cinder.gwt.client.ui");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		List<MenuToken> list = new ArrayList<MenuToken>();
		for (Class<?> clazz : scanner.getClazzes()) {
			if (clazz.isAnnotationPresent(MenuToken.class)) {
				list.add(clazz.getAnnotation(MenuToken.class));
			}
		}
		menuService.insertMenuConfig(list);
	}

}
