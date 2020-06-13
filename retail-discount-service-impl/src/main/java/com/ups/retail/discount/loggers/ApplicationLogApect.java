package com.ups.retail.discount.loggers;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class ApplicationLogApect {
	static final Logger logger = LoggerFactory.getLogger(ApplicationLogApect.class);

	@Around("execution(* com.ups.retail.discount.*..*(..))")
	public Object aroundLogic(ProceedingJoinPoint joinPoint) throws Throwable {
		String method = joinPoint.getTarget().getClass() + "." + joinPoint.getSignature().getName();
		long before = System.currentTimeMillis();
		logger.info(method + "start,args=" + Arrays.toString(joinPoint.getArgs()));
		Object proceed = joinPoint.proceed();
		logger.info(
				method + "afterCall,execTime=" + (System.currentTimeMillis() - before) + "ms," + "result=" + proceed);
		return proceed;
	}

//	@AfterThrowing(pointcut = "execution(* com.ups.retail.discount.*..*(..))", throwing = "ex")
//	public void throwing(JoinPoint joinPoint, Exception ex) {
//		ex.printStackTrace();
//	}
}
