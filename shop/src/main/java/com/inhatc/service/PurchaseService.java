package com.inhatc.service;

import java.util.List;
import java.util.Map;

import com.inhatc.vo.PurchaseVO;

public interface PurchaseService {
	public void insert(PurchaseVO vo) throws Exception;
	public PurchaseVO read(Integer pno) throws Exception;
	public void update(PurchaseVO vo) throws Exception;
	public void delete(Integer pno) throws Exception;
	public List<PurchaseVO> list() throws Exception;
}