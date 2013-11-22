package com.voole.leech.gwt.client.common.validator;

import java.util.Collection;
import java.util.Set;

import javax.validation.ConstraintViolation;

import com.sencha.gxt.widget.core.client.info.Info;
import com.voole.leech.gwt.client.common.BeanValidatorFactory;

public class SimpleCinderValidator implements CinderValidator {

	public static final CinderValidator Instance = new SimpleCinderValidator();

	private SimpleCinderValidator() {
	}

	@Override
	public boolean validate(Object t) {
		Set<ConstraintViolation<Object>> set = BeanValidatorFactory.validate(t);
		if (!set.isEmpty()) {
			String sb = "";
			for (ConstraintViolation<?> cv : set) {
				if (!"".equals(sb)) {
					sb += "<br />";
				}
				sb += cv.getPropertyPath() + " " + cv.getMessage();
			}
			Info.display("操作失败", sb);
			return false;
		}
		return true;
	}

	@Override
	public boolean validateCollection(Collection<?> list) {
		int i = 1;
		for (Object t : list) {
			Set<ConstraintViolation<Object>> set = BeanValidatorFactory
					.validate(t);
			if (!set.isEmpty()) {
				String header = "第" + i + "个数据:<br />";
				String sb = "";
				for (ConstraintViolation<?> cv : set) {
					if (!"".equals(sb)) {
						sb += "<br />";
					}
					sb += cv.getPropertyPath() + " " + cv.getMessage();
				}
				Info.display("操作失败", header + sb);
				return false;
			}
			i++;
		}
		return true;
	}

}
