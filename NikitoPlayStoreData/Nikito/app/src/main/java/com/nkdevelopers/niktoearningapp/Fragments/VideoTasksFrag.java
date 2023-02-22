package com.nkdevelopers.niktoearningapp.Fragments;



import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.nkdevelopers.niktoearningapp.databinding.FragmentVideoTasksBinding;
import com.unity3d.ads.IUnityAdsInitializationListener;
import com.unity3d.ads.IUnityAdsLoadListener;
import com.unity3d.ads.IUnityAdsShowListener;
import com.unity3d.ads.UnityAds;
import com.unity3d.ads.UnityAdsShowOptions;




public class VideoTasksFrag extends Fragment implements IUnityAdsInitializationListener{


    FragmentVideoTasksBinding binding;
    DatabaseReference databaseReference;



    // Unity Ads Initializing

    private static final String INTR_ID = "Rewarded_Android";
    private static final boolean TEST_MODE = false;

    private IUnityAdsLoadListener loadListener = new IUnityAdsLoadListener() {
        @Override
        public void onUnityAdsAdLoaded(String placementId) {
            UnityAds.show((getActivity()), INTR_ID, new UnityAdsShowOptions(), showListener);
        }

        @Override
        public void onUnityAdsFailedToLoad(String placementId, UnityAds.UnityAdsLoadError error, String message) {

            Toast.makeText(getContext(), "No More Videos Available", Toast.LENGTH_SHORT).show();

        }
    };

    private IUnityAdsShowListener showListener = new IUnityAdsShowListener() {
        @Override
        public void onUnityAdsShowFailure(String placementId, UnityAds.UnityAdsShowError error, String message) {

        }

        @Override
        public void onUnityAdsShowStart(String placementId) {
            Toast.makeText(getContext(), "Watch Complete Video to Get Reward!", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onUnityAdsShowClick(String placementId) {
        }

        @Override
        public void onUnityAdsShowComplete(String placementId, UnityAds.UnityAdsShowCompletionState state) {


            databaseReference = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getUid());

            databaseReference.child("coins").setValue(ServerValue.increment(10));

            Toast.makeText(getContext(), "You Won 10 Coins", Toast.LENGTH_SHORT).show();


        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        binding = FragmentVideoTasksBinding.inflate(getLayoutInflater(), container, false);




        // Unity Ads Initializing
        UnityAds.initialize(getContext(),"4947123",TEST_MODE,this);







        databaseReference  = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                String COINS = String.valueOf(snapshot.child("coins").getValue(Integer.class));

                binding.UserCoins.setText(COINS);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        binding.AdmobAdBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    if (UnityAds.isInitialized()){
                        DisplayIntAd();
                        CountDownTimer countDownTimer = new CountDownTimer(10000,1000) {
                            @Override
                            public void onTick(long l) {

                                binding.wtext.setText("Next Video is Available after " +l/1000 + " Seconds");
                                binding.UintyAdBtn.setVisibility(View.INVISIBLE);
                                binding.AdmobAdBtn.setVisibility(View.INVISIBLE);
                            }

                            @Override
                            public void onFinish() {

                                binding.wtext.setText("Watch Videos");
                                binding.UintyAdBtn.setVisibility(View.VISIBLE);
                                binding.AdmobAdBtn.setVisibility(View.VISIBLE);
                            }
                        }.start();

                    }else {
                        Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();


                    }


                }
            });







        binding.UintyAdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CountDownTimer countDownTimer = new CountDownTimer(10000,1000) {
                    @Override
                    public void onTick(long l) {
                        binding.wtext.setText("Next Video is Available after " +l/1000 + " Seconds");
                        binding.UintyAdBtn.setVisibility(View.INVISIBLE);
                        binding.AdmobAdBtn.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onFinish() {
                        binding.wtext.setText("Watch Videos");
                        binding.UintyAdBtn.setVisibility(View.VISIBLE);
                        binding.AdmobAdBtn.setVisibility(View.VISIBLE);

                    }
                }.start();

                if (UnityAds.isInitialized()){
                    DisplayIntAd();


                }else {
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();


                }
            }
        });

        return binding.getRoot();

}



    @Override
    public void onInitializationComplete() {

    }

    @Override
    public void onInitializationFailed(UnityAds.UnityAdsInitializationError unityAdsInitializationError, String s) {

    }
    public void DisplayIntAd () {
        UnityAds.load(INTR_ID, loadListener);

    }

}
