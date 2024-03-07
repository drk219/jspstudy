package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;

public class MyInterfaceImpl implements MyInterface {
  
  @Override
  public ActionForward getDate(HttpServletRequest request) {  // 내 결과를 뭐뭐.jsp에 반환해서 보여줄게요 (화면을 넘겨준다) => forward 사용
                                                       // 날짜와 시간은 request 에 저장해서 전달한다.
    request.setAttribute("date", DateTimeFormatter.ofPattern("yyyy. MM. dd.").format(LocalDate.now()));
    return new ActionForward("/view/date.jsp", false);  // 날짜에 관한 요청은 date.jsp 뷰 쪽에서 해결할거다 (경로만 반환시켜준다.)
  }

  @Override
  public ActionForward getTime(HttpServletRequest request) {
    
    request.setAttribute("time", DateTimeFormatter.ofPattern("HH:mm:ss.SSS").format(LocalTime.now()));
    return new ActionForward("/view/time.jsp", false); // 시간에 관한 요청은 time.jsp 뷰 쪽에서 해결할거다 (경로만 반환시켜준다.)
  }

  @Override
  public ActionForward getDateTime(HttpServletRequest request) {
    
    request.setAttribute("datetime", DateTimeFormatter.ofPattern("yyyy. MM. dd. HH:mm:ss.SSS").format(LocalDateTime.now()));
    return new ActionForward("/view/datetime.jsp", false);
  }

}
