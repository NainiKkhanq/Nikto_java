package com.nkdevelopers.niktoearningapp.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nkdevelopers.niktoearningapp.R;
import com.nkdevelopers.niktoearningapp.databinding.FragmentOnboardBinding;

public class OnboardFrag extends Fragment {


    FragmentOnboardBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding =FragmentOnboardBinding.inflate(getLayoutInflater(),container,false);


        binding.SingupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // opening Fragment From Fragment // We open Signup Fragment on Signup click
                SingupFrag singupFrag = new SingupFrag();
                FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.Replacer,singupFrag)
                        .addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        binding.Loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // opening login Frag on Login Click
                LoginFrag loginFrag = new LoginFrag();
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.Replacer,loginFrag).addToBackStack(null);


                transaction.commit();

            }
        });

        return binding.getRoot();

    }



}