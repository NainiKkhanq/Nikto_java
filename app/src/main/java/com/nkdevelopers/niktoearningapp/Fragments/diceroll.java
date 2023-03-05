package com.nkdevelopers.niktoearningapp.Fragments;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.CountDownTimer;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.nkdevelopers.niktoearningapp.R;
import com.nkdevelopers.niktoearningapp.databinding.FragmentDicerollBinding;

import java.util.Random;

public class diceroll extends Fragment implements MaxAdListener, MaxRewardedAdListener {



    FragmentDicerollBinding binding;
    Random random = new Random();
    MediaPlayer mp;
    int DICE_ROLL;
    int Coins;
    private MaxInterstitialAd interstitialAd;
    private MaxRewardedAd rewardedAd;
    AppCompatActivity activity;
    Context context;
    DatabaseReference databaseReference;
    private MaxNativeAdLoader nativeAdLoader;
    private MaxAd nativeAd;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentDicerollBinding.inflate(getLayoutInflater(),container,false);

        // Setting App Lovin Ad

        context = getContext();
        activity = (AppCompatActivity) getContext();




        AppLovinSdk.getInstance( requireContext() ).setMediationProvider( "max" );
        AppLovinSdk.initializeSdk( requireContext(), new AppLovinSdk.SdkInitializationListener() {
            @Override
            public void onSdkInitialized(final AppLovinSdkConfiguration configuration)
            {
                // AppLovin SDK is initialized, start loading ads
            }
        } );


        // Native Ads

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



//        Max int Ad
        interstitialAd = new MaxInterstitialAd( "6de043508d679164", requireActivity());
        interstitialAd.setListener( this );
        interstitialAd.loadAd();

        // Max Rewarded Ad
        rewardedAd = MaxRewardedAd.getInstance( "b6f634278187fefc", requireActivity() );
        rewardedAd.setListener( this );
        rewardedAd.loadAd();

        // Database Work
        databaseReference = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getCurrentUser().getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DICE_ROLL = snapshot.child("diceroll").getValue(Integer.class);
                int USER_COINS = snapshot.child("coins").getValue(Integer.class);
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
                    Toast.makeText(activity, "Invalid Activity Detected Account Suspended!", Toast.LENGTH_SHORT).show();
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

                            databaseReference = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getCurrentUser().getUid());
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


                MediaPlayer mediaPlayer = MediaPlayer.create(getContext(), R.raw.dicesound);
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

                            databaseReference = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getCurrentUser().getUid());
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



                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.MainReplacer, new CategoryFrag());

                transaction.commit();


            }
        });


        // Get more DiceRoll Code

        binding.ADBUTTON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if ( rewardedAd.isReady() )
                {
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

        return binding.getRoot();
    }

    private void rolldice() {


        int coins = random.nextInt(6)+1;
        Animation anim = AnimationUtils.loadAnimation(getContext(),R.anim.rotate);
        binding.d11.startAnimation(anim);
        binding.d12.setAnimation(anim);
        switch (coins){

            case 1: {

                binding.d11.setImageResource(R.drawable.d1);
                binding.d12.setImageResource(R.drawable.d1);
                databaseReference = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getCurrentUser().getUid());
                databaseReference.child("coins").setValue(ServerValue.increment(coins));
                binding.notice.setText("You Won " + 1 + " Coins");
                if ( interstitialAd.isReady() )
                {
                    interstitialAd.showAd();
                }

            }

            break;
            case 2: {

                binding.d11.setImageResource(R.drawable.d2);
                binding.d12.setImageResource(R.drawable.d2);

                databaseReference = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getCurrentUser().getUid());
                databaseReference.child("coins").setValue(ServerValue.increment(coins));
                binding.notice.setText("You Won " + 2 + " Coins");
                interstitialAd.loadAd();
                if ( interstitialAd.isReady() )
                {
                    interstitialAd.showAd();
                }
            }
            break;

            case 3: {

                binding.d11.setImageResource(R.drawable.d3);
                binding.d12.setImageResource(R.drawable.d3);

                databaseReference = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getCurrentUser().getUid());
                databaseReference.child("coins").setValue(ServerValue.increment(coins));
                binding.notice.setText("You Won " + 3 + " Coins");
                interstitialAd.loadAd();
                if ( interstitialAd.isReady() )
                {
                    interstitialAd.showAd();
                }
            }
            break;

            case 4: {

                binding.d11.setImageResource(R.drawable.d4);
                binding.d12.setImageResource(R.drawable.d4);

                databaseReference = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getCurrentUser().getUid());
                databaseReference.child("coins").setValue(ServerValue.increment(coins));
                binding.notice.setText("You Won " + 4 + " Coins");
                if ( interstitialAd.isReady() )
                {
                    interstitialAd.showAd();
                }}

            break;
            case 5: {

                binding.d11.setImageResource(R.drawable.d5);
                binding.d12.setImageResource(R.drawable.d5);

                databaseReference = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getCurrentUser().getUid());
                databaseReference.child("coins").setValue(ServerValue.increment(coins));
                binding.notice.setText("You Won " + 5 + " Coins");            }

            if ( interstitialAd.isReady() )
            {
                interstitialAd.showAd();
            }

            case 6: {

                binding.d11.setImageResource(R.drawable.d6);
                binding.d12.setImageResource(R.drawable.d6);

                databaseReference = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getCurrentUser().getUid());
                databaseReference.child("coins").setValue(ServerValue.increment(coins));

                binding.notice.setText("You Won " + 6 + " Coins");

                interstitialAd.loadAd();
                if ( interstitialAd.isReady() )
                {
                    interstitialAd.showAd();
                }

            }

            break;
        }

    }

    // Method to Showing App Lovin Ads


    @Override
    public void onAdLoaded(MaxAd ad) {

    }

    @Override
    public void onAdDisplayed(MaxAd ad) {

        databaseReference = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getCurrentUser().getUid());

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
        binding.notice.setText("Try Again After 24 Hours.");

        rewardedAd.loadAd();

    }

    public void onAdDisplayFailedR(final MaxAd maxAd, final MaxError error)
    {
        // Rewarded ad failed to display. We recommend loading the next ad
        rewardedAd.loadAd();
    }


    @Override
    public void onRewardedVideoStarted(final MaxAd maxAd) {

        Toast.makeText(context, "Watch Complete Video to Get Reward", Toast.LENGTH_SHORT).show();



    } // deprecated

    @Override
    public void onRewardedVideoCompleted(final MaxAd maxAd) {} // deprecated

    @Override
    public void onUserRewarded(final MaxAd maxAd, final MaxReward maxReward)
    {
        // Rewarded ad was displayed and user should receive the reward
        databaseReference = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getUid());

        databaseReference.child("diceroll").setValue(6);

        binding.d11.setVisibility(View.VISIBLE);
        binding.d12.setVisibility(View.VISIBLE);
        binding.ADBUTTON.setVisibility(View.INVISIBLE);
        databaseReference = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getCurrentUser().getUid());

        databaseReference.child("reward_AD").setValue(ServerValue.increment(1));


        DatabaseReference  reference = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Coins = snapshot.child("coins").getValue(Integer.class);


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