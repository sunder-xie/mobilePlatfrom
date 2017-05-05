package com.kintiger.xplatform.framework.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.util.StopWatch;

import com.kintiger.xplatform.api.monitor.IMethodMonitorService;
import com.kintiger.xplatform.api.monitor.bo.MethodMonitor;
import com.kintiger.xplatform.framework.annotation.Profiler;

/**
 * method profiler for count method execute time.
 * 
 * @author xujiakun
 * 
 */
public class MethodProfiler {

	private IMethodMonitorService methodMonitorService;

	public Object profile(ProceedingJoinPoint call) throws Throwable {
		MethodSignature o = (MethodSignature) call.getSignature();
		Method method = o.getMethod();

		Profiler profiler = method.getAnnotation(Profiler.class);
		if (profiler == null) {
			return call.proceed();
		}

		StopWatch clock = new StopWatch();
		try {
			clock.start(call.toShortString());
			return call.proceed();
		} finally {
			clock.stop();

			MethodMonitor methodMonitor = new MethodMonitor();
			methodMonitor.setClassName(method.getDeclaringClass().getName());
			methodMonitor.setMethodName(method.getName());
			methodMonitor.setCost(clock.getTotalTimeMillis());

			methodMonitorService.createMethodMonitor(methodMonitor);
			// clock.prettyPrint()
		}
	}

	public IMethodMonitorService getMethodMonitorService() {
		return methodMonitorService;
	}

	public void setMethodMonitorService(IMethodMonitorService methodMonitorService) {
		this.methodMonitorService = methodMonitorService;
	}

}
