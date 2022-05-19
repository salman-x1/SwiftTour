package com.example.swifttour;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class SecondExploreAdapter extends RecyclerView.Adapter<SecondExploreAdapter.MyViewHolder> {

    ArrayList<TripData> tripData;

    public SecondExploreAdapter(ArrayList<TripData> tripData) {
        this.tripData = tripData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.triplist, parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Glide.with(holder.img1.getContext())
                .load(tripData.get(position).getImgurl())
                .centerCrop()
                .placeholder(R.drawable.tripadvisiorlogo)
                .into(holder.img1);
                holder.loc1.setText(tripData.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return tripData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView img1;
        TextView loc1;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img1=itemView.findViewById(R.id.img1);
            loc1=itemView.findViewById(R.id.loc1);


        }
    }
}
