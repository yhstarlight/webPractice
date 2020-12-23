<%@page import="el.basic.Person"%>
<%@page import="sun.print.PeekGraphics"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%
    	request.setCharacterEncoding("UTF-8");
    
/*     	Person person = new Person(
    			request.getParameter("name"),
    			request.getParameter("age"),
    			request.getParameter("genger"),
    			request.getParameter("address")
    			); */
    %>
    
    <jsp:useBean id="person" class="el.basic.Person"/>
    <jsp:setProperty name="person" property="*"/>
    
    <%
    	request.setAttribute("p", person);
    %>
    
    <jsp:forward page="el_obj3.jsp"/>