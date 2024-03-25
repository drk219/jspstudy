package service;

import java.io.IOException;

import common.ActionForward;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class StudentServiceImpl implements StudentService{
  
  private StudentDao studentDao = StudentDao.getDao();
  
  @Override
  public void studentAdd(HttpServletRequest request, HttpServletResponse response) throws IOException {
    
    
    
  }
  
  @Override
  public ActionForward studentDetail(HttpServletRequest request) {
    
    
    
    return null;
  }
  
  @Override
  public ActionForward studentQuery(HttpServletRequest request) {
    
    
    
    return null;
  }
  
  @Override
  public ActionForward studentList(HttpServletRequest request) {
    
    
    
    return null;
  }
  
  @Override
  public void studentModify(HttpServletRequest request, HttpServletResponse response) throws IOException {
    
    
    
  }
  
  @Override
  public void studentDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
    
    
    
  }

  
}
