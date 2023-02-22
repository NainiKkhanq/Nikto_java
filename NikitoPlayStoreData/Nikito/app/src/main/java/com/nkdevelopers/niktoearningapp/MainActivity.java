package com.nkdevelopers.niktoearningapp;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.nkdevelopers.niktoearningapp.Fragments.CategoryFrag;
import com.nkdevelopers.niktoearningapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());







    }

    @Override
    protected void onStart() {
        super.onStart();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.MainReplacer,new CategoryFrag());
        transaction.commit();

    }

    @Override
    public void onBackPressed() {

    }


}