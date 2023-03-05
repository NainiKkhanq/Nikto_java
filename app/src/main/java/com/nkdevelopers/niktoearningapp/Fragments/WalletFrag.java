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




                if (Coins >= 50 && Coins <=100){

                    binding.UBTC.setText("0.00000002");
                    reference.child("BTC").setValue("0.00000002");
                }
                else if (Coins >= 100 && Coins<=150){
                    binding.UBTC.setText("0.00000004");
                    reference.child("BTC").setValue("0.00000004");

                }


                else if (Coins >= 200 && Coins <= 250){
                        binding.UBTC.setText("0.00000006");
                    reference.child("BTC").setValue("0.0000006");}

                 else if (Coins >= 250 && Coins <= 300){
                    binding.UBTC.setText("0.00000008");
                    reference.child("BTC").setValue("0.00000008");

                }else if (Coins >= 300 && Coins <= 350){
                    binding.UBTC.setText("0.00000009");
                    reference.child("BTC").setValue("0.00000009");

                }else if (Coins >=350 && Coins <= 400){
                    binding.UBTC.setText("0.00000010");
                    reference.child("BTC").setValue("0.00000010");

                }else if (Coins >= 400 && Coins <= 450){
                    binding.UBTC.setText("0.00000012");
                    reference.child("BTC").setValue("0.00000012");

                }else if (Coins >= 450 && Coins <= 500 ){
                    binding.UBTC.setText("0.00000014");
                    reference.child("BTC").setValue("0.00000014");

                }else if (Coins >= 500 && Coins <=550){
                    binding.UBTC.setText("0.00000015");
                    reference.child("BTC").setValue("0.00000015");

                }else if (Coins >= 550 && Coins <= 600){
                        binding.UBTC.setText("0.00000017");
                    reference.child("BTC").setValue("0.00000017");

                }else if (Coins >= 600 && Coins<= 700){
                    binding.UBTC.setText("0.00000019");
                    reference.child("BTC").setValue("0.00000019");

                }else if (Coins >= 700 && Coins <= 800){
                    binding.UBTC.setText("0.00000021");
                    reference.child("BTC").setValue("0.00000021");

                }else if (Coins >= 800 && Coins<=900){
                    binding.UBTC.setText("0.00000022");
                    reference.child("BTC").setValue("0.00000022");

                }else if (Coins >= 900 && Coins <=1000){
                    binding.UBTC.setText("0.00000023");
                    reference.child("BTC").setValue("0.00000024");

                }else if (Coins >= 1000 && Coins <=1050){
                    binding.UBTC.setText("0.00000025");
                    reference.child("BTC").setValue("0.00000025");

                }else if (Coins >= 1050 && Coins <= 1100){
                    binding.UBTC.setText("0.00000026");
                    reference.child("BTC").setValue("0.00000026");

                }else if (Coins >= 1100 && Coins <= 1150){
                    binding.UBTC.setText("0.00000027");
                    reference.child("BTC").setValue("0.00000027");

                }else if (Coins >= 1150 && Coins <= 1200){
                    binding.UBTC.setText("0.00000028");
                    reference.child("BTC").setValue("0.00000028");

                }else if (Coins >= 1200 && Coins <= 1300){
                    binding.UBTC.setText("0.00000029");
                    reference.child("BTC").setValue("0.00000029");

                }else if (Coins >= 1300 && Coins <= 1400){
                    binding.UBTC.setText("0.00000030");
                    reference.child("BTC").setValue("0.00000030");

                }else if (Coins >= 1400 && Coins <= 1600){
                    binding.UBTC.setText("0.00000032");
                    reference.child("BTC").setValue("0.00000032");

                }else if (Coins >= 1600 && Coins <= 1700){
                    binding.UBTC.setText("0.00000033");
                    reference.child("BTC").setValue("0.00000033");

                }else if (Coins >= 1700 && Coins <= 1750){
                    binding.UBTC.setText("0.00000034");
                    reference.child("BTC").setValue("0.00000034");

                }else if (Coins >= 1750 && Coins <= 1800){
                    binding.UBTC.setText("0.00000035");
                    reference.child("BTC").setValue("0.00000035");

                }else if (Coins >= 1800 && Coins <= 1900){
                    binding.UBTC.setText("0.00000036");
                    reference.child("BTC").setValue("0.00000036");

                }else if (Coins >= 1900 && Coins <= 2000){
                    binding.UBTC.setText("0.00000037");
                    reference.child("BTC").setValue("0.00000037");

                }else if (Coins >= 2000 && Coins <= 2100){
                    binding.UBTC.setText("0.00000038");
                    reference.child("BTC").setValue("0.00000038");

                }else if (Coins >= 2100 && Coins <= 2200){
                    binding.UBTC.setText("0.00000039");
                    reference.child("BTC").setValue("0.00000039");

                }else if (Coins >= 2200 && Coins <= 2300){
                    binding.UBTC.setText("0.00000040");
                    reference.child("BTC").setValue("0.00000040");

                }else if (Coins >= 2300 && Coins <= 2350){
                    binding.UBTC.setText("0.00000041");
                    reference.child("BTC").setValue("0.00000041");

                }else if (Coins >=2350  && Coins <= 2400){
                    binding.UBTC.setText("0.00000042");
                    reference.child("BTC").setValue("0.00000042");

                }else if (Coins >=2450 && Coins<= 2500){
                    binding.UBTC.setText("0.00000043");
                    reference.child("BTC").setValue("0.00000043");
                }else if (Coins >=2550 && Coins<= 2600){
                    binding.UBTC.setText("0.00000044");
                    reference.child("BTC").setValue("0.00000044");
                }else if (Coins >=2600 && Coins<= 2650){
                    binding.UBTC.setText("0.00000045");
                    reference.child("BTC").setValue("0.00000045");
                }else if (Coins >=2650 && Coins<= 2700){
                    binding.UBTC.setText("0.00000046");
                    reference.child("BTC").setValue("0.00000046");
                }else if (Coins >=2700 && Coins<= 2800){
                    binding.UBTC.setText("0.00000047");
                    reference.child("BTC").setValue("0.00000047");
                }else if (Coins >=2800 && Coins<= 2900){
                    binding.UBTC.setText("0.00000048");
                    reference.child("BTC").setValue("0.00000048");
                }else if (Coins >=2900 && Coins<= 3000){
                    binding.UBTC.setText("0.00000049");
                    reference.child("BTC").setValue("0.00000049");
                }else if (Coins >=3000 && Coins<=3050 ){
                    binding.UBTC.setText("0.00000051");
                    reference.child("BTC").setValue("0.00000051");
                }else if (Coins >=3050 && Coins<= 3100){
                    binding.UBTC.setText("0.00000053");
                    reference.child("BTC").setValue("0.00000053");
                }else if (Coins >=3150 && Coins <=3200){
                    binding.UBTC.setText("0.00000055");
                    reference.child("BTC").setValue("0.00000055");
                }else if (Coins >= 3200 && Coins <=3250 ){
                    binding.UBTC.setText("0.00000056");
                    reference.child("BTC").setValue("0.00000056");
                }else if (Coins >= 3250 && Coins <= 3300){
                    binding.UBTC.setText("0.00000059");
                    reference.child("BTC").setValue("0.00000059");
                }else if (Coins >= 3300 && Coins <= 3350){
                    binding.UBTC.setText("0.00000061");
                    reference.child("BTC").setValue("0.00000061");
                }else if (Coins >= 3350 && Coins <= 3400){
                    binding.UBTC.setText("0.00000063");
                    reference.child("BTC").setValue("0.00000063");}
                    else if (Coins >= 3400 && Coins <= 3500){
                        binding.UBTC.setText("0.00000065");
                        reference.child("BTC").setValue("0.00000065");}
                else if (Coins >= 3500 && Coins <= 3600){
                    binding.UBTC.setText("0.00000067");
                    reference.child("BTC").setValue("0.00000067");}
                else if (Coins >= 3600 && Coins <= 3700){
                    binding.UBTC.setText("0.00000068");
                    reference.child("BTC").setValue("0.00000068");}
                else if (Coins >= 3700 && Coins <= 3800){
                    binding.UBTC.setText("0.00000070");
                    reference.child("BTC").setValue("0.00000070");}
                else if (Coins >= 3800 && Coins <= 3900){
                    binding.UBTC.setText("0.00000072");
                    reference.child("BTC").setValue("0.00000072");}
                else if (Coins >= 3900 && Coins <= 4000){
                    binding.UBTC.setText("0.00000074");
                    reference.child("BTC").setValue("0.00000074");}

                    else if (Coins > 4050 || Coins >= 5000) {
                        binding.UBTC.setText("0.000000760+");
                        reference.child("BTC").setValue("0.00000076+");
                    binding.info1.setText("Keep Earning! Calculator is calculating Your Revenue!");
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