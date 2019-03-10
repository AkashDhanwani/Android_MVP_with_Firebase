package com.akash.rohonfancy.view_presenter.image_detail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.akash.rohonfancy.R;
import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;

public class ImageDetail extends AppCompatActivity {

    PhotoView photoView;

    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);

        photoView = findViewById(R.id.photoview);

        Intent intent = getIntent();
        url = intent.getStringExtra("url");

        Picasso.with(getApplicationContext()).load(url).into(photoView);
    }
}
