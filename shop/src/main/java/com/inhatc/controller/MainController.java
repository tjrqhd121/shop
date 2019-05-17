package com.inhatc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.inhatc.service.ProductService;
import com.inhatc.vo.Criteria;
import com.inhatc.vo.LoginVO;
import com.inhatc.vo.QnaVO;
import com.inhatc.vo.Search;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import org.springframework.ui.Model;
/**
 * Handles requests for the application home page.
 */
@Controller
public class MainController {
	
	@Inject
	ProductService service;
	
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
		
//	 @RequestMapping(value="/", method= RequestMethod.GET)
//	 public String main() {
//	 	return "/main";
//	 }
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String boardSearch(Model model, HttpSession session) throws Exception {
		model.addAttribute("list",service.list());
		if(session.getAttribute("id") != null) {
			model.addAttribute("session", "yes");
			model.addAttribute("id", session.getAttribute("id"));
		}else {
			model.addAttribute("session", "no");
		}
	return "/main";
	}
	
	@RequestMapping(value = "/main1", method = RequestMethod.GET)
	public String boardSearch1(Model model, HttpSession session) throws Exception {
		model.addAttribute("list",service.list());
		if(session.getAttribute("id") != null) {
			model.addAttribute("session", "yes");
			model.addAttribute("id", session.getAttribute("id"));
		}else {
			model.addAttribute("session", "no");
		}
	return "/main1";
	}
	
	
}
