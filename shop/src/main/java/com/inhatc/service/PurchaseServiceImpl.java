package com.inhatc.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inhatc.dao.PurchaseDAO;
import com.inhatc.vo.PurchaseVO;

@Service
public class PurchaseServiceImpl implements PurchaseService {
	
	@Inject
	private PurchaseDAO dao;
	
	@Override
	public void insert(PurchaseVO vo) throws Exception {
		dao.insert(vo); 
	}
	@Override
	public PurchaseVO read(Integer pno) throws Exception {
	
		return dao.read(pno);
	}
	@Override
	public void update(PurchaseVO vo) throws Exception {
		dao.update(vo);
	}
	@Override
	public void delete(Integer pno) throws Exception {
	 	dao.delete(pno);
	}
	@Override
	public List<PurchaseVO> list() throws Exception {
		return dao.list();    
	}
}