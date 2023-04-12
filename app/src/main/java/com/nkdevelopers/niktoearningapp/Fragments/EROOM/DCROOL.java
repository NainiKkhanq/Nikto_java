package com.nkdevelopers.niktoearningapp.Fragments.EROOM;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.MaxReward;
import com.applovin.mediation.MaxRewardedAdListener;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.applovin.mediation.ads.MaxRewardedAd;
import com.applovin.mediation.nativeAds.MaxNativeAd;
import com.applovin.mediation.nativeAds.MaxNativeAdListener;
import com.applovin.mediation.nativeAds.MaxNativeAdLoader;
import com.applovin.mediation.nativeAds.MaxNativeAdView;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkConfiguration;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import com.nkdevelopers.niktoearningapp.R;
import com.nkdevelopers.niktoearningapp.databinding.ActivityDcroolBinding;


import java.util.Random;

public class DCROOL extends AppCompatActivity implements MaxRewardedAdListener , MaxAdListener {


    ActivityDcroolBinding binding;
    Random random = new Random();
    MediaPlayer mp;
    int DICE_ROLL;
    int Coins;
    DatabaseReference databaseReference;
    private MaxRewardedAd rewardedAd;

    private MaxNativeAdLoader nativeAdLoader;

    private MaxAd nativeAd;
    MaxInterstitialAd interstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDcroolBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // AppLovin

        AppLovinSdk.getInstance( DCROOL.this ).setMediationProvider( "max" );
        AppLovinSdk.initializeSdk( DCROOL.this, new AppLovinSdk.SdkInitializationListener() {
            @Override
            public void onSdkInitialized(final AppLovinSdkConfiguration configuration)
            {


            }
        } );
        // Max Rewarded Ad
        rewardedAd = MaxRewardedAd.getInstance( "b6f634278187fefc", DCROOL.this );
        rewardedAd.setListener( this );
        rewardedAd.loadAd();

        //        Max int Ad
        interstitialAd = new MaxInterstitialAd( "6de043508d679164", DCROOL.this);
        interstitialAd.setListener(this);
        interstitialAd.loadAd();


        // Native Ad

        nativeAdLoader = new MaxNativeAdLoader( "b8a16854f4cd8625", DCROOL.this );
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


        // Database Work
        databaseReference = FirebaseDatabase.getInstance().getReference().child("MY_USERS").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DICE_ROLL = snapshot.child("diceroll").getValue(Integer.class);
                int USER_COINS = snapshot.child("coins").getValue(Integer.class);
                int WTC = snapshot.child("withdraw").getValue(Integer.class);
                binding.UCOINS.setText(""+USER_COINS);

                binding.UCHANCES.setText(""+DICE_ROLL);
                if (DICE_ROLL <=0){
                    binding.notice.setText("Watch Ad to Continue! ");
                    binding.d11.setVisibility(View.GONE);
                    binding.d12.setVisibility(View.GONE);

                    CountDownTimer countDownTimer = new CountDownTimer(1000,1 ) {
                        @Override
                        public void onTick(long l) {
                            binding.ADBUTTON.setVisibility(View.VISIBLE);

                        }

                        @Override
                        public void onFinish() {

                        }
                    };
                    countDownTimer.start();




                }

