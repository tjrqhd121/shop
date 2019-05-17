package com.inhatc.dao;

import java.util.List;

import com.inhatc.vo.QnaVO;
import com.inhatc.vo.Criteria;
import com.inhatc.vo.Search;

public interface QnaDAO {
	public List<QnaVO> listAll() throws Exception;
	
	public QnaVO read(int bno) throws Exception;
	
	public int add_hits(int bno) throws Exception;
	
	public int update_post(QnaVO vo) throws Exception;
	
	public int delete_post(int bno) throws Exception;
	
	public void write_post(QnaVO vo) throws Exception;
	
	public int getCount() throws Exception;
	
	public List<QnaVO> selectPage(Criteria cri) throws Exception;
	
	public List<QnaVO> search(Search sch, Criteria cri) throws Exception;
	
	public int searchQnaCount(Search sch) throws Exception;
	
}