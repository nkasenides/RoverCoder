package uk.ac.uclan.nkasenides.rovercoder.api;

import com.panickapps.response.ErrorResponse;
import com.panickapps.response.MissingParameterResponse;
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
import java.util.prefs.AbstractPreferences;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class UploadCodeServlet extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        APIUtils.setResponseHeader(response);

        final PrintWriter out = response.getWriter();

        final String playerIDInput = request.getParameter("playerID");
        final String playerNameInput = request.getParameter("playerName");
        final String codeInput = request.getParameter("code");
        final String workspaceInput = request.getParameter("workspace");

        if (playerIDInput == null || playerIDInput.trim().isEmpty()) {
            out.write(new MissingParameterResponse("playerID").toJSON());
            return;
        }

        if (playerNameInput == null || playerNameInput.trim().isEmpty()) {
            out.write(new MissingParameterResponse("playerName").toJSON());
            return;
        }

        if (codeInput == null || codeInput.trim().isEmpty()) {
            out.write(new MissingParameterResponse("code").toJSON());
            return;
        }

        if (workspaceInput == null || workspaceInput.trim().isEmpty()) {
            out.write(new MissingParameterResponse("workspace").toJSON());
            return;
        }

        //Check player code entries for entries of the current player that have not been played yet:
        List<PlayerCodeEntry> entriesOfPlayer = ofy().load().type(PlayerCodeEntry.class).filter("playerID", playerIDInput).list();
        int countOfEntriesOfPlayerNotPlayed = 0;
        for (PlayerCodeEntry p : entriesOfPlayer) {
            if (!p.isPlayed()) {
                countOfEntriesOfPlayerNotPlayed++;
            }
        }
        if (countOfEntriesOfPlayerNotPlayed > 0) {
            out.write(new ErrorResponse("Code pending", "You already have an uploaded code that has not been executed by the rover. Please wait until your previous code is executed by the rover before submitting new code.").toJSON());
            return;
        }

        PlayerCodeEntry entry = new PlayerCodeEntry(playerIDInput, playerNameInput, codeInput, workspaceInput);
        ofy().save().entity(entry).now();

        out.write(new SuccessResponse("Code uploaded", "Your code has been uploaded successfully!").toJSON());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

}
