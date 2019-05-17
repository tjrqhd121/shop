package com.inhatc.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inhatc.dao.ProductDAO;
import com.inhatc.vo.ProductVO;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Inject
	private ProductDAO dao;
	
	@Transactional
	@Override
	public void insert(ProductVO vo) throws Exception {
		dao.insert(vo); 
		
		String[] files = vo.getFiles();
		System.out.println("files : "+files);
		if(files == null) {return ;}
		for(String filename : files) {
			dao.addAttach(filename);
		}
	}
	@Override
	public ProductVO read(Integer pno) throws Exception {
	
		return dao.read(pno);
	}
	@Transactional
	@Override
	public void update(ProductVO vo) throws Exception {
		dao.update(vo);
		
		Integer pno = vo.getPno();
		dao.deleteAttach(pno);
		String[] files = vo.getFiles();
		if(files == null) {return;}
		
		for(String fileName : files) {
			dao.replaceAttach(fileName, pno);
		}
	}
	@Override
	public void delete(Integer pno) throws Exception {
	 	dao.delete(pno);
	 	dao.deleteAttach(pno);
	}
	@Override
	public List<ProductVO> list() throws Exception {
		return dao.list();    
	}
	@Override
	public List<ProductVO> healthy_list() throws Exception {
		return dao.healthy_list();
	}
	@Override
	public List<ProductVO> object_list() throws Exception {
		return dao.object_list();
	}
	@Override
	public List<ProductVO> mass_list() throws Exception {
		return dao.mass_list();
	}
	@Override
	public List<String> getAttach(Integer pno) throws Exception {
		return dao.getAttach(pno);
	}
	@Override
	public int insertcartlist(Map<String, Object> dataMap) throws Exception {
		int exsistFlag = dao.checkcart(dataMap);
		if(exsistFlag >0) {
			return 0;
		}
		else {
			return dao.insertcartlist(dataMap);
		}
	}
	@Override
	public List<ProductVO> readcartlist(Map<String, Object> dataMap) throws Exception {
		return dao.readcartlist(dataMap);
	}
	@Override
	public int delete_cartlist(Map<String, Object> dataMap) {
		return dao.delete_cartlist(dataMap);
	}
}