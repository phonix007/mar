package com.od.sharemarketmarathi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;

public class qusAdapter extends RecyclerView.Adapter<qusAdapter.MyViewHolder> {

    Context context;
    ArrayList<questionModel> questionModelArrayList;

    public qusAdapter(Context context, ArrayList<questionModel> questionModelArrayList) {
        this.context = context;
        this.questionModelArrayList = questionModelArrayList;
    }

    @NonNull
    @Override
    public qusAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.qditeam,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull qusAdapter.MyViewHolder holder, int position) {

        questionModel questionModel = questionModelArrayList.get(position);

        holder.que.setText(questionModel.que);
        holder.ans.setText(questionModel.ans);

    }

    @Override
    public int getItemCount() {
        return questionModelArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView que,ans;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            que = itemView.findViewById(R.id.tvquestion);
            ans = itemView.findViewById(R.id.tvanswer);

        }
    }
}
