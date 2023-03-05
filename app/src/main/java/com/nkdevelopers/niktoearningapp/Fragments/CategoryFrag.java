package com.nkdevelopers.niktoearningapp.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.CountDownTimer;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.MaxReward;
import com.applovin.mediation.MaxRewardedAdListener;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.applovin.mediation.ads.MaxRewardedAd;
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
import com.google.firebase.database.core.Context;

import com.nkdevelopers.niktoearningapp.R;
import com.nkdevelopers.niktoearningapp.databinding.FragmentCategoryBinding;



public class CategoryFrag extends Fragment  implements MaxRewardedAdListener {

    FirebaseUser user;
    DatabaseReference databasereference;
    FragmentCategoryBinding binding;
    String UID;
    int COINS;
    int Coins;
    private MaxRewardedAd rewardedAd;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCategoryBinding.inflate(inflater, container, false);

        getActivity().getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                        View.SYSTEM_UI_FLAG_FULLSCREEN);


        DatabaseReference df2 = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getCurrentUser().getUid());
        df2.child("APP_VERSION").setValue(1.26);



        AppLovinSdk.getInstance( getContext()).setMediationProvider( "max" );
        AppLovinSdk.initializeSdk( getContext(), new AppLovinSdk.SdkInitializationListener() {
            @Override
            public void onSdkInitialized(final AppLovinSdkConfiguration configuration)
            {
                // AppLovin SDK is initialized, start loading ads
            }
        } );



        // Max Rewarded Ad
        rewardedAd = MaxRewardedAd.getInstance( "508e2a6446c03e82", requireActivity() );
        rewardedAd.setListener( this );
        rewardedAd.loadAd();




        // Getting Live User Coins From Firebase

        user = FirebaseAuth.getInstance().getCurrentUser();
        UID = user.getUid();


        databasereference = FirebaseDatabase.getInstance().getReference();

        databasereference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // We convert the upcoming coins int value from fierbase to String
                COINS = snapshot.child(UID).child("coins").getValue(Integer.class);


                binding.UserCoins.setText(String.valueOf(COINS));




                String Note = snapshot.child(UID).child("notice").getValue(String.class);
                binding.noticeforuser.setText(Note);






            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





        binding.WalletBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.MainReplacer, new WalletFrag());
                transaction.commit();

                if ( rewardedAd.isReady() )
                {
                    rewardedAd.showAd();
                }





            }
        });

        binding.withdrawbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.MainReplacer, new WithdrawFrag());
                transaction.commit();

                if ( rewardedAd.isReady() )
                {
                    rewardedAd.showAd();
                }


            }
        });


        binding.ProfileRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.MainReplacer, new profilefrag());
                transaction.commit();
                if ( rewardedAd.isReady() )
                {
                    rewardedAd.showAd();
                }



            }
        });

        binding.Category1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.MainReplacer, new SpinWheelFrag());
                transaction.commit();
                if ( rewardedAd.isReady() )
                {
                    rewardedAd.showAd();
                }


            }
        });

        binding.Category2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(android.content.Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.nkdevelopers.niktoearningapp&hl=en&gl=US"));
                startActivity(i);
            }
        });

        binding.Category3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.MainReplacer, new scratchfrag());
                transaction.addToBackStack("scratch");
                transaction.commit();
                if ( rewardedAd.isReady() )
                {
                    rewardedAd.showAd();
                }
            }
        });

        binding.Category4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                rewardedAd.loadAd();
                if ( rewardedAd.isReady() )
                {
                    rewardedAd.showAd();
                }

            }
        });

        binding.Category5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.MainReplacer, new diceroll());
                transaction.addToBackStack("scratch");
                transaction.commit();
                if ( rewardedAd.isReady() )
                {
                    rewardedAd.showAd();
                }
            }
        });

        binding.Category6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.MainReplacer, new moneyhistroy());
                transaction.addToBackStack("scratch");
                transaction.commit();
                if ( rewardedAd.isReady() )
                {
                    rewardedAd.showAd();
                }
            }
        });

        return binding.getRoot();
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

        databasereference = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getCurrentUser().getUid());

        databasereference.child("reward_AD").setValue(ServerValue.increment(1));
        // Rewarded ad was displayed and user should receive the reward
       databasereference = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getUid());

        databasereference.child("coins").setValue(ServerValue.increment(8));
        Toast.makeText(getContext(), "You Earned 8 Coins", Toast.LENGTH_SHORT).show();
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


    @Override
    public void onAdLoaded(MaxAd ad) {

    }

    @Override
    public void onAdDisplayed(MaxAd ad) {

    }

    @Override
    public void onAdHidden(MaxAd ad) {

    }

    @Override
    public void onAdClicked(MaxAd ad) {

    }

    @Override
    public void onAdLoadFailed(String adUnitId, MaxError error) {

    }

    @Override
    public void onAdDisplayFailed(MaxAd ad, MaxError error) {
        Toast.makeText(getContext(), "Come back After some Time", Toast.LENGTH_SHORT).show();

    }
}