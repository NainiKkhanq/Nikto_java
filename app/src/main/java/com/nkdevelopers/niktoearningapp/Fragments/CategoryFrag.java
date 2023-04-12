package com.nkdevelopers.niktoearningapp.Fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
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
import com.applovin.mediation.MaxError;
import com.applovin.mediation.MaxReward;
import com.applovin.mediation.MaxRewardedAdListener;
import com.applovin.mediation.ads.MaxRewardedAd;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkConfiguration;
import com.google.firebase.FirebaseApp;
import com.google.firebase.appcheck.FirebaseAppCheck;
import com.google.firebase.appcheck.playintegrity.PlayIntegrityAppCheckProviderFactory;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import com.nkdevelopers.niktoearningapp.BuildConfig;
import com.nkdevelopers.niktoearningapp.Fragments.EROOM.DCROOL;
import com.nkdevelopers.niktoearningapp.Fragments.EROOM.NSP;
import com.nkdevelopers.niktoearningapp.Fragments.EROOM.stc;
import com.nkdevelopers.niktoearningapp.MainActivity;
import com.nkdevelopers.niktoearningapp.R;
import com.nkdevelopers.niktoearningapp.databinding.FragmentCategoryBinding;
import com.nkdevelopers.niktoearningapp.waitingroom.Waitroom;



public class CategoryFrag extends Fragment  implements MaxRewardedAdListener{

    FirebaseUser user;
    DatabaseReference databasereference;
    FragmentCategoryBinding binding;
    String UID;
    int COINS;
    int Coins;
    int WTC;
    ProgressDialog dialog;

    private MaxRewardedAd rewardedAd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCategoryBinding.inflate(inflater, container, false);

        getActivity().getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                        View.SYSTEM_UI_FLAG_FULLSCREEN);


        // AppLovin

        AppLovinSdk.getInstance( requireContext() ).setMediationProvider( "max" );
        AppLovinSdk.initializeSdk( requireContext(), new AppLovinSdk.SdkInitializationListener() {
            @Override
            public void onSdkInitialized(final AppLovinSdkConfiguration configuration)
            {


            }
        } );
        FirebaseApp.initializeApp(getContext());
        FirebaseAppCheck firebaseAppCheck = FirebaseAppCheck.getInstance();
        firebaseAppCheck.installAppCheckProviderFactory(
                PlayIntegrityAppCheckProviderFactory.getInstance());
    // Max Rewarded Ad
        rewardedAd = MaxRewardedAd.getInstance( "508e2a6446c03e82", requireActivity() );
        rewardedAd.setListener( this );
        rewardedAd.loadAd();


        dialog = new ProgressDialog(getContext());

        dialog.setMessage("Loading!");

        new CountDownTimer(3000,1000){

            @Override
            public void onTick(long l) {
                dialog.show();

            }

            @Override
            public void onFinish() {

                dialog.dismiss();


            }
        }.start();

        DatabaseReference wthc =  FirebaseDatabase.getInstance().getReference().child("MY_USERS").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        wthc.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                WTC = snapshot.child("withdraw").getValue(Integer.class);




            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });







        DatabaseReference df2 = FirebaseDatabase.getInstance().getReference().child("MY_USERS").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
       int VPS=  BuildConfig.VERSION_CODE;
        df2.child("APP_VERSION").setValue(VPS);






        // Getting Live User Coins From Firebase

        user = FirebaseAuth.getInstance().getCurrentUser();
        UID = user.getUid();


        databasereference = FirebaseDatabase.getInstance().getReference().child("MY_USERS");

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
                try {
                    FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                    transaction.replace(R.id.MainReplacer, new WalletFrag());
                    transaction.commit();
                }catch (Exception e){

                }

            }
        });

        binding.withdrawbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                try {
                    FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                    transaction.replace(R.id.MainReplacer, new WithdrawFrag());
                    transaction.commit();
                }catch (Exception e){

                }


            }
        });


        binding.ProfileRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                    transaction.replace(R.id.MainReplacer, new profilefrag());
                    transaction.commit();
                }catch (Exception e){

                }




            }
        });

        binding.Category1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (WTC == 0){
                    try {
                      startActivity(new Intent(getActivity(), NSP.class));
                        getActivity().overridePendingTransition(R.anim.fad, R.anim.fad);
                    }catch (Exception e){}
                }else {

                    try {
                        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                        transaction.replace(R.id.MainReplacer, new Waitroom());
                        transaction.commit();

                    }catch (Exception e){}

                }





            }
        });

        binding.Category2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                try {
                    Intent i = new Intent(android.content.Intent.ACTION_VIEW);
                    i.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.nkdevelopers.niktoearningapp&hl=en&gl=US"));
                    startActivity(i);
                }catch (Exception e){

                }

            }
        });

        binding.Category3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (WTC == 0){
                    try {

                        startActivity(new Intent(getActivity(), stc.class));
                        getActivity().overridePendingTransition(R.anim.fad, R.anim.fad);

                    }catch (Exception e){}
                }else {

                    try {
                        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                        transaction.replace(R.id.MainReplacer, new Waitroom());
                        transaction.commit();
                    }catch (Exception e){

                    }
                }



            }
        });

        binding.Category4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (WTC == 0){
                    try {

                        if (rewardedAd.isReady()){
                            rewardedAd.showAd();
                        }

                    }catch (Exception e){}

                }
                else{

                    FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                    transaction.replace(R.id.MainReplacer, new Waitroom());
                    transaction.commit();
                }

            }
        });

        binding.Category5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (WTC == 0) {
                    try {

                        startActivity(new Intent(getActivity(),DCROOL.class));
                        getActivity().overridePendingTransition(R.anim.fad, R.anim.fad);

                    } catch (Exception e) {}
                }else {
                    try {
                        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                        transaction.replace(R.id.MainReplacer, new Waitroom());
                        transaction.commit();
                    } catch (Exception e) {}
                }

            }
        });

        binding.Category6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                    transaction.replace(R.id.MainReplacer, new moneyhistroy());
                    transaction.commit();

                }catch (Exception e){
                    Toast.makeText(getContext(), "Try Again Later", Toast.LENGTH_SHORT).show();
                }

            }
        });



        return binding.getRoot();
    }

        @Override
        public void onUserRewarded(MaxAd maxAd, MaxReward maxReward) {

        }

        @Override
        public void onRewardedVideoStarted(MaxAd maxAd) {

        }

        @Override
        public void onRewardedVideoCompleted(MaxAd maxAd) {

            DatabaseReference df2 = FirebaseDatabase.getInstance().getReference().child("MY_USERS").child(FirebaseAuth.getInstance().getCurrentUser().getUid());

            df2.child("reward_AD").setValue(ServerValue.increment(1));

            df2.child("coins").setValue(ServerValue.increment(8));
            Toast.makeText(getContext(), "You Earned 8 Coins", Toast.LENGTH_SHORT).show();
            DatabaseReference  reference1 = FirebaseDatabase.getInstance().getReference().child("MY_USERS").child(FirebaseAuth.getInstance().getUid());
            reference1.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Coins = snapshot.child("coins").getValue(Integer.class);
                    binding.UserCoins.setText(String.valueOf(Coins));


                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("MY_USERS").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
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

    }



