package com.mws3000.bestfriendspethotel;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.GridView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class DogsInHotelActivity extends ActionBarActivity {


    // Log tag
    private static final String TAG = DogsInHotelActivity.class.getSimpleName();

    // Movies json url
    private static final String url = "http://mws3000.com/dog_pictures/dogs.json";
    private ProgressDialog pDialog;
    private List<Dogs> dogsList = new ArrayList<Dogs>();
    private GridView gridView;
    private DogsAdapter adapter;
    int i;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dogs_in_hotel);

        gridView = (GridView) findViewById(R.id.gridView);
        adapter = new DogsAdapter(this, dogsList);
        gridView.setAdapter(adapter);



        pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading ...");
        pDialog.show();

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Creating volley request obj
        JsonArrayRequest dogsReq = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                Log.d(TAG, response.toString());
                hidePDialog();

                // Parsing json
                for (int i = 0; i < response.length(); i++){
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        Dogs dogs = new Dogs();
                        dogs.setName(obj.getString("name"));
                        dogs.setPicture(obj.getString("picture"));
                        dogs.setGender(obj.getString("gender"));
                        dogs.setBreed(obj.getString("breed"));

                        dogsList.add(dogs);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                // notifying adapter about data changes
                // so that it renders the grid view with updated data
                adapter.notifyDataSetChanged();
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog(){
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }
}
