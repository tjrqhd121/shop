package com.inhatc.controller;

import java.util.List;

import javax.inject.Inject;
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

import com.inhatc.vo.QnaVO;
import com.inhatc.vo.Criteria;
import com.inhatc.vo.Search;
import com.inhatc.service.QnaService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class QnaController {
	private static final Logger logger = LoggerFactory.getLogger(QnaController.class);

	@Inject
	QnaService service;

	/*
	 * @Inject QnaService com_service;
	 */


	@RequestMapping(value = "/qna/qna", method = RequestMethod.GET)
	public String QnaSearch(Model model, Criteria cri, Search sch, HttpSession session) throws Exception {
		cri.setSizeOfPage(5);
		cri.setNumberOfRecords(service.searchQnaCount(sch));
		cri.makePaging();
		if (session.getAttribute("id") != null) {
			model.addAttribute("session", "yes");
			model.addAttribute("id", session.getAttribute("id"));
		}else {
			model.addAttribute("session", "no");
		}
		//게시물 내용
		model.addAttribute("page", service.search(sch, cri));
		//버튼 개수
		model.addAttribute("pager", cri);
		//검색한 내용
		model.addAttribute("search", sch);
		return "/qna/qna";
	}

	@RequestMapping(value = "/qna/read", method = RequestMethod.GET)
	public void read(Model model, @RequestParam("bno") int bno, Criteria cri, Search sch, HttpSession session)
			throws Exception {
		logger.info("뷰페이지");
		service.add_hits(bno);
		model.addAttribute("post", service.read(bno));
		model.addAttribute("cri", cri);
		model.addAttribute("search", sch);
		if (session.getAttribute("id") != null) {
			model.addAttribute("session", "yes");
			model.addAttribute("id", session.getAttribute("id"));
		}else {
			model.addAttribute("session", "no");
		}
	}

	@RequestMapping(value = "/qna/modify", method = RequestMethod.GET)
	public void modify(Model model, @RequestParam("bno") int bno, Criteria cri, Search sch, HttpSession session)
			throws Exception {
		logger.info("수정페이지");
		model.addAttribute("post", service.read(bno));
		model.addAttribute("cri", cri);
		model.addAttribute("search", sch);
		if (session.getAttribute("id") != null) {
			model.addAttribute("session", "yes");
			model.addAttribute("id", session.getAttribute("id"));
		}else {
			model.addAttribute("session", "no");
		}
	}

	@RequestMapping(value = "/qna/modify", method = RequestMethod.POST)
	public String modify_post(Model model, QnaVO vo, HttpSession session) throws Exception {
		logger.info("수정완료");
		System.out.println(vo.getTitle());
		vo.setId(session.getAttribute("id").toString());
		service.update_post(vo);
		if (session.getAttribute("id") != null) {
			model.addAttribute("session", "yes");
			model.addAttribute("id", session.getAttribute("id"));
		}else {
			model.addAttribute("session", "no");
		}
		return "redirect:/qna/read?bno=" + vo.getBno();
	}

	@RequestMapping(value = "/qna/delete", method = RequestMethod.POST)
	public String delete(Model model, QnaVO vo, HttpSession session) throws Exception {
		logger.info("삭제완료");
		service.delete_post(vo.getBno());
		if (session.getAttribute("id") != null) {
			model.addAttribute("session", "yes");
			model.addAttribute("id", session.getAttribute("id"));
		}else {
			model.addAttribute("session", "no");
		}
		return "redirect:/qna/qna";
	}

	@RequestMapping(value = "/qna/write", method = RequestMethod.GET)
	public void write(Model model, Criteria cri, QnaVO vo, Search sch, HttpSession session) throws Exception {
		logger.info("글쓰기 페이지");
		model.addAttribute("cri", cri);
		model.addAttribute("search", sch);
		if (session.getAttribute("id") != null) {
			model.addAttribute("session", "yes");
			model.addAttribute("id", session.getAttribute("id"));
		}else {
			model.addAttribute("session", "no");
		}
	}

	@RequestMapping(value = "/qna/write", method = RequestMethod.POST)
	public String write_post(Model model, QnaVO vo, HttpSession session) throws Exception {
		logger.info("쓰기완료");
		vo.setId(session.getAttribute("id").toString());

		service.write_post(vo);
		if (session.getAttribute("id") != null) {
			model.addAttribute("session", "yes");
			model.addAttribute("id", session.getAttribute("id"));
		}else {
			model.addAttribute("session", "no");
		}
		return "redirect:/qna/qna";
	}
}