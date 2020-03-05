<%@ page import="static com.googlecode.objectify.ObjectifyService.ofy" %>
<%@ page import="uk.ac.uclan.nkasenides.rovercoder.model.PlayerCodeEntry" %>
<%@ page import="com.googlecode.objectify.Key" %><%--
  User: hfnov
  Date: 05-Mar-20
  Time: 15:13
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%

    final String idInput = request.getParameter("id");
    if (idInput == null || idInput.trim().isEmpty()) {
        response.sendRedirect("codes.jsp?error=Missing ID.");
        return;
    }

    long id = -1;
    try {
        id = Long.parseLong(idInput);
    } catch (NumberFormatException e) {
        response.sendRedirect("codes.jsp?error=Invalid ID.");
        return;
    }

    PlayerCodeEntry entry = ofy().load().key(Key.create(PlayerCodeEntry.class, id)).now();
    if (entry == null) {
        response.sendRedirect("codes.jsp?error=Could not find entry to delete.");
        return;
    }

    ofy().delete().entity(entry).now();
    response.sendRedirect("codes.jsp");
%>
