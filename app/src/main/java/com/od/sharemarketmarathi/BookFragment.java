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


public class BookFragment extends Fragment {


    public BookFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book, container, false);
        ImageView mrk_gau = (ImageView) view.findViewById(R.id.gauid_mrk);
        Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/stock-market-marathi.appspot.com/o/Books%2FGuide%20To%20Indian%20Stock%20Market.jpg?alt=media&token=41e2b968-770e-4ccd-8634-89abdb071618").placeholder(R.drawable.refresh).into(mrk_gau);
        view.findViewById(R.id.book1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.amazon.in/Guide-Indian-Stock-Market-Jitendra/dp/B00GZMS8GK"))); // Add privacy policy url
                }catch (Exception e){
                    Toast.makeText(getContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ImageView streetwall = (ImageView) view.findViewById(R.id.wallstreet);
        Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/stock-market-marathi.appspot.com/o/Books%2FOne%20Up%20On%20Wall%20Street%20%E2%80%93%20Peter%20Lynch.jpg?alt=media&token=b6ca0ccd-feb7-49b3-aaf8-fb2b1f08cb73").placeholder(R.drawable.refresh).into(streetwall);
        view.findViewById(R.id.book2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.amazon.in/s?k=one+upon+wall+street+in+hindi&i=stripbooks&crid=2QFPELBCCKZBJ&sprefix=one+upon%2Cstripbooks%2C487&ref=nb_sb_ss_ts-doa-p_3_8"))); // Add privacy policy url
                }catch (Exception e){
                    Toast.makeText(getContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ImageView cr_this = (ImageView) view.findViewById(R.id.cr);
        Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/stock-market-marathi.appspot.com/o/Books%2FShare%20Market%20Se%20Kaise%20Banaye%20Mene%2010%20Crore.jfif?alt=media&token=cb1b7152-033e-4399-ab64-0f9f7199c5ba").placeholder(R.drawable.refresh).into(cr_this);
        view.findViewById(R.id.book3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.amazon.in/dp/B07BKC529J/ref=dp-kindle-redirect?_encoding=UTF8&btkr=1"))); // Add privacy policy url
                }catch (Exception e){
                    Toast.makeText(getContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ImageView stock_ri = (ImageView) view.findViewById(R.id.stock_rich);
        Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/stock-market-marathi.appspot.com/o/Books%2FStocks%20to%20Riches%20%E2%80%93%20Parag%20Parikh.jpg?alt=media&token=40fdf2bd-bbad-4b30-9c3d-c0b3deecac2c").placeholder(R.drawable.refresh).into(stock_ri);
        view.findViewById(R.id.book4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.amazon.in/dp/B0789J2FKF/ref=dp-kindle-redirect?_encoding=UTF8&btkr=1"))); // Add privacy policy url
                }catch (Exception e){
                    Toast.makeText(getContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ImageView inteleg = (ImageView) view.findViewById(R.id.itelle);
        Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/stock-market-marathi.appspot.com/o/Books%2FThe%20Intelligent%20Investor-Benjamin%20Graham.jpg?alt=media&token=a910cd4c-915a-478e-ac22-d1b6667d05fc").placeholder(R.drawable.refresh).into(inteleg);
        view.findViewById(R.id.book5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.amazon.in/dp/B09B7SD9V8/ref=dp-kindle-redirect?_encoding=UTF8&btkr=1"))); // Add privacy policy url
                }catch (Exception e){
                    Toast.makeText(getContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ImageView buffet = (ImageView) view.findViewById(R.id.buff);
        Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/stock-market-marathi.appspot.com/o/Books%2FThe%20Warren%20Buffet%20Way-%20Robert%20G.%20Hagstrom.jpg?alt=media&token=a745e0c8-2efd-49f7-ae8b-baf79a3b2536").placeholder(R.drawable.refresh).into(buffet);
        view.findViewById(R.id.book6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.amazon.in/Warren-Buffett-Nivesh-Rahasya-Hindi-ebook/dp/B085W2R9S6"))); // Add privacy policy url
                }catch (Exception e){
                    Toast.makeText(getContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });

        view.findViewById(R.id.audo1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.audible.in/"))); // Add privacy policy url
                }catch (Exception e){
                    Toast.makeText(getContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });

        view.findViewById(R.id.audo2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.audible.in/"))); // Add privacy policy url
                }catch (Exception e){
                    Toast.makeText(getContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}