package com.od.sharemarketmarathi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.tasks.OnCompleteListener;
import com.google.android.play.core.tasks.OnSuccessListener;
import com.google.android.play.core.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

// Category
public class Basic_Activity extends AppCompatActivity {

    DatabaseReference databaseReference;
    ListView listView;
    List<String> title_list, story_list, img_list;
    ArrayAdapter<String> adapter;
    MyBasic myBasic;
    private long backPressedTime;
    private Toast backToast;

    ReviewManager manager;
    ReviewInfo reviewInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);



        listView = findViewById(R.id.listView);
        databaseReference = FirebaseDatabase.getInstance().getReference("basicbook"); //
        myBasic = new MyBasic();
        title_list = new ArrayList<>();
        story_list = new ArrayList<>();
        img_list = new ArrayList<>();

        adapter = new ArrayAdapter<>(this,R.layout.basic_iteam,R.id.iteamtext,title_list); // what you want to show on List. It  Shows only title

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                title_list.clear();
                story_list.clear();
                img_list.clear();

                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    myBasic = ds.getValue(MyBasic.class);
                    title_list.add(myBasic.getTi());
                    story_list.add(myBasic.getTx());
                    img_list.add(myBasic.getUrl());
                }

                listView.setAdapter(adapter); // show title on list
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        // When click on any of the textview it will redirect  to the paratiular activity
                        Intent intent = new Intent(Basic_Activity.this,Basic_Next_Activity.class);
                        String p = story_list.get(i);
                        String urlstr = img_list.get(i);
                        intent.putExtra("key",p);
                        intent.putExtra("url",urlstr);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public void onBackPressed() { //double press to exit
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast.cancel();
            super.onBackPressed();
            return;
        } else {
            // review
            manager = ReviewManagerFactory.create(Basic_Activity.this);
            Task<ReviewInfo> request = manager.requestReviewFlow();

            request.addOnCompleteListener(new OnCompleteListener<ReviewInfo>() {
                @Override
                public void onComplete(@NonNull Task<ReviewInfo> task) {

                    if (task.isSuccessful()){
                        reviewInfo = task.getResult();
                        Toast.makeText(Basic_Activity.this, "Please Give Us 5 ✮ Star Rating", Toast.LENGTH_SHORT).show();
                        Task<Void> flow = manager.launchReviewFlow(Basic_Activity.this,reviewInfo);

                        flow.addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void result) {

                            }
                        });
                    }else {
                        Toast.makeText(Basic_Activity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            // review end
        }
        backPressedTime = System.currentTimeMillis();
    }

}