package com.sprintron.keyfinder.asynctask;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

import com.sprintron.keyfinder.activity.DeviceControlActivity;

public class UpdateDevData extends AsyncTask<String, String, String> {
    private final static String LOG_TAG = DeviceControlActivity.class.getSimpleName();
    //private final static String URL_UPDATEDEVDATA = "http://sprintron.com/pch/uploadDevData.php";
    private final static String URL_UPDATEDEVDATA = "http://sprintron.com/pch/postDevData.php";
    //private final static String URL_UPDATEDEVDATA = "http://sprintron.com/pch/ConnectTest.php";
    private static final String TAG_SUCCESS = "success";
    //private static final String TAG_MESSAGE = "message";

    @Override
    protected String doInBackground(String... args) {
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
        String format = s.format(new Date());

        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        //params.add(new BasicNameValuePair(TAG_USERID,"\""+userID+"\""));
        params.add(new BasicNameValuePair("username", "tester"));
        params.add(new BasicNameValuePair("password", "tester"));
        params.add(new BasicNameValuePair("devid", "123456"));
        params.add(new BasicNameValuePair("opcode", "1"));
        params.add(new BasicNameValuePair("devtype", "0"));
        params.add(new BasicNameValuePair("latitude", "33.45"));
        params.add(new BasicNameValuePair("longtitude", "-117.62"));
        params.add(new BasicNameValuePair("datetime", format));
        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject json = jsonParser.makeHttpRequest(URL_UPDATEDEVDATA,
                    "POST", params);
            Log.v(LOG_TAG, "post to " + URL_UPDATEDEVDATA + " with " + json.toString());
            int success = json.getInt(TAG_SUCCESS);
            if (success == 1) {
                Log.v(LOG_TAG, "post success");
            } else {
                //String meg = json.getString(TAG_MESSAGE);
                Log.v(LOG_TAG, "post fail:");
            }
        } catch (JSONException e) {
            Log.d(LOG_TAG, "UpdateDevData : Exeption");
            e.printStackTrace();
        }
        return null;
    }

}
