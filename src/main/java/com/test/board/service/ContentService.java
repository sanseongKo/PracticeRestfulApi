package com.test.board.service;

import java.util.List;

import com.test.board.domain.ContentVO;
import com.test.board.domain.ReplyVO;

public interface ContentService {
	// 클래스 게시 기능

	// 메인 리스트
	public abstract List<ContentVO> mainList();
	// 빅카테고리 리스트
	public abstract List<ContentVO> bigcateList(String big_name, int on_off);
	// 스몰카테고리 리스트
	public abstract List<ContentVO> smallcateList(String small_name, int on_off);
	// 신규 리스트
	public abstract List<ContentVO> newList(int on_off);
	// onoff 리스트
	public abstract List<ContentVO> onoffList(int on_off);
	// 인기 리스트
	public abstract List<ContentVO> hotList();
	// 지역 리스트
	public abstract List<ContentVO> areaList(String area, int on_off);

	// 판매글 공지 게시
	public abstract void formInsert(ContentVO contentVO);
	// 판매글 게시
	public abstract void classInsert(ContentVO contentVO);

	// 판매글 선택 - 읽기기능
	public abstract ContentVO select(int cid);

	// 판매글 수정
	public abstract int update(ContentVO contentVO);

	// 판매글 삭제
	public abstract int delete(ContentVO contentVO);


	// 댓글 기능

	public abstract List<ReplyVO> repList(int cid);

	public abstract void repInsert(ReplyVO replyVO);

	public abstract int repUpdate(ReplyVO replyVO);

	public abstract int repDelete(int rid);

}