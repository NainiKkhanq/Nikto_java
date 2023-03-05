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

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.MaxReward;
import com.applovin.mediation.MaxRewardedAdListener;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.applovin.mediation.ads.MaxRewardedAd;
import com.applovin.mediation.nativeAds.MaxNativeAdListener;
import com.applovin.mediation.nativeAds.MaxNativeAdLoader;
import com.applovin.mediation.nativeAds.MaxNativeAdView;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkConfiguration;
import com.google.android.gms.tasks.Task;
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
    private MaxNativeAdLoader nativeAdLoader;
    private MaxAd nativeAd;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSpinWheelBinding.inflate(getLayoutInflater(),container,false);
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

        // Native Ad




        nativeAdLoader = new MaxNativeAdLoader( "b8a16854f4cd8625", getContext() );
        nativeAdLoader.setNativeAdListener( new MaxNativeAdListener()
        {
            @Override
            public void onNativeAdLoaded(final MaxNativeAdView nativeAdView, final MaxAd ad)
            {
                // Clean up any pre-existing native ad to prevent memory leaks.
                if ( nativeAd != null )
                {
                    nativeAdLoader.destroy( nativeAd );
                }

                // Save ad for cleanup.
                nativeAd = ad;

                // Add ad view to view.
                binding.MAXNATIVE.removeAllViews();
                binding.MAXNATIVE.addView( nativeAdView );
            }

            @Override
            public void onNativeAdLoadFailed(final String adUnitId, final MaxError error)
            {
                // We recommend retrying with exponentially higher delays up to a maximum delay
            }

            @Override
            public void onNativeAdClicked(final MaxAd ad)
            {
                // Optional click callback
            }
        } );

        nativeAdLoader.loadAd();




