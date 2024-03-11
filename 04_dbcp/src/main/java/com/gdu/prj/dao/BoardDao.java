package com.gdu.prj.dao;

import java.util.List;
import java.util.Map;

import com.gdu.prj.dto.BoardDto;


// DAO : 데이터베이스나 다른 영속성 저장소에 대한 접근을 추상화하여 제공하는 객체
//       데이터베이스 연결을 설정하고, 쿼리를 실행하고, 데이터를 가져오는 역할
//       데이터베이스의 테이블에 대한 CRUD(Create, Read, Update, Delete) 기능을 제공
public interface BoardDao {
  
  int insertBoard(BoardDto board);                          // 삽입하기로 한 행의 개수가 0 이면 실패 
  int updateBoard(BoardDto board);                          // 수정하기로 한 행의 개수가 0 이면 실패
  int deleteBoard(int board_no);                            // 삭제할 게시글의 번호를 int 로 삭제 가능
  int deleteBoards(String param);                           // 삭제할 게시글의 번호를 문자열로 받아 삭제 가능 
  List<BoardDto> selectBoardList(Map<String, Object> params);  // 게시판의 ## ~ ##까지 목록을 가져온다
  int getBoardCount();                                      // 모든 게시글의 개수
  BoardDto selectBoardByNo(int board_no);                   // 게시글 번호로 해당 게시글 상세보기
  
  void close();  // 닫기

}
