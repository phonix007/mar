package com.od.sharemarketmarathi;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
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
import com.startapp.sdk.adsbase.StartAppAd;
import com.vungle.warren.InitCallback;
import com.vungle.warren.LoadAdCallback;
import com.vungle.warren.Vungle;
import com.vungle.warren.error.VungleException;


import java.util.ArrayList;
import java.util.List;

import eu.dkaratzas.android.inapp.update.InAppUpdateManager;
import eu.dkaratzas.android.inapp.update.InAppUpdateStatus;

// Category
public class Basic_Activity extends AppCompatActivity implements  InAppUpdateManager.InAppUpdateHandler  {

    DatabaseReference databaseReference;
    ListView listView;
    List<String> title_list, story_list, img_list;
    ArrayAdapter<String> adapter;
    MyBasic myBasic;
    InAppUpdateManager inAppUpdateManager;



    private Dialog loadingDialog;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);

        StartAppAd.showAd(this);
//        SdkConfiguration.Builder sdkConfiguration = new SdkConfiguration.Builder(getString(R.string.mob_pub_banner));
//        MoPub.initializeSdk(this, sdkConfiguration.build(), initSdkListener());

        loadingDialog = new Dialog(this);
        loadingDialog.setContentView(R.layout.loading);
        loadingDialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.rounded_corner));
        loadingDialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        loadingDialog.setCancelable(false);

        listView = findViewById(R.id.listView);
        databaseReference = FirebaseDatabase.getInstance().getReference("basicbook"); //
        myBasic = new MyBasic();
        title_list = new ArrayList<>();
        story_list = new ArrayList<>();
        img_list = new ArrayList<>();

        adapter = new ArrayAdapter<>(this,R.layout.basic_iteam,R.id.iteamtext,title_list); // what you want to show on List. It  Shows only title

        loadingDialog.show();

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
                loadingDialog.dismiss();
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        // When click on any of the textview it will redirect  to the paratiular activity
                        Intent intent = new Intent(Basic_Activity.this,Basic_Next_Activity.class);
                        String p = story_list.get(i);
                        String urlstr = img_list.get(i);
                        String title = title_list.get(i);
                        intent.putExtra("key",p);
                        intent.putExtra("url",urlstr);
                        intent.putExtra("title",title);
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
    public void onInAppUpdateError(int code, Throwable error) {

    }

    @Override
    public void onInAppUpdateStatus(InAppUpdateStatus status) {

        if (status.isDownloaded()) {
            View view = getWindow().getDecorView().findViewById(android.R.id.content);
            Snackbar snackbar = Snackbar.make(view,
                    "App has been downloaded successfullly",
                    Snackbar.LENGTH_INDEFINITE);
            snackbar.setAction("", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    inAppUpdateManager.completeUpdate();

                }
            });

            snackbar.show();

        }

    }

}