<%@page import="kr.co.jsp.user.model.UserVO"%>
<%@page import="kr.co.jsp.user.model.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%
    	/*
    		1. 폼 데이터 처리하세요
    		2. DAO 연동을 통해 updateUser()라는 메서드를 호출하여 회원정보를 수정합니다.
    		회원정보 수정 성공했다면 이름에 대한 세션을 다시 제작해주셔야 합니다.
    		3. 수정 성공시 "회원 정보가 수정되었습니다" 출력 후 mypage
    		4. 수정 실패시 "회원정보 수정에 실패했습니다." 출력 후 mypage
    	*/
    	
    	request.setCharacterEncoding("UTF-8");
    
    	UserVO user = new UserVO();
    
    	user.setId((String)session.getAttribute("user_id"));
    	user.setName(request.getParameter("name"));
    	user.setEmail(request.getParameter("email"));
    	user.setAddress(request.getParameter("address"));
    	
    	if(UserDAO.getInstance().updateUser(user)){ 
    		session.setAttribute("user_name", request.getParameter("name")); %>
    		<script>
    			alert("회원 정보가 수정되었습니다.");
    			location.href="user_mypage.jsp";
    		</script>
    	<% } else { %>
    		<script>
    			alert("회원 정보 수정에 실패했습니다.");
    			location.href="user_mypage.jsp";
    		</script>
    	<% }
    %>