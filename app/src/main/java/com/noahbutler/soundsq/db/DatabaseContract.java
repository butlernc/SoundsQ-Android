package com.noahbutler.soundsq.db;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Noah Butler on 5/26/2015.
 *
 * Object that can perform tasks off of the UI thread.
 * Great and easy to use for sending data our HTTP
 */
public class DatabaseContract extends AsyncTask<String, Object, Object>{

    private static final String URL_SEND_SOUND = "url for php file to send id and url to";

    @Override
    protected Object doInBackground(String... strings) {
        if(strings[0].contains("sendSoundToID")) {
            sendSoundToID(strings[1], strings[2]);
        }
        return null;
    }

    private void sendSoundToID(String soundCloudURL, String QR_ID) {

        /* Create our packaged params for the HTTP POST */
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("url", soundCloudURL));
        params.add(new BasicNameValuePair("id", QR_ID));

        /* Create our HTTP Handler */
        ServiceHandler serviceClient = new ServiceHandler();

        /* Send our Sound URL and ID via a HTTP POST */
        String json = serviceClient.makeServiceCall(URL_SEND_SOUND,
                ServiceHandler.POST, params);

        /* look for a response */
        if (json != null) {
            try {
                JSONObject jsonObj = new JSONObject(json);
                boolean error = jsonObj.getBoolean("error");
                // checking for error node in json
                if (!error) {
                    // sound was sent successfully
                } else {
                    // sound was not sent successfully
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        } else {
            Log.e("JSON Data", "JSON data error!");
        }
    }
}