//      Max int Ad
        interstitialAd = new MaxInterstitialAd( "390881b42411d57f", requireActivity());
        interstitialAd.setListener( this );
        interstitialAd.loadAd();
        // Max Rewarded Ad
        rewardedAd = MaxRewardedAd.getInstance( "2eda4b8d0c86c6fc", requireActivity() );
        rewardedAd.setListener( this );

        rewardedAd.loadAd();




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






                if( randomnumber == 2 || randomnumber == 4 ||  randomnumber == 8 || randomnumber == 10 || randomnumber == 0){
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


                if (Coins >= 50 && Coins <=100){
                    reference.child("BTC").setValue("0.00000002");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("WithdrawAdmin");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000002");
                }
                else if (Coins >= 100 && Coins<=150){
                    reference.child("BTC").setValue("0.00000004");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("WithdrawAdmin");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000004");
                }
                else if (Coins >= 200 && Coins <= 250){
                    reference.child("BTC").setValue("0.0000006");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("WithdrawAdmin");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000006");
                }

                else if (Coins >= 250 && Coins <= 300){
                    reference.child("BTC").setValue("0.00000008");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("WithdrawAdmin");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000008");

                }else if (Coins >= 300 && Coins <= 350){
                    reference.child("BTC").setValue("0.00000009");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("WithdrawAdmin");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000009");

                }else if (Coins >=350 && Coins <= 400){
                    reference.child("BTC").setValue("0.00000010");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("WithdrawAdmin");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000010");

                }else if (Coins >= 400 && Coins <= 450){
                    reference.child("BTC").setValue("0.00000012");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("WithdrawAdmin");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000012");

                }else if (Coins >= 450 && Coins <= 500 ){
                    reference.child("BTC").setValue("0.00000014");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("WithdrawAdmin");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000014");

                }else if (Coins >= 500 && Coins <=550){
                    reference.child("BTC").setValue("0.00000015");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("WithdrawAdmin");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000015");

                }else if (Coins >= 550 && Coins <= 600){
                    reference.child("BTC").setValue("0.00000017");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("WithdrawAdmin");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000017");

                }else if (Coins >= 600 && Coins<= 700){
                    reference.child("BTC").setValue("0.00000019");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("WithdrawAdmin");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000019");

                }else if (Coins >= 700 && Coins <= 800){
                    reference.child("BTC").setValue("0.00000021");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("WithdrawAdmin");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000021");

                }else if (Coins >= 800 && Coins<=900){
                    reference.child("BTC").setValue("0.00000022");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("WithdrawAdmin");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000022");


                }else if (Coins >= 900 && Coins <=1000){
                    reference.child("BTC").setValue("0.00000024");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("WithdrawAdmin");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000024");


                }else if (Coins >= 1000 && Coins <=1050){
                    reference.child("BTC").setValue("0.00000025");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("WithdrawAdmin");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000025");


                }else if (Coins >= 1050 && Coins <= 1100){
                    reference.child("BTC").setValue("0.00000026");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("WithdrawAdmin");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000026");


                }else if (Coins >= 1100 && Coins <= 1150){
                    reference.child("BTC").setValue("0.00000027");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("WithdrawAdmin");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000027");


                }else if (Coins >= 1150 && Coins <= 1200){
                    reference.child("BTC").setValue("0.00000028");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("WithdrawAdmin");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000028");


                }else if (Coins >= 1200 && Coins <= 1300){
                    reference.child("BTC").setValue("0.00000029");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("WithdrawAdmin");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000029");


                }else if (Coins >= 1300 && Coins <= 1400){
                    reference.child("BTC").setValue("0.00000030");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("WithdrawAdmin");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000030");


                }else if (Coins >= 1400 && Coins <= 1600){
                    reference.child("BTC").setValue("0.00000032");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("WithdrawAdmin");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000032");


                }else if (Coins >= 1600 && Coins <= 1700){
                    reference.child("BTC").setValue("0.00000033");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("WithdrawAdmin");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000033");

                }else if (Coins >= 1700 && Coins <= 1750){
                    reference.child("BTC").setValue("0.00000034");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("WithdrawAdmin");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000034");

                }else if (Coins >= 1750 && Coins <= 1800){
                    reference.child("BTC").setValue("0.00000035");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("WithdrawAdmin");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000035");

                }else if (Coins >= 1800 && Coins <= 1900){
                    reference.child("BTC").setValue("0.00000036");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("WithdrawAdmin");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000036");

                }else if (Coins >= 1900 && Coins <= 2000){
                    reference.child("BTC").setValue("0.00000037");
                    Task<Void> rfw = FirebaseDatabase.getInstance().getReference("WithdrawAdmin").child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue("0.00000037");

                }else if (Coins >= 2000 && Coins <= 2100){
                    reference.child("BTC").setValue("0.00000038");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("WithdrawAdmin");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000038");

                }else if (Coins >= 2100 && Coins <= 2200){
                    reference.child("BTC").setValue("0.00000039");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("WithdrawAdmin");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000039");

                }else if (Coins >= 2200 && Coins <= 2300){
                    reference.child("BTC").setValue("0.00000040");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("WithdrawAdmin");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000040");

                }else if (Coins >= 2300 && Coins <= 2350){
                    reference.child("BTC").setValue("0.00000041");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("WithdrawAdmin");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000041");

                }else if (Coins >=2350  && Coins <= 2400){
                    reference.child("BTC").setValue("0.00000042");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("WithdrawAdmin");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000042");

                }else if (Coins >=2450 && Coins<= 2500){
                    reference.child("BTC").setValue("0.00000043");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("WithdrawAdmin");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000043");
                }else if (Coins >=2550 && Coins<= 2600){
                    reference.child("BTC").setValue("0.00000044");
                    Task<Void> rfw = FirebaseDatabase.getInstance().getReference("WithdrawAdmin").child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue("0.00000044");
                }else if (Coins >=2600 && Coins<= 2650){
                    reference.child("BTC").setValue("0.00000045");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("WithdrawAdmin");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000045");
                }else if (Coins >=2650 && Coins<= 2700){
                    reference.child("BTC").setValue("0.00000046");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("WithdrawAdmin");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000046");
                }else if (Coins >=2700 && Coins<= 2800){
                    reference.child("BTC").setValue("0.00000047");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("WithdrawAdmin");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000047");
                }else if (Coins >=2800 && Coins<= 2900){
                    reference.child("BTC").setValue("0.00000048");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("WithdrawAdmin");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000048");
                }else if (Coins >=2900 && Coins<= 3000){
                    reference.child("BTC").setValue("0.00000049");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("WithdrawAdmin");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000049");
                }else if (Coins >=3000 && Coins<=3050 ){
                    reference.child("BTC").setValue("0.00000051");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("WithdrawAdmin");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000050");
                }else if (Coins >=3050 && Coins<= 3100){
                    reference.child("BTC").setValue("0.00000053");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("WithdrawAdmin");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000053");
                }else if (Coins >=3150 && Coins <=3200){
                    reference.child("BTC").setValue("0.00000055");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("WithdrawAdmin");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000055");
                }else if (Coins >= 3200 && Coins <=3250 ){
                    reference.child("BTC").setValue("0.00000056");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("WithdrawAdmin");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000056");
                }else if (Coins >= 3250 && Coins <= 3300){
                    reference.child("BTC").setValue("0.00000059");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("WithdrawAdmin");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000059");
                }else if (Coins >= 3300 && Coins <= 3350){
                    reference.child("BTC").setValue("0.00000061");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("WithdrawAdmin");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000061");
                }else if (Coins >= 3350 && Coins <= 3400){
                    reference.child("BTC").setValue("0.00000063");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("WithdrawAdmin");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000063");
                }
                else if (Coins >= 3400 && Coins <= 3500){
                    reference.child("BTC").setValue("0.00000065");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("WithdrawAdmin");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000065");
                }
                else if (Coins >= 3500 && Coins <= 3600){
                    reference.child("BTC").setValue("0.00000067");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("WithdrawAdmin");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000067");
                }
                else if (Coins >= 3600 && Coins <= 3700){
                    reference.child("BTC").setValue("0.00000068");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("WithdrawAdmin");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000068");
                }
                else if (Coins >= 3700 && Coins <= 3800){
                    reference.child("BTC").setValue("0.00000070");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("WithdrawAdmin");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000070");
                }
                else if (Coins >= 3800 && Coins <= 3900){
                    reference.child("BTC").setValue("0.00000072");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("WithdrawAdmin");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000072");
                }
                else if (Coins >= 3900 && Coins <= 4000){
                    reference.child("BTC").setValue("0.00000074");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("WithdrawAdmin");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000074");
                }

                else if (Coins > 4050 || Coins >= 5000) {
                    reference.child("BTC").setValue("0.00000076+");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("WithdrawAdmin");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000076+");
                }






            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

}











