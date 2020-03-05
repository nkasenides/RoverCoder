<%--
  User: hfnov
  Date: 05-Mar-20
  Time: 14:56
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%

    if (session.getAttribute("LOGIN") == null) {
        response.sendRedirect("index.jsp?error=Please log in.");
    }

%>

<html>
<head>
    <title>Admin - Rover Coder</title>
</head>
<body>

    <h1>Rover Coder Admin</h1>

    <a href="codes.jsp">View codes</a><br/><br/>
    <a href="script_logout.jsp">Log out</a><br/><br/>

</body>
</html>
