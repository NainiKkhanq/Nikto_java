package com.nkdevelopers.niktoearningapp.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nkdevelopers.niktoearningapp.ModelClasses.WithdrawModel;
import com.nkdevelopers.niktoearningapp.R;
import com.nkdevelopers.niktoearningapp.databinding.FragmentWithdrawBinding;

public class WithdrawFrag extends Fragment {

    FragmentWithdrawBinding binding;

    int COINK;

    DatabaseReference databaseReference;

    WithdrawModel withdrawModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentWithdrawBinding.inflate(getLayoutInflater(),container,false);






        databaseReference = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                int Token = snapshot.child("withdraw").getValue(Integer.class);




//
                if (Token != 0){
                    binding.Wtihdrawbtn.setVisibility(View.INVISIBLE);
                    binding.paypalemail.setVisibility(View.INVISIBLE);
                    binding.paypalname.setVisibility(View.INVISIBLE);
                    binding.info.setVisibility(View.INVISIBLE);
                    databaseReference = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getUid());
                    databaseReference.child("notice").setValue("Withdraw Request Send Our Team Will Send you the money As soon as possible");


                }
                else {
                    databaseReference = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getUid());
                    binding.Wtihdrawbtn.setVisibility(View.VISIBLE);
                    binding.paypalemail.setVisibility(View.VISIBLE);
                    binding.paypalname.setVisibility(View.VISIBLE);
                    binding.info.setVisibility(View.VISIBLE);






                }


            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        databaseReference  = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                String COINJ = String.valueOf(snapshot.child("coins").getValue(Integer.class));
                binding.UserCoins.setText(COINJ);

                COINK = Integer.parseInt(COINJ);
                binding.withdrawrequest.setText(snapshot.child("notice").getValue(String.class));


            }








            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        binding.Wtihdrawbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String EmailID = binding.paypalemail.getText().toString();
                String PaypaluserName = binding.paypalname.getText().toString();
                String UID = FirebaseAuth.getInstance().getUid();

                if (TextUtils.isEmpty(EmailID)) {

                    binding.paypalemail.setError("Incorrect Email");
                } else if (TextUtils.isEmpty(PaypaluserName)) {

                    binding.paypalname.setError("Incorrect Name");
                } else {


                    if (COINK >= 10000) {


                        withdrawModel = new WithdrawModel(UID, EmailID, PaypaluserName);


                        databaseReference = FirebaseDatabase.getInstance().getReference(UID);


                        databaseReference.child("coins").setValue(0);
                        databaseReference.child("withdraw").setValue(1);

                        databaseReference.child("notice").setValue("Withdraw request Received You will get your Withdraw Soon!");


                        databaseReference = FirebaseDatabase.getInstance().getReference("WithdrawAdmin");

                        databaseReference.child(FirebaseAuth.getInstance().getUid()).child("WithdrawRequest").setValue(withdrawModel);
                        databaseReference.child(FirebaseAuth.getInstance().getUid()).child("WithdrawRequest").child("coins").setValue(COINK);
                        binding.paypalname.setText("");
                        binding.paypalemail.setText("");


                    } else {



                        databaseReference = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getUid());
                        databaseReference.child("notice").setValue("Payment Failed Due to Insufficient Balance ");

                    }

                }
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



        return binding.getRoot();
    }
}