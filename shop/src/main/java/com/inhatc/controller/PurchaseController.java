package com.inhatc.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.inhatc.service.ProductService;
import com.inhatc.service.PurchaseService;
import com.inhatc.service.RegisterService;
import com.inhatc.vo.Criteria;
import com.inhatc.vo.ProductVO;
import com.inhatc.vo.PurchaseVO;
import com.inhatc.vo.QnaVO;
import com.inhatc.vo.Search;

@Controller
public class PurchaseController{
	
	private static final Logger logger = LoggerFactory.getLogger(PurchaseController.class);
	
	@Inject
	ProductService service;
	
	@Inject
	RegisterService service1;
	
	@Inject
	PurchaseService service2;
	
	@RequestMapping(value="/purchase/purchase", method = RequestMethod.GET)
	public void list(Model model, HttpSession session) throws Exception{
		logger.info("뷰페이지");
		logger.info("장바구니리스트");
		String userId = session.getAttribute("id").toString();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("id", userId);
		model.addAttribute("cartlist",service.readcartlist(dataMap));
		model.addAttribute("list", service1.read_profile(userId));
		if(session.getAttribute("id") != null) {
			model.addAttribute("session", "yes");
			model.addAttribute("id", session.getAttribute("id"));
		}else {
			model.addAttribute("session", "no");
		}
	}
	
	@RequestMapping(value="/purchase/buy",method = RequestMethod.POST)
	public String purchase_buy(Model model,HttpSession session,HttpServletRequest req,PurchaseVO vo) throws Exception {
		
		String userId = session.getAttribute("id").toString();
		String del_add = req.getParameter("postadd")+" "+req.getParameter("roadadd")+" "+req.getParameter("jibadd");
		System.out.println(del_add);
		System.out.println(req.getParameter("del_name"));
		System.out.println(req.getParameter("del_phone"));
		System.out.println(userId);
		vo.setId(userId);
		vo.setDel_add(del_add);
		
		service2.insert(vo);
		
		if(session.getAttribute("id") != null) {
			model.addAttribute("session", "yes");
			model.addAttribute("id", session.getAttribute("id"));
		}else {
			model.addAttribute("session", "no");
		}
		return "redirect:/";
	}

}
