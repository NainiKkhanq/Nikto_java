package com.nkdevelopers.niktoearningapp.Fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
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
import com.nkdevelopers.niktoearningapp.BuildConfig;
import com.nkdevelopers.niktoearningapp.ModelClasses.WTMCD;
import com.nkdevelopers.niktoearningapp.R;
import com.nkdevelopers.niktoearningapp.databinding.FragmentWithdrawBinding;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class WithdrawFrag extends Fragment {

    FragmentWithdrawBinding binding;

    int COINK;
    String t_INT,R_RWD;
    int Coins;

    DatabaseReference databaseReference;

    WTMCD WTMCD;

    FirebaseAuth auth;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String MONEY;
    int Token,APV;

    String MG;
    String SPN;

    String AppV;
String COINW;

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








                    databaseReference = FirebaseDatabase.getInstance().getReference().child("MY_USERS").child(FirebaseAuth.getInstance().getUid());
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            Token = snapshot.child("withdraw").getValue(Integer.class);
                            MONEY = snapshot.child("BTC").getValue(String.class);

                            // TK CHK

                            if (Token == 1){
                                binding.WITHDRAW.setVisibility(View.INVISIBLE);
                                binding.COINBASEEMAIL.setVisibility(View.INVISIBLE);
                                databaseReference = FirebaseDatabase.getInstance().getReference().child("MY_USERS").child(FirebaseAuth.getInstance().getUid());
                                databaseReference.child("notice").setValue("Withdraw Request Send! Your money will be transferred to your account soon");
                                binding.B2.setText("Request Send");
                                binding.B3.setText("Please Wait Money Will be Transfer to your Account Very Soon!");




                                String COINJ = String.valueOf(snapshot.child("coins").getValue(Integer.class));
                                binding.UserCoins.setText(MONEY);

                                COINK = Integer.parseInt(COINJ);
                                COINW = String.valueOf(snapshot.child("coins").getValue(Integer.class));
                                Coins = snapshot.child("coins").getValue(Integer.class);

                                t_INT = String.valueOf(snapshot.child("inter_AD").getValue(Integer.class));
                                R_RWD = String.valueOf(snapshot.child("reward_AD").getValue(Integer.class));



                            }
                            else {
                                binding.WITHDRAW.setVisibility(View.VISIBLE);
                                binding.COINBASEEMAIL.setVisibility(View.VISIBLE);
                                binding.B3.setText("Enter Coinbase Email to Get Your Withdraw!");



                                String COINJ = String.valueOf(snapshot.child("coins").getValue(Integer.class));
                                binding.UserCoins.setText(MONEY);

                                COINK = Integer.parseInt(COINJ);
                                COINW = String.valueOf(snapshot.child("coins").getValue(Integer.class));
                                Coins = snapshot.child("coins").getValue(Integer.class);

                                t_INT = String.valueOf(snapshot.child("inter_AD").getValue(Integer.class));
                                R_RWD = String.valueOf(snapshot.child("reward_AD").getValue(Integer.class));
                            }


                        }



                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });





        // calcjb


        DatabaseReference  reference = FirebaseDatabase.getInstance().getReference().child("MY_USERS").child(FirebaseAuth.getInstance().getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Coins = snapshot.child("coins").getValue(Integer.class);




                DatabaseReference reference  =FirebaseDatabase.getInstance().getReference().child("MY_USERS").child(FirebaseAuth.getInstance().getUid());

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










        binding.WITHDRAW.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SuspiciousIndentation")
            @Override
            public void onClick(View view) {


                String EmailID = binding.COINBASEEMAIL.getText().toString();
                String UID = FirebaseAuth.getInstance().getUid();
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("MY_USERS").child(FirebaseAuth.getInstance().getCurrentUser().getUid());



                if ((!EmailID.matches(emailPattern))) {

                    binding.COINBASEEMAIL.setError("Please Type Email ID");
                }else if (TextUtils.isEmpty(EmailID)){
                    binding.COINBASEEMAIL.setError("Incorrect Email");
                }else if (COINK >= 800 && Token == 0) {


                        WTMCD = new WTMCD(UID,EmailID,MONEY,COINW,getDateTime(),t_INT,R_RWD);


                     databaseReference = FirebaseDatabase.getInstance().getReference().child("MY_USERS").child(FirebaseAuth.getInstance().getUid());


                        databaseReference.child("coins").setValue(0);
                        databaseReference.child("withdraw").setValue(1);
                        databaseReference.child("reward_AD").setValue(0);
                        databaseReference.child("inter_AD").setValue(0);


                    databaseReference.child("notice").setValue("BackOut request Received You will get your Withdraw Soon!");
                    DatabaseReference databaseReferenceBTC = FirebaseDatabase.getInstance().getReference().child("MY_USERS").child(FirebaseAuth.getInstance().getUid());

                    databaseReference.child("BTC").setValue("0");







                            // Histroy Job
                    Random random = new Random();
                    int RNDS = random.nextInt(1000000000);
                    databaseReference.child("PAYMENTINFO").child("H1"+RNDS).child("TOTAL_MONEY").setValue(MONEY);
                    databaseReference.child("PAYMENTINFO").child("H1"+RNDS).child("DATE").setValue(getDateTime());


                    databaseReference = FirebaseDatabase.getInstance().getReference().child("WITHDRAW_ADMIN");

                        databaseReference.child(FirebaseAuth.getInstance().getUid()).child("WithdrawRequest").setValue(WTMCD);
                        binding.COINBASEEMAIL.setText("");
                    databaseReference.child(FirebaseAuth.getInstance().getUid()).child("WithdrawRequest").child("Withdraw Date").setValue(getDateTime());







                } else {


                    Toast.makeText(getContext(), "Try Again Later or Contact with Support Team", Toast.LENGTH_SHORT).show();
                        databaseReference = FirebaseDatabase.getInstance().getReference().child("MY_USERS").child(FirebaseAuth.getInstance().getUid());
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