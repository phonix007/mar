package com.od.sharemarketmarathi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Basic_Next_Activity extends AppCompatActivity {

    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_next);

        txt = findViewById(R.id.next_tetx);
        String t = getIntent().getStringExtra("key");
        txt.setText(t);
    }
}