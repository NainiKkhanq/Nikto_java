package com.nkdevelopers.niktoearningapp.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nkdevelopers.niktoearningapp.R;
import com.nkdevelopers.niktoearningapp.databinding.FragmentProfilefragBinding;


public class profilefrag extends Fragment {

    FragmentProfilefragBinding binding;
    DatabaseReference databaseReference;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfilefragBinding.inflate(getLayoutInflater(),container,false);

        databaseReference = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getUid());


                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {


                        String Name = snapshot.child("name").getValue(String.class);
                        binding.UNAME.setText(Name);

                        String Email = snapshot.child("email").getValue(String.class);
                        binding.UEMAIL.setText(Email);
                        
                        String Coins = String.valueOf(snapshot.child("coins").getValue(Integer.class));
                        binding.UCOINS.setText(Coins);


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                        Toast.makeText(getContext(), "Internet Connection lost", Toast.LENGTH_SHORT).show();

                    }
                });



                binding.ReportaProblem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://forms.gle/dzJ4t1Vqh34bSgb77"));
                        startActivity(browserIntent);
                    }
                });

                binding.Contactus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://forms.gle/nQ7NoEipoZ3tBEf18"));
                        startActivity(browserIntent);
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

        return  binding.getRoot();
    }
}