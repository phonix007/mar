package com.od.sharemarketmarathi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BasicAdapter extends RecyclerView.Adapter<BasicAdapter.viewholder> {

    private List<BasicModel> basicModelList;

    public BasicAdapter(List<BasicModel> basicModelList) {
        this.basicModelList = basicModelList;
    }



    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.basic_iteam,parent,false);
                return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.setData(basicModelList.get(position).getTitel());

    }

    @Override
    public int getItemCount() {
        return basicModelList.size();
    }

    class viewholder extends RecyclerView.ViewHolder{

        private TextView title;

        public viewholder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
        }
        private void setData(String title){
            this.title.setText(title);
        }
    }
}
