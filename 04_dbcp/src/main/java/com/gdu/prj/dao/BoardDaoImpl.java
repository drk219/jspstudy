package com.gdu.prj.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.gdu.prj.dto.BoardDto;

/*
 * view - controller - service - dao - db
 */

//UserApp 동작 흐름 (자바에서 했던 내용들)
/*
*    < UserApp >           < UserController >                                    < UserService >                               < UserDao >
*   
*   작업 선택       ->
*   View 실행 결과  -> 
*                      String requestHandle(작업 선택, View 실행 결과) {
*                        작업에 따른 서비스 호출(View 실행 결과 전달)    -> 
*   작업결과메시지  <-   작업결과메시지 반환
*   확인               }
*                                                                            반환타입 선택된메소드(View 실행 결과) {
*                                                                              DAO 메소드 호출(View 실행 결과)         ->
*                                                                        <-    반환값 반환         
*                                                                            }
*                                                                                                                         반환타입 선택된메소드(View 실행 결과) {
*                                                                                                                           DB 처리
*                                                                                                                      <-   반환값 반환
*/

public class BoardDaoImpl implements BoardDao {
  
  // dao 는 db 를 처리한다.
  private Connection con;
  private PreparedStatement ps;
  private ResultSet rs;
  
  // Connection pool 관리를 위한 DataSource 객체 선언
  private DataSource dataSource;
  
  // SingletonPattern 
  private static BoardDao boardDao = new BoardDaoImpl();
  private BoardDaoImpl() {    // 다른 곳에서 못 쓰게 프라이빗 처리
    // META-INFO\context.xml 파일에 명시된 자원(resource)를 이용해 DataSource 객체 생성
    try {
      Context context = new InitialContext();
      Context env = (Context)context.lookup("java:comp/env");   // 읽어드릴떄 필요한 네이밍
      dataSource = (DataSource)env.lookup("jdbc/myoracle");
    } catch (NamingException e) {
      System.out.println("관련 자원을 찾을 수 없습니다.");
    }
  }
  public static BoardDao getInstance() {
    return boardDao;
  }

  @Override
  public int insertBoard(BoardDto board) {
    int insertCount = 0;
    try {
      con = dataSource.getConnection();      // 커넥션 요청
      String sql = "INSERT INTO BOARD_T(BOARD_NO, TITLE, CONTENTS, MODIFIED_AT, CREATED_AT) VALUES(BOARD_SEQ.NEXTVAL, ?, ?, CURRENT_DATE, CURRENT_DATE)";  // 쿼리문 작성
      ps = con.prepareStatement(sql);        // 쿼리문 전달
      ps.setString(1, board.getTitle());     // 1번째 물음표에 타이틀 가져오기
      ps.setString(2, board.getContents());  // 2번째 물음표에 내용 가져오기
      insertCount = ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      close();   // this.close();
    }
    return insertCount;
  }

  @Override
  public int updateBoard(BoardDto board) {
    int updateCount = 0;
    try {
      con = dataSource.getConnection();
      String sql = "UPDATE BOARD_T SET TITLE = ?, CONTENTS = ?, MODIFIED_AT = CURRENT_TIME WHERE BOARD_NO = ?";
      ps = con.prepareStatement(sql);
      ps.setString(1, board.getTitle());
      ps.setString(2, board.getContents());
      ps.setInt(2, board.getBoard_no());
      updateCount = ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      close();
    }
    return updateCount;
  }

  @Override
  public int deleteBoard(int board_no) {
    int deleteCount = 0;
    try {
      con = dataSource.getConnection();
      String sql = "DELETE FROM BOARD_T WHERE BOARD_NO = ?";
      ps = con.prepareStatement(sql);
      ps.setInt(1, board_no);
      deleteCount = ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
     close(); 
    }
    return deleteCount;
  }

  @Override
  public List<BoardDto> selectBoardList(Map<String, Object> map) {
    List<BoardDto> boardList = new ArrayList<>();
    try {
      con = dataSource.getConnection();
      String sql = "SELECT BOARD_NO, TITLE, CONTENTS, MODIFIED_AT, CREATED_AT FROM BOARD_T ORDER BY BOARD_NO DESC";
      ps = con.prepareStatement(sql);
      rs = ps.executeQuery();
      while(rs.next()) {
        BoardDto board = BoardDto.builder()
                                 .board_no(rs.getInt(1))
                                 .title(rs.getString(2))
                                 .contents(rs.getString(3))
                                 .modified_at(rs.getDate(4))
                                 .created_at(rs.getDate(5))
                                 .build();
        boardList.add(board);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      close();
    }
    return boardList;
  }

  @Override
  public int getBoardCount() {
    int boardCount = 0;
    try {
      con = dataSource.getConnection();
      String sql = "SELECT COUNT(*) FROM BOARD_T";
      ps = con.prepareStatement(sql);
      rs = ps.executeQuery();
      if(rs.next()) {
        boardCount = rs.getInt(1);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      close();
    }
    return boardCount;
  }

  @Override
  public BoardDto selectBoardByNo(int board_no) {
    BoardDto board = null;
    try {
      con = dataSource.getConnection();
      String sql = "SELECT BOARD_NO, TITLE, CONTENTS, MODIFIED_AT, CREATED_AT FROM BOARD_T WHERE BOARD_NO = ?";
      ps = con.prepareStatement(sql);
      ps.setInt(1, board_no);
      rs = ps.executeQuery();
      if(rs.next()) {
        board = BoardDto.builder()
                        .board_no(rs.getInt(1))
                        .title(rs.getString(2))
                        .contents(rs.getString(3))
                        .modified_at(rs.getDate(4))
                        .created_at(rs.getDate(5))
                        .build();
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      close();
    }
    return board;
  }

  @Override
  public void close() {
    try {
      if(rs != null) rs.close();
      if(ps != null) ps.close();
      if(con != null) con.close();  // Connection 반납으로 동작
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
