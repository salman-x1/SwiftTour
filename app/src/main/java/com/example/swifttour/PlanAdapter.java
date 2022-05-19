package com.example.swifttour;

import android.content.pm.LabeledIntent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class PlanAdapter extends RecyclerView.Adapter<PlanAdapter.myviewHolder> {
    ArrayList<UploadTrip> list;

    public PlanAdapter(ArrayList<UploadTrip> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public myviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new myviewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.plan,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull myviewHolder holder, int position) {

        holder.ptext.setText(list.get(position).getCaption());
        Glide.with(holder.pimg.getContext())
                .load(list.get(position).getImgurl())
                .centerCrop()
                .placeholder(R.drawable.tripadvisiorlogo)
                .into(holder.pimg);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class myviewHolder extends RecyclerView.ViewHolder {
        ImageView pimg;
        TextView ptext;
        public myviewHolder(@NonNull View itemView) {
            super(itemView);
            pimg=itemView.findViewById(R.id.pimg);
            ptext=itemView.findViewById(R.id.ptext);
        }
    }
}
