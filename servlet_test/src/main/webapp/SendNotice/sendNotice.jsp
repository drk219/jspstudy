<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 작성</title>
</head>
<body>
    <form action="<%=request.getContextPath()%>/sendNotice" method="post">
        <div>
            <label for="">작성자</label>
            <input type="text" name="writer_id" id="writer_id">
        </div>
        <div>
            <label for="">제목</label>
            <input type="text" name="title" id="title">
        </div>
        <div>
            <textarea name="contents" id="contents" cols="30" rows="10" placeholder="내용 써줭"></textarea>
        </div>
        <div>
            <input type="submit" value="작성완료">
            <button id="reset">작성초기화</button>
        </div>
    </form>
    
    <jsp:include page="../footer.jsp">
    	<jsp:param value="바닥" name="footer"/>
  	</jsp:include>

    <script>
        document.querySelector('#reset').addEventListener('click', (event) => {
            document.querySelector('#writer_id').value = '';
            document.querySelector('#title').value = '';
            document.querySelector('#contents').value = '';
            event.preventDefault();
        });
    </script>
</body>
</html>