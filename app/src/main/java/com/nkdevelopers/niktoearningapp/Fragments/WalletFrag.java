package com.nkdevelopers.niktoearningapp.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkConfiguration;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nkdevelopers.niktoearningapp.R;
import com.nkdevelopers.niktoearningapp.databinding.FragmentWalletBinding;

public class WalletFrag extends Fragment {


    FragmentWalletBinding binding;
    DatabaseReference reference;
    int Coins;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentWalletBinding.inflate(getLayoutInflater(),container,false);

        getActivity().getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                        View.SYSTEM_UI_FLAG_FULLSCREEN);

        AppLovinSdk.getInstance(getContext()).setMediationProvider("max");
        AppLovinSdk.initializeSdk(getContext(), new AppLovinSdk.SdkInitializationListener() {
            @Override
            public void onSdkInitialized(final AppLovinSdkConfiguration configuration)
            {
                // AppLovin SDK is initialized, start loading ads
            }
        } );

        binding.maxAdView1.loadAd();


        reference = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
             Coins = snapshot.child("coins").getValue(Integer.class);
                binding.UserCoins.setText(String.valueOf(Coins));


                reference  =FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getUid());

                if (Coins < 50){

                    binding.UBTC.setText("0.00000000");
                    reference.child("BTC").setValue("Collect 500 Coins to Get Bitcoin Rate");



                }else if (Coins <= 500){
                        binding.UBTC.setText("0.00000004");
                    reference.child("BTC").setValue("0.0000004");}

                 else if (Coins >= 600 && Coins <= 700){
                    binding.UBTC.setText("0.00000006");
                    reference.child("BTC").setValue("0.00000006");

                }else if (Coins >= 700 && Coins <= 800){
                    binding.UBTC.setText("0.000000065");
                    reference.child("BTC").setValue("0.000000065");

                }else if (Coins >=800 && Coins <= 900){
                    binding.UBTC.setText("0.00000008");
                    reference.child("BTC").setValue("0.00000008");

                }else if (Coins >= 900 && Coins <= 1000){
                    binding.UBTC.setText("0.00000010");
                    reference.child("BTC").setValue("0.00000010");

                }else if (Coins >= 1000 && Coins <= 1100 ){
                    binding.UBTC.setText("0.00000012");
                    reference.child("BTC").setValue("0.00000012");

                }else if (Coins >= 1100 && Coins <=1200){
                    binding.UBTC.setText("0.00000014");
                    reference.child("BTC").setValue("0.00000014");

                }else if (Coins >= 1200 && Coins <= 1300){
                    binding.UBTC.setText("0.00000016");
                    reference.child("BTC").setValue("0.00000016");

                }else if (Coins >= 1300 && Coins<= 1400){
                    binding.UBTC.setText("0.00000018");
                    reference.child("BTC").setValue("0.00000018");

                }else if (Coins >= 1400 && Coins <= 1500){
                    binding.UBTC.setText("0.00000020");
                    reference.child("BTC").setValue("0.00000020");

                }else if (Coins >= 1500 && Coins<=1600){
                    binding.UBTC.setText("0.00000022");
                    reference.child("BTC").setValue("0.00000022");

                }else if (Coins >= 1600 && Coins <=1700){
                    binding.UBTC.setText("0.00000024");
                    reference.child("BTC").setValue("0.00000024");

                }else if (Coins >= 1800 && Coins <=1900){
                    binding.UBTC.setText("0.00000026");
                    reference.child("BTC").setValue("0.00000026");

                }else if (Coins >= 1900 && Coins <= 2000){
                    binding.UBTC.setText("0.00000028");
                    reference.child("BTC").setValue("0.00000028");

                }else if (Coins >= 2000 && Coins <= 2100){
                    binding.UBTC.setText("0.00000030");
                    reference.child("BTC").setValue("0.00000030");

                }else if (Coins >= 2100 && Coins <= 2200){
                    binding.UBTC.setText("0.00000032");
                    reference.child("BTC").setValue("0.00000032");

                }else if (Coins >= 2200 && Coins <= 2300){
                    binding.UBTC.setText("0.00000034");
                    reference.child("BTC").setValue("0.00000034");

                }else if (Coins >= 2300 && Coins <= 2400){
                    binding.UBTC.setText("0.00000036");
                    reference.child("BTC").setValue("0.00000036");

                }else if (Coins >= 2400 && Coins <= 2500){
                    binding.UBTC.setText("0.00000038");
                    reference.child("BTC").setValue("0.00000038");

                }else if (Coins >= 2500 && Coins <= 2600){
                    binding.UBTC.setText("0.00000040");
                    reference.child("BTC").setValue("0.00000040");

                }else if (Coins >= 2600 && Coins <= 2700){
                    binding.UBTC.setText("0.00000042");
                    reference.child("BTC").setValue("0.00000042");

                }else if (Coins >= 2700 && Coins <= 2800){
                    binding.UBTC.setText("0.00000044");
                    reference.child("BTC").setValue("0.00000044");

                }else if (Coins >= 2800 && Coins <= 2900){
                    binding.UBTC.setText("0.00000046");
                    reference.child("BTC").setValue("0.00000046");

                }else if (Coins >= 3000 && Coins <= 3100){
                    binding.UBTC.setText("0.00000048");
                    reference.child("BTC").setValue("0.00000048");

                }else if (Coins >= 3100 && Coins <= 3200){
                    binding.UBTC.setText("0.00000050");
                    reference.child("BTC").setValue("0.00000050");

                }else if (Coins >= 3200 && Coins <= 3300){
                    binding.UBTC.setText("0.00000052");
                    reference.child("BTC").setValue("0.00000052");

                }else if (Coins >= 3300 && Coins <= 3400){
                    binding.UBTC.setText("0.00000054");
                    reference.child("BTC").setValue("0.00000054");

                }else if (Coins >= 3400 && Coins <= 3500){
                    binding.UBTC.setText("0.00000056");
                    reference.child("BTC").setValue("0.00000056");

                }else if (Coins >=3500  && Coins <= 3600){
                    binding.UBTC.setText("0.00000058");
                    reference.child("BTC").setValue("0.00000058");

                }else if (Coins >=3700 && Coins<= 3800){
                    binding.UBTC.setText("0.00000060");
                    reference.child("BTC").setValue("0.00000060");
                }else if (Coins >=3800 && Coins<= 4000){
                    binding.UBTC.setText("0.00000062");
                    reference.child("BTC").setValue("0.00000062");
                }else if (Coins >=4000 && Coins<= 5000){
                    binding.UBTC.setText("0.00000064");
                    reference.child("BTC").setValue("0.00000064");
                }else if (Coins >=5000 && Coins<= 6000){
                    binding.UBTC.setText("0.00000066");
                    reference.child("BTC").setValue("0.00000066");
                }else if (Coins >=6000 && Coins<= 7000){
                    binding.UBTC.setText("0.00000068");
                    reference.child("BTC").setValue("0.00000068");
                }else if (Coins >=7000 && Coins<= 8000){
                    binding.UBTC.setText("0.00000070");
                    reference.child("BTC").setValue("0.00000070");
                }else if (Coins >=8000 && Coins<= 10000){
                    binding.UBTC.setText("0.00000074");
                    reference.child("BTC").setValue("0.00000074");
                }else if (Coins >=10000 && Coins<= 11000){
                    binding.UBTC.setText("0.00000078");
                    reference.child("BTC").setValue("0.00000078");
                }else if (Coins >=12000 && Coins<= 13000){
                    binding.UBTC.setText("0.00000084");
                    reference.child("BTC").setValue("0.00000084");
                }else if (Coins ==13000 || Coins >14000){
                    binding.UBTC.setText("0.00000088+");
                    reference.child("BTC").setValue("0.00000080+");
                    binding.info1.setText("Bitcoin Calculator is Working in Backend. So Don't Worry about Earning Price Calculated With Coins");
                }





            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        binding.backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction  = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.MainReplacer,new CategoryFrag());
                transaction.commit();
            }
        });

        binding.backoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction  = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.MainReplacer,new WithdrawFrag());
                transaction.commit();
            }
        });

        return binding.getRoot();
    }
}