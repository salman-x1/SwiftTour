package com.example.swifttour;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PlansFragment extends android.app.Fragment {
    RecyclerView planRv;
    ArrayList<UploadTrip> list;
    DatabaseReference reference;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_plans, container, false);
//        planRv=view.findViewById(R.id.planRv);
//        planRv.setHasFixedSize(true);
        list=new ArrayList<>();
        reference= FirebaseDatabase.getInstance().getReference().child("swift-tour");
        reference.child(HomeActivity.uid).child("Trips").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list.clear();
                for (DataSnapshot snapshot1:snapshot.getChildren())
                {
                    UploadTrip uploadTrip = snapshot1.getValue(UploadTrip.class);
//                    assert uploadTrip != null;
                    list.add(uploadTrip);
                }
                PlanAdapter planAdapter=new PlanAdapter(list);
                planRv.setAdapter(planAdapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        return view;
    }
}