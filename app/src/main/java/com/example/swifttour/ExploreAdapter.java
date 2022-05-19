package com.example.swifttour;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExploreAdapter extends RecyclerView.Adapter<ExploreAdapter.MyViewHolder> {

    ArrayList<TripData> tripData;
    Context context;

    public ExploreAdapter(ArrayList<TripData> tripData, Context context) {
        this.tripData = tripData;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.rec2.setHasFixedSize(true);
//        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);
//        holder.rec2.setLayoutManager(linearLayoutManager);
        holder.rec2.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        SecondExploreAdapter secondExploreAdapter=new SecondExploreAdapter(tripData);
        holder.rec2.setAdapter(secondExploreAdapter);


    }

    @Override
    public int getItemCount() {
        return tripData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        RecyclerView rec2;
        TextView exploree;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            rec2=itemView.findViewById(R.id.rec2);

        }
    }
}
