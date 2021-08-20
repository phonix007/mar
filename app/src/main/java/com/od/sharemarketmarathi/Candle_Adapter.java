package com.od.sharemarketmarathi;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.od.sharemarketmarathi.R;

import java.util.List;



public class Candle_Adapter extends RecyclerView.Adapter<Candle_Adapter.viewholder> {

    private List<Candle_Model> Candle_ModelList;

    public Candle_Adapter(List<Candle_Model> Candle_ModelList) {
        this.Candle_ModelList = Candle_ModelList;
    }

    @NonNull
    @Override
    public Candle_Adapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.candle_iteam,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Candle_Adapter.viewholder holder, int position) {
        holder.setData(Candle_ModelList.get(position).getUrl(),Candle_ModelList.get(position).getTitle(),Candle_ModelList.get(position).getInfo());
    }

    @Override
    public int getItemCount() {
        return Candle_ModelList.size();
    }

    class viewholder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView title;
        private TextView info;

        public viewholder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.img_can);
            title = itemView.findViewById(R.id.name_can);
            info = itemView.findViewById(R.id.info_can);
        }
        private  void setData(String url,String title, String info){  //Remove final from here if it gives error
            Glide.with(itemView.getContext()).load(url).into(imageView);
            this.title.setText(title);
            this.info.setText(info);

        }

    }

}