                if (DICE_ROLL > 20){
                    databaseReference.child("coins").setValue(0);
                    databaseReference.child("DiceStatus").setValue("Suspended");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });







        binding.d11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rolldice();




                CountDownTimer countDownTimer = new CountDownTimer(800,1) {
                    @Override
                    public void onTick(long l) {
                        binding.d11.setClickable(false);
                        binding.d12.setClickable(false);
                    }

                    @Override
                    public void onFinish() {

                        binding.d11.setClickable(true);
                        binding.d12.setClickable(true);


                        if (DICE_ROLL > 0){

                            databaseReference = FirebaseDatabase.getInstance().getReference().child("MY_USERS").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                            databaseReference.child("diceroll").setValue(ServerValue.increment(-1));

                        }else {


                        }

                    }
                };
                countDownTimer.start();
            }
        });


        binding.d12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                rolldice();


                MediaPlayer mediaPlayer = MediaPlayer.create(DCROOL.this, R.raw.dicesound);
                mediaPlayer.start();

                CountDownTimer countDownTimer = new CountDownTimer(800,1) {
                    @Override
                    public void onTick(long l) {
                        binding.d11.setClickable(false);
                        binding.d12.setClickable(false);
                    }

                    @Override
                    public void onFinish() {


                        if (DICE_ROLL > 0){

                            databaseReference = FirebaseDatabase.getInstance().getReference().child("MY_USERS").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                            databaseReference.child("diceroll").setValue(ServerValue.increment(-1));
                            binding.d11.setClickable(true);
                            binding.d12.setClickable(true);

                        }else {
                            binding.d11.setClickable(false);
                            binding.d12.setClickable(false);

                        }
                    }
                };
                countDownTimer.start();
            }
        });

        binding.backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
                IntAd();

            }
        });


        // Get more DiceRoll Code

        binding.ADBUTTON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (rewardedAd.isReady()){
                    rewardedAd.showAd();
                }
                new CountDownTimer(10000, 1000) {

                    public void onTick(long millisUntilFinished) {
                        binding.notice.setText("Try After " + millisUntilFinished / 1000 + "s");

                        binding.ADBUTTON.setClickable(false);}

                    public void onFinish() {


                        binding.notice.setText("Roll the Dice");

                        binding.ADBUTTON.setClickable(true);}





                }.start();
            }
        });



    }

    private void rolldice() {


        int coins = random.nextInt(6)+1;
        Animation anim = AnimationUtils.loadAnimation(DCROOL.this,R.anim.rotate);
        binding.d11.startAnimation(anim);
        binding.d12.setAnimation(anim);
        switch (coins){

            case 1: {
                IntAd();

                binding.d11.setImageResource(R.drawable.d1);
                binding.d12.setImageResource(R.drawable.d1);
                databaseReference = FirebaseDatabase.getInstance().getReference().child("MY_USERS").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                databaseReference.child("coins").setValue(ServerValue.increment(coins));
                binding.notice.setText("You Won " + 1 + " Coins");

            }

            break;
            case 2: {
                IntAd();

                binding.d11.setImageResource(R.drawable.d2);
                binding.d12.setImageResource(R.drawable.d2);

                databaseReference = FirebaseDatabase.getInstance().getReference().child("MY_USERS").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                databaseReference.child("coins").setValue(ServerValue.increment(coins));
                binding.notice.setText("You Won " + 2 + " Coins");
            }
            break;

            case 3: {
                IntAd();

                binding.d11.setImageResource(R.drawable.d3);
                binding.d12.setImageResource(R.drawable.d3);

                databaseReference = FirebaseDatabase.getInstance().getReference().child("MY_USERS").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                databaseReference.child("coins").setValue(ServerValue.increment(coins));
                binding.notice.setText("You Won " + 3 + " Coins");
            }
            break;

            case 4: {
                IntAd();

                binding.d11.setImageResource(R.drawable.d4);
                binding.d12.setImageResource(R.drawable.d4);

                databaseReference = FirebaseDatabase.getInstance().getReference().child("MY_USERS").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                databaseReference.child("coins").setValue(ServerValue.increment(coins));
                binding.notice.setText("You Won " + 4 + " Coins");
            }

            break;
            case 5: {
                IntAd();
                binding.d11.setImageResource(R.drawable.d5);
                binding.d12.setImageResource(R.drawable.d5);

                databaseReference = FirebaseDatabase.getInstance().getReference().child("MY_USERS").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                databaseReference.child("coins").setValue(ServerValue.increment(coins));
                binding.notice.setText("You Won " + 5 + " Coins");            }

            case 6: {
                IntAd();

                binding.d11.setImageResource(R.drawable.d6);
                binding.d12.setImageResource(R.drawable.d6);

                databaseReference = FirebaseDatabase.getInstance().getReference().child("MY_USERS").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                databaseReference.child("coins").setValue(ServerValue.increment(coins));

                binding.notice.setText("You Won " + 6 + " Coins");


            }

            break;
        }

    }


    @Override
    public void onUserRewarded(MaxAd maxAd, MaxReward maxReward) {

    }

    @Override
    public void onRewardedVideoStarted(MaxAd maxAd) {

    }

    @Override
    public void onRewardedVideoCompleted(MaxAd maxAd) {

        databaseReference = FirebaseDatabase.getInstance().getReference().child("MY_USERS").child(FirebaseAuth.getInstance().getUid());
        databaseReference.child("RF_RT").setValue(ServerValue.increment(1));
        databaseReference.child("diceroll").setValue(6);

        binding.d11.setVisibility(View.VISIBLE);
        binding.d12.setVisibility(View.VISIBLE);
        binding.ADBUTTON.setVisibility(View.INVISIBLE);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("MY_USERS").child(FirebaseAuth.getInstance().getCurrentUser().getUid());


        databaseReference.child("reward_AD").setValue(ServerValue.increment(1));


        DatabaseReference  reference = FirebaseDatabase.getInstance().getReference().child("MY_USERS").child(FirebaseAuth.getInstance().getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Coins = snapshot.child("coins").getValue(Integer.class);


                DatabaseReference reference  =FirebaseDatabase.getInstance().getReference().child("MY_USERS").child(FirebaseAuth.getInstance().getUid());

                if (Coins >= 100 && Coins<=300 ){


                    reference.child("BTC").setValue("0.00000002");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("USERS_CURRENT_MONEY");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000002");

                }else if (Coins >= 300 && Coins < 500 ){

                    reference.child("BTC").setValue("0.00000003");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("USERS_CURRENT_MONEY");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000003");
                }


                else if (Coins >= 500 && Coins < 600){

                    reference.child("BTC").setValue("0.00000006");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("USERS_CURRENT_MONEY");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000006");
                }

                else if (Coins >= 600 && Coins <= 700){

                    reference.child("BTC").setValue("0.0000008");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("USERS_CURRENT_MONEY");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000008");

                }else if (Coins >= 700 && Coins <= 800){

                    reference.child("BTC").setValue("0.00000012");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("USERS_CURRENT_MONEY");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000012");

                }else if (Coins >=800 && Coins <= 900){

                    reference.child("BTC").setValue("0.00000014");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("USERS_CURRENT_MONEY");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000014");

                }else if (Coins >= 900 && Coins <= 1000){

                    reference.child("BTC").setValue("0.00000016");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("USERS_CURRENT_MONEY");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000016");

                }else if (Coins >= 1000 && Coins <= 1100 ){

                    reference.child("BTC").setValue("0.00000018");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("USERS_CURRENT_MONEY");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000018");

                }else if (Coins >= 1100 && Coins <=1200){

                    reference.child("BTC").setValue("0.00000020");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("USERS_CURRENT_MONEY");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000020");

                }else if (Coins >= 1200 && Coins <= 1300){

                    reference.child("BTC").setValue("0.00000021");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("USERS_CURRENT_MONEY");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000021");

                }else if (Coins >= 1300 && Coins<= 1400){

                    reference.child("BTC").setValue("0.00000022");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("USERS_CURRENT_MONEY");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000022");

                }else if (Coins >= 1400 && Coins <= 1500){

                    reference.child("BTC").setValue("0.00000023");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("USERS_CURRENT_MONEY");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000023");

                }else if (Coins >= 1500 && Coins<=1600){

                    reference.child("BTC").setValue("0.00000024");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("USERS_CURRENT_MONEY");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000024");

                }else if (Coins >= 1600 && Coins <=1700){

                    reference.child("BTC").setValue("0.00000025");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("USERS_CURRENT_MONEY");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000025");

                }else if (Coins >= 1800 && Coins <=1900){

                    reference.child("BTC").setValue("0.00000026");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("USERS_CURRENT_MONEY");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000026");

                }else if (Coins >= 1900 && Coins <= 2000){

                    reference.child("BTC").setValue("0.00000027");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("USERS_CURRENT_MONEY");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000027");

                }else if (Coins >= 2000 && Coins <= 2100){

                    reference.child("BTC").setValue("0.00000028");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("USERS_CURRENT_MONEY");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000028");

                }else if (Coins >= 2100 && Coins <= 2200){

                    reference.child("BTC").setValue("0.00000029");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("USERS_CURRENT_MONEY");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000029");

                }else if (Coins >= 2200 && Coins <= 2300){

                    reference.child("BTC").setValue("0.00000030");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("USERS_CURRENT_MONEY");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000030");

                }else if (Coins >= 2300 && Coins <= 2400){

                    reference.child("BTC").setValue("0.00000031");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("USERS_CURRENT_MONEY");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000031");

                }else if (Coins >= 2400 && Coins <= 2500){

                    reference.child("BTC").setValue("0.00000031");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("USERS_CURRENT_MONEY");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000031");

                }else if (Coins >= 2500 && Coins <= 2600){
                    reference.child("BTC").setValue("0.00000032");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("USERS_CURRENT_MONEY");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000032");

                }else if (Coins >= 2600 && Coins <= 2700){
                    reference.child("BTC").setValue("0.00000034");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("USERS_CURRENT_MONEY");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000034");

                }else if (Coins >= 2700 && Coins <= 2800){
                    reference.child("BTC").setValue("0.00000036");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("USERS_CURRENT_MONEY");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000036");

                }else if (Coins >= 2800 && Coins <= 2900){
                    reference.child("BTC").setValue("0.00000038");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("USERS_CURRENT_MONEY");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000038");

                }else if (Coins >= 3000 && Coins <= 3100){
                    reference.child("BTC").setValue("0.00000039");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("USERS_CURRENT_MONEY");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000039");

                }else if (Coins >= 3000 && Coins <= 3100){
                    reference.child("BTC").setValue("0.00000042");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("USERS_CURRENT_MONEY");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000042");

                }else if (Coins >= 3100 && Coins <= 3200){
                    reference.child("BTC").setValue("0.00000041");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("USERS_CURRENT_MONEY");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000041");

                }else if (Coins >= 3200 && Coins <= 3300){
                    reference.child("BTC").setValue("0.00000042");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("USERS_CURRENT_MONEY");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000042");

                }else if (Coins >= 3300 && Coins <= 3400){
                    reference.child("BTC").setValue("0.00000043");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("USERS_CURRENT_MONEY");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000043");

                }else if (Coins >= 3400 && Coins <= 3500){
                    reference.child("BTC").setValue("0.00000044");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("USERS_CURRENT_MONEY");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000044");

                }else if (Coins >=3500  && Coins <= 3600){
                    reference.child("BTC").setValue("0.00000045");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("USERS_CURRENT_MONEY");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000045");

                }else if (Coins >=3700 && Coins<= 3800){
                    reference.child("BTC").setValue("0.00000046");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("USERS_CURRENT_MONEY");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000046");
                }else if (Coins >=3800 && Coins<= 4000){
                    reference.child("BTC").setValue("0.00000047");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("USERS_CURRENT_MONEY");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000047");
                }else if (Coins >=4000 && Coins<= 5000){
                    reference.child("BTC").setValue("0.00000048");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("USERS_CURRENT_MONEY");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000048");
                }else if (Coins >=5000 && Coins<= 6000){
                    reference.child("BTC").setValue("0.00000049");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("USERS_CURRENT_MONEY");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000049");
                }else if (Coins >=6000 && Coins<= 7000){
                    reference.child("BTC").setValue("0.00000050");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("USERS_CURRENT_MONEY");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000050");
                }else if (Coins >=7000 && Coins<= 8000){
                    reference.child("BTC").setValue("0.00000051");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("USERS_CURRENT_MONEY");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000051");
                }else if (Coins >=8000 && Coins<= 10000){
                    reference.child("BTC").setValue("0.00000052");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("USERS_CURRENT_MONEY");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000052");
                }else if (Coins >=10000 && Coins<= 11000){
                    reference.child("BTC").setValue("0.00000053");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("USERS_CURRENT_MONEY");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000053");
                }else if (Coins >=12000 && Coins<= 13000){
                    reference.child("BTC").setValue("0.00000054");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("USERS_CURRENT_MONEY");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000054");
                }else if (Coins ==13000 || Coins >14000){
                    reference.child("BTC").setValue("0.00000058+");
                    DatabaseReference rfw = FirebaseDatabase.getInstance().getReference("USERS_CURRENT_MONEY");
                    rfw.child("UMONEY")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("U_MONEY").setValue("0.00000058+");
                }





            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





    }

    @Override
    public void onAdLoaded(MaxAd maxAd) {

    }

    @Override
    public void onAdDisplayed(MaxAd maxAd) {


    }

    @Override
    public void onAdHidden(MaxAd maxAd) {

    }

    @Override
    public void onAdClicked(MaxAd maxAd) {

    }

    @Override
    public void onAdLoadFailed(String s, MaxError maxError) {

    }

    @Override
    public void onAdDisplayFailed(MaxAd maxAd, MaxError maxError) {

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        IntAd();
    }
    // Int Ads
    private void IntAd(){

        if (interstitialAd.isReady()){
            interstitialAd.showAd();
            databaseReference = FirebaseDatabase.getInstance().getReference().child("MY_USERS").child(FirebaseAuth.getInstance().getUid());
            databaseReference.child("inter_AD").setValue(ServerValue.increment(1));


        }else {

        }


    }

}