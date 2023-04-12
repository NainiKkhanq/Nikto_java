package com.nkdevelopers.niktoearningapp.Fragments;


import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.applovin.mediation.nativeAds.MaxNativeAdLoader;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nkdevelopers.niktoearningapp.BuildConfig;
import com.nkdevelopers.niktoearningapp.R;
import com.nkdevelopers.niktoearningapp.databinding.FragmentSplashBinding;

public class SpinWheelFrag extends Fragment {

    FragmentSplashBinding binding;
    FirebaseAuth auth;
    Context context;
    FirebaseUser user;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        getActivity().getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                        View.SYSTEM_UI_FLAG_FULLSCREEN);



        binding = FragmentSplashBinding.inflate(inflater,container,false);

        auth = FirebaseAuth.getInstance();
        user = auth.getInstance().getCurrentUser();



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                if (!isconnected()) {

                    binding.warntext.setText("Connection Failed :(");

                }else if (auth.getCurrentUser() == null){



                    FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                    transaction.replace(R.id.MainReplacer, new GLogin());
                    transaction.commit();



                }
                else if (auth.getCurrentUser() !=null){



                                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                                transaction.replace(R.id.MainReplacer, new CategoryFrag());
                                transaction.commit();




                }


            }
        },1000);


        return binding.getRoot();




    }
    private  boolean isconnected() {

        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo()!=null && connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting();


    }



}