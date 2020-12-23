<%@page import="kr.co.jsp.user.model.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%
    	/*
    		1. 파라미터값 얻어오기
    		2. DAO 주소값 얻어오기
    		3. 로그인 유효성 검증 메서드 userCheck 호출
    		(아이디가 없다면 스크립트로 경고창 출력 후 회원가입 페이지로 이동)
    		(비밀번호가 틀린 경우 비밀번호가 틀렸다고 경고창을 출력해주세요)
    		(로그인 성공인 경우 이름과 아이디 값으로 각각 세션을 하나씩 생성하세요(user_name, user_id)
    		(세션 만들기 전에 getUserInfo()로 로그인 성공 회원의 정보를 모두 얻어오세요)
    		(user_mypage.jsp로 리다이렉팅)
    	*/
    	
    	request.setCharacterEncoding("UTF-8");
    	
    	String id = request.getParameter("id");
    	String pw = request.getParameter("pw");
    	
    	UserDAO dao = UserDAO.getInstance();
    	int check = dao.userCheck(id, pw);
    	
    	if (check == -1){ %>
    		<script>
    			alert("아이디가 존재하지 않습니다.");
    			location.href="user_join.jsp";
    		</script>
    	<% } else if (check == 0){ %>
    		<script>
    			alert("비밀번호가 틀렸습니다.");
    			history.back();
    		</script>
    	<% } else {
    		session.setAttribute("user_id", id);
    		session.setAttribute("user_name", dao.getUserInfo(id).getName());
    		response.sendRedirect("user_mypage.jsp");
    	}
    %>