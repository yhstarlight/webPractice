<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	tbody{
		font-size: 20px;
	}
</style>

</head>
<body>


	<jsp:include page="../include/header.jsp"/>
	
	<%-- 회원이 아닌 사용자는 게시판을 이용하지 못하도록 로직을 작성해주세요. --%>
	<c:if test="${sessionScope.user_id ==null }">
		<script>
			alert("로그인을 해주세요!");
			location.href="../user/user_login.jsp";
		</script>
	</c:if>
	
	<div class="container">
		<h2>My Web게시판</h2>

		<table class="table table-secondary table-hover table-bordered">
			<thead style="font-size: 25px">
				<tr>
					<th>글 번호</th>
					<th>작성자</th>
					<th>제목</th>
					<th>날짜</th>
					<th>조회수</th>
				</tr>
			</thead>

			<c:forEach var="article" items="${boardList }">
			<tbody>
				<tr>
					<td>${article.board_id }</td>
					<td>${article.writer }</td>
					<td>
						<a href="/MyWeb/content.board?bId=${article.board_id }">${article.title }</a>
					</td>
					<td>
						<fmt:formatDate value="${article.date }" pattern="yyyy년 MM월 dd일 hh:mm:ss"/>
					</td>
					<td>${article.hit }</td>
				</tr>
			</tbody>
			</c:forEach>
			
			
			<tbody>
				<tr>
					<td colspan="5" align="right">
						<form action="/MyWeb/search.board" class="form-inline" >
						  <div class="form-group">
						  	<select name="category">
						  		<option value="title">제목</option>
						  		<option value="writer">작성자</option>
						  	</select>
						    <input type="text" name="search" placeholder="검색어 입력" class="form-control" >
						  	<input type="submit" value="검색" class="btn btn-default">
							<input type="button" value="글 작성" class="btn btn-default" onclick="location.href='/MyWeb/write.board'">
						  </div>
						</form> 
					</td>
				</tr>
			</tbody>
		
		</table>
	</div>

	<jsp:include page="../include/footer.jsp"/>

</body>
</html>







