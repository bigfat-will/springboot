package com.smallfat.demo.springboot.util;

import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;

public class HttpUtil {
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if(ip.indexOf(",")!=-1){
            ip = ip.split(",")[0];
        }
        return ip;
    }
    /**
     * 
     * @param request
     * @return
     */
    public static Map<String,String> getParam(HttpServletRequest request) {
		Map<String,String> map = Maps.newHashMap();
		Enumeration<String> paraNames=request.getParameterNames();
		for(Enumeration<String> e=paraNames;e.hasMoreElements();){
		      String key=e.nextElement().toString();
		      String value=request.getParameter(key);
		      map.put(key, value);
		}
		return map;
    }
    /**
     * 
     * @param response
     * @param str
     * @throws Exception
     */
	public static void writer(HttpServletResponse response, String str) throws Exception {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = response.getWriter();
		writer.print(str);
		writer.close();
		response.flushBuffer();
    }
	/**
	 * 
	 * @param response
	 * @param obj
	 * @throws Exception
	 */
	public static void writerWithJson(HttpServletResponse response, Object obj) throws Exception {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = response.getWriter();
		writer.print(new ObjectMapper().writeValueAsString(obj));
		writer.close();
		response.flushBuffer();
    }
}
