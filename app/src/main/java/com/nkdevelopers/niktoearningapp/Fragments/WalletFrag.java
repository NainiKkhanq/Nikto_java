package com.nkdevelopers.niktoearningapp.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    String BITCOIN;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentWalletBinding.inflate(getLayoutInflater(),container,false);

        getActivity().getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                        View.SYSTEM_UI_FLAG_FULLSCREEN);



        reference = FirebaseDatabase.getInstance().getReference().child("MY_USERS").child(FirebaseAuth.getInstance().getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
             Coins = snapshot.child("coins").getValue(Integer.class);
             BITCOIN = snapshot.child("BTC").getValue(String.class);
                binding.UserCoins.setText(String.valueOf(Coins));



                reference  =FirebaseDatabase.getInstance().getReference().child("MY_USERS").child(FirebaseAuth.getInstance().getUid());

                    binding.UBTC.setText(BITCOIN);
                if (Coins >= 100 && Coins<=300 ){


                    reference.child("BTC").setValue("0.00000002");


                }else if (Coins >= 300 && Coins < 500 ){

                    reference.child("BTC").setValue("0.00000003");

                }


                else if (Coins >= 500 && Coins < 600){

                    reference.child("BTC").setValue("0.00000006");

                }
                else if (Coins >= 600 && Coins <= 700){

                    reference.child("BTC").setValue("0.0000008");

                }else if (Coins >= 700 && Coins <= 800){

                    reference.child("BTC").setValue("0.00000012");


                }else if (Coins >=800 && Coins <= 900){

                    reference.child("BTC").setValue("0.00000014");


                }else if (Coins >= 900 && Coins <= 1000){

                    reference.child("BTC").setValue("0.00000016");


                }else if (Coins >= 1000 && Coins <= 1100 ){

                    reference.child("BTC").setValue("0.00000018");

                }else if (Coins >= 1100 && Coins <=1200){

                    reference.child("BTC").setValue("0.00000020");

                }else if (Coins >= 1200 && Coins <= 1300){

                    reference.child("BTC").setValue("0.00000024");


                }else if (Coins >= 1300 && Coins<= 1400){

                    reference.child("BTC").setValue("0.00000026");


                }else if (Coins >= 1400 && Coins <= 1500){

                    reference.child("BTC").setValue("0.00000028");


                }else if (Coins >= 1500 && Coins<=1600){

                    reference.child("BTC").setValue("0.00000030");

                }else if (Coins >= 1600 && Coins <=1700){

                    reference.child("BTC").setValue("0.00000032");


                }else if (Coins >= 1800 && Coins <=1900){

                    reference.child("BTC").setValue("0.00000036");


                }else if (Coins >= 1900 && Coins <= 2000){

                    reference.child("BTC").setValue("0.00000038");

                }else if (Coins >= 2000 && Coins <= 2100){

                    reference.child("BTC").setValue("0.00000040");


                }else if (Coins >= 2100 && Coins <= 2200){

                    reference.child("BTC").setValue("0.00000043");


                }else if (Coins >= 2200 && Coins <= 2300){

                    reference.child("BTC").setValue("0.00000046");

                }else if (Coins >= 2300 && Coins <= 2400){

                    reference.child("BTC").setValue("0.00000048");


                }else if (Coins >= 2400 && Coins <= 2500){

                    reference.child("BTC").setValue("0.00000050");


                }else if (Coins >= 2500 && Coins <= 2600){
                    reference.child("BTC").setValue("0.00000052");


                }else if (Coins >= 2600 && Coins <= 2700){
                    reference.child("BTC").setValue("0.00000054");

                }else if (Coins >= 2700 && Coins <= 2800){
                    reference.child("BTC").setValue("0.00000056");


                }else if (Coins >= 2800 && Coins <= 2900){
                    reference.child("BTC").setValue("0.00000058");

                }else if (Coins >= 3000 && Coins <= 3100){
                    reference.child("BTC").setValue("0.00000062");

                }else if (Coins >= 3100 && Coins <= 3200){
                    reference.child("BTC").setValue("0.00000074");

                }else if (Coins >= 3200 && Coins <= 3300){
                    reference.child("BTC").setValue("0.00000076");

                }else if (Coins >= 3300 && Coins <= 3400){
                    reference.child("BTC").setValue("0.00000079");

                }else if (Coins >= 3400 && Coins <= 3500){
                    reference.child("BTC").setValue("0.00000081");

                }else if (Coins >=3500  && Coins <= 3600){
                    reference.child("BTC").setValue("0.00000084");

                }else if (Coins >=3700 && Coins<= 3800){
                    reference.child("BTC").setValue("0.00000086");
                }else if (Coins >=3800 && Coins<= 4000){
                    reference.child("BTC").setValue("0.00000088");
                }else if (Coins >=4000 && Coins<= 5000){
                    reference.child("BTC").setValue("0.00000090");
                }else if (Coins >=5000 && Coins<= 6000){
                    reference.child("BTC").setValue("0.00000092");
                }else if (Coins >=6000 && Coins<= 7000){
                    reference.child("BTC").setValue("0.00000100");
                }else if (Coins >=7000 && Coins<= 8000){
                    reference.child("BTC").setValue("0.00000106");
                }else if (Coins >=8000 && Coins<= 10000){
                    reference.child("BTC").setValue("0.00000108");
                }else if (Coins >=10000 && Coins<= 11000){
                    reference.child("BTC").setValue("0.00000112");
                }else if (Coins >=12000 && Coins<= 13000){
                    reference.child("BTC").setValue("0.00000114");
                }else if (Coins ==13000 || Coins >14000){
                    reference.child("BTC").setValue("0.00000118+");
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