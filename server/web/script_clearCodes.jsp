<%@ page import="static com.googlecode.objectify.ObjectifyService.ofy" %>
<%@ page import="uk.ac.uclan.nkasenides.rovercoder.api.GetNextCodeServlet" %>
<%@ page import="java.util.List" %>
<%@ page import="uk.ac.uclan.nkasenides.rovercoder.model.PlayerCodeEntry" %>
<%@ page import="com.googlecode.objectify.Key" %><%--
  User: hfnov
  Date: 05-Mar-20
  Time: 15:16
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%

    List<PlayerCodeEntry> entries = ofy().load().type(PlayerCodeEntry.class).list();
    for (PlayerCodeEntry e : entries) {
        ofy().delete().key(Key.create(PlayerCodeEntry.class, e.getId())).now();
    }

    response.sendRedirect("codes.jsp");

%>