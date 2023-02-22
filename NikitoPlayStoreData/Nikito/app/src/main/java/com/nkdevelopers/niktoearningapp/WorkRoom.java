package com.nkdevelopers.niktoearningapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.admanager.AdManagerAdRequest;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.nkdevelopers.niktoearningapp.Fragments.SpinWheelFrag;
import com.nkdevelopers.niktoearningapp.Fragments.VideoTasksFrag;
import com.nkdevelopers.niktoearningapp.Fragments.scratchfrag;
import com.nkdevelopers.niktoearningapp.databinding.ActivityWorkRoomBinding;

public class WorkRoom extends AppCompatActivity {

    InterstitialAd mInterstitialAd;
    private String INTAD = "ca-app-pub-3646073337294523/2523964941";
    ActivityWorkRoomBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWorkRoomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        MobileAds.initialize(WorkRoom.this);
        AdManagerAdRequest adRequest = new AdManagerAdRequest.Builder().build();

        // Admob Ad

        InterstitialAd.load(WorkRoom.this,INTAD, adRequest,

                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;


                        if (mInterstitialAd != null) {
                            mInterstitialAd.show(WorkRoom.this);
                            InterstitialAd.load(WorkRoom.this,INTAD, adRequest,
                                    new InterstitialAdLoadCallback() {
                                        @Override
                                        public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                                            // The mInterstitialAd reference will be null until
                                            // an ad is loaded.
                                            mInterstitialAd = interstitialAd;

                                        }

                                        @Override
                                        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                                            // Handle the error

                                            mInterstitialAd = null;
                                        }
                                    });
                        } else {

                        }


                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error

                        mInterstitialAd = null;
                    }
                });



        String CategoryIDs= getIntent().getStringExtra("CategoryID");


        if (CategoryIDs.equals("5KyW2zauuEv5GkbJluMe")){
            FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
            transaction1.replace(R.id.OptionReplacer,new SpinWheelFrag());
            transaction1.commit();
            InterstitialAd.load(WorkRoom.this,INTAD, adRequest,
                    new InterstitialAdLoadCallback() {
                        @Override
                        public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                            // The mInterstitialAd reference will be null until
                            // an ad is loaded.
                            mInterstitialAd = interstitialAd;

                        }

                        @Override
                        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                            // Handle the error

                            mInterstitialAd = null;
                        }
                    });

        } else if (CategoryIDs.equals("Db1RKNQRy1hvgZLfHMQF")){
            FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();
            transaction2.replace(R.id.OptionReplacer,new VideoTasksFrag());
            transaction2.commit();
            InterstitialAd.load(WorkRoom.this,INTAD, adRequest,
                    new InterstitialAdLoadCallback() {
                        @Override
                        public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                            // The mInterstitialAd reference will be null until
                            // an ad is loaded.
                            mInterstitialAd = interstitialAd;

                        }

                        @Override
                        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                            // Handle the error

                            mInterstitialAd = null;
                        }
                    });



        } else if (CategoryIDs.equals("fqd61kWVp29u28wixb1m")){
            FragmentTransaction transaction3 = getSupportFragmentManager().beginTransaction();
            transaction3.replace(R.id.OptionReplacer,new scratchfrag());
            transaction3.commit();
            InterstitialAd.load(WorkRoom.this,INTAD, adRequest,
                    new InterstitialAdLoadCallback() {
                        @Override
                        public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                            // The mInterstitialAd reference will be null until
                            // an ad is loaded.
                            mInterstitialAd = interstitialAd;

                        }

                        @Override
                        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                            // Handle the error

                            mInterstitialAd = null;
                        }
                    });



        }else if (CategoryIDs.equals("gHsrVebpN4pEXNsBMdRQ")){


            try{
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id="+getPackageName())));
            }
            catch (Exception e){
            }

        }



    }
}