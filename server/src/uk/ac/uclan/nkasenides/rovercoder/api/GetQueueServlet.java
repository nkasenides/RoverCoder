package uk.ac.uclan.nkasenides.rovercoder.api;

import com.google.gson.JsonObject;
import com.panickapps.response.ErrorResponse;
import com.panickapps.response.JsonUtil;
import com.panickapps.response.SuccessResponse;
import uk.ac.uclan.nkasenides.rovercoder.model.PlayerCodeEntry;
import uk.ac.uclan.nkasenides.rovercoder.util.APIUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class GetQueueServlet extends HttpServlet {
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        APIUtils.setResponseHeader(response);

        final PrintWriter out = response.getWriter();

        List<PlayerCodeEntry> entriesQueued = ofy().load().type(PlayerCodeEntry.class).filter("played", false).order("-uploadedOn").list();
        if (entriesQueued == null) {
            out.write(new ErrorResponse("Error", "Failed to fetch scoreboard.").toJSON());
            return;
        }

        JsonObject data = new JsonObject();
        data.add("queue", JsonUtil.listToJsonArray(entriesQueued));
        out.write(new SuccessResponse("Queue fetched", "Play queue fetched.", data).toJSON());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}