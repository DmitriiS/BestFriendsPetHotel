package com.mws3000.bestfriendspethotel;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;


public class MainActivity extends ActionBarActivity {

    TextView t, t2;
    TextView a, a2;
    Button button;

    public static final int MAXIMUM = 30;


    // Movies json url
    private static final String url = "http://mws3000.com/dog_pictures/dogs.json";
    private ProgressDialog pDialog;

    // Log tag
    private static final String TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        a = (TextView) findViewById(R.id.textView);
        a2 = (TextView) findViewById(R.id.textView2);

        t = (TextView) findViewById(R.id.textViewOccup);
        t2 = (TextView) findViewById(R.id.textViewAvail);

        button = (Button) findViewById(R.id.button);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);

        Typeface myCustomFont = Typeface.createFromAsset(getAssets(), "fonts/Lobster_1.3.otf");
        t.setTypeface(myCustomFont);
        t2.setTypeface(myCustomFont);


        pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading ...");
        pDialog.show();
        makeJsonArrayRequest();

    }


    public void dogsInHotel(View v) {
        Intent intent = new Intent(this, DogsInHotelActivity.class);
        startActivity(intent);
    }


    private void makeJsonArrayRequest() {

        // Creating volley request obj
        JsonArrayRequest dogsReq = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                Log.d(TAG, response.toString());
                hidePDialog();
                a.setText(String.valueOf(response.length()));
                int avail = (MAXIMUM - response.length());
                a2.setText(String.valueOf(avail));
            }


        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                VolleyLog.d(TAG, "Error: " + volleyError.getMessage());
                hidePDialog();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(dogsReq);

    }


    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

    public void iconButtons(View view) {

        Intent intentIcon;
        Intent chooser;

        if (view.getId() == R.id.imageButtonDir) {
            intentIcon = new Intent(Intent.ACTION_VIEW);
            intentIcon.setData(Uri.parse("geo: 18.448722, -69.964556?q=18.448722, -69.964556"));
            chooser = Intent.createChooser(intentIcon, "Launch Maps");
            startActivity(chooser);
        }
        if (view.getId() == R.id.imageButtonFb) {
            intentIcon = new Intent(Intent.ACTION_VIEW);
            intentIcon.setData(Uri.parse("https://www.facebook.com/bestfriendsvetdr"));
            chooser = Intent.createChooser(intentIcon, "Launch Facebook app or browser");
            startActivity(chooser);
        }
        if (view.getId() == R.id.imageButtonEmail) {
            intentIcon = new Intent(Intent.ACTION_SEND);
            intentIcon.setData(Uri.parse("mailto:"));
            String to = "hel4eg@yandex.ru";
            intentIcon.putExtra(Intent.EXTRA_EMAIL, to);
            intentIcon.setType("message/rfc822");
            chooser = Intent.createChooser(intentIcon, "Send Email");
            startActivity(chooser);
        }
    }

}

