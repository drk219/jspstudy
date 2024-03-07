<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>number</title>
</head>
<body>

  <c:set var="number" value="12345.6789"/>
  
  <div>
    <fmt:formatNumber value="${number}" pattern="#,##0"/>                    <%-- 12,346 --%>
  </div>
  
  <div>
    <fmt:formatNumber value="${number}" pattern="#,##0.00"/>                 <%-- 12,345.68 --%>
  </div>
  
  <div>
    <fmt:formatNumber value="${number}" type="percent"/>                     <%-- 1,234,568% --%>
  </div>
  
  <div>
    <fmt:formatNumber value="${number}" type="currency"/>                    <%-- UTF-8로 설정되어서 기본이 원화로 나옴 --%>
  </div>
  
  <div>
    <fmt:formatNumber value="${number}" type="currency" currencySymbol="$"/> <%-- 달러로 표시 --%>
  </div>
  
  
  

</body>
</html>