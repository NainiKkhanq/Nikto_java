package com.nkdevelopers.niktoearningapp;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkConfiguration;

import com.nkdevelopers.niktoearningapp.Fragments.CategoryFrag;
import com.nkdevelopers.niktoearningapp.databinding.ActivityMainBinding;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    JSONObject consentObject = new JSONObject();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        AppLovinSdk.getInstance(MainActivity.this).setMediationProvider("max");
        AppLovinSdk.initializeSdk(MainActivity.this, new AppLovinSdk.SdkInitializationListener() {
            @Override
            public void onSdkInitialized(final AppLovinSdkConfiguration configuration)
            {
                // AppLovin SDK is initialized, start loading ads
            }
        } );



    }

    @Override
    protected void onStart() {
        super.onStart();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.MainReplacer,new CategoryFrag());
        transaction.commit();

    }

    @Override
    public void onBackPressed() {

    }


}