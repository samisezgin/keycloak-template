package com.solmaz.keycloak.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	/*
	 * @GetMapping
	 * 
	 * @RolesAllowed(value = { "admin" }) public void helloAdmin() {
	 * System.out.println("hello admin"); }
	 */

	@GetMapping("/user")
	public String helloUser() {
		return "hello user";
	}

	@GetMapping("/greeting")
	public String greeting() {
		return "hello user";
	}

	@GetMapping("/admin")
	public String admin() {
		return "hello admin";
	}

	@GetMapping("/hello")
	public String testHello() {
		return "hello tester";
	}

	@GetMapping("/hello1")
	public String testHello1() {
		return "hello tester1";
	}
}
