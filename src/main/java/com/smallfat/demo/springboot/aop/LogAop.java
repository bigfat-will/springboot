package com.smallfat.demo.springboot.aop;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smallfat.demo.springboot.util.HttpUtil;

@Aspect
@Component
public class LogAop {
	private static Logger logger = LoggerFactory.getLogger(LogAop.class);

	@Pointcut("execution(public *  com.smallfat.demo.springboot.controller..*.*(..))")
	public void log() {
	}

	@Before("log()")
	public void doBefore(JoinPoint joinPoint) throws Throwable {
		logger.debug("doBefore");
		// 接收到请求，记录请求内容
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		logger.info("doBefore：    URL:{}   HTTP_METHOD:{}  IP:{}   Param :{}", request.getRequestURL().toString(),
				request.getMethod(), HttpUtil.getIpAddr(request), HttpUtil.getParam(request).toString());

	}

	@Around("log()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		logger.debug("@Around 方法执行前");
		Object obj = pjp.proceed();
		logger.debug("@Around 方法执行后");
		return obj;
	}

	@AfterReturning(returning = "ret", pointcut = "log()")
	public void doAfterReturning(Object ret) throws Throwable {
		logger.debug("doAfterReturning");
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		logger.info("doAfter ：    URL:{}   HTTP_METHOD:{}  IP:{}   Param :{}  Res :{}", request.getRequestURL().toString(),
				request.getMethod(), HttpUtil.getIpAddr(request), HttpUtil.getParam(request).toString(),
				new ObjectMapper().writeValueAsString(ret));

	}
}
