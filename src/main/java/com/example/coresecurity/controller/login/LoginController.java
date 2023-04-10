package com.example.coresecurity.controller.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.coresecurity.service.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	@GetMapping(value = "/login")
	public String login() {
		return "user/login/login";
	}


	@GetMapping(value = "/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null){
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		}

		return "user/login/login";
	}

	@GetMapping(value = "/denied")
	public String accessDenied() throws Exception {

		return "user/login/denied";
	}
}
