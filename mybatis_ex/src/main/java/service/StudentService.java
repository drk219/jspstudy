package service;

import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import common.ActionForward;

public interface StudentService {
  
  public void studentAdd(HttpServletRequest request, HttpServletResponse response) throws IOException;
  public ActionForward studentDetail(HttpServletRequest request);
  public ActionForward studentQuery(HttpServletRequest request);
  public ActionForward studentList(HttpServletRequest request);
  public void studentModify(HttpServletRequest request, HttpServletResponse response) throws IOException;
  public void studentDelete(HttpServletRequest request, HttpServletResponse response) throws IOException;
  
}