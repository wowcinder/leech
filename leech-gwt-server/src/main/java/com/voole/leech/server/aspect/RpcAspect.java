package com.voole.leech.server.aspect;

import java.lang.reflect.Method;

import javax.validation.ConstraintViolationException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;

import com.google.gwt.user.client.rpc.RemoteService;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.voole.leech.server.rpc.open.OpenRpcService;
import com.voole.leech.server.util.HibernateBeanUtil;
import com.voole.leech.service.AuthorizeService;
import com.voole.leech.shared.exception.NoLoginException;
import com.voole.leech.shared.exception.PermissionException;
import com.voole.leech.shared.exception.SharedException;

@Component
@Aspect
public class RpcAspect implements Ordered {

	@Autowired
	private AuthorizeService authorizeService;

	@Around(value = "execution(* xdata.etl.cinder.server.rpc..*(..))")
	public Object dealResult(ProceedingJoinPoint pjp) throws Throwable {
		doAccessCheck(pjp);
		Object retVal = pjp.proceed();
		if (retVal != null
				&& RequestContextHolder.getRequestAttributes() != null
				&& pjp.getTarget() instanceof RemoteService) {
			if (retVal instanceof PagingLoadResult) {
				new HibernateBeanUtil().dealBean(((PagingLoadResult<?>) retVal)
						.getData());
			} else {
				new HibernateBeanUtil().dealBean(retVal);
			}
		}
		return retVal;
	}

	public void doAccessCheck(JoinPoint jp) throws SecurityException, NoSuchMethodException {
		if (RequestContextHolder.getRequestAttributes() == null) {
			return;
		}
		if (jp.getTarget() instanceof OpenRpcService) {
			return;
		}
		if (!authorizeService.isLogin()) {
			throw new NoLoginException();
		}
		MethodSignature signature = (MethodSignature) jp.getSignature();
		Method invokeMethod = signature.getMethod();
		Class<?> targetClass = jp.getTarget().getClass();
		invokeMethod = targetClass.getMethod(invokeMethod.getName(), invokeMethod.getParameterTypes());
		if (!authorizeService.verify(targetClass, invokeMethod)) {
			throw new PermissionException();
		}
	}

	@AfterThrowing(pointcut = "execution(* xdata.etl.cinder.server.rpc..*(..))", throwing = "ex")
	public void errorInterceptor(Exception ex) throws SharedException,
			ConstraintViolationException {
		if (ex instanceof SharedException) {
			throw (SharedException) ex;
		}
		if (ex instanceof ConstraintViolationException) {
			throw (ConstraintViolationException) ex;
		}
		System.out.println("--------------Exception------------");
		ex.printStackTrace();
		throw new SharedException(ExceptionUtils.getRootCauseMessage(ex));
	}

	@Override
	public int getOrder() {
		return -100;
	}

}
