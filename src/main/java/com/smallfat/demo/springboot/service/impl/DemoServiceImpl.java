package com.smallfat.demo.springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.smallfat.demo.springboot.mapper.DemoMapper;
import com.smallfat.demo.springboot.model.Demo;
import com.smallfat.demo.springboot.service.IDemoService;

@Service
public class DemoServiceImpl implements IDemoService {
	@Autowired
	private DemoMapper demoMapper;
	/**
	 * 先判断 缓存有没有，没有则 db 获取，然后存入缓存
	 */
	@Override
	@Cacheable(value="demo:demo",key="'demo:demo&id:' + #id")
	public Demo get(int id) {
		return demoMapper.selectByPrimaryKey(id);
	}
	/**
	 * 不管缓存有没有，都 db 获取，然后存入缓存
	 */
	@Override
	@CachePut(value="demo:demo",key="'demo:demo&id:' + #id")
	public Demo put(int id) {
		Demo demo = new Demo();
		demo.setId(id);
		demo.setName("new name");
		demoMapper.updateByPrimaryKey(demo);
		return demo;
	}
	/**
	 * 删除缓存
	 */
	@Override
	@CacheEvict(value="demo:demo",key="'demo:demo&id:' + #id")
	public void del(int id) {
		demoMapper.deleteByPrimaryKey(id);
	}

}
