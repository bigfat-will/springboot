package com.smallfat.demo.springboot.service;

import com.smallfat.demo.springboot.model.Demo;

public interface IDemoService {
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Demo get(int id);
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Demo put(int id);
	/**
	 * 
	 * @param id
	 * @return
	 */
	public void del(int id);
}
