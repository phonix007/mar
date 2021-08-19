package com.od.sharemarketmarathi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class Basic_Next_Activity extends AppCompatActivity {

    TextView txt;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_next);

        txt = findViewById(R.id.next_tetx);
        img = findViewById(R.id.next_image);
        String t = getIntent().getStringExtra("key");
        String url = getIntent().getStringExtra("url");
        txt.setText(t);
        Glide.with(this)
                .load(url)
                .into(img);
    }
}