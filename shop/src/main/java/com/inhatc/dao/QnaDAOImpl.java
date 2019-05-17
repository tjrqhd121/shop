package com.inhatc.dao;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.inhatc.vo.QnaVO;
import com.inhatc.vo.Criteria;
import com.inhatc.vo.Search;

@Repository
public class QnaDAOImpl implements QnaDAO {
	
	@Inject
	private SqlSession session;
	
	private static String namespace = "com.js.mapper.QnaMapper";

	@Override
	public List<QnaVO> listAll() throws Exception {
		return session.selectList(namespace+".allSelect");
	}

	@Override
	public QnaVO read(int bno) throws Exception {
		return session.selectOne(namespace+".read", bno);
	}

	@Override
	public int add_hits(int bno) throws Exception {
		return session.update(namespace+".add_hits", bno);
	}
	
	@Override
	public int update_post(QnaVO vo) throws Exception {
		return session.update(namespace+".update_post", vo);
	}

	@Override
	public int delete_post(int bno) throws Exception {
		return session.delete(namespace+".delete_post", bno);
	}

	@Override
	public void write_post(QnaVO vo) throws Exception {
		System.out.println(vo.getTitle());
		session.insert(namespace+".write_post", vo);
	}
	 
	@Override
	public int getCount() throws Exception {
		return session.selectOne(namespace+".getCount");
	}

	@Override
	public List<QnaVO> selectPage(Criteria cri) throws Exception {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		if(cri.getNumberOfRecords() != 0)
		{
			paramMap.put("start", (cri.getCurrentPageNo() - 1) * cri.getMaxPost());
			paramMap.put("end", cri.getMaxPost());
		}
		else
		{
			paramMap.put("start", 0);
			paramMap.put("end", cri.getMaxPost());			
		}
		//System.out.println(paramMap.get("start") + " " + paramMap.get("end"));
		return session.selectList(namespace+".selectPage", paramMap);
	}
	
	@Override
	public List<QnaVO> search(Search sch, Criteria cri) throws Exception {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		if(cri.getNumberOfRecords() != 0)
		{
			paramMap.put("start", (cri.getCurrentPageNo() - 1) * cri.getMaxPost());
			paramMap.put("end", cri.getMaxPost());
		}
		else
		{
			paramMap.put("start", 0);
			paramMap.put("end", cri.getMaxPost());			
		}
		paramMap.put("searchType", sch.getSearchType());
		paramMap.put("search", sch.getSearch());
		return session.selectList(namespace+".search_qna", paramMap);
	}

	@Override
	public int searchQnaCount(Search sch) throws Exception {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("searchType", sch.getSearchType());
		paramMap.put("search", sch.getSearch());
		return session.selectOne(namespace+".search_qna_count", paramMap);
	}
}