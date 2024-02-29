package pkg02_request;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MyRequest extends HttpServlet {
  
  private static final long serialVersionUID = 1L;
       
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    // 1. 요청 UTF-8 인코딩
    request.setCharacterEncoding("UTF-8");
    
    // 2. 요청 파라미터
    //  1) 모든 파라미터는 string 타입이다.
    //  2) 파라미터가 없다면 null 이다. 
    //  3) 파라미터의 값이 없으면 ""(빈 문자열) 이다.
    
    /* if 문을 이용한 null / 빈 문자열 처리 */
    String strNumber = request.getParameter("number");
    int number = 0; 
    if(strNumber != null && strNumber.isEmpty())
      number = Integer.parseInt(strNumber);
    System.out.println(number);
    
    /* Optional<T> 클래스를 이용한 null 처리 */
    String strNumber2 = request.getParameter("number2");
    Optional<String> opt = Optional.ofNullable(strNumber2);
    // strNumber2가 null 이면 빈 Optional 을, 그렇지 않으면 해당 값을 갖는 Optional 을 생성
    
    double number2 = Double.parseDouble(opt.orElse("0").isEmpty() ? "0" : opt.orElse("0"));
    // opt.orElse("0")를 통해 Optional 의 값을 가져오는데, 만약 값이 없다면 "0"을 반환
    // 반환된 문자열이 비어있는지 여부를 확인하여, 비어있다면 "0"으로 대체
    // 이렇게 처리된 값을 Double.parseDouble()을 통해 double 타입으로 변환
    
    System.out.println(number2);
    
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    // 1. 요청 UTF-8 인코딩
    request.setCharacterEncoding("UTF-8"); // 항상 먼저 해야할 것!!!!! 안그러면 글자 싹 다 깨짐
    
    // 2. 요청 파라미터
    //  1) name 속성이 파라미터이다.
    //  2) 동일한 name 속성을 가진 입력 요소들은 다음과 같이 처리한다.
    //    (1) type="radio" : 변수 처리한다.
    //    (2) 이외의 경우  : 배열 처리한다.
    
    String name = request.getParameter("name");               // 입력 그대로 출력됨
    String email = request.getParameter("email");             // 입력 그대로 출력됨
    String gender = request.getParameter("gender");           // 눌렀으면 on 출력 -> input 태그에 value 설정 -> 선택한 내용 출력
    String[] hobbies = request.getParameterValues("hobbies"); // 선택한 상자는 on 으로 표시됨 [on, on, on] -> input 태그에 value 설정을 해줘야 한다.
    String[] mobile = request.getParameterValues("mobile");   // select 태그는 선택한 내용 그래도 출력 [skt, 01011112222] -> option 태그에 value 설정
    
    // 내용을 안 적어서 전송하면 text 상자는 빈문자열, 체크요소들은 null 값
    
    System.out.println(name);
    System.out.println(email);
    System.out.println(gender);
    System.out.println(Arrays.toString(hobbies));
    System.out.println(Arrays.toString(mobile));
  
  }

}