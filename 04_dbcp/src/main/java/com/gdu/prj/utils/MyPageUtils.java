package com.gdu.prj.utils;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data

public class MyPageUtils {
  
  private int total;     // 전체 게시글 (데이터베이스에서 구한다.) -> getBoardCount
  private int display;   // 한 화면(page)에 몇개씩 보여줄지 표시할 개수  (10개씩 20개씩 30개씩 보기는 요청 파라미터로 받는다.)
  private int page;      // 현재 페이지 번호 (요청 파라미터로 받는다.)
  private int begin;     // 한 페이지에 표시할 게시글의 시작 번호 (계산한다.)
  private int end;       // 한 페이지에 표시할 게시글의 종료 번호 (계산한다.)
  
  private int pagePerBlock = 10;  // 한 블록당 표시할 페이지 링크는 10개      (임의로 결정)
  private int totalPage;          // 전체 페이지 개수
  private int beginPage;          // 한 블록에 표시할 페이지 링크의 시작 번호 (계산한다.)
  private int endPage;            // 한 블록에 표시할 페이지 링크의 종료 번호 (계산한다.)
  
  
  public String getPaging(String requestURI, String sort, int display) {   // 페이지 화면 눌렀을때 작동 
    
    StringBuilder builder =  new StringBuilder();
    
    // <
    if(beginPage == 1) {
      builder.append("<div class=\"dont-click\">&lt;</div>");
    } else {
      builder.append("<div><a href=\"" + requestURI + "?page=" + (beginPage - 1) + "&sort=" + sort + "&display=" + display + "\">&lt;</a></div>");
    }
    
    // 1 2 3 4 5 6 7 8 9 10
    for(int p = beginPage; p <= endPage; p++) {
      if(p == page) {
        builder.append("<div><a class=\"current-page\" href=\"" + requestURI + "?page=" + p + "&sort=" + sort + "&display=" + display + "\">" + p + "</a></div>");
      } else {
        builder.append("<div><a href=\"" + requestURI + "?page=" + p + "&sort=" + sort + "&display=" + display + "\">" + p + "</a></div>");
      }
    }
    
    // >
    if(endPage == totalPage) {
      builder.append("<div class=\"dont-click\">&gt;</div>");
    } else {
      builder.append("<div><a href=\"" + requestURI + "?page=" + (endPage + 1) + "&sort=" + sort + "&display=" + display +"\">&gt;</a></div>");
    }
    
    return builder.toString();
  }
  

  public void setPaging(int total, int display, int page) {   // 페이지에 표시될 게시글 수, 페이지 블록당 표시할 페이지 설정
    
    /* 한 페이지에 표시할 게시글 개수 설정 */
    
    this.total = total;
    this.display = display;
    this.page = page;
    
    /*
     * begin end 값은 게시글의 board_no가 아니라 db 의 행번호로 설정해야 한다.
     * => 중간에 게시글을 삭제하면 게시글 번호가 엉키기 때문
     * 
     * total    1000
     * display  20
     * 
     * page=1   1    20
     * page=2   21   40
     * page=3   41   60
     * page=4   61   80
     *        .
     *        .
     *        .
     * page=50  981  1000
     */
    
    begin = (page - 1) * display + 1;
    end = begin + display - 1;
    
    
    /* 블록당 표시할 페이지 설정 */
    
    totalPage = (int)Math.ceil((double)total / display);          // double 타입으로 나오는 전체게시글/표시된게시글 을 정수올림해서 int 타입으로
    beginPage = ((page - 1) / pagePerBlock) * pagePerBlock + 1;   // 시작 페이지 계산
    endPage = Math.min(totalPage, beginPage + pagePerBlock - 1);  // 종료 페이지 계산
    //       ----------
    //       -> 게시글이 1002개라면 남는 페이지 계산을 하고 나머지 값 2개의 게시글 때문에 다 많은 페이지블록이 생기는것을 막기 위해서 최소값 설정
  
  }
  
}

