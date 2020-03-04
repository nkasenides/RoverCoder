package uk.ac.uclan.nkasenides.rovercoder.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.panickapps.response.ErrorResponse;
import com.panickapps.response.JsonUtil;
import com.panickapps.response.SuccessResponse;
import io.ably.lib.realtime.AblyRealtime;
import io.ably.lib.realtime.Channel;
import io.ably.lib.types.AblyException;
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

public class GetNextCodeServlet extends HttpServlet {
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        APIUtils.setResponseHeader(response);

        final PrintWriter out = response.getWriter();

        List<PlayerCodeEntry> playerCodeEntriesNotPlayed = ofy().load().type(PlayerCodeEntry.class).filter("played", false).order("-uploadedOn").limit(1).list();
        if (playerCodeEntriesNotPlayed == null || playerCodeEntriesNotPlayed.size() < 1) {
            out.write(new SuccessResponse("No codes", "No more codes found to run.").toJSON());
            return;
        }

        JsonObject data = new JsonObject();
        data.add("code", new Gson().toJsonTree(playerCodeEntriesNotPlayed.get(0)));
        out.write(new SuccessResponse("Code fetched", "Next code to run fetched successfully", data).toJSON());
        playerCodeEntriesNotPlayed.get(0).setPlayed(true);
        ofy().save().entity(playerCodeEntriesNotPlayed.get(0));


        //Send message:
        List<PlayerCodeEntry> entriesQueued = ofy().load().type(PlayerCodeEntry.class).filter("played", false).order("-uploadedOn").list();
        if (entriesQueued == null) {
            out.write(new ErrorResponse("Error", "Failed to fetch scoreboard.").toJSON());
            return;
        }

        JsonObject scoreboardData = new JsonObject();
        scoreboardData.add("queue", JsonUtil.listToJsonArray(entriesQueued));
        final String message = new SuccessResponse("Queue fetched", "Play queue fetched.", scoreboardData).toJSON();

        try {
            AblyRealtime ably = new AblyRealtime("dujpIA.Oc6Olw:OZ_kAbMfRdlY5kod");
            Channel channel = ably.channels.get("queue");
            channel.publish("response", message);
        } catch (AblyException e) {
            e.printStackTrace();
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

}
