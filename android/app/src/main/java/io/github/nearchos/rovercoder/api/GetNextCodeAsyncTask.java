//package io.github.nearchos.rovercoder.api;
//
//import android.os.AsyncTask;
//import android.util.Log;
//import android.widget.Toast;
//
//import com.google.gson.Gson;
//import com.panickapps.javahttp.HTTPRequest;
//import com.panickapps.javahttp.HTTPResponse;
//import com.panickapps.javahttp.RequestMethod;
//import com.panickapps.response.Response;
//import com.panickapps.response.ResponseStatus;
//
//import org.mozilla.javascript.tools.jsc.Main;
//
//import io.github.nearchos.rovercoder.MainActivity;
//
//public class GetNextCodeAsyncTask extends AsyncTask<Void, Void, String> {
//
//    private MainActivity mainActivity;
//
//    public GetNextCodeAsyncTask(MainActivity mainActivity) {
//        this.mainActivity = mainActivity;
//    }
//
//    @Override
//    protected String doInBackground(Void... voids) {
//        HTTPResponse response = new HTTPRequest.Builder(APIConstants.GET_NEXT_CODE_URL, RequestMethod.GET)
//                .build()
//                .call();
//
//        if (response.getStatus() != 200) {
//            return null;
//        } else {
//            return response.getContent();
//        }
//    }
//
//    @Override
//    protected void onPostExecute(String jsonResponse) {
//        System.out.println(jsonResponse);
//        Response responseJsonObject = new Gson().fromJson(jsonResponse, Response.class);
//        if (responseJsonObject.getStatus() != ResponseStatus.OK) {
//            Toast.makeText(mainActivity, "Error: " + responseJsonObject.getMessage(), Toast.LENGTH_LONG).show();
//        } else {
//            if (responseJsonObject.getData() != null) {
//                if (responseJsonObject.getData().has("code")) {
//                    String code = responseJsonObject.getData().getAsJsonObject("code").get("code").getAsString();
//                    Log.d(MainActivity.TAG, code);
//                    mainActivity.setJavaScript(code);
//                    mainActivity.updateRoverState(true);
//                    mainActivity.getRunNextCodeButton().setEnabled(false);
//                    mainActivity.getStopCodeButton().setEnabled(true);
//                    return;
//                }
//            }
//            Toast.makeText(mainActivity, responseJsonObject.getMessage(), Toast.LENGTH_LONG).show();
//        }
//    }
//
//}
