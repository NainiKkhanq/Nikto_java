package com.nkdevelopers.niktoearningapp.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nkdevelopers.niktoearningapp.R;
import com.nkdevelopers.niktoearningapp.databinding.FragmentWalletBinding;

public class WalletFrag extends Fragment {


    FragmentWalletBinding binding;
    DatabaseReference reference;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentWalletBinding.inflate(getLayoutInflater(),container,false);



        reference = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String Coins = String.valueOf(snapshot.child("coins").getValue(Integer.class));
                binding.UserCoins.setText(Coins);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        binding.backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction  = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.MainReplacer,new CategoryFrag());
                transaction.commit();
            }
        });

        binding.withdrawbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction  = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.MainReplacer,new WithdrawFrag());
                transaction.commit();
            }
        });

        return binding.getRoot();
    }
}