<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%--
		JSTL choose 태그와 EL을 활용하여
		90점이 넘으면 "당신의 학점은 A입니다."를 출력,
		80대는 8, 70점대는 C, 60점 대는 D 나머지는 F를 출력하세요
		점수는 100점 만점입니다. 초과점수는 "점수를 잘못 입력하셨습니다."
	 --%>
	 
	 <c:set var="socre" value="${param.point }"/>
	 
	 <c:choose>
	 <c:when test="${socre > 100 }">
	 		점수를 잘못입력하셨습니다.
	 	</c:when>
	 	<c:when test="${socre >= 90 }">
	 		당신의 학점은 <b>A</b>입니다.
	 	</c:when>
	 	<c:when test="${socre >= 80 }">
	 		당신의 학점은 <b>B</b>입니다.
	 	</c:when>
	 	<c:when test="${socre >= 70 }">
	 		당신의 학점은 <b>C</b>입니다.
	 	</c:when>
	 	<c:when test="${socre >= 60 }">
	 		당신의 학점은 <b>D</b>입니다.
	 	</c:when>
	 	<c:otherwise>
	 		당신의 학점은<b>F</b>입니다.
	 	</c:otherwise>
	 </c:choose>
</body>
</html>