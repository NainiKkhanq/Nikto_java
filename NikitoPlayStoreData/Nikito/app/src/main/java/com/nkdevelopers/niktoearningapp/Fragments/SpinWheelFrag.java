package com.nkdevelopers.niktoearningapp.Fragments;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.admanager.AdManagerAdRequest;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.nkdevelopers.niktoearningapp.R;
import com.nkdevelopers.niktoearningapp.SpinWheel.LuckyWheelView;
import com.nkdevelopers.niktoearningapp.databinding.FragmentSpinWheelBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SpinWheelFrag extends Fragment  {


    FragmentSpinWheelBinding binding;
    FirebaseUser user;
    DatabaseReference databaseReference;
    String UID;
    InterstitialAd mInterstitialAd;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSpinWheelBinding.inflate(inflater,container,false);


        user= FirebaseAuth.getInstance().getCurrentUser();

        UID = user.getUid();





        // Getting and setting Current Coin Value From database

        databaseReference = FirebaseDatabase.getInstance().getReference(UID);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                String UCOINS = String.valueOf(snapshot.child("coins").getValue(Integer.class));
                int  CUCOINS = Integer.parseInt(UCOINS);
                binding.UserCoins.setText(UCOINS);

                int token = snapshot.child("withdraw").getValue(Integer.class);

                if (CUCOINS >= 10000 & token==0 ){


                    binding.spinbutton.setVisibility(View.INVISIBLE);
                    binding.nospins.setVisibility(View.VISIBLE);
                    binding.WithdrawNote.setText("*Coin limit reached If you want to earn more money, submit a withdrawal request.");

                    databaseReference.child("notice").setValue("*Coin limit reached If you want to earn more money, submit a withdrawal request.");




                }
                else{

                    binding.WithdrawNote.setText("Collect 10000 Coins to Get withdraw!");


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        // Disabling Spins Button if Spin Limit == 0




        List<com.nkappsdeveloper.spinandwin.SpinWheel.model.LuckyItem> luckyItemList = new ArrayList<>();

        // Creating Lucky Items class Object than we create lucky items

        com.nkappsdeveloper.spinandwin.SpinWheel.model.LuckyItem luckyItem1 = new com.nkappsdeveloper.spinandwin.SpinWheel.model.LuckyItem();

        luckyItem1.topText = "5";
        luckyItem1.secondaryText = " Coins";
        luckyItem1.color = Color.parseColor("#FF9700");
        luckyItem1.textColor = Color.parseColor("#FFFFFF");
        luckyItemList.add(luckyItem1);

        com.nkappsdeveloper.spinandwin.SpinWheel.model.LuckyItem luckyItem2 = new com.nkappsdeveloper.spinandwin.SpinWheel.model.LuckyItem();

        luckyItem2.topText = "10";
        luckyItem2.secondaryText = "Coins";
        luckyItem2.color = Color.parseColor("#208F00");
        luckyItem2.textColor = Color.parseColor("#FFFFFF");
        luckyItemList.add(luckyItem2);

        com.nkappsdeveloper.spinandwin.SpinWheel.model.LuckyItem luckyItem3 = new com.nkappsdeveloper.spinandwin.SpinWheel.model.LuckyItem();

        luckyItem3.topText = "15";
        luckyItem3.secondaryText = "Coins";
        luckyItem3.color = Color.parseColor("#FFFFFF");
        luckyItem3.textColor = Color.parseColor("#000000");
        luckyItemList.add(luckyItem3);


        com.nkappsdeveloper.spinandwin.SpinWheel.model.LuckyItem luckyItem4 = new com.nkappsdeveloper.spinandwin.SpinWheel.model.LuckyItem();

        luckyItem4.topText = "20";
        luckyItem4.secondaryText = "Coins";
        luckyItem4.color = Color.parseColor("#0074FF");
        luckyItem4.textColor = Color.parseColor("#FFFFFF");
        luckyItemList.add(luckyItem4);


        com.nkappsdeveloper.spinandwin.SpinWheel.model.LuckyItem luckyItem5 = new com.nkappsdeveloper.spinandwin.SpinWheel.model.LuckyItem();

        luckyItem5.topText = "25";
        luckyItem5.secondaryText = "Coins";
        luckyItem5.color = Color.parseColor("#FF00D1");
        luckyItem5.textColor = Color.parseColor("#FFFFFF");
        luckyItemList.add(luckyItem5);

        com.nkappsdeveloper.spinandwin.SpinWheel.model.LuckyItem luckyItem6 = new com.nkappsdeveloper.spinandwin.SpinWheel.model.LuckyItem();

        luckyItem6.topText = "30";
        luckyItem6.secondaryText = "Coins";
        luckyItem6.color = Color.parseColor("#FF0000");
        luckyItem6.textColor = Color.parseColor("#FFFFFF");
        luckyItemList.add(luckyItem6);

        com.nkappsdeveloper.spinandwin.SpinWheel.model.LuckyItem luckyItem7 = new com.nkappsdeveloper.spinandwin.SpinWheel.model.LuckyItem();

        luckyItem7.topText = "35";
        luckyItem7.secondaryText = "Coins";
        luckyItem7.color = Color.parseColor("#970A0A");
        luckyItem7.textColor = Color.parseColor("#FFFFFF");
        luckyItemList.add(luckyItem7);


        com.nkappsdeveloper.spinandwin.SpinWheel.model.LuckyItem luckyItem8 = new com.nkappsdeveloper.spinandwin.SpinWheel.model.LuckyItem();

        luckyItem8.topText = "40";
        luckyItem8.secondaryText = "Coins";
        luckyItem8.color = Color.parseColor("#000000");
        luckyItem8.textColor = Color.parseColor("#FFFFFF");
        luckyItemList.add(luckyItem8);


        binding.wheelview.setData(luckyItemList);
        binding.wheelview.setRound(8);


        binding.spinbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Random random = new Random();
                int randomnumber =  random.nextInt(4); // We will bound this number so user will get less coins
                binding.wheelview.startLuckyWheelWithTargetIndex(randomnumber);
            }
        });


        // Setting Click Listener on Spin Wheel the item which was selected

        binding.wheelview.setLuckyRoundItemSelectedListener(new LuckyWheelView.LuckyRoundItemSelectedListener() {
            @Override
            public void LuckyRoundItemSelected(int index) {


                CashUpdater(index);

            }
        });

        // Calling RealtimeData method which update the Spin Data when spin data change





        return binding.getRoot();

    }



    void CashUpdater(int index ) {


        long cash = 0;
        switch (index) {

            case 0:
                cash = 5;


            case 1:
                cash = 10;


                break;


            case 2:
                cash = 15;


                break;


            case 3:
                cash = 20;


            case 4:
                cash = 25;


                break;


            case 5:
                cash = 30;

                break;


            case 6:
                cash = 35;


                break;


            case 7:
                cash = 40;


                break;
        }



        // Decreasing Spins When User Spin the Wheel


        // Saving Coin value in Firebase


        databaseReference = FirebaseDatabase.getInstance().getReference(UID);
        databaseReference.child("coins").setValue(ServerValue.increment(cash));






    }





}

