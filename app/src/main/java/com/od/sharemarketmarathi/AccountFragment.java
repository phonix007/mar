package com.od.sharemarketmarathi;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;


public class AccountFragment extends Fragment {

    public AccountFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_account, container, false);

        ImageView upstox = (ImageView) view.findViewById(R.id.upstox_logo);
        Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/stock-market-marathi.appspot.com/o/Account_logo%2Fupstox_logo.png?alt=media&token=91ee29dc-c02e-4240-bdf9-36bbc5257afb").placeholder(R.drawable.refresh).into(upstox);

        ImageView angel = (ImageView) view.findViewById(R.id.angel_logo);
        Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/stock-market-marathi.appspot.com/o/Account_logo%2FAngle_Logo.jpg?alt=media&token=9226bbb8-2ce5-400d-a451-fc074768548b").placeholder(R.drawable.refresh).into(angel);

        ImageView zerodha = (ImageView) view.findViewById(R.id.zerodha_logo);
        Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/stock-market-marathi.appspot.com/o/Account_logo%2FZerodha-Logo.jpg?alt=media&token=f933dc40-e5ed-43cb-8df8-873607080a3c").placeholder(R.drawable.refresh).into(zerodha);

        view.findViewById(R.id.open_upstox).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://bit.ly/3AZXD6f"))); // Add privacy policy url
                }catch (Exception e){
                    Toast.makeText(getContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });

        view.findViewById(R.id.open_angle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://bit.ly/383xFT3"))); // Add privacy policy url
                }catch (Exception e){
                    Toast.makeText(getContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });

        view.findViewById(R.id.open_zerodha).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("telegram/com"))); // Add privacy policy url
                }catch (Exception e){
                    Toast.makeText(getContext(), "https://zerodha.com/open-account/", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}