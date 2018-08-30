package com.smallfat.demo.springboot.util;

public class RespondModel {
	private int errNum;
	private String errMsg;
	private String errShowMsg;
	private Object retData;
	
	public RespondModel(ErrorNumEnum errNum,String errMsg,String errShowMsg,Object retData) {
		this.errNum = errNum.value();
		this.errMsg = errMsg;
		this.errShowMsg = errShowMsg;
		this.retData = retData;
	}
	public RespondModel(ErrorNumEnum errNum,String errMsg,Object retData) {
		this(errNum, errMsg, "", retData);
	}
	public static RespondModel success(String errMsg,Object retData) {
		return new RespondModel(ErrorNumEnum.SUCCESS, errMsg,"", retData);
	}
	public static RespondModel failed(String errMsg,Object retData) {
		return new RespondModel(ErrorNumEnum.FAIL, errMsg,"", retData);
	}
	
	public int getErrNum() {
		return errNum;
	}
	public void setErrNum(int errNum) {
		this.errNum = errNum;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	public String getErrShowMsg() {
		return errShowMsg;
	}
	public void setErrShowMsg(String errShowMsg) {
		this.errShowMsg = errShowMsg;
	}
	public Object getRetData() {
		return retData;
	}
	public void setRetData(Object retData) {
		this.retData = retData;
	}

}
