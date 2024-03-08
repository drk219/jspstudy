package com.gdu.prj.service;

import com.gdu.prj.common.ActionForward;

import jakarta.servlet.http.HttpServletRequest;

public interface BoardService {
  
  ActionForward addBoard(HttpServletRequest request);          // 게시글 추가 하기 
  ActionForward getBoardList(HttpServletRequest request);      // 게시글 목록 보기
  ActionForward getBoardByNo(HttpServletRequest request);  // 게시글 번호 선택해서 상세 보기
  ActionForward editBoard(HttpServletRequest request);         // 편집화면으로 넘어가는
  ActionForward modifyBoard(HttpServletRequest request);       // 데이터 베이스를 수정하는 서비스 
  ActionForward removeBoard(HttpServletRequest request);       // 게시글 삭제 하기

}
