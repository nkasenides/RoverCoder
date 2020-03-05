<%--
  Created by IntelliJ IDEA.
  User: hfnov
  Date: 21-Dec-19
  Time: 11:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Rover coder - Admin</title>
</head>
<body>
<p>Please log in</p>

<%

    if (session.getAttribute("LOGIN") != null) {
        response.sendRedirect("main.jsp");
    }

    if (request.getParameter("error") != null) {
        final String error = request.getParameter("error");
        response.getWriter().write("<p style='color: red'>" + error + "</p>");
    }
%>

<h1>Rover Coder Admin</h1>

<form action="script_login.jsp" method="POST">
    <p>Password: <input type="password" name="password"/></p>
    <input type="submit" value="Log in"/>
</form>

</body>
</html>
