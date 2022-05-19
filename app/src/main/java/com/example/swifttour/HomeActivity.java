package com.example.swifttour;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    TabLayout tabLayout;
    Fragment fragment;
    boolean doubleBackToExitPressedOnce=false;
    public static String uid;
    FirebaseAuth firebaseAuth;


    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        firebaseAuth = FirebaseAuth.getInstance();

        tabLayout = findViewById(R.id.nav1);

        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_baseline_home_24));
//        tabLayout.addTab(tabLayout.newTab().setText("Search"));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_baseline_search_24));
//        tabLayout.addTab(tabLayout.newTab().setText("Review"));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.blank_profile));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        FragmentManager manager = getFragmentManager();

        ExploreFragment timeLine_fragment = new ExploreFragment();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.frame1, timeLine_fragment, "timeline");
        transaction.commit();


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        fragment = new ExploreFragment();
                        break;
//                    case 1:
//                        fragment = new SearchFragment();
//                        break;
                    case 1:
                        fragment = new PlansFragment();
                        break;
//                    case 3:
////                        fragment = new ReviewFragment();
//                        break;
                    case 2:
                        fragment = new Fragment_Profile();
                        break;
                }
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.frame1, fragment);
                transaction.commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onBackPressed() {

            Toast.makeText(this, "Please click back again to exit", Toast.LENGTH_SHORT).show();
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }
            this.doubleBackToExitPressedOnce = true;

            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce=false;
                }
            }, 2000);

    }

    @Override
    protected void onStart() {
        super.onStart();
        uid=firebaseAuth.getUid();

    }
}