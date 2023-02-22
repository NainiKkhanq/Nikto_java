package com.nkdevelopers.niktoearningapp.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.anupkumarpanwar.scratchview.ScratchView;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.admanager.AdManagerAdRequest;
import com.google.android.gms.ads.admanager.AdManagerAdView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.nkdevelopers.niktoearningapp.R;
import com.nkdevelopers.niktoearningapp.databinding.FragmentScratchfragBinding;

import java.util.Random;

public class scratchfrag extends Fragment {


    FragmentScratchfragBinding binding;
    DatabaseReference databaseReference;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        binding = FragmentScratchfragBinding.inflate(getLayoutInflater(),container,false);


        // banner Ad in Admob

        AdManagerAdRequest adRequest1 = new AdManagerAdRequest.Builder().build();
        binding.mAdManagerAdView.loadAd(adRequest1);

        binding.mAdManagerAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                binding.mAdManagerAdView.loadAd(adRequest1);
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        });

//
//        //Banner Ad End Here


        binding.scratchView.setRevealListener(new ScratchView.IRevealListener() {
            @Override
            public void onRevealed(ScratchView scratchView) {
                final int random = new Random().nextInt(12) ;

                binding.text.setText("Congratulations You Won  " +random + " \uD83D\uDCB0 Coins");
                binding.text.setVisibility(View.VISIBLE);

                Toast.makeText(getContext(), "You won " + random+"Coins",  Toast.LENGTH_SHORT).show();

                databaseReference = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getUid());


                databaseReference.child("coins").setValue(ServerValue.increment(random));

                binding.tryagain.setOnClickListener(view -> {
                    if (scratchView.isRevealed()){

                        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                        transaction.replace(R.id.OptionReplacer, new scratchfrag());
                        transaction.commit();

                    }
                    else {

                        Toast.makeText(getContext(), "No Clear", Toast.LENGTH_SHORT).show();
                    }
                });



            }

            @Override
            public void onRevealPercentChangedListener(ScratchView scratchView, float percent) {
                if (percent>=0.5) {
                    Toast.makeText(getContext(), "You won "  + percent, Toast.LENGTH_SHORT).show();
                }
            }
        });


        // Fetching data in Firebase

        databaseReference = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String coin =String.valueOf( snapshot.child("coins").getValue(Integer.class));


                binding.UserCoins.setText(coin);

                binding.WithdrawNote.setText("Collect 10,000 Coins to Get Withdraw");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return binding.getRoot();


    }
}