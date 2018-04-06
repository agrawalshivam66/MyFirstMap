package labs.a.s.myfirstmap;


import android.text.TextUtils;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public final class QueryUtils {

    private static final String LOG_TAG = QueryUtils.class.getSimpleName();
     String  text1 = "Not Available";
    String description1 = "Not Available";

    private QueryUtils(){
    }

    public static List<pollution> fetchpollutionData(String requestUrl)
    {
        URL url = createUrl(requestUrl);

        String jsonResponse=null;

        try {
            jsonResponse =makeHttpRequest(url);
        }
        catch (IOException e){
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }
        List<pollution> pollutionlist1= extractFeatureFromJson(jsonResponse);

        return pollutionlist1;
    }



    private static URL createUrl(String stringUrl)
    {
        URL url=null;

        try{
            url = new URL(stringUrl);
        }
        catch (MalformedURLException e){
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }
        return url;
    }


    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the pollution JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // Closing the input stream could throw an IOException, which is why
                // the makeHttpRequest(URL url) method signature specifies than an IOException
                // could be thrown.
                inputStream.close();
            }
        }
        return jsonResponse;
    }


    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }


    private static List<pollution> extractFeatureFromJson(String pollutionjson) {
        if (TextUtils.isEmpty(pollutionjson)) {
            return null;
        }

        List<pollution> pollution_list = new ArrayList<>();

        try {
            JSONObject basejsonResponse = new JSONObject(pollutionjson);

            JSONObject baseData = basejsonResponse.getJSONObject("data");
            String  text = "Not Available";
            String description = "Not Available";


            text = baseData.optString("text");


            description =  baseData.optString("alert");
            Log.e("QueryUtils", text);
            Log.e("QueryUtils", description);


            pollution pol_Obj = new pollution(text,description);
            pollution_list.add(pol_Obj);


        } catch (JSONException e) {
            Log.e("QueryUtils", "Problem parsing the pollution JSON results", e);
        }

        return pollution_list;
    }

}
