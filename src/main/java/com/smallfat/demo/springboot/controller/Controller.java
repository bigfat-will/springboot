package com.smallfat.demo.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.smallfat.demo.springboot.model.Demo;
import com.smallfat.demo.springboot.service.IDemoService;

@RestController
@RequestMapping("/")
public class Controller {
	@Autowired
	private IDemoService demoService;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public Demo home() {
		return demoService.get(1);
	}

}
