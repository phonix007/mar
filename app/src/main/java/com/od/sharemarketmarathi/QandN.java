package com.od.sharemarketmarathi;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.util.EventLog;
import android.util.Log;
import android.widget.LinearLayout;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.vungle.warren.InitCallback;
import com.vungle.warren.LoadAdCallback;
import com.vungle.warren.Vungle;
import com.vungle.warren.error.VungleException;
//import com.mopub.common.MoPub;
//import com.mopub.common.SdkConfiguration;
//import com.mopub.common.SdkInitializationListener;
//import com.mopub.mobileads.MoPubErrorCode;
//import com.mopub.mobileads.MoPubInterstitial;
//import com.mopub.mobileads.MoPubView;

import java.util.ArrayList;

public class QandN extends AppCompatActivity /* implements MoPubInterstitial.InterstitialAdListener */ {

    RecyclerView recyclerView;
    ArrayList<questionModel> questionModelArrayList;
    qusAdapter qusAdapter;
    FirebaseFirestore db;
    private Dialog loadingDialog;

//    private MoPubView moPubView;
//    private MoPubInterstitial mInterstitial;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qand_n);

//        SdkConfiguration.Builder sdkConfiguration = new SdkConfiguration.Builder(getString(R.string.mob_pub_banner));
//        MoPub.initializeSdk(this, sdkConfiguration.build(), initSdkListener());

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


        loadingDialog = new Dialog(this);
        loadingDialog.setContentView(R.layout.loading);
        loadingDialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.rounded_corner));
        loadingDialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        loadingDialog.setCancelable(false);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();
        questionModelArrayList = new ArrayList<questionModel>();
        qusAdapter = new qusAdapter(QandN.this,questionModelArrayList);

        loadingDialog.show();

        recyclerView.setAdapter(qusAdapter);

        EventChangeListner();

    }
//
//    private SdkInitializationListener initSdkListener() {
//        return new SdkInitializationListener() {
//            @Override
//            public void onInitializationFinished() {
//                bannerAd();
//                intrestitialAd();
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
//    private void intrestitialAd(){
//        mInterstitial = new MoPubInterstitial(this, getString(R.string.mob_pub_intrestitial));
//        mInterstitial.setInterstitialAdListener(this);
//        mInterstitial.load();
//    }
//
//    @Override
//    protected void onDestroy() {
//        moPubView.destroy();
//        mInterstitial.destroy();
//        super.onDestroy();
//    }


    private void EventChangeListner() {
        db.collection("questionscoll")  // Collection Name
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if (error != null){

                            if (loadingDialog.isShowing())
                                loadingDialog.dismiss();
                            Log.e("FireStore Error",error.getMessage());
                            return;
                        }

                        for (DocumentChange dc : value.getDocumentChanges()){

                            if (dc.getType() == DocumentChange.Type.ADDED){

                                questionModelArrayList.add(dc.getDocument().toObject(questionModel.class)); //
                            }
                            qusAdapter.notifyDataSetChanged(); //
                            if (loadingDialog.isShowing())
                                loadingDialog.dismiss();
                        }
                    }
                });
    }

//    @Override
//    public void onInterstitialLoaded(MoPubInterstitial moPubInterstitial) {
//        yourAppsShowInterstitialMethod();
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