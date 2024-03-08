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
 * view - (filter) - controller - service - dao - db
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
      Context env = (Context)context.lookup("java:comp/env");   // 읽어드릴때 필요한 네이밍
      dataSource = (DataSource)env.lookup("jdbc/myoracle");
    } catch (NamingException e) {
      System.out.println("관련 자원을 찾을 수 없습니다.");
    }
  }
  public static BoardDao getInstance() {  // 프라이빗 처리한 BoardDaoImpl을 다른 곳에서 사용할 수 있게 getInstance으로 따로 만들어준다. 
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
      close();   // this.close(); 사용 가능
    }
    return insertCount;
  }

  @Override
  public int updateBoard(BoardDto board) {
    int updateCount = 0;
    try {
      con = dataSource.getConnection();
      String sql = "UPDATE BOARD_T SET TITLE = ?, CONTENTS = ?, MODIFIED_AT = CURRENT_DATE WHERE BOARD_NO = ?";
      ps = con.prepareStatement(sql);
      ps.setString(1, board.getTitle());
      ps.setString(2, board.getContents());
      ps.setInt(3, board.getBoard_no());
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
  public int deleteBoards(String param) {
    int deleteCount = 0;
    try {
      con = dataSource.getConnection();
      String sql = "DELETE FROM BOARD_T WHERE BOARD_NO IN(" + param + ")";
      ps = con.prepareStatement(sql);
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
      rs = ps.executeQuery();   // select 는 rs 로 실행 
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
