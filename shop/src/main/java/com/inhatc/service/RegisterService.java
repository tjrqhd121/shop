package com.inhatc.service;


import java.util.List;

import com.inhatc.vo.RegisterVO;

public interface RegisterService {
	public int register(RegisterVO vo);
	public int idcheck(RegisterVO vo);
	public RegisterVO read_profile(String id) throws Exception;
	public void update_profile(RegisterVO vo);
	public List<RegisterVO> read_memberlist() throws Exception;
	public RegisterVO modifymember(String id);
	public void update_member(RegisterVO vo);
	public void delete_member(String id);
}