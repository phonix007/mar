package com.od.sharemarketmarathi;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

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


        return view;
    }
}