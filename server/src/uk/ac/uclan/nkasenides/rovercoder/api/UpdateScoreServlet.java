package uk.ac.uclan.nkasenides.rovercoder.api;

import com.googlecode.objectify.Key;
import com.panickapps.response.ErrorResponse;
import com.panickapps.response.InvalidParameterResponse;
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

import static com.googlecode.objectify.ObjectifyService.ofy;

public class UpdateScoreServlet extends HttpServlet {
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        APIUtils.setResponseHeader(response);

        final PrintWriter out = response.getWriter();

        final String entryIDInput = request.getParameter("entryID");
        final String scoreInput = request.getParameter("score");

        if (entryIDInput == null || entryIDInput.trim().isEmpty()) {
            out.write(new MissingParameterResponse("entryID").toJSON());
            return;
        }

        if (scoreInput == null || scoreInput.trim().isEmpty()) {
            out.write(new MissingParameterResponse("score").toJSON());
            return;
        }

        long entryID = -1;
        try {
            entryID = Long.parseLong(entryIDInput);
        } catch (NumberFormatException e) {
            out.write(new InvalidParameterResponse("entryID").toJSON());
            return;
        }

        int score = -1;
        try {
            score = Integer.parseInt(scoreInput);
        } catch (NumberFormatException e) {
            out.write(new InvalidParameterResponse("score").toJSON());
            return;
        }

        PlayerCodeEntry entry = ofy().load().key(Key.create(PlayerCodeEntry.class, entryID)).now();
        if (entry == null) {
            out.write(new ErrorResponse("Entry not found", "The entry with ID '" + entryIDInput + "' was not found.").toJSON());
            return;
        }

        entry.setPoints(score);
        ofy().save().entity(entry);
        out.write(new SuccessResponse("Score updated", "The score for entry with ID '" + entryID + "' has been updated.").toJSON());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
