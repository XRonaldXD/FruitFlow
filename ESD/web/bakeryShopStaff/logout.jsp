<%-- 
    Document   : logout
    Created on : 2025年4月22日, 下午8:33:54
    Author     : user
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    // Invalidate the session
    if (session != null) {
        session.invalidate();
    }

    // Redirect to the login page
    response.sendRedirect("../login.jsp");
%>
