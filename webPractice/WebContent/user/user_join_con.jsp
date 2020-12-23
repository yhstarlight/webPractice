<%@page import="kr.co.jsp.user.model.UserVO"%>
<%@page import="kr.co.jsp.user.model.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%
    	/*
    		1. 폼 액션으로 날아온 폼 데이터들(parameter)을 얻어옴
    		2. DAO 객체를 공개된 메서드를 호출하여 주소값 얻어오기
    		3. 회원가입 승인 전에 이미 DB에 존재하는 ID인지 검사(confirmId)
    		4. 이미 회원가입된 id라면 alert으로 아이디가 중복되었다고 경고한 후
    		뒤로가기 진행
    		5. 가입된 회원이 아니라면 객체에 포장 후 회원 가입 승인 메서드 호출 (insertUser)
    		가입 성공 > 회원가입을 축하합니다. > location.href="user_login.jsp"
    		가입 실패 > 회원가입에 실패했습니다. > 뒤로가기
    	*/
    	
    	request.setCharacterEncoding("UTF-8");
    		
    	String id = request.getParameter("id");
    	String pw = request.getParameter("pw");
    	String name = request.getParameter("name");
    	String email = request.getParameter("email");
    	String address = request.getParameter("address");
    	
    	UserDAO dao = UserDAO.getInstance();
    	
    	if(dao.confirmId(id)){ %>
    		<script>
    			alert("중복된 아이디가 존재합니다.");
    			history.back();
    		</script>
    	<% } else {
    		UserVO user = new UserVO(id,pw,name,email,address);
    		if (dao.insertUser(user)){ %>
    			<script>
    				alert("회원가입을 축하합니다.");
    				location.href="user_login.jsp";
    			</script>
    		<% } else { %>
    			<script>
    				alert("회원가입에 실패했습니다.");
    				history.back();
    			</script>
    	<% }
    	}
    %>