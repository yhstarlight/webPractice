<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h2>회원 정보 확인하기</h2>
	<p>
		# 이름: ${sessionScope.user.name } <br>
		# 아이디: ${sessionScope.user.id } <br>
		# 이메일: ${sessionScope.user.email } <br>
		# 주소: ${sessionScope.user.address } <br>
	</p>
</body>
</html>