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
<%-- 	<%
	 	int total = 0;
		for(int i = 1; i<=100; i++){
			total += i;
		}
		out.println("<h4>1부터 100까지의 누적합: "+total+"</h4>");
	%> --%>
	<c:set var="total" value="0"/>
	
	<c:forEach var="i" begin="1" end="100" step="1"> <%--step 생략시 자동으로 1 처리 --%>
		<c:set var="total" value="${total + i }"/>
	</c:forEach>
	<h3>1~100까지의 누적합 : ${total }</h3>
	
	<hr>
	
	<h4>구구단 4단</h4>
	<c:forEach var="i" begin="1" end="9" step="1">
		4 * ${i } = ${4*i } <br>
	</c:forEach>
	
	<hr>
	
	<h4>구구단</h4>
	<c:forEach var="i" begin="2" end="9">
		<hr>
		<h5>구구단 ${i }단</h5>
		<c:forEach var="j" begin="1" end="9">
		${i } * ${j } = ${i*j } <br>
		</c:forEach>
	</c:forEach>
	
	<hr>
	
	<h4>배열이나 컬렉션 내부의 값을 출력</h4>
	<c:set var="arr" value="<%= new int[] {1,3,5,7,9} %>"/>
	<c:forEach var="n" items="${arr }">
		${n } &nbsp;
	</c:forEach>
	
</body>
</html>