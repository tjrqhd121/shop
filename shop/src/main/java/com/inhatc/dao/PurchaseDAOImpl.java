package com.inhatc.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.inhatc.vo.PurchaseVO;

@Repository
public class PurchaseDAOImpl implements PurchaseDAO{
	
	@Inject
	private SqlSession session;
	
	private static String namespace = "com.js.mapper.PurchaseMapper";
	
	@Override
	public void insert(PurchaseVO vo) throws Exception {		
		session.insert(namespace+".create", vo);
	}
	@Override
	public PurchaseVO read(Integer pno) throws Exception{
		return session.selectOne(namespace+".read", pno);
	}
	@Override
	public void update(PurchaseVO vo) throws Exception {
		 session.update(namespace+".update", vo);
	}
	@Override
	public void delete(Integer pno) throws Exception {
		 session.delete(namespace+".delete", pno);
	}
	@Override
	public List<PurchaseVO> list() throws Exception {
		return session.selectList(namespace+".list");
	}
}