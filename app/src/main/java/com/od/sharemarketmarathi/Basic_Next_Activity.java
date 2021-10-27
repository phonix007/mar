package com.od.sharemarketmarathi;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.tasks.OnCompleteListener;
import com.google.android.play.core.tasks.OnSuccessListener;
import com.google.android.play.core.tasks.Task;
import com.startapp.sdk.adsbase.Ad;
import com.startapp.sdk.adsbase.StartAppAd;
import com.startapp.sdk.adsbase.adlisteners.AdEventListener;
import com.startapp.sdk.adsbase.adlisteners.VideoListener;
import com.vungle.warren.InitCallback;
import com.vungle.warren.LoadAdCallback;
import com.vungle.warren.Vungle;
import com.vungle.warren.error.VungleException;

import eu.dkaratzas.android.inapp.update.InAppUpdateManager;
import eu.dkaratzas.android.inapp.update.InAppUpdateStatus;
//import com.mopub.common.MoPub;
//import com.mopub.common.SdkConfiguration;
//import com.mopub.common.SdkInitializationListener;
//import com.mopub.mobileads.MoPubErrorCode;
//import com.mopub.mobileads.MoPubInterstitial;
//import com.mopub.mobileads.MoPubView;

public class Basic_Next_Activity extends AppCompatActivity implements InAppUpdateManager.InAppUpdateHandler {

    TextView txt,txttitle;
    ImageView img;

    InAppUpdateManager inAppUpdateManager;
    BottomNavigationView navigationView;

    ReviewManager manager;
    ReviewInfo reviewInfo;

//    private MoPubView moPubView;

    private Dialog loadingDialog;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_next);

        // sdk
        Vungle.init(getString(R.string.vengal_appid), getApplicationContext(), new InitCallback() {  // change app id
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(VungleException exception) {

            }

            @Override
            public void onAutoCacheAdAvailable(String placementId) {

            }
        });
        // interstial
        Vungle.loadAd(getString(R.string.vengal_interstial), new LoadAdCallback() {
            @Override
            public void onAdLoad(String placementId) {
                if ( Vungle.canPlayAd(getString(R.string.vengal_interstial))){
                    Vungle.playAd(getString(R.string.vengal_interstial),null,null);
                }
            }

            @Override
            public void onError(String placementId, VungleException exception) {

            }
        });

//        SdkConfiguration.Builder sdkConfiguration = new SdkConfiguration.Builder(getString(R.string.mob_pub_banner));
//        MoPub.initializeSdk(this, sdkConfiguration.build(), initSdkListener());

        loadingDialog = new Dialog(this);
        loadingDialog.setContentView(R.layout.loading);
        loadingDialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.rounded_corner));
        loadingDialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        loadingDialog.setCancelable(false);
        loadingDialog.show();

        txt = findViewById(R.id.next_tetx);
        img = findViewById(R.id.next_image);
        txttitle = findViewById(R.id.next_title);

        String t = getIntent().getStringExtra("key");
        String url = getIntent().getStringExtra("url");
        String title = getIntent().getStringExtra("title");

        txt.setText(t);
        txttitle.setText(title);

        Glide.with(this)
                .load(url)
                .into(img);

        loadingDialog.dismiss();

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

//    private SdkInitializationListener initSdkListener() {
//        return new SdkInitializationListener() {
//            @Override
//            public void onInitializationFinished() {
//                bannerAd();
//            }
//        };
//    }
//
//    private void bannerAd(){
//
//        moPubView = (MoPubView) findViewById(R.id.adview);
//        moPubView.setAdUnitId(getString(R.string.mob_pub_banner)); // Enter your Ad Unit ID from www.mopub.com
//        moPubView.loadAd();
//
//    }
//
//    @Override
//    protected void onDestroy() {
//        moPubView.destroy();
//        super.onDestroy();
//    }

}