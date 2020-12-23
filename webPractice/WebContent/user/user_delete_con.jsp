<%@page import="kr.co.jsp.user.model.UserVO"%>
<%@page import="kr.co.jsp.user.model.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%
    	/*
    		1. 폼 데이터 처리
    		2. 로그인 유효성 검사 하세요
    		3. deleteUser 호출
    		4. 탈퇴 성공 시 세션 삭제 후 user_login.jsp
    		5. 탈퇴 실패시 mypage 이동
    	*/
    
    	request.setCharacterEncoding("UTF-8");
    
    	String checkPw = request.getParameter("check_pw");
    	String id = (String)session.getAttribute("user_id");
    	UserDAO dao = UserDAO.getInstance();
    	int check = dao.userCheck(id, checkPw);
    	
    	if(check == 1) {
    		if (dao.deleteUser(id)) { 
    			session.invalidate();%>
    			<script>
    				alert("회원 탈퇴 되었습니다.");
    				location.href="user_login.jsp";
    			</script> 
    		<% } else { %>
    			<script>
    				alert("회원 탈퇴가 실패되었습니다.");
    				location.href="user_mypage.jsp";
    			</script>
    		<% }
    	} else { %>
    		<script>
    			alert("비밀번호가 일치하지 않습니다.");
    			location.href="user_mypage.jsp";
    		</script>
    	<% }
    
    	
    %>