package com.inhatc.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.inhatc.vo.ProductVO;

@Repository
public class ProductDAOImpl implements ProductDAO{
	
	@Inject
	private SqlSession session;
	
	private static String namespace = "com.js.mapper.ProductMapper";
	
	@Override
	public void insert(ProductVO vo) throws Exception {		
		session.insert(namespace+".create", vo);
	}
	@Override
	public ProductVO read(Integer pno) throws Exception{
		return session.selectOne(namespace+".read", pno);
	}
	@Override
	public void update(ProductVO vo) throws Exception {
		 session.update(namespace+".update", vo);
	}
	@Override
	public void delete(Integer pno) throws Exception {
		 session.delete(namespace+".delete", pno);
	}
	@Override
	public List<ProductVO> list() throws Exception {
		return session.selectList(namespace+".list");
	}
	@Override
	public void addAttach(String fullName) throws Exception {
		session.insert(namespace+".addAttach", fullName);
	}
	@Override
	public List<ProductVO> healthy_list() {
		return session.selectList(namespace+".healthy_list");
	}
	@Override
	public List<ProductVO> object_list() {
		return session.selectList(namespace+".object_list");
	}
	@Override
	public List<ProductVO> mass_list() {
		return session.selectList(namespace+".mass_list");
	}
	@Override
	public List<String> getAttach(Integer pno) {
		return session.selectList(namespace +".getAttach", pno);
	}
	@Override
	public int insertcartlist(Map<String, Object> dataMap) {
		return session.insert(namespace+".cartlist_insert",dataMap);
	}
	@Override
	public int checkcart(Map<String, Object> dataMap) {
		return session.selectOne(namespace+".cartlist_select",dataMap);
	}
	@Override
	public List<ProductVO> readcartlist(Map<String, Object> dataMap) {
		return session.selectList(namespace+".cartlist_read",dataMap);
	}
	@Override
	public int delete_cartlist(Map<String, Object> dataMap) {
		return session.delete(namespace+".delete_cartlist",dataMap);
	}
	@Override
	public void deleteAttach(Integer pno) throws Exception {
		session.delete(namespace+".deleteAttach", pno);
	}
	@Override
	public void replaceAttach(String fullName, Integer pno) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("pno", pno);
	    paramMap.put("fullName", fullName);
	    session.insert(namespace+".replaceAttach", paramMap);
	}
}