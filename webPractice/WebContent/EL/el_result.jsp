<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>
		# data1: <%=session.getAttribute("data1") %> <br>
		# data2: <%=application.getAttribute("data2") %> <br>
	</p>
	
	<hr>
	
	<p>
	
		<%--
			* EL에서 데이터를 참조할 때 scope영역을 명시하지 않는다면
			page -> request -> session -> application
			순서대로 검색해서 찾아내 표현합니다.
			그래서 requestScope는 잘 사용하지 않음
		 --%>
		# data1: ${data1} <br>
		# data2: ${data2} <br>
	</p>
</body>
</html>