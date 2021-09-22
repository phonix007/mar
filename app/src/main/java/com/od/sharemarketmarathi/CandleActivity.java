package com.od.sharemarketmarathi;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.startapp.sdk.adsbase.StartAppAd;
import com.vungle.warren.InitCallback;
import com.vungle.warren.LoadAdCallback;
import com.vungle.warren.PlayAdCallback;
import com.vungle.warren.Vungle;
import com.vungle.warren.error.VungleException;
//import com.mopub.common.MoPub;
//import com.mopub.common.MoPubReward;
//import com.mopub.common.SdkConfiguration;
//import com.mopub.common.SdkInitializationListener;
//import com.mopub.mobileads.MoPubErrorCode;
//import com.mopub.mobileads.MoPubInterstitial;
//import com.mopub.mobileads.MoPubRewardedAdListener;
//import com.mopub.mobileads.MoPubRewardedAds;
//import com.mopub.mobileads.MoPubView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import eu.dkaratzas.android.inapp.update.InAppUpdateManager;
import eu.dkaratzas.android.inapp.update.InAppUpdateStatus;

public class CandleActivity extends AppCompatActivity implements  InAppUpdateManager.InAppUpdateHandler {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    private RecyclerView recyclerView;
    private List<Candle_Model> list;

    InAppUpdateManager inAppUpdateManager;
    BottomNavigationView navigationView;

//    private MoPubView moPubView;
//    private MoPubInterstitial mInterstitial;
//    private MoPubReward moPubReward;
//    private MoPubRewardedAdListener rewardedAdListener;


    private Dialog loadingDialog;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candle);

        StartAppAd.showAd(this);

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
        Vungle.loadAd(getString(R.string.vangel_video), new LoadAdCallback() {
            @Override
            public void onAdLoad(String placementId) {
                if ( Vungle.canPlayAd(getString(R.string.vangel_video))){
                    Vungle.playAd(getString(R.string.vangel_video),null,null);
                }
            }

            @Override
            public void onError(String placementId, VungleException exception) {

            }
        });



//        Toast.makeText(this, "कॅन्डल स्टिक बद्दल वाचण्याआधी तुम्हालाही व्हिडिओ ॲड पाहावे लागेल", Toast.LENGTH_LONG).show();

//        MoPub.onCreate(this);
//        rewardedAdListener = new MoPubRewardedAdListener() {
//            @Override
//            public void onRewardedAdLoadSuccess(String s) {
//                MoPubRewardedAds.showRewardedAd(getString(R.string.moboub_reward));
//            }
//
//            @Override
//            public void onRewardedAdLoadFailure(String s, MoPubErrorCode moPubErrorCode) {
//
//                MoPubRewardedAds.loadRewardedAd(getString(R.string.moboub_reward));
//            }
//
//            @Override
//            public void onRewardedAdStarted(String s) {
//
//            }
//
//            @Override
//            public void onRewardedAdShowError(String s, MoPubErrorCode moPubErrorCode) {
//                Toast.makeText(CandleActivity.this, moPubErrorCode.getIntCode(), Toast.LENGTH_SHORT).show();
//
//            }
//
//            @Override
//            public void onRewardedAdClicked(String s) {
//
//            }
//
//            @Override
//            public void onRewardedAdClosed(String s) {
//                yourAppsShowInterstitialMethod();
//            }
//
//            @Override
//            public void onRewardedAdCompleted(Set<String> set, MoPubReward moPubReward) {
//
//
//            }
//        };
//
//        MoPubRewardedAds.setRewardedAdListener(rewardedAdListener);
//
//        SdkConfiguration.Builder sdkConfiguration = new SdkConfiguration.Builder(getString(R.string.mob_pub_banner));
//        MoPub.initializeSdk(this, sdkConfiguration.build(), initSdkListener());
//        MoPubRewardedAds.loadRewardedAd(getString(R.string.moboub_reward));

        loadingDialog = new Dialog(this);
        loadingDialog.setContentView(R.layout.loading);
        loadingDialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.rounded_corner));
        loadingDialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        loadingDialog.setCancelable(false);

        recyclerView = findViewById(R.id.recyclerViewc_candle);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);

        loadingDialog.show();

        recyclerView.setLayoutManager(layoutManager);

        list = new ArrayList<>();
        Candle_Adapter candle_adapter = new Candle_Adapter(list);
        recyclerView.setAdapter(candle_adapter);

        myRef.child("candles").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    list.add(dataSnapshot1.getValue(Candle_Model.class)); // passing class
                }
                candle_adapter.notifyDataSetChanged();
                loadingDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                loadingDialog.dismiss();
                Toast.makeText(CandleActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
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

//    private SdkInitializationListener initSdkListener() {
//        return new SdkInitializationListener() {
//            @Override
//            public void onInitializationFinished() {
//                bannerAd();
//                intrestitialAd();
//                rewardedAd();
//            }
//        };
//    }
//
//    private void bannerAd() {
//
//        moPubView = (MoPubView) findViewById(R.id.adview);
//        moPubView.setAdUnitId(getString(R.string.mob_pub_banner)); // Enter your Ad Unit ID from www.mopub.com
//        moPubView.loadAd();
//
//    }
//
//    private void intrestitialAd() {
//        mInterstitial = new MoPubInterstitial(this, getString(R.string.mob_pub_intrestitial));
//        mInterstitial.setInterstitialAdListener(this);
//        mInterstitial.load();
//    }
//
//    private void rewardedAd() {
//
//        MoPubRewardedAds.loadRewardedAd(getString(R.string.moboub_reward));
//    }
//
//    @Override
//    protected void onDestroy() {
//        moPubView.destroy();
//        mInterstitial.destroy();
//        super.onDestroy();
//    }
//
//    @Override
//    public void onInterstitialLoaded(MoPubInterstitial moPubInterstitial) {
//
//    }
//
//    @Override
//    public void onInterstitialFailed(MoPubInterstitial moPubInterstitial, MoPubErrorCode moPubErrorCode) {
//
//    }
//
//    @Override
//    public void onInterstitialShown(MoPubInterstitial moPubInterstitial) {
//
//    }
//
//    @Override
//    public void onInterstitialClicked(MoPubInterstitial moPubInterstitial) {
//
//    }
//
//    @Override
//    public void onInterstitialDismissed(MoPubInterstitial moPubInterstitial) {
//
//    }
//
//    private void yourAppsShowInterstitialMethod() {
//        if (mInterstitial.isReady()) {
//            mInterstitial.show();
//        }
//    }

}