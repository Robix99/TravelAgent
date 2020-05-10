package com.example.android.travelagent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class HotelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView hotelTitle = findViewById(R.id.titleDetail);

        ImageView hotelImage = findViewById(R.id.hotelImageDetail);

        hotelTitle.setText(getIntent().getStringExtra("title"));

        Glide.with(this).load(getIntent().getIntExtra("image_resource", 0))
                .into(hotelImage);
    }
}
