package com.test.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.board.domain.ContentVO;
import com.test.board.domain.ReplyVO;

@Repository
public class ContentDaoImpl implements ContentDao{

	private SqlSessionTemplate sqlSessionTemplate;

	@Autowired
	public ContentDaoImpl(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	// 클래스 게시 기능

	@Override
	public List<ContentVO> mainList() {
		return sqlSessionTemplate.selectList("mainList");
	}

	@Override
	public List<ContentVO> onoffList(int on_off) {
		return sqlSessionTemplate.selectList("onoffList", on_off);
	}

	@Override
	public List<ContentVO> bigcateList(String big_name, int on_off) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("big_name", big_name);
		paramMap.put("on_off", on_off);
		return sqlSessionTemplate.selectList("bigcateList", paramMap);
	}

	@Override
	public List<ContentVO> smallcateList(String small_name, int on_off) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("small_name", small_name);
		paramMap.put("on_off", on_off);
		return sqlSessionTemplate.selectList("smallcateList", paramMap);
	}

	@Override
	public List<ContentVO> newList(int on_off) {
		return sqlSessionTemplate.selectList("newList", on_off);
	}

	@Override
	public List<ContentVO> hotList() {
		return sqlSessionTemplate.selectList("hotList");
	}

	@Override
	public List<ContentVO> areaList(String area, int on_off) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("area", area);
		paramMap.put("on_off", on_off);
		
		return sqlSessionTemplate.selectList("areaList", paramMap);
	}

	@Override
	public void formInsert(ContentVO contentVO) {
		sqlSessionTemplate.insert("formInsert", contentVO);
	}

	@Override
	public void classInsert(ContentVO contentVO) {
		sqlSessionTemplate.insert("classInsert", contentVO);
	}

	@Override
	public ContentVO select(int cid) {
		ContentVO contentVO = (ContentVO) sqlSessionTemplate.selectOne("select", cid);
		return contentVO;
	}

	@Override
	public int update(ContentVO contentVO) {
		return sqlSessionTemplate.update("update", contentVO);
	}

	@Override
	public int delete(ContentVO contentVO) {
		return sqlSessionTemplate.delete("delete", contentVO);
	}


	// 댓글기능

	@Override
	public List<ReplyVO> repList(int cid) {
		return sqlSessionTemplate.selectList("repList", cid);
	}

	@Override
	public void repInsert(ReplyVO replyVO) {
		sqlSessionTemplate.insert("repInsert", replyVO);
	}

	@Override
	public int repUpdate(ReplyVO replyVO) {
		return sqlSessionTemplate.update("repUpdate", replyVO);
	}

	@Override
	public int repDelete(int rid) {
		return sqlSessionTemplate.delete("repDelete", rid);
	}

}
