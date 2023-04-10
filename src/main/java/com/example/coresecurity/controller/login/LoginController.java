package com.example.coresecurity.controller.login;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.coresecurity.domain.entity.Account;

@Controller
public class LoginController {

	@RequestMapping(value = "/login")
	public String login(@RequestParam(value = "error", required = false) String error,
		@RequestParam(value = "exception", required = false) String exception, Model model) {
		model.addAttribute("error", error);
		model.addAttribute("exception", exception);
		return "login";
	}

	@GetMapping(value = "/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		}

		return "redirect:/login";
	}

	@GetMapping(value = "/denied")
	public String accessDenied(@RequestParam(value = "exception", required = false) String exception,
		Principal principal, Model model) throws Exception {

		if (principal instanceof UsernamePasswordAuthenticationToken) {
			Account account = (Account)((UsernamePasswordAuthenticationToken)principal).getPrincipal();
			model.addAttribute("username", account.getUsername());
			model.addAttribute("exception", exception);
		}

		return "user/login/denied";
	}
}