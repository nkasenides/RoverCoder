package uk.ac.uclan.nkasenides.rovercoder.util;

import javax.servlet.http.HttpServletResponse;

public class APIUtils {

    public static void setResponseHeader(HttpServletResponse response) {
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
    }

}
