package com.smallfat.demo.springboot.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.smallfat.demo.springboot.util.HttpUtil;


public class ApiInterceptor implements HandlerInterceptor {
	private static Logger logger = LoggerFactory.getLogger(ApiInterceptor.class);
	/**
	 * 整个请求处理完毕回调方法，即在视图渲染完毕时回调，
	 */
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}
	/**
	 * 后处理回调方法，实现处理器的后处理（但在渲染视图之前）
	 */
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
	}
	/**
	 * 预处理回调方法
	 */
	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		Map<String,String> map = HttpUtil.getParam(arg0);
		logger.info("interceptor：    URL:{}   HTTP_METHOD:{}  IP:{}   Param :{}", arg0.getRequestURL().toString(),
				arg0.getMethod(), HttpUtil.getIpAddr(arg0), HttpUtil.getParam(arg0).toString());
		return true;
//		return false;
	}

}
