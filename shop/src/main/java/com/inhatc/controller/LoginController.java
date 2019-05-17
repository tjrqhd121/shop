package com.inhatc.controller;


import java.util.HashMap;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpSession;

import com.inhatc.vo.LoginVO;
import com.inhatc.service.LoginService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class LoginController {
	
	@Inject
	LoginService service;
	@RequestMapping(value = "/login/checkid", method = RequestMethod.GET)
	public void check(Model model, HttpSession session) {
		if(session.getAttribute("id") != null) {
			model.addAttribute("session", "yes");
			model.addAttribute("id", session.getAttribute("id"));
		}else {
			model.addAttribute("session", "no");
		}
	}
	@RequestMapping(value = "/login/checkpw", method = RequestMethod.GET)
	public void check2(Model model, HttpSession session) {
		if(session.getAttribute("id") != null) {
			model.addAttribute("session", "yes");
			model.addAttribute("id", session.getAttribute("id"));
		}else {
			model.addAttribute("session", "no");
		}
	}
	@RequestMapping(value = "/login/login", method = RequestMethod.GET)
	public void login_check(Model model, HttpSession session) {
		if(session.getAttribute("id") != null) {
			model.addAttribute("session", "yes");
			model.addAttribute("id", session.getAttribute("id"));
		}else {
			model.addAttribute("session", "no");
		}
	}	
	@RequestMapping(value = "/login/login", method = RequestMethod.POST)
	public String login_check(Model model, LoginVO vo, HttpSession session) {
		boolean result = service.login_check(vo.getId(), vo.getPw());
		System.out.println(result);
		if(result)
		{
			session.setAttribute("id", vo.getId());
			return "redirect:/";
		}
		else
		{
			model.addAttribute("check", "no");
			return "/login/login";			
		}
	}
	
	@RequestMapping(value = "/login/checkid", method = RequestMethod.POST)
	public @ResponseBody Object check(Model model, LoginVO vo, HttpSession session) throws Exception {
		String result = service.check(vo.getName(), vo.getEmail());
		System.out.println(result);
		  HashMap<String, Object> paramMap = new HashMap<String, Object>();
	      paramMap.put("id", result);
			System.out.println("id"+paramMap);
	      return paramMap;	
	}	
	@RequestMapping(value = "/login/checkpw", method = RequestMethod.POST)
	public @ResponseBody Object check2(Model model, LoginVO vo, HttpSession session) throws Exception {
		String result = service.check2(vo.getId(),vo.getName(), vo.getEmail());
		System.out.println(result);
			  HashMap<String, Object> paramMap = new HashMap<String, Object>();		      		      
			  paramMap.put("pw", result);
			  System.out.println("pw"+result);
		      return paramMap;
	}	
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute("id");
		return "redirect:/";
		
	}	
}