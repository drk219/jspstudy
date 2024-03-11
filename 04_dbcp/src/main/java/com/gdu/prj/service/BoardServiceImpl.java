package com.gdu.prj.service;

import java.util.List;
import java.util.Optional;

import com.gdu.prj.common.ActionForward;
import com.gdu.prj.dao.BoardDao;
import com.gdu.prj.dao.BoardDaoImpl;
import com.gdu.prj.dto.BoardDto;

import jakarta.servlet.http.HttpServletRequest;

/*
 * view - (filter) - controller - service - dao - db
 */

public class BoardServiceImpl implements BoardService {
  
  // service 는 Dao 를 호출한다.
  private BoardDao boardDao = BoardDaoImpl.getInstance();  // 접근할 수 있는 getInstance로 호출
  
  @Override
  public ActionForward addBoard(HttpServletRequest request) {  // 게시글 추가 서비스
    String title = request.getParameter("title");
    String contents = request.getParameter("contents");
    BoardDto board = BoardDto.builder()
                             .title(title)
                             .contents(contents)
                             .build();
    int insertCount = boardDao.insertBoard(board);
    
    // redirect 경로는 URLMapping 으로 작성한다.
    String view = null;
    if(insertCount == 1) {
      view = request.getContextPath() + "/board/list.brd";     // redirect 는 contextPath 부터 작성해야함 + '.jsp' 가 아닌 '.brd' 로 작성해야한다
    } else if(insertCount == 0) {
      view = request.getContextPath() + "/main.brd";   
    }
    
    // INSERT 이후 이동은 redirect
    return new ActionForward(view, true);  // -> redirect
  }

  @Override
  public ActionForward getBoardList(HttpServletRequest request) {  // 게시글 목록 가져오기 서비스
    int boardCount = boardDao.getBoardCount();
    List<BoardDto> boardList = boardDao.selectBoardList(null);
    request.setAttribute("boardCount", boardCount);
    request.setAttribute("boardList", boardList);
    return new ActionForward("/board/list.jsp", false);
  }

  @Override
  public ActionForward getBoardByNo(HttpServletRequest request) {  // 게시글 번호로 상세보기 서비스
    Optional<String> opt = Optional.ofNullable(request.getParameter("board_no"));
    int board_no = Integer.parseInt(opt.orElse("0"));
    BoardDto board = boardDao.selectBoardByNo(board_no);
    String view = null;
    if(board != null) {
      view = "/board/detail.jsp";
      request.setAttribute("board", board);
    } else {
      view = "/index.jsp";
    }
    return new ActionForward(view, false);  // false => forward 는 .jsp 로 이동
  }

  @Override
  public ActionForward editBoard(HttpServletRequest request) {    // 게시글 수정 서비스
    String param = request.getParameter("board_no");
    int board_no = 0;
    if(!param.isEmpty()) {   // 빈 문자열이 아니라면
      board_no = Integer.parseInt(param);
    }
    BoardDto board = boardDao.selectBoardByNo(board_no);
    String view = null;
    if(board != null) {
      view = "/board/edit.jsp";
      request.setAttribute("board", board);
    } else {
      view = "/index.jsp";
    }
    return new ActionForward(view, false);   // forward
  }

  @Override
  public ActionForward modifyBoard(HttpServletRequest request) {    // 수정에 성공하면 편집 화면으로 간다
    int board_no = Integer.parseInt(request.getParameter("board_no"));
    String title = request.getParameter("title");
    String contents = request.getParameter("contents");
    BoardDto board = BoardDto.builder()
                             .title(title)
                             .contents(contents)
                             .board_no(board_no)
                             .build();
    int updateCount = boardDao.updateBoard(board);
    String view = null;
    if(updateCount == 0) {
      view = request.getContextPath() + "/main.brd";  
    } else {
      view = request.getContextPath() + "/board/detail.brd?board_no=" + board_no;
    }
    return new ActionForward(view, true);   // redirect
  }

  @Override
  public ActionForward removeBoard(HttpServletRequest request) {   // 게시글 한개 삭제 서비스
    String param = request.getParameter("board_no");
    int board_no = 0;
    if(!param.isEmpty()) {
      board_no = Integer.parseInt(param);
    }
    int deleteCount = boardDao.deleteBoard(board_no);
    String view = null;
    if(deleteCount == 0) {
      view = request.getContextPath() + "/main.brd";   
    } else {
      view = request.getContextPath() + "/board/list.brd";
    }
    return new ActionForward(view, true);  // redirect
  }
  
  @Override
  public ActionForward removeBoards(HttpServletRequest request) {   // 게시글 여러개 삭제 서비스 
    String param = request.getParameter("param");
    int deleteCount = boardDao.deleteBoards(param);
    String view = null;
    if(deleteCount == 0) {
      view = request.getContextPath() + "/main.brd";
    } else {
      view = request.getContextPath() + "/board/list.brd";
    }
    return new ActionForward(view, true);
  }

}
