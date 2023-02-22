package com.nkdevelopers.niktoearningapp.Fragments;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.ads.MobileAds;
import com.google.firebase.auth.FirebaseAuth;
import com.nkdevelopers.niktoearningapp.MainActivity;
import com.nkdevelopers.niktoearningapp.R;
import com.nkdevelopers.niktoearningapp.databinding.FragmentSplashBinding;
import com.unity3d.ads.UnityAds;

public class SplashFrag extends Fragment {

    FragmentSplashBinding binding;
    FirebaseAuth auth;
    Context context;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        UnityAds.initialize(getContext(),"4947123",false);

        MobileAds.initialize(getContext());




        binding = FragmentSplashBinding.inflate(inflater,container,false);

        auth = FirebaseAuth.getInstance();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                if (!isconnected()) {

                    binding.warntext.setText("Connection Failed :(");

                }else if (auth.getCurrentUser() == null){

                    FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                    transaction.replace(R.id.Replacer, new OnboardFrag());
                    transaction.commit();

                }
                else {


                    Toast.makeText(getContext(), "Welcome Back", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getContext(), MainActivity.class));
                }}
        },3000);


        return binding.getRoot();




    }
    private  boolean isconnected() {

        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo()!=null && connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting();


    }

}