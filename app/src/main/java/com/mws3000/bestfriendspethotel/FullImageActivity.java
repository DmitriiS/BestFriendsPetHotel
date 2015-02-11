package com.mws3000.bestfriendspethotel;


import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

public class FullImageActivity extends Activity {

    DogsAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_image);


        ImageView imageView = (ImageView) findViewById(R.id.largeImage);

        Bitmap bmp = getIntent().getParcelableExtra("image");
        imageView.setImageBitmap(bmp);

    }

}