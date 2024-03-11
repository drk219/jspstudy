package com.gdu.prj.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.gdu.prj.common.ActionForward;
import com.gdu.prj.dao.BoardDao;
import com.gdu.prj.dao.BoardDaoImpl;
import com.gdu.prj.dto.BoardDto;
import com.gdu.prj.utils.MyPageUtils;

import jakarta.servlet.http.HttpServletRequest;

/*
 * view - (filter) - controller - service - dao - db
 */

public class BoardServiceImpl implements BoardService {
  
  // service 는 Dao 를 호출한다.
  private BoardDao boardDao = BoardDaoImpl.getInstance();  // 접근할 수 있는 getInstance로 호출
  
  // 목록보기는 MyPageUtils 객체가 필요하다.
  private MyPageUtils myPageUtils = new MyPageUtils();
  
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
    
    // 전체 게시글 개수 
    int total = boardDao.getBoardCount();
    
    /*
     * ?page=2&display=10 : 2 페이지로 가서 게시글을 10개씩 보여줘
     */
    
    // display : 한 페이지에 표시할 게시글 개수 (기본 20으로 설정)
    Optional<String> optDisplay = Optional.ofNullable(request.getParameter("display"));
    int display = Integer.parseInt(optDisplay.orElse("20"));
    
    // page : 현재 페이지 번호 (기본 1페이지로 설정)
    Optional<String> optPage = Optional.ofNullable(request.getParameter("page"));
    int page = Integer.parseInt(optPage.orElse("1"));
    
    // sort : 정렬 방식 (전달 안되면 내림차순 -> 최신글이 1페이지 1번으로 오도록)
    Optional<String> optSort = Optional.ofNullable(request.getParameter("sort"));
    String sort = optSort.orElse("DESC");
    
    // 페이징 처리에 필요한 변수 값 계산하기
    myPageUtils.setPaging(total, display, page);
    
    // 목록을 가져올때 필요한 파라미터를 Map 에 저장함 (selectBoardList에서 Map 으로 설정함, 나중에 검색으로도 사용할 수 있게)
    Map<String, Object> params = Map.of("begin", myPageUtils.getBegin(),     // 시작번호 파라미터
                                        "end", myPageUtils.getEnd(),         // 종료번호 파라미터
                                        "sort", sort);                       // 정렬 파라미터
    
    // 목록 가져오기
    List<BoardDto> boardList = boardDao.selectBoardList(params);
    
    // 페이지 링크 가져오기
    String paging = myPageUtils.getPaging(request.getRequestURI(), sort, display);
    
    // jsp 에 전달할 데이터들
    request.setAttribute("total", total);
    request.setAttribute("boardList", boardList);
    request.setAttribute("paging", paging);
    request.setAttribute("display", display);
    request.setAttribute("sort", sort);
    
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
