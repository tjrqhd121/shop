package com.inhatc.dao;

import java.util.List;

import com.inhatc.vo.RegisterVO;;
public interface RegisterDAO {
	public int register(RegisterVO vo);
	public int idcheck(RegisterVO vo);
	public RegisterVO read_profile(String id);
	public void update_profile(RegisterVO vo);
	public List<RegisterVO> read_memberlist();
	public RegisterVO modifymember(String id);
	public void delete_member(String id);
}