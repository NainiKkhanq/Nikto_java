package com.nkdevelopers.niktoearningapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.os.Bundle;

import com.nkdevelopers.niktoearningapp.Fragments.SplashFrag;
import com.nkdevelopers.niktoearningapp.databinding.ActivityOnboardScreenLoginAndSignupBinding;
import com.unity3d.ads.IUnityAdsInitializationListener;
import com.unity3d.ads.IUnityAdsLoadListener;
import com.unity3d.ads.IUnityAdsShowListener;
import com.unity3d.ads.UnityAds;
import com.unity3d.ads.UnityAdsShowOptions;

public class OnboardScreen_LoginAndSignup extends AppCompatActivity   {





    ActivityOnboardScreenLoginAndSignupBinding binding;











    // Unity Reward Ad End HEre
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOnboardScreenLoginAndSignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        // Unity Reward Ads Setup




        //   Loading Fragments

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.Replacer, new SplashFrag());
        fragmentTransaction.commit();

    }



}