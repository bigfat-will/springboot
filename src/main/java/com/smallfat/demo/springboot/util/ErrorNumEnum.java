package com.smallfat.demo.springboot.util;

public enum ErrorNumEnum {
	/**
	 * 系统繁忙
	 */
	FAIL(-1),
	/**
	 * 请求成功
	 */
	SUCCESS(0);
	
    private final Integer value;

    ErrorNumEnum(Integer value) {
        this.value = value;
    }

    public Integer value() {
        return this.value;
    }
}
