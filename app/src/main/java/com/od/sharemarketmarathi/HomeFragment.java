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
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.tradingmarathi.learn"))); // Add
                }catch (Exception e){
                    Toast.makeText(getContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
        view.findViewById(R.id.share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_SUBJECT, " " + getString(R.string.app_name));
                    String msg = "स्टॉक मार्केट, ट्रेडिंग अॅप हे मराठी लोकांमध्ये शेअर मार्केट, ट्रेडिंग आणि क्रिप्टोकरन्सी ज्ञान प्रदान करण्यासाठी आहे, जिथे तुम्ही स्टॉक मार्केट,ट्रेडिंग मराठी भाषेत शिकू शकता. हे अॅप तुम्हाला शेअर बाजारातील मूलभूत गोष्टी, प्रकारांसह कॅण्डल्स आणि मराठीत क्रिप्टोकरन्सी बद्दल माहिती पुरवते. \n" +
                            "\n" +
                            "शेअर मार्केट, ट्रेडिंग आणि क्रिप्टो करेंसी बद्दल माहिती मिळवण्यासाठी लगेच डाउनलोड करा \n https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID+"\n\n"; // Change your message
                    intent.putExtra(Intent.EXTRA_TEXT,msg);
                    startActivity(Intent.createChooser(intent,"Share App with your friends"));
                }catch (Exception e){
                    Toast.makeText(getContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show();
                }

            }
        });
        view.findViewById(R.id.telegram).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/joinchat/V3H9kU_aQwoOs-96"))); // Add privacy policy url
                }catch (Exception e){
                    Toast.makeText(getContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

}