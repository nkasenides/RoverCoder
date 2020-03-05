<%--
  User: hfnov
  Date: 05-Mar-20
  Time: 14:50
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%

    final String password = request.getParameter("password");

    if (password == null) {
        response.sendRedirect("index.jsp?error=Enter a password.");
        return;
    }

    if (password.equals("rroverccoder")) {
        session.setAttribute("LOGIN", "login");
        response.sendRedirect("main.jsp");
        return;
    }
    else {
        response.sendRedirect("index.jsp?error=Invalid password.");
        return;
    }


%>