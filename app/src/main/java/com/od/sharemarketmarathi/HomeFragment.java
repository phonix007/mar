package com.od.sharemarketmarathi;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

public class HomeFragment extends Fragment {



    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_home, container, false);
        view.findViewById(R.id.basic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Basic_Activity.class));
            }
        });
        view.findViewById(R.id.qna).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), QandN.class));
            }
        });
        view.findViewById(R.id.candle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CandleActivity.class));
            }
        });
        view.findViewById(R.id.trading).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Coming Soon...", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_SUBJECT, " " + getString(R.string.app_name));
                    String msg = "Hey, install this application for practice of Computer Related IMP MCQ types questions, which was asked in  MH-CIT Exam. \n" +
                            "\n" +
                            "हे, मित्रांनो मला MS-CIT चा प्रॅक्टिस साठी एक ॲप सापडलेल आहे तुम्ही पण ते ॲप डाऊनलोड करा. \n https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID+"\n\n"; // Change your message
                    intent.putExtra(Intent.EXTRA_TEXT,msg);
                    startActivity(Intent.createChooser(intent,"share via"));
                }catch (Exception e){
                    Toast.makeText(getContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show();
                }

            }
        });
        view.findViewById(R.id.telegram).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("telegram/com"))); // Add privacy policy url
                }catch (Exception e){
                    Toast.makeText(getContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

}