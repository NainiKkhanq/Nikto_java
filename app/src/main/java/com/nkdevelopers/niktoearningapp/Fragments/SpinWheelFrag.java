package com.nkdevelopers.niktoearningapp.Fragments;

import android.annotation.SuppressLint;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
 import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.applovin.adview.AppLovinAdView;
import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.MaxReward;
import com.applovin.mediation.MaxRewardedAdListener;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.applovin.mediation.ads.MaxRewardedAd;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkConfiguration;
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

public class SpinWheelFrag extends Fragment implements MaxAdListener, MaxRewardedAdListener {




    FragmentSpinWheelBinding binding;
    FirebaseUser user;
    DatabaseReference databaseReference;
    String UID;
    int SPINVAL;
    private  MaxInterstitialAd interstitialAd;
    private MaxRewardedAd rewardedAd;

    int Coins;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSpinWheelBinding.inflate(getLayoutInflater(),container,false);
        getActivity().getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                        View.SYSTEM_UI_FLAG_FULLSCREEN);

//        context = getContext();
//
        AppLovinSdk.getInstance(getContext()).setMediationProvider("max");
        AppLovinSdk.initializeSdk(getContext(), new AppLovinSdk.SdkInitializationListener() {
            @Override
            public void onSdkInitialized(final AppLovinSdkConfiguration configuration)
            {
                // AppLovin SDK is initialized, start loading ads

            }
        } );
//      Max int Ad
        interstitialAd = new MaxInterstitialAd( "390881b42411d57f", requireActivity());
        interstitialAd.setListener( this );
        interstitialAd.loadAd();
        // Max Rewarded Ad
        rewardedAd = MaxRewardedAd.getInstance( "2eda4b8d0c86c6fc", requireActivity() );
        rewardedAd.setListener( this );

        rewardedAd.loadAd();

        // Banner Ad

            AppLovinAdView  bannerad  = new AppLovinAdView(AppLovinAdSize.BANNER,getContext());

            binding.MAXBANNER.addView(bannerad);
            bannerad.loadNextAd();


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

                SPINVAL = snapshot.child("spins").getValue(Integer.class);
                binding.spins.setText(String.valueOf(SPINVAL));



                if (SPINVAL <= 0){

                    binding.spinbutton.setVisibility(View.INVISIBLE);
                    binding.nospins.setVisibility(View.VISIBLE);

                    binding.wheelview.setVisibility(View.INVISIBLE);

                    binding.getmorespins.setVisibility(View.VISIBLE);
                    binding.nospins.setVisibility(View.INVISIBLE);
                    binding.imageView5.setVisibility(View.INVISIBLE);
                    binding.imageView3.setVisibility(View.INVISIBLE);








                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        binding.backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.MainReplacer, new CategoryFrag());

                transaction.commit();

            }
        });

        // Disabling Spins Button if Spin Limit == 0




        List<com.nkappsdeveloper.spinandwin.SpinWheel.model.LuckyItem> luckyItemList = new ArrayList<>();

        // Creating Lucky Items class Object than we create lucky items

        com.nkappsdeveloper.spinandwin.SpinWheel.model.LuckyItem luckyItem1 = new com.nkappsdeveloper.spinandwin.SpinWheel.model.LuckyItem();

        luckyItem1.topText = "2";
        luckyItem1.secondaryText = " Coins";
        luckyItem1.color = Color.parseColor("#FF9700");
        luckyItem1.textColor = Color.parseColor("#FFFFFF");
        luckyItemList.add(luckyItem1);

        com.nkappsdeveloper.spinandwin.SpinWheel.model.LuckyItem luckyItem2 = new com.nkappsdeveloper.spinandwin.SpinWheel.model.LuckyItem();

        luckyItem2.topText = "4";
        luckyItem2.secondaryText = "Coins";
        luckyItem2.color = Color.parseColor("#208F00");
        luckyItem2.textColor = Color.parseColor("#FFFFFF");
        luckyItemList.add(luckyItem2);

        com.nkappsdeveloper.spinandwin.SpinWheel.model.LuckyItem luckyItem3 = new com.nkappsdeveloper.spinandwin.SpinWheel.model.LuckyItem();

        luckyItem3.topText = "6";
        luckyItem3.secondaryText = "Coins";
        luckyItem3.color = Color.parseColor("#FFFFFF");
        luckyItem3.textColor = Color.parseColor("#000000");
        luckyItemList.add(luckyItem3);


        com.nkappsdeveloper.spinandwin.SpinWheel.model.LuckyItem luckyItem4 = new com.nkappsdeveloper.spinandwin.SpinWheel.model.LuckyItem();

        luckyItem4.topText = "8";
        luckyItem4.secondaryText = "Coins";
        luckyItem4.color = Color.parseColor("#0074FF");
        luckyItem4.textColor = Color.parseColor("#FFFFFF");
        luckyItemList.add(luckyItem4);


        com.nkappsdeveloper.spinandwin.SpinWheel.model.LuckyItem luckyItem5 = new com.nkappsdeveloper.spinandwin.SpinWheel.model.LuckyItem();

        luckyItem5.topText = "10";
        luckyItem5.secondaryText = "Coins";
        luckyItem5.color = Color.parseColor("#FF00D1");
        luckyItem5.textColor = Color.parseColor("#FFFFFF");
        luckyItemList.add(luckyItem5);

        com.nkappsdeveloper.spinandwin.SpinWheel.model.LuckyItem luckyItem6 = new com.nkappsdeveloper.spinandwin.SpinWheel.model.LuckyItem();

        luckyItem6.topText = "0";
        luckyItem6.secondaryText = "Coins";
        luckyItem6.color = Color.parseColor("#000000");
        luckyItem6.textColor = Color.parseColor("#FFFFFF");
        luckyItemList.add(luckyItem6);

        com.nkappsdeveloper.spinandwin.SpinWheel.model.LuckyItem luckyItem7 = new com.nkappsdeveloper.spinandwin.SpinWheel.model.LuckyItem();

