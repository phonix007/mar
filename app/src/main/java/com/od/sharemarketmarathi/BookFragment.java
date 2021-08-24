package com.od.sharemarketmarathi;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

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

        ImageView streetwall = (ImageView) view.findViewById(R.id.wallstreet);
        Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/stock-market-marathi.appspot.com/o/Books%2FOne%20Up%20On%20Wall%20Street%20%E2%80%93%20Peter%20Lynch.jpg?alt=media&token=b6ca0ccd-feb7-49b3-aaf8-fb2b1f08cb73").placeholder(R.drawable.refresh).into(streetwall);

        ImageView cr_this = (ImageView) view.findViewById(R.id.cr);
        Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/stock-market-marathi.appspot.com/o/Books%2FShare%20Market%20Se%20Kaise%20Banaye%20Mene%2010%20Crore.jfif?alt=media&token=cb1b7152-033e-4399-ab64-0f9f7199c5ba").placeholder(R.drawable.refresh).into(cr_this);

        ImageView stock_ri = (ImageView) view.findViewById(R.id.stock_rich);
        Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/stock-market-marathi.appspot.com/o/Books%2FStocks%20to%20Riches%20%E2%80%93%20Parag%20Parikh.jpg?alt=media&token=40fdf2bd-bbad-4b30-9c3d-c0b3deecac2c").placeholder(R.drawable.refresh).into(stock_ri);

        ImageView inteleg = (ImageView) view.findViewById(R.id.itelle);
        Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/stock-market-marathi.appspot.com/o/Books%2FThe%20Intelligent%20Investor-Benjamin%20Graham.jpg?alt=media&token=a910cd4c-915a-478e-ac22-d1b6667d05fc").placeholder(R.drawable.refresh).into(inteleg);

        ImageView buffet = (ImageView) view.findViewById(R.id.buff);
        Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/stock-market-marathi.appspot.com/o/Books%2FThe%20Warren%20Buffet%20Way-%20Robert%20G.%20Hagstrom.jpg?alt=media&token=a745e0c8-2efd-49f7-ae8b-baf79a3b2536").placeholder(R.drawable.refresh).into(buffet);


        return view;
    }
}