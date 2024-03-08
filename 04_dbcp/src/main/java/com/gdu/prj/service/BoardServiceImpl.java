package com.gdu.prj.service;

import java.util.List;
import java.util.Optional;

import com.gdu.prj.common.ActionForward;
import com.gdu.prj.dao.BoardDao;
import com.gdu.prj.dao.BoardDaoImpl;
import com.gdu.prj.dto.BoardDto;

import jakarta.servlet.http.HttpServletRequest;

/*
 * view - controller - service - dao - db
 */

public class BoardServiceImpl implements BoardService {
  
  // service 는 Dao 를 호출한다.
  private BoardDao boardDao = BoardDaoImpl.getInstance();
  
  @Override
  public ActionForward addBoard(HttpServletRequest request) {
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
      view = request.getContextPath() +"/board/list.brd";     // redirect 는 contextPath 부터 작성해야함 + '.jsp' 가 아닌 '.brd' 로 작성해야한다
    } else if(insertCount == 0) {
      view = request.getContextPath() + "/main.brd";   
    }
    
    // INSERT 이후 이동은 redirect
    return new ActionForward(view, true);
  }

  @Override
  public ActionForward getBoardList(HttpServletRequest request) {
    int boardCount = boardDao.getBoardCount();
    List<BoardDto> boardList = boardDao.selectBoardList(null);
    request.setAttribute("boardCount", boardCount);
    request.setAttribute("boardList", boardList);
    return new ActionForward("/board/list.jsp", false);
  }

  @Override
  public ActionForward getBoardByNo(HttpServletRequest request) {
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
  public ActionForward editBoard(HttpServletRequest request) {
    
    return null;
  }

  @Override
  public ActionForward modifyBoard(HttpServletRequest request) {
    
    return null;
  }

  @Override
  public ActionForward removeBoard(HttpServletRequest request) {
    
    return null;
  }

}
