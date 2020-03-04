package uk.ac.uclan.nkasenides.rovercoder.util;

import uk.ac.uclan.nkasenides.rovercoder.model.PlayerCodeEntry;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class APIUtils {

    public static void setResponseHeader(HttpServletResponse response) {
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
    }

    public static PlayerCodeEntry getPlayingCodeEntry() {
        List<PlayerCodeEntry> playingEntryList = ofy().load().type(PlayerCodeEntry.class).filter("currentlyPlaying", true).limit(1).list();
        if (playingEntryList.size() > 0) {
            return playingEntryList.get(0);
        }
        return null;
    }

}
