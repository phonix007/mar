package com.od.sharemarketmarathi;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mopub.common.MoPub;
import com.mopub.common.MoPubReward;
import com.mopub.common.SdkConfiguration;
import com.mopub.common.SdkInitializationListener;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubInterstitial;
import com.mopub.mobileads.MoPubRewardedAdListener;
import com.mopub.mobileads.MoPubRewardedAds;
import com.mopub.mobileads.MoPubView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CandleActivity extends AppCompatActivity implements MoPubInterstitial.InterstitialAdListener {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    private RecyclerView recyclerView;
    private List<Candle_Model> list;

    private MoPubView moPubView;
    private MoPubInterstitial mInterstitial;
    private MoPubReward moPubReward;
    private MoPubRewardedAdListener rewardedAdListener;


    private Dialog loadingDialog;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candle);
        Toast.makeText(this, "You Need To Watch Video Ad to Continue", Toast.LENGTH_SHORT).show();

        MoPub.onCreate(this);
        rewardedAdListener = new MoPubRewardedAdListener() {
            @Override
            public void onRewardedAdLoadSuccess(String s) {
                MoPubRewardedAds.showRewardedAd(getString(R.string.moboub_reward));
            }

            @Override
            public void onRewardedAdLoadFailure(String s, MoPubErrorCode moPubErrorCode) {

                MoPubRewardedAds.loadRewardedAd("920b6145fb1546cf8b5cf2ac34638bb7");
            }

            @Override
            public void onRewardedAdStarted(String s) {

            }

            @Override
            public void onRewardedAdShowError(String s, MoPubErrorCode moPubErrorCode) {
                Toast.makeText(CandleActivity.this, moPubErrorCode.getIntCode() , Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onRewardedAdClicked(String s) {

            }

            @Override
            public void onRewardedAdClosed(String s) {
                yourAppsShowInterstitialMethod();
            }

            @Override
            public void onRewardedAdCompleted(Set<String> set, MoPubReward moPubReward) {

                

            }
        };

        MoPubRewardedAds.setRewardedAdListener(rewardedAdListener);

        SdkConfiguration.Builder sdkConfiguration = new SdkConfiguration.Builder(getString(R.string.mob_pub_banner));
        MoPub.initializeSdk(this, sdkConfiguration.build(), initSdkListener());
        MoPubRewardedAds.loadRewardedAd("920b6145fb1546cf8b5cf2ac34638bb7");

        loadingDialog = new Dialog(this);
        loadingDialog.setContentView(R.layout.loading);
        loadingDialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.rounded_corner));
        loadingDialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
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
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
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

    private SdkInitializationListener initSdkListener() {
        return new SdkInitializationListener() {
            @Override
            public void onInitializationFinished() {
                bannerAd();
                intrestitialAd();
                rewardedAd();
            }
        };
    }

    private void bannerAd(){

        moPubView = (MoPubView) findViewById(R.id.adview);
        moPubView.setAdUnitId(getString(R.string.mob_pub_banner)); // Enter your Ad Unit ID from www.mopub.com
        moPubView.loadAd();

    }

    private void intrestitialAd(){
        mInterstitial = new MoPubInterstitial(this, getString(R.string.mob_pub_intrestitial));
        mInterstitial.setInterstitialAdListener(this);
        mInterstitial.load();
    }

    private void rewardedAd(){

        MoPubRewardedAds.loadRewardedAd(getString(R.string.moboub_reward));
    }

    @Override
    protected void onDestroy() {
        moPubView.destroy();
        mInterstitial.destroy();
        super.onDestroy();
    }

    @Override
    public void onInterstitialLoaded(MoPubInterstitial moPubInterstitial) {

    }

    @Override
    public void onInterstitialFailed(MoPubInterstitial moPubInterstitial, MoPubErrorCode moPubErrorCode) {

    }

    @Override
    public void onInterstitialShown(MoPubInterstitial moPubInterstitial) {

    }

    @Override
    public void onInterstitialClicked(MoPubInterstitial moPubInterstitial) {

    }

    @Override
    public void onInterstitialDismissed(MoPubInterstitial moPubInterstitial) {

    }

    private void yourAppsShowInterstitialMethod() {
        if (mInterstitial.isReady()) {
            mInterstitial.show();
        }
    }

}