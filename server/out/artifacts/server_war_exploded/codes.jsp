<%@ page import="uk.ac.uclan.nkasenides.rovercoder.model.PlayerCodeEntry" %>
<%@ page import="java.util.List" %>
<%@ page import="static com.googlecode.objectify.ObjectifyService.ofy" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.TimeZone" %>
<%@ page import="java.util.Comparator" %>
<%@ page import="uk.ac.uclan.nkasenides.rovercoder.model.SortMethod" %>

<%--
  User: hfnov
  Date: 05-Mar-20
  Time: 15:00
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%

    if (session.getAttribute("LOGIN") == null) {
        response.sendRedirect("index.jsp?error=Please log in.");
    }

    SortMethod sortMethod;

    String sort = request.getParameter("sort");
    if (sort == null) {
        sortMethod = SortMethod.TIME;
    }
    else {
        switch (sort) {
            case "time":
                sortMethod = SortMethod.TIME;
                break;
            case "points":
                sortMethod = SortMethod.POINTS;
                break;
            case "played":
                sortMethod = SortMethod.PLAYED;
                break;
            case "playing":
                sortMethod = SortMethod.PLAYING;
                break;
            default:
                sortMethod = SortMethod.TIME;
                break;
        }
    }


%>

<html>
<head>
    <title>Codes - Rover coder</title>
</head>
<body>

<h1>Player codes</h1>

<p>Player codes, sorted by <%= sortMethod %></p>

<nav>
    <a style="margin-right: 20px" href="?sort=time">Sort by upload time</a>
    <a style="margin-right: 20px" href="?sort=points">Sort by points</a>
    <a style="margin-right: 20px" href="?sort=played">Sort by played</a>
    <a style="margin-right: 20px" href="?sort=playing">Sort by playing</a>
</nav>

<table border="1" cellpadding="5" cellspacing="5">
    <tr>
        <th>Entry ID</th>
        <th>Player ID</th>
        <th>Player name</th>
        <th>Uploaded on</th>
        <th>Played</th>
        <th>Currently playing</th>
        <th>Points</th>
        <th>-</th>
        <th>-</th>
    </tr>
    <%

        final SimpleDateFormat format = new SimpleDateFormat("dd/MM/YYYY @ HH:mm:ss");
        format.setTimeZone(TimeZone.getTimeZone("Europe/Athens"));

        List<PlayerCodeEntry> entries = ofy().load().type(PlayerCodeEntry.class).order("uploadedOn").list();

        //Sort:
        switch (sortMethod) {
            case TIME:
                entries.sort((o1, o2) -> {
                    long diff = o2.getUploadedOn() - o1.getUploadedOn();
                    return diff > 0 ? 1 : 0;
                });
                break;
            case POINTS:
                entries.sort((o1, o2) -> o2.getPoints() - o1.getPoints());
                break;
            case PLAYED:
                entries.sort((o1, o2) -> o1.isPlayed() ? 0 : 1);
                break;
            case PLAYING:
                entries.sort((o1, o2) -> o1.isCurrentlyPlaying() ? 0 : 1);
                break;
        }


        if (entries.size() < 1) {
            java.lang.String stringBuilder = "<tr>" +
                    "<td>-</td>" +
                    "<td>-</td>" +
                    "<td>-</td>" +
                    "<td>-</td>" +
                    "<td>-</td>" +
                    "<td>-</td>" +
                    "<td>-</td>" +
                    "<td>-</td>" +
                    "<td>-</td>" +
                    "</tr>";
            out.print(stringBuilder);
        }
        else {
            for (PlayerCodeEntry e : entries) {
                String stringBuilder = "<tr>" +
                        "<td>" + e.getId() + "</td>" +
                        "<td>" + e.getPlayerID() + "</td>" +
                        "<td>" + e.getPlayerName() + "</td>" +
                        "<td>" + format.format(e.getUploadedOn()) + "</td>" +
                        "<td>" + e.isPlayed() + "</td>" +
                        "<td>" + e.isCurrentlyPlaying() + "</td>" +
                        "<td>" + e.getPoints() + "</td>" +
                        "<td><a href='#' onclick='markAsNotPlayed(" + e.getId() + ")'>Mark as not played</a></td>" +
                        "<td><a href='#' onclick='deleteEntry(" + e.getId() + ")'>Delete</a></td>" +
                        "</tr>";
                out.print(stringBuilder);
            }
        }
    %>
</table>

<br/><br/>

<a href="#" onclick="if (confirm('Are you sure you would like to delete all codes? This action cannot be undone!')) document.location.href='script_clearCodes.jsp'">Delete all</a>

<script>
    function deleteEntry(id) {
        if (confirm('Are you sure you would like to delete code entry with ID ' + id + '? This action cannot be undone!')) {
            document.location.href = "script_deleteCode.jsp?id=" + id;
        }
    }

    function markAsNotPlayed(id) {
        if (confirm('Are you sure you would like to mark code entry with ID ' + id + ' as not played?')) {
            document.location.href = "script_unplayCode.jsp?id=" + id;
        }
    }
</script>

</body>
</html>
