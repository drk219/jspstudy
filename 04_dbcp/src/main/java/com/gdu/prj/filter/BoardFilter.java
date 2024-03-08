package com.gdu.prj.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BoardFilter extends HttpFilter implements Filter {
  
  /*
   * HTTP 요청 -> WAS -> 필터1 -> 필터2 -> 필터3 -> Dispatcher Servlet -> 컨트롤러
   * 
   * 필터와 인터셉터는 웹과 관련된 공통 관심사 (로그인, 사용자 권한)를 처리할 때 주로 사용된다.
   * 물론 공통 관심사는 AOP로도 해결할 수 있지만, 
   * 웹과 관련된 공통 관심사를 처리할 때에는 HTTP header 나 URL 정보 등이 필요한데, 
   * 필터와 인터셉터의 경우 HttpServletRequest를 제공하기 때문이다.
   */
  
  /*
   * 공통적인 기능들을 서블릿이 호출되기 전에 수행(전처리)되게 하고 싶거나
   * 서블릿이 호출 되고 난 뒤에 수행(후처리) 하고 싶으면 공통적인 기능들을 서블릿 필터로 구현하면 된다.
   */
       

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

	  // HttpServletRequest / HttpServletResponse
	  HttpServletRequest req = (HttpServletRequest) request;
	  HttpServletResponse res = (HttpServletResponse) response;
	  
	  // 요청 UTF-8 인코딩
    req.setCharacterEncoding("UTF-8");
    
    // 요청 방식 / 주소 확인
    System.out.print(String.format("%-4s", req.getMethod()));
    System.out.print(" | ");
    System.out.println(req.getRequestURI());
    
    // 요청 파라미터 확인
    Map<String, String[]> params = req.getParameterMap();
    for(Map.Entry<String, String[]> param : params.entrySet()) {
      System.out.print(String.format("%7s", " "));
      System.out.print(String.format("%-10s", param.getKey())+ " : ");
      System.out.println(Arrays.toString(param.getValue()));
    }
	  
	  // doFilter 이전
		chain.doFilter(request, response);
		
	}
}