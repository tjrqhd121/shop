package com.inhatc.service;


import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.inhatc.vo.ProductVO;
import com.inhatc.vo.RegisterVO;
import com.inhatc.dao.RegisterDAO;

@Service
public class RegisterServiceImpl implements RegisterService {

	@Inject
	private RegisterDAO dao;
	
	@Override
	public int register(RegisterVO vo){
		// TODO Auto-generated method stub
		System.out.println(vo+"Test");
		return dao.register(vo);
	}

	public int idcheck(RegisterVO vo){
	      return dao.idcheck(vo);
	   }

	@Override
	public RegisterVO read_profile(String id) throws Exception {
		return dao.read_profile(id);
	}

	@Override
	public void update_profile(RegisterVO vo) {
		dao.update_profile(vo);
		
	}
	
	@Override
	public List<RegisterVO> read_memberlist() throws Exception {
		return dao.read_memberlist();
	}

	@Override
	public RegisterVO modifymember(String id) {
		return dao.modifymember(id);
	}

	@Override
	public void update_member(RegisterVO vo) {
		dao.update_profile(vo);	
	}
	
	@Override
	public void delete_member(String id) {
		dao.delete_member(id);	
	}
}