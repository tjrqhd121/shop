package com.inhatc.service;

import java.util.List;
import java.util.Map;

import com.inhatc.vo.ProductVO;

public interface ProductService {
	public void insert(ProductVO vo) throws Exception;
	public ProductVO read(Integer pno) throws Exception;
	public void update(ProductVO vo) throws Exception;
	public void delete(Integer pno) throws Exception;
	public List<ProductVO> list() throws Exception;
	public List<ProductVO> healthy_list() throws Exception;
	public List<ProductVO> object_list() throws Exception;
	public List<ProductVO> mass_list() throws Exception;
	public List<String> getAttach(Integer pno) throws Exception;
	public int insertcartlist(Map<String, Object> dataMap) throws Exception;
	public List<ProductVO> readcartlist(Map<String, Object> dataMap) throws Exception;
	public int delete_cartlist(Map<String, Object> dataMap);
}