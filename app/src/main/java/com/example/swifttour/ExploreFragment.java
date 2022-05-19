package com.example.swifttour;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class ExploreFragment extends android.app.Fragment {

    RecyclerView rec1;
    ArrayList<TripData> tripData;
Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_explore, container, false);
        rec1=view.findViewById(R.id.rec1);
        Context context=getActivity().getApplicationContext();
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(layoutManager.HORIZONTAL);
        rec1.setLayoutManager(layoutManager);
        rec1.setHasFixedSize(false);




        tripData=new ArrayList<>();
        TripData tripData2=new TripData("Lahore","https://wallpapercave.com/wp/wp9260731.jpg");
        tripData.add(tripData2);

        TripData tripData3=new TripData("Lahore","https://wallpapercave.com/wp/wp9260731.jpg");
        tripData.add(tripData3);

        TripData tripData4=new TripData("Lahore","https://wallpapercave.com/wp/wp9260731.jpg");
        tripData.add(tripData4);

        TripData tripData5=new TripData("Lahore","https://wallpapercave.com/wp/wp9260731.jpg");
        tripData.add(tripData5);

        TripData tripData6=new TripData("Lahore","https://wallpapercave.com/wp/wp9260731.jpg");
        tripData.add(tripData6);

        TripData tripData7=new TripData("Lahore","https://wallpapercave.com/wp/wp9260731.jpg");
        tripData.add(tripData7);

        TripData tripData8=new TripData("Lahore","https://wallpapercave.com/wp/wp9260731.jpg");
        tripData.add(tripData8);


        ExploreAdapter exploreAdapter= new ExploreAdapter(tripData,context);
        rec1.setAdapter(exploreAdapter);
        return view;

    }
}