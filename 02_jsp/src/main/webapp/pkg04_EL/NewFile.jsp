<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="pkg04_EL.Book"%>  <%-- 자바에서 현 jsp 파일로 import 해준다 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%-- 
  < EL >
  
  1. Expression Language (표현 언어)
  2. Bind 영역에 저장된 값을 나타낼 수 있다.
  3. JSP 스크립트 요소 중 하나인 표현식(<%=값%>)을 대체할 수 있다.
  4. 형식
     : ${값}
--%>

<%-- 
  < JSP Bind 영역 (데이터 저장 영역) >
  
  1. pageContext : this(= NewFile_jsp) : 현재 JSP 인 NewFile.jsp 에서만 접근 가능
  2. request     : HttpServletRequest  : 응답 전까지 접근 가능 (1회용 저장소)
  3. session     : HttpSession         : 브라우저 닫기 전까지 접근 가능
  4. application : ServletContext      : 애플리케이션 종료전까지 접근 가능 (서비스 중지) 
--%>

<%-- C:\GDJ77\jspstudy\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\work\Catalina\localhost\jsp\org\apache\jsp --%>

<%-- 
  < JSP Bind 영역의 우선순위 >
  
  1. 동일한 이름의 속성(Attribute)이 여러 영역에 동시에 존재하는 경우 우선적으로 사용되는 속성이 있다.
  2.  <높은 순위>                        <낮은 순위>
     pageContext >> request >> session >> application
--%>

<%-- 
  < JSP Bind 영역의 EL 내장 객체 >
  
  1. pageContext : pageScope
  2. request     : requestScope
  3. session     : sessionScope
  4. application : applicationScope
--%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL</title>
</head>
<body>

  <%-- JSP Bind 영역에 데이터 저장하기 --%>
  <% 
    pageContext.setAttribute("a", 1);
    request.setAttribute("a", 2);
    session.setAttribute("a", 3);
    application.setAttribute("a", 4);
  %>
  
  <%-- JSP Bind 영역의 우선 순위 확인 --%>
  <div>${a}</div>     <%-- 1 : pageContext가 우선순위에서 제일 높기 때문에 가장 먼저 실행됨 --%>
  
  <hr>
  
  <%-- JSP Bind 영역의 EL 내장 객체 확인 --%>
  <div>${pageScope.a}</div>        <%-- 각 영역에 저장된 값을 출력 --%>
  <div>${requestScope.a}</div>
  <div>${sessionScope.a}</div>
  <div>${applicationScope.a}</div>

  <hr>
  
  <%-- JSP Bind 영역에 저장된 Java 객체 정보 확인 --%>
  <% 
    Book book = Book.builder()
                    .title("소나기")
                    .author("황순원")
                    .price(10000)
                    .build();     // 여기까지 만들어! 라고 알려준다
    pageContext.setAttribute("book", book); //객체 저장
  %>
  <div>${book.title}</div>  <%-- book.getTitle() 방식으로 호출됨 --%>
  <div>${book.author}</div> <%-- book.getAuthor() 방식으로 호출됨 --%>
  <div>${book.price}</div>  <%-- book.getPrice() 방식으로 호출됨 --%>
  
  <hr>
  
  <%-- JSP Bind 영역에 저장된 Map 정보 확인 --%>
  <% 
    Map<String, Object> map = Map.of("title", "어린왕자", 
                                     "author", "생텍쥐베리", 
                                     "price", "15000");
    pageContext.setAttribute("map", map);
  %>
  <div>${map.title}</div>
  <div>${map.author}</div>
  <div>${map.price}</div>
  
  <hr>
  
  <%-- List 에 Book 객체 3개 저장하고 EL로 확인하기 --%>
  <% 
    List<Book> books = Arrays.asList(
        new Book("해리포터", "조앤K롤링", 20000),
        new Book("미비포유", "조조모예스", 15000),
        new Book("데미안", "헤르만헤세", 13000)
        ); 
    pageContext.setAttribute("books", books);
  %>
  <div>${books.get(0).title}</div>
  <div>${books.get(0).author}</div>
  <div>${books.get(0).price}</div>
  <div>${books[1].title}</div>
  <div>${books[1].author}</div>
  <div>${books[1].price}</div>
  <div>${books[2].title}</div>
  <div>${books[2].author}</div>
  <div>${books[2].price}</div>

  <%-- 
    <% for(int i = 0, size = books.size(); i < size; i++) { %>       ** for 문이 불가
				 <div>${books[0].title}</div>
				 <div>${books[1].author}</div>
				 <div>${books[2].price}</div>
    <% } %>
  --%>
  
  <hr>
  
  <%-- EL 연산자 --%>
  <%
    pageContext.setAttribute("a", 5);
    pageContext.setAttribute("b", 2);
  %>
  <div>=산술 연산=</div>
  <div>더하기 : ${a + b}</div>
  <div>빼기 : ${a - b}</div>
  <div>곱하기 : ${a * b}</div>
  <div>나누기 : ${a / b}, ${a div b}</div>
  <div>나누기(나머지) : ${a % b}, ${a mod b}</div>
  
  <hr>
  
  <div>=비교 연산 (true, false 반환)=</div>
  <div>${a > b},  ${a gt b}</div>
  <div>${a >= b}, ${a ge b}</div>
  <div>${a < b},  ${a lt b}</div>
  <div>${a <= b}, ${a le b}</div>
  <div>${a == b}, ${a eq b}</div>
  <div>${a != b}, ${a ne b}</div>
  
  <hr>
  
  <div>=논리 연산 (true, false 반환)=</div>
  <div>${a == 5 && b == 5}, ${a eq 5 and b eq 5}</div>
  <div>${a == 5 || b == 5}, ${a eq 5 or b eq 5}</div>
  <div>${!(a == 5)},        ${not(a eq 5)}</div>
  
  <hr>
  
  <div>=조건 연산 (삼항 연산)=</div>
  <div>${a > 0 ? "양수" : "음수"}</div>
  
  <hr>
  
  <%-- 
    !! request 영역 사용 시 주의사항 !!
    
    1. 속성(Attribute)
      request.setAttribute("number", 10);
      request.getRequestDispather("/URLMapping").forward(request, response);
      
        ${number}, 숫자 타입    10 > 5 : true
      
    2. 파라미터(Parameter)
      response.sendRedirect("/contextPath/URLMapping?number=10");
      
        ${param.number}, 문자열 타입 (파라미터는 모두 문자열이기 때문)  "10" > 5 : false
  --%>
  
  
</body>
</html>