//        luckyItem7.topText = "";
//        luckyItem7.secondaryText = "Coins";
//        luckyItem7.color = Color.parseColor("#970A0A");
//        luckyItem7.textColor = Color.parseColor("#FFFFFF");
//        luckyItemList.add(luckyItem7);
//
//
//        com.nkappsdeveloper.spinandwin.SpinWheel.model.LuckyItem luckyItem8 = new com.nkappsdeveloper.spinandwin.SpinWheel.model.LuckyItem();
//
//        luckyItem8.topText = "0";
//        luckyItem8.secondaryText = "Coins";
//        luckyItem8.color = Color.parseColor("#000000");
//        luckyItem8.textColor = Color.parseColor("#FFFFFF");
//        luckyItemList.add(luckyItem8);





        binding.wheelview.setData(luckyItemList);
        binding.wheelview.setRound(8);



        binding.spinbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Random random = new Random();
                int randomnumber = random.nextInt(6); // We will bound this number so user will get less coins
                binding.wheelview.startLuckyWheelWithTargetIndex(randomnumber);






                if( randomnumber == 6 || randomnumber == 2 || randomnumber == 4 ){
                    interstitialAd.loadAd();

                    if ( interstitialAd.isReady() )
                    {
                        interstitialAd.showAd();
                    }




                }


            }
            });



        // Setting Click Listener on Spin Wheel the item which was selected

        binding.wheelview.setLuckyRoundItemSelectedListener(new LuckyWheelView.LuckyRoundItemSelectedListener() {
            @Override
            public void LuckyRoundItemSelected(int index) {


                CashUpdater(index);

            }
        });



        // Get More Spins Button Click

        binding.getmorespins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                rewardedAd.loadAd();
                if ( rewardedAd.isReady() )
                {
                    rewardedAd.showAd();
                }

                new CountDownTimer(10000, 1000) {

                    public void onTick(long millisUntilFinished) {
                        binding.WithdrawNote.setText("If Ad Not Loaded Try Again After " + millisUntilFinished / 1000 + "s");

                        binding.getmorespins.setClickable(false);}

                    public void onFinish() {


                    }

                }.start();
            }
        });









        return binding.getRoot();

    }




    void CashUpdater(int index ) {


        long cash = 0;
        switch (index) {

            case 0:
                cash = 2;


                break;

            case 1:
                cash = 4;


                break;


            case 2:
                cash = 6;


                break;


            case 3:
                cash = 8;
                break;

            case 4:
                cash = 10;



                break;


            case 5:
                cash = 0;

                break;

//
//            case 6:
//                cash = 28;
//
//
//                break;
//
//
//            case 7:
//                cash = 0;
//
//
//                break;
        }



        // Decreasing Spins When User Spin the Wheel


        // Saving Coin value in Firebase


        databaseReference = FirebaseDatabase.getInstance().getReference(UID);
        databaseReference.child("coins").setValue(ServerValue.increment(cash));
        binding.WithdrawNote.setVisibility(View.VISIBLE);
        binding.WithdrawNote.setText("You Won " + cash + " Coins");
        if (SPINVAL >0) {


            databaseReference.child("spins").setValue(ServerValue.increment(-1));

        }

    }



    @Override
    public void onAdLoaded(MaxAd ad) {

    }

    @Override
    public void onAdDisplayed(MaxAd ad) {
        databaseReference = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getUid());


        databaseReference.child("inter_AD").setValue(ServerValue.increment(1));

    }

    @Override
    public void onAdHidden(MaxAd ad) {

    }

    @Override
    public void onAdClicked(MaxAd ad) {

    }

    @Override
    public void onAdLoadFailed(String adUnitId, MaxError error) {
        interstitialAd.loadAd();
    }

    @Override
    public void onAdDisplayFailed(MaxAd ad, MaxError error) {

        interstitialAd.loadAd();

    }

    // Reward Ad Listener
    // MAX Ad Listener
    public void onAdLoadedR(final MaxAd maxAd)
    {
        // Rewarded ad is ready to be shown. rewardedAd.isReady() will now return 'true'

        // Reset retry attempt
    }


    public void onAdLoadFailedR(final String adUnitId, final MaxError error)
    {
        // Rewarded ad failed to load
        // We recommend retrying with exponentially higher delays up to a maximum delay (in this case 64 seconds)

        rewardedAd.loadAd();

    }

    public void onAdDisplayFailedR(final MaxAd maxAd, final MaxError error)
    {
        // Rewarded ad failed to display. We recommend loading the next ad
        rewardedAd.loadAd();
    }


    @Override
    public void onRewardedVideoStarted(final MaxAd maxAd) {

        Toast.makeText(getContext(), "Watch Complete Video to Get Reward", Toast.LENGTH_SHORT).show();



    } // deprecated

    @Override
    public void onRewardedVideoCompleted(final MaxAd maxAd) {} // deprecated

    @Override
    public void onUserRewarded(final MaxAd maxAd, final MaxReward maxReward)
    {
        // Rewarded ad was displayed and user should receive the reward
        databaseReference = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getUid());

        databaseReference.child("spins").setValue(6);


        binding.spinbutton.setVisibility(View.VISIBLE);
        binding.nospins.setVisibility(View.INVISIBLE);

        binding.wheelview.setVisibility(View.VISIBLE);

        binding.getmorespins.setVisibility(View.INVISIBLE);
        binding.nospins.setVisibility(View.INVISIBLE);
        binding.imageView5.setVisibility(View.VISIBLE);
        binding.imageView3.setVisibility(View.VISIBLE);



        databaseReference = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getCurrentUser().getUid());


        databaseReference.child("reward_AD").setValue(ServerValue.increment(1));

        DatabaseReference  reference = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Coins = snapshot.child("coins").getValue(Integer.class);
                binding.UserCoins.setText(String.valueOf(Coins));


                DatabaseReference reference  =FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getUid());

                if (Coins < 50){


                    reference.child("BTC").setValue("Collect 500 Coins to Get Bitcoin Rate");



                }else if (Coins <= 500){

                    reference.child("BTC").setValue("0.0000004");}

                else if (Coins >= 600 && Coins <= 700){

                    reference.child("BTC").setValue("0.00000006");

                }else if (Coins >= 700 && Coins <= 800){

                    reference.child("BTC").setValue("0.000000065");

                }else if (Coins >=800 && Coins <= 900){

                    reference.child("BTC").setValue("0.00000008");

                }else if (Coins >= 900 && Coins <= 1000){

                    reference.child("BTC").setValue("0.00000010");

                }else if (Coins >= 1000 && Coins <= 1100 ){

                    reference.child("BTC").setValue("0.00000012");

                }else if (Coins >= 1100 && Coins <=1200){

                    reference.child("BTC").setValue("0.00000014");

                }else if (Coins >= 1200 && Coins <= 1300){

                    reference.child("BTC").setValue("0.00000016");

                }else if (Coins >= 1300 && Coins<= 1400){

                    reference.child("BTC").setValue("0.00000018");

                }else if (Coins >= 1400 && Coins <= 1500){

                    reference.child("BTC").setValue("0.00000020");

                }else if (Coins >= 1500 && Coins<=1600){

                    reference.child("BTC").setValue("0.00000022");

                }else if (Coins >= 1600 && Coins <=1700){

                    reference.child("BTC").setValue("0.00000024");

                }else if (Coins >= 1800 && Coins <=1900){

                    reference.child("BTC").setValue("0.00000026");

                }else if (Coins >= 1900 && Coins <= 2000){

                    reference.child("BTC").setValue("0.00000028");

                }else if (Coins >= 2000 && Coins <= 2100){

                    reference.child("BTC").setValue("0.00000030");

                }else if (Coins >= 2100 && Coins <= 2200){

                    reference.child("BTC").setValue("0.00000032");

                }else if (Coins >= 2200 && Coins <= 2300){

                    reference.child("BTC").setValue("0.00000034");

                }else if (Coins >= 2300 && Coins <= 2400){

                    reference.child("BTC").setValue("0.00000036");

                }else if (Coins >= 2400 && Coins <= 2500){

                    reference.child("BTC").setValue("0.00000038");

                }else if (Coins >= 2500 && Coins <= 2600){
                    reference.child("BTC").setValue("0.00000040");

                }else if (Coins >= 2600 && Coins <= 2700){
                    reference.child("BTC").setValue("0.00000042");

                }else if (Coins >= 2700 && Coins <= 2800){
                    reference.child("BTC").setValue("0.00000044");

                }else if (Coins >= 2800 && Coins <= 2900){
                    reference.child("BTC").setValue("0.00000046");

                }else if (Coins >= 3000 && Coins <= 3100){
                    reference.child("BTC").setValue("0.00000048");

                }else if (Coins >= 3100 && Coins <= 3200){
                    reference.child("BTC").setValue("0.00000050");

                }else if (Coins >= 3200 && Coins <= 3300){
                    reference.child("BTC").setValue("0.00000052");

                }else if (Coins >= 3300 && Coins <= 3400){
                    reference.child("BTC").setValue("0.00000054");

                }else if (Coins >= 3400 && Coins <= 3500){
                    reference.child("BTC").setValue("0.00000056");

                }else if (Coins >=3500  && Coins <= 3600){
                    reference.child("BTC").setValue("0.00000058");

                }else if (Coins >=3700 && Coins<= 3800){
                    reference.child("BTC").setValue("0.00000060");
                }else if (Coins >=3800 && Coins<= 4000){
                    reference.child("BTC").setValue("0.00000062");
                }else if (Coins >=4000 && Coins<= 5000){
                    reference.child("BTC").setValue("0.00000064");
                }else if (Coins >=5000 && Coins<= 6000){
                    reference.child("BTC").setValue("0.00000066");
                }else if (Coins >=6000 && Coins<= 7000){
                    reference.child("BTC").setValue("0.00000068");
                }else if (Coins >=7000 && Coins<= 8000){
                    reference.child("BTC").setValue("0.00000070");
                }else if (Coins >=8000 && Coins<= 10000){
                    reference.child("BTC").setValue("0.00000074");
                }else if (Coins >=10000 && Coins<= 11000){
                    reference.child("BTC").setValue("0.00000078");
                }else if (Coins >=12000 && Coins<= 13000){
                    reference.child("BTC").setValue("0.00000084");
                }else if (Coins ==13000 || Coins >14000){
                    reference.child("BTC").setValue("0.00000080+");
                }





            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

}











