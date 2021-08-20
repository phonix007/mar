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
        Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/stock-market-marathi.appspot.com/o/basic%2Fmoney.png?alt=media&token=587a415e-6880-437b-98a8-97e935b04c39").placeholder(R.drawable.refresh).into(upstox);

        ImageView angel = (ImageView) view.findViewById(R.id.angel_logo);
        Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/stock-market-marathi.appspot.com/o/basic%2Fmoney.png?alt=media&token=587a415e-6880-437b-98a8-97e935b04c39").placeholder(R.drawable.refresh).into(angel);


        return view;
    }
}