package com.od.sharemarketmarathi;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;


public class BitcoinFragment extends Fragment {





    public BitcoinFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_bitcoin, container, false);
        WebView webView = (WebView)v.findViewById(R.id.webView);
        webView.loadUrl("file:///android_asset/bitcoin.html");


        return v;
    }
}