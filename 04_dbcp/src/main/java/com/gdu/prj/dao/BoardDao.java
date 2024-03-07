package com.gdu.prj.dao;

import java.util.List;
import java.util.Map;

import com.gdu.prj.dto.BoardDto;

public interface BoardDao {
  
  int insertBoard(BoardDto board);  // 삽입하기로 한 행의 개수, 0 이면 실패 
  int updateBoard(BoardDto board);  // 수정한 행의 개수
  int deleteBoard(int board_no);    // 삭제할 게시글의 번호를 알면 삭제 가능
  List<BoardDto> selectBoardList(Map<String, Object> map);  // 게시판의 ## ~ ##까지 목록을 가져온다
  int getBoardCount();              // 모든 게시글의 개수
  BoardDto selectBoardByNo(int board_no);  // 상세보기
  
  void close();  // 닫기

}
