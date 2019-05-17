package com.inhatc.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.inhatc.vo.QnaVO;
import com.inhatc.vo.Criteria;
import com.inhatc.dao.QnaDAO;
import com.inhatc.vo.Search;

@Service
public class QnaServiceImpl implements QnaService {
	
	@Inject
	private QnaDAO dao;
	@Override
	public List<QnaVO> listAll() throws Exception {
		// TODO Auto-generated method stub
		return dao.listAll();
	}
	
	@Override
	public QnaVO read(int bno) throws Exception {
		return dao.read(bno);
	}
	
	@Override
	public int add_hits(int bno) throws Exception {
		return dao.add_hits(bno);
	}
	
	@Override
	public void update_post(QnaVO vo) throws Exception {
	    dao.update_post(vo);
	}
	
	@Override
	public void delete_post(int bno) throws Exception {
		System.out.println(dao.delete_post(bno));
	    dao.delete_post(bno);
	}
	@Override
	public void write_post(QnaVO vo) throws Exception {
	 	dao.write_post(vo);    
	}
	
	@Override
	public int getCount() throws Exception {
		return dao.getCount();
	}
	
	@Override
	public List<QnaVO> selectPage(Criteria cri) throws Exception {
		return dao.selectPage(cri);
	}
	
	@Override
	public List<QnaVO> search(Search sch, Criteria cri) throws Exception {
		return dao.search(sch, cri);
	}
	
	@Override
	public int searchQnaCount(Search sch) throws Exception {
		return dao.searchQnaCount(sch);
	}
}