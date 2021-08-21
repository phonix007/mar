package com.od.sharemarketmarathi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.tasks.OnCompleteListener;
import com.google.android.play.core.tasks.OnSuccessListener;
import com.google.android.play.core.tasks.Task;

public class Basic_Next_Activity extends AppCompatActivity {

    TextView txt;
    ImageView img;

    ReviewManager manager;
    ReviewInfo reviewInfo;

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

        // review
        manager = ReviewManagerFactory.create(Basic_Next_Activity.this);
        Task<ReviewInfo> request = manager.requestReviewFlow();

        request.addOnCompleteListener(new OnCompleteListener<ReviewInfo>() {
            @Override
            public void onComplete(@NonNull Task<ReviewInfo> task) {

                if (task.isSuccessful()){
                    reviewInfo = task.getResult();
                    Task<Void> flow = manager.launchReviewFlow(Basic_Next_Activity.this,reviewInfo);

                    flow.addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void result) {

                        }
                    });
                }else {
                    Toast.makeText(Basic_Next_Activity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // review end
    }
}