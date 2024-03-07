package model;

import common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;

public interface MyInterface {
  
  // public, abstract 생략 가능
  
  ActionForward getDate(HttpServletRequest request);
  ActionForward getTime(HttpServletRequest request);
  ActionForward getDateTime(HttpServletRequest request);
  
}