package com.smallfat.demo.springboot.exception;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.smallfat.demo.springboot.util.ErrorNumEnum;
import com.smallfat.demo.springboot.util.HttpUtil;
import com.smallfat.demo.springboot.util.RespondModel;


@ControllerAdvice
public class GlobalExceptionHandler {
	private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
    /**
     * 参数 规则校验异常处理
     * @Validated . 若验证不通过, 则会抛出BindException
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = BindException.class)
    public RespondModel bindException(BindException e) {
        List<ObjectError> objectErrors = e.getBindingResult().getAllErrors();
        StringBuffer sb = new StringBuffer("");
        for (ObjectError error : objectErrors){
            FieldError fieldError = (FieldError)error;
            sb.append(fieldError.getField()).append(":").append(fieldError.getDefaultMessage()).append("; ");
        }
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
        logger.error("请求缺失参数: IP{}   : {}",HttpUtil.getIpAddr(request),sb.toString());
        return new RespondModel(ErrorNumEnum.FAIL, sb.toString(), null);
    }
    /**
     * 参数 规则校验异常处理
     * @Validated @RequestBody . 若验证不通过, 则会抛出MethodArgumentNotValidException
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public RespondModel bindException(MethodArgumentNotValidException e) {
        List<ObjectError> objectErrors = e.getBindingResult().getAllErrors();
        StringBuffer sb = new StringBuffer("");
        for (ObjectError error : objectErrors){
            FieldError fieldError = (FieldError)error;
            sb.append(fieldError.getField()).append(":").append(fieldError.getDefaultMessage()).append("; ");
        }
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
        logger.error("请求缺失参数: IP{}   : {}",HttpUtil.getIpAddr(request),sb.toString());
        return new RespondModel(ErrorNumEnum.FAIL, sb.toString(), null);
    }
    
	 /**
     * @param e
     * @return
     * @throws Exception
     */
	@ResponseBody
    @ExceptionHandler(value = Exception.class)
    public RespondModel defaultErrorHandler(Exception e){
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		logger.error("Exception ：    URL:{}   HTTP_METHOD:{}  IP:{}   Param :{}", request.getRequestURL().toString(),
				request.getMethod(), HttpUtil.getIpAddr(request), HttpUtil.getParam(request).toString(),e);
        return RespondModel.failed("", null);

    }
}
