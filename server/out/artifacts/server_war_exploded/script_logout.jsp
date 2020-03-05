<%--
  User: hfnov
  Date: 05-Mar-20
  Time: 15:51
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%

    session.removeAttribute("LOGIN");
    response.sendRedirect("index.jsp");

%>