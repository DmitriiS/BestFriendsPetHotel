package com.mws3000.bestfriendspethotel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

public class DogsAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<Dogs> dogsItems;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();


    public DogsAdapter(Activity activity, List<Dogs> dogsItems) {
        this.activity = activity;
        this.dogsItems = dogsItems;
    }


    @Override
    public int getCount() {
        return dogsItems.size();
    }

    @Override
    public Object getItem(int location) {
        return dogsItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        if (convertView == null)
            convertView = inflater.inflate(R.layout.row_in_grid, null);


        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();


        final NetworkImageView thumbNail = (NetworkImageView) convertView.findViewById(R.id.dogImage);
        TextView name = (TextView) convertView.findViewById(R.id.dogName);
        TextView gender = (TextView) convertView.findViewById(R.id.dogGender);
        TextView breed = (TextView) convertView.findViewById(R.id.dogBreed);

        //final NetworkImageView fullScreen = (NetworkImageView) convertView.findViewById(R.id.largeImage);

        // getting dogs data for the row
        final Dogs d = dogsItems.get(position);

        // thumbnail image
        thumbNail.setImageUrl(d.getPicture(), imageLoader);

        // fullscreen image
        // fullScreen.setImageUrl(d.getPicture(), imageLoader);

        // name
        name.setText(d.getName());

        //gender
        gender.setText(d.getGender());

        //breed
        breed.setText(d.getBreed());

        thumbNail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(activity, d.getName() + " -" + d.getGender() + "- " + d.getBreed() + d.getPicture(), Toast.LENGTH_SHORT).show();


                Intent intent = new Intent(activity, FullImageActivity.class);

                thumbNail.setDrawingCacheEnabled(true);
                Bitmap b = thumbNail.getDrawingCache();

                intent.putExtra("image", b);
                activity.startActivity(intent);
            }
        });


        return convertView;

    }


}
