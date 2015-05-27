package com.noahbutler.soundsq;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.noahbutler.soundsq.db.DatabaseContract;

/**
 * Created by Noah Butler 5/26/2015
 *
 * Activity that is started by SoundCloud when the user "shares" the sound to our app.
 */

public class SendSoundActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_sound);

        /* Grab String sent from SoundCloud App */
        String rawSoundCloudData = getIntent().getStringExtra(Intent.EXTRA_TEXT);
        /* Grab the URL of the Sound from the given String */
        String parsedURL = rawSoundCloudData.substring(rawSoundCloudData.indexOf("https://"));

        /* for testing */
        Log.d("Sent", rawSoundCloudData);
        Log.d("URL", parsedURL);

        /* TODO: start QR Scanner and get ID from the QR code */

        /* Create our database object that will handle sending the sound info and ID to our server */
        DatabaseContract databaseContract = new DatabaseContract();
        /* Send our info to our server */
        databaseContract.execute("sendSoundToID", parsedURL, "ID_FROM_QR_CODE");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
