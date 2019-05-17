package com.inhatc.dao;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;


import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import com.inhatc.vo.RegisterVO;

@Repository
public class RegisterDAOImpl implements RegisterDAO {
	@Inject
	private SqlSession session;
	@Inject
	private static String namespace = "com.js.mapper.registerMapper";

	@Override
	public int register(RegisterVO vo){

		HashMap<String, Object> paramMap = new HashMap<String, Object>();

		paramMap.put("id", vo.getId());
		paramMap.put("pw", vo.getPw());
		paramMap.put("email", vo.getEmail());
        paramMap.put("name", vo.getName());
        paramMap.put("phone", vo.getPhone());
        paramMap.put("postadd", vo.getPostadd());
        paramMap.put("roadadd", vo.getRoadadd());
        paramMap.put("jibadd", vo.getJibadd());        
		System.out.println("id : "+vo.getId());
		System.out.println("pw : "+vo.getPw());
		System.out.println("email : "+vo.getEmail());
		System.out.println("name : "+vo.getName());       
		System.out.println("phone"+ vo.getPhone());       
		System.out.println("postadd"+ vo.getPostadd());       
		System.out.println("roadadd"+ vo.getRoadadd());       
		System.out.println("jibadd"+ vo.getJibadd());
		int result = session.insert(namespace+".register",paramMap);
		return result;
	}

	public int idcheck(RegisterVO vo) {
	      HashMap<String, Object> paramMap = new HashMap<String, Object>();
	      paramMap.put("id", vo.getId());
	      System.out.println("id : "+vo.getId());
	      int result=session.selectOne(namespace+".idcheck", vo);
	      System.out.println("DAOImpl°á°ú : "+result);
	      return result;
	   }

	@Override
	public RegisterVO read_profile(String id) {
		return session.selectOne(namespace+".read_profile", id);
	}
	@Override
	public void update_profile(RegisterVO vo) {
		session.update(namespace+".update_profile", vo);
	}
	
	@Override
	public List<RegisterVO> read_memberlist() {
		return session.selectList(namespace+".read_memberlist");
	}

	@Override
	public RegisterVO modifymember(String id) {
		return session.selectOne(namespace+".read_profile", id);
	}
	
	@Override
	public void delete_member(String id) {
		session.delete(namespace+".delete_member", id);
	}
}