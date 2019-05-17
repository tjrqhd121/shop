package com.inhatc.controller;

import java.util.HashMap;
import java.io.PrintWriter;

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
import com.inhatc.vo.QnaVO;
import com.inhatc.vo.RegisterVO;
import com.inhatc.service.RegisterService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class RegisterController {
	
	@Inject
	RegisterService service;
	
	private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);
	
	@RequestMapping(value = "/login/signup", method = RequestMethod.GET)
	public void login(Model model, HttpSession session) {
		if(session.getAttribute("id") != null) {
			model.addAttribute("session", "yes");
			model.addAttribute("id", session.getAttribute("id"));
		}else {
			model.addAttribute("session", "no");
		}
	}	
	
	@RequestMapping(value = "/checkid", method = RequestMethod.POST)
	     @ResponseBody 
	      public Object idcheck(Model model, RegisterVO vo) throws Exception {
		  HashMap<String, Object> paramMap = new HashMap<String, Object>();
	      int result = service.idcheck(vo);
	      System.out.println(result);
	      paramMap.put("result", result);
	      return paramMap;
	   }

	@RequestMapping(value = "/login/signup", method = RequestMethod.POST)
	public String register(RegisterVO vo, Model model, RedirectAttributes rttr, HttpSession session) throws Exception {
		System.out.println("test");
		if(session.getAttribute("id") != null) {
			model.addAttribute("session", "yes");
			model.addAttribute("id", session.getAttribute("id"));
		}else {
			model.addAttribute("session", "no");
		}
		int result=service.register(vo);
		if(result==1){
			System.out.println("회원가입 성공");
			rttr.addFlashAttribute("success","success");
			 return "redirect:/";
		}else{
			System.out.println("회원가입 실패");
			model.addAttribute("success","fail");
			return "/login/signup";	
		}
	}
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public void modify(Model model, @RequestParam("id") String id, HttpSession session)
			throws Exception {
		logger.info("회원정보수정");
		System.out.println("id는?"+id);
		model.addAttribute("list", service.read_profile(id));
		if (session.getAttribute("id") != null) {
			model.addAttribute("session", "yes");
			model.addAttribute("id", session.getAttribute("id"));
		}else {
			model.addAttribute("session", "no");
		}
	}

	@RequestMapping(value = "/profile", method = RequestMethod.POST)
	public String modify_post(Model model, RegisterVO vo, HttpSession session) throws Exception {
		logger.info("회원수정완료");
		service.update_profile(vo);
		if (session.getAttribute("id") != null) {
			model.addAttribute("session", "yes");
			model.addAttribute("id", session.getAttribute("id"));
		}else {
			model.addAttribute("session", "no");
		}
		return "redirect:/";
	}
	
	@RequestMapping(value = "/membermanage", method = RequestMethod.GET)
	public void boardSearch(Model model, HttpSession session) throws Exception {
		logger.info("회원리스트");
		model.addAttribute("memberlist",service.read_memberlist());
		if(session.getAttribute("id") != null) {
			model.addAttribute("session", "yes");
			model.addAttribute("id", session.getAttribute("id"));
		}else {
			model.addAttribute("session", "no");
		}
	}
	
	@RequestMapping(value="/membermodify", method = RequestMethod.GET)
	public void readmember(@RequestParam("id") String id, Model model, HttpSession session) throws Exception {
		logger.info("수정페이지");
		model.addAttribute("list",service.modifymember(id));
		if(session.getAttribute("id") != null) {
			model.addAttribute("session", "yes");
			model.addAttribute("id", session.getAttribute("id"));
		}else {
			model.addAttribute("session", "no");
		}
	}
	
	@RequestMapping(value = "/membermodify", method = RequestMethod.POST)
	public String modify_member(Model model, RegisterVO vo, HttpSession session, HttpServletRequest request) throws Exception {
		logger.info("수정완료");
		String id = request.getParameter("id");
		vo.setId(id);
		service.update_member(vo);
		if (session.getAttribute("id") != null) {
			model.addAttribute("session", "yes");
			model.addAttribute("id", session.getAttribute("id"));
		}else {
			model.addAttribute("session", "no");
		}
		return "redirect:/membermanage";
	}
	
	@RequestMapping(value = "/membermodify/delete", method = RequestMethod.POST)
	@ResponseBody 
	public String delete(Model model, RegisterVO vo, HttpSession session, HttpServletRequest request) throws Exception {
		logger.info("삭제완료");
		String id = request.getParameter("id");
		System.out.println(id);
		vo.setId(id);
		System.out.println(vo.getId());
		service.delete_member(vo.getId());
		if (session.getAttribute("id") != null) {
			model.addAttribute("session", "yes");
			model.addAttribute("id", session.getAttribute("id"));
		}else {
			model.addAttribute("session", "no");
		}
		return "redirect:/membermanage";
	}
}