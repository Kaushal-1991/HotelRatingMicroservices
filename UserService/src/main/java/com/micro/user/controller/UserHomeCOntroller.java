package com.micro.user.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/home")
public class UserHomeCOntroller {

	@GetMapping
	public List<String> getList(){
		List<String> list = new ArrayList<>();
		 list.add("Kaushal");
		 list.add("Amit");
		 list.add("Anukul");
		return list;
	}
}
