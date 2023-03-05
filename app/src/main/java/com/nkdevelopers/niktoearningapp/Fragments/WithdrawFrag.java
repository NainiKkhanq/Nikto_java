package com.nkdevelopers.niktoearningapp.Fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.nkdevelopers.niktoearningapp.ModelClasses.WithdrawModel;
import com.nkdevelopers.niktoearningapp.R;
import com.nkdevelopers.niktoearningapp.databinding.FragmentWithdrawBinding;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class WithdrawFrag extends Fragment {

    FragmentWithdrawBinding binding;

    int COINK;

    DatabaseReference databaseReference;

    WithdrawModel withdrawModel;

    FirebaseAuth auth;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String MONEY;
    int Token;
    int INTADS,RWRDADS;
    String MG;
    String SPN;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentWithdrawBinding.inflate(getLayoutInflater(),container,false);
        getActivity().getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                        View.SYSTEM_UI_FLAG_FULLSCREEN);
        auth = FirebaseAuth.getInstance();

        SPN = "+92 3185856591";
        MG = "Thanks for using Bitcoin Gaze! Please type your concern and we will reply you as soon as possible.!";


        databaseReference = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Token = snapshot.child("withdraw").getValue(Integer.class);
                MONEY = snapshot.child("BTC").getValue(String.class);
                INTADS = snapshot.child("inter_AD").getValue(Integer.class);
                RWRDADS = snapshot.child("reward_AD").getValue(Integer.class);








                if (Token == 1){
                    binding.WITHDRAW.setVisibility(View.INVISIBLE);
                    binding.COINBASEEMAIL.setVisibility(View.INVISIBLE);
                    databaseReference = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getUid());
                    databaseReference.child("notice").setValue("Withdraw Request Send! Your money will be transferred to your account soon");
                    binding.B2.setText("Request Send");
                    binding.B3.setText("Please Wait Money Will be Transfer to your Account Very Soon!");




                }
                else {
                    databaseReference = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getUid());
                    binding.WITHDRAW.setVisibility(View.VISIBLE);
                    binding.COINBASEEMAIL.setVisibility(View.VISIBLE);







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
                binding.UserCoins.setText(MONEY);

                COINK = Integer.parseInt(COINJ);


            }








            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        binding.WITHDRAW.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SuspiciousIndentation")
            @Override
            public void onClick(View view) {


                String EmailID = binding.COINBASEEMAIL.getText().toString();
                String UID = FirebaseAuth.getInstance().getUid();


                if ((!EmailID.matches(emailPattern))) {

                    binding.COINBASEEMAIL.setError("Please Type Email ID");
                }else if (TextUtils.isEmpty(EmailID)){
                    binding.COINBASEEMAIL.setError("Incorrect Email");
                }else if (COINK >= 500 && Token == 0 && INTADS>=10 && RWRDADS>=2 ) {


                        withdrawModel = new WithdrawModel(UID,EmailID,MONEY,INTADS,RWRDADS);


                        databaseReference = FirebaseDatabase.getInstance().getReference(UID);


                        databaseReference.child("coins").setValue(0);
                        databaseReference.child("withdraw").setValue(1);

                        databaseReference.child("inter_AD").setValue(0);
                        databaseReference.child("reward_AD").setValue(0);
                    databaseReference.child("notice").setValue("Withdraw request Received You will get your Withdraw Soon!");
                    databaseReference.child("BTC").setValue(0);


                    Random random = new Random();
                    int RNDS = random.nextInt(1000000000);
                    databaseReference.child("PAYMENTINFO").child("H1"+RNDS).child("TOTAL_MONEY").setValue(MONEY);
                    databaseReference.child("PAYMENTINFO").child("H1"+RNDS).child("DATE").setValue(getDateTime());


                    databaseReference = FirebaseDatabase.getInstance().getReference("WithdrawAdmin");

                        databaseReference.child(FirebaseAuth.getInstance().getUid()).child("WithdrawRequest").setValue(withdrawModel);
                        databaseReference.child(FirebaseAuth.getInstance().getUid()).child("WithdrawRequest").child("coins").setValue(COINK);
                        binding.COINBASEEMAIL.setText("");
                    databaseReference.child(FirebaseAuth.getInstance().getUid()).child("WithdrawRequest").child("Withdraw Date").setValue(getDateTime());







                } else {


                    Toast.makeText(getContext(), "Try Again Later or Contact with Support Team", Toast.LENGTH_SHORT).show();
                        databaseReference = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getUid());
                        databaseReference.child("notice").setValue("Payment Failed Due to Some Reasons Try Again later or Contact us ");

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



        binding.supportteam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(
                        new Intent(Intent.ACTION_VIEW,
                                Uri .parse(
                                        String.format("https://api.whatsapp.com/send?phone=%s&text=%s",SPN,MG )
                                )
                        )
                );
            }
        });

        return binding.getRoot();
    }
    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}