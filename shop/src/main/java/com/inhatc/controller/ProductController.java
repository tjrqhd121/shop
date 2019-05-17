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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.inhatc.service.ProductService;
import com.inhatc.vo.Criteria;
import com.inhatc.vo.ProductVO;
import com.inhatc.vo.QnaVO;
import com.inhatc.vo.Search;

@Controller
public class ProductController{
	
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@Inject
	ProductService service;
	
	@RequestMapping(value="/main", method = RequestMethod.GET)
	public void list(Model model, HttpSession session) throws Exception{
		logger.info("뷰페이지");
		model.addAttribute("list",service.list());
		if(session.getAttribute("id") != null) {
			model.addAttribute("session", "yes");
			model.addAttribute("id", session.getAttribute("id"));
		}else {
			model.addAttribute("session", "no");
		}
	}
	@RequestMapping(value="/healthy", method = RequestMethod.GET)
	public void healthy_list(Model model, HttpSession session) throws Exception{
		logger.info("건강식품 뷰페이지");
		model.addAttribute("healthy_list",service.healthy_list());
		if(session.getAttribute("id") != null) {
			model.addAttribute("session", "yes");
			model.addAttribute("id", session.getAttribute("id"));
		}else {
			model.addAttribute("session", "no");
		}
	}
	@RequestMapping(value="/object", method = RequestMethod.GET)
	public void object_list(Model model, HttpSession session) throws Exception{
		logger.info("보조기구 뷰페이지");
		model.addAttribute("object_list",service.object_list());
		if(session.getAttribute("id") != null) {
			model.addAttribute("session", "yes");
			model.addAttribute("id", session.getAttribute("id"));
		}else {
			model.addAttribute("session", "no");
		}
	}
	@RequestMapping(value="/mass", method = RequestMethod.GET)
	public void mass_list(Model model, HttpSession session) throws Exception{
		logger.info("프로틴 뷰페이지");
		model.addAttribute("mass_list",service.mass_list());
		if(session.getAttribute("id") != null) {
			model.addAttribute("session", "yes");
			model.addAttribute("id", session.getAttribute("id"));
		}else {
			model.addAttribute("session", "no");
		}
	}	
	@RequestMapping(value="/product/pwrite", method = RequestMethod.GET)
	public void insert_get(ProductVO vo, Model model, HttpSession session) throws Exception{
		logger.info("작성페이지");
		if(session.getAttribute("id") != null) {
			model.addAttribute("session", "yes");
			model.addAttribute("id", session.getAttribute("id"));
		}else {
			model.addAttribute("session", "no");
		}
	}
	
	@RequestMapping(value="/product/pwrite", method = RequestMethod.POST)
	public String insert_post(ProductVO vo, Model model, RedirectAttributes rttr, HttpSession session) throws Exception{
		logger.info("작성완료");		
		
		service.insert(vo);
		rttr.addFlashAttribute("msg", "success");
		if(session.getAttribute("id") != null) {
			model.addAttribute("session", "yes");
			model.addAttribute("id", session.getAttribute("id"));
		}else {
			model.addAttribute("session", "no");
		}
		return "redirect:/";
	}
	
	@RequestMapping(value="/product/pread", method = RequestMethod.GET)
	public void read(@RequestParam("pno") int pno, Model model, HttpSession session) throws Exception {
		logger.info("상세페이지");
		model.addAttribute("read",service.read(pno));
		if(session.getAttribute("id") != null) {
			model.addAttribute("session", "yes");
			model.addAttribute("id", session.getAttribute("id"));
		}else {
			model.addAttribute("session", "no");
		}
	}
	
	@RequestMapping(value = "/product/pmodify", method = RequestMethod.GET)
	public void modify(Model model, @RequestParam("pno") int pno, HttpSession session)
			throws Exception {
		logger.info("수정페이지");	
		model.addAttribute("read",service.read(pno));
		if(session.getAttribute("id") != null) {
			model.addAttribute("session", "yes");
			model.addAttribute("id", session.getAttribute("id"));
		}else {
			model.addAttribute("session", "no");
		}
	}

	@RequestMapping(value = "/product/pmodify", method = RequestMethod.POST)
	public String modify_post(Model model, ProductVO vo, HttpSession session) throws Exception {
		logger.info("수정완료");
		service.update(vo);
		if (session.getAttribute("id") != null) {
			model.addAttribute("session", "yes");
			model.addAttribute("id", session.getAttribute("id"));
		}else {
			model.addAttribute("session", "no");
		}
		return "redirect:/product/pread?pno=" + vo.getPno();
	}

	@RequestMapping(value = "/product/delete", method = RequestMethod.POST)
	public String delete(@RequestParam("pno") int pno, Model model, ProductVO vo, HttpSession session) throws Exception {
		logger.info("삭제완료");
		service.delete(vo.getPno());
		if (session.getAttribute("id") != null) {
			model.addAttribute("session", "yes");
			model.addAttribute("id", session.getAttribute("id"));
		}else {
			model.addAttribute("session", "no");
		}
		return "redirect:/";
	}
	
	  @RequestMapping(value = "/getAttach/{pno}")
	  @ResponseBody
	  public List<String> getAttach(@PathVariable("pno")Integer pno)throws Exception{
	    return service.getAttach(pno);
	  }
	  
	  @RequestMapping(value = "/product/newcartlist", method = RequestMethod.POST)
	  @ResponseBody
	  public String insertcartlist(HttpSession session, HttpServletRequest request)throws Exception{
		logger.info("장바구니추가");
		String pno = request.getParameter("pno");
		String p_count = request.getParameter("p_count");
		String userId = session.getAttribute("id").toString();
		System.out.println("test"+p_count);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("pno", pno);
		dataMap.put("id", userId);
		dataMap.put("p_count", p_count);
		Integer result = service.insertcartlist(dataMap);
	    return result.toString();
	  }
	  
	  @RequestMapping(value = "/product/cartlist", method = RequestMethod.GET)
		public void cartlist(Model model, HttpSession session, HttpServletRequest request) throws Exception{
			logger.info("장바구니리스트");
			String userId = session.getAttribute("id").toString();
			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("id", userId);
			model.addAttribute("cartlist",service.readcartlist(dataMap));
			if(session.getAttribute("id") != null) {
				model.addAttribute("session", "yes");
				model.addAttribute("id", session.getAttribute("id"));
			}else {
				model.addAttribute("session", "no");
			}
		}
	  
	  @RequestMapping(value = "/product/deletelist", method = RequestMethod.POST)
	  @ResponseBody
	  public String delete_cartlist(HttpSession session, Model model, HttpServletRequest request)throws Exception{
			logger.info("장바구니제거");
			String cno = request.getParameter("cno");
			String userId = session.getAttribute("id").toString();
			System.out.println(cno);
			System.out.println(userId);
			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("cno", cno);
			dataMap.put("id", userId);
			Integer result = service.delete_cartlist(dataMap);
			if (session.getAttribute("id") != null) {
				model.addAttribute("session", "yes");
				model.addAttribute("id", session.getAttribute("id"));
			}else {
				model.addAttribute("session", "no");
			}
		  return result.toString();
	  }
}
