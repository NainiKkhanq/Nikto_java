package com.nkdevelopers.niktoearningapp.Auth;

import android.app.ProgressDialog;
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

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.firestore.FirebaseFirestore;

import com.nkdevelopers.niktoearningapp.MainActivity;
import com.nkdevelopers.niktoearningapp.ModelClasses.AuthdataModel;
import com.nkdevelopers.niktoearningapp.R;
import com.nkdevelopers.niktoearningapp.databinding.FragmentSingupBinding;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SingupFrag extends Fragment {


    FragmentSingupBinding binding;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth auth;
    FirebaseDatabase database;
    Task<Void> reference;
    ProgressDialog dialog;
    String MG;
    String SPN;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSingupBinding.inflate(inflater,container,false);

        // Getting Instances

        firebaseFirestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        SPN = "+92 3185856591";
        MG = "Thanks for using Bitcoin Bonanza! Please type your concern and we will reply you as soon as possible.!";

        binding.SignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Creating Authdata Model Class Object

                String Name,Email,Password,country;
                Name = binding.SName.getText().toString();
                Email = binding.SEmail.getText().toString();
                Password = binding.SPswrd.getText().toString();
                dialog = new ProgressDialog(getContext());
                dialog.setMessage("Creating Your Account Please Wait!");
                String UPIC = "https://res.cloudinary.com/dghloo9lv/image/upload/v1673249067/logo_pmjcny.png";
                String JOINDATE = getDateTime();
                String NOTICE = "Welcome to Bitcoin Bonanza! Enjoy the Journey";
                int UCOINS = 0;
                int spins = 5;
                int Scratcher = 5;
                int Withdraw = 0;
                int Dice = 5;
                int RewardAd = 0;
                int INTERAD = 0;


                if (TextUtils.isEmpty(Name)){

                    binding.SName.setError("Incorrect Name");
                }else if(TextUtils.isEmpty(Email)){

                    binding.SEmail.setError("Incorrect Email");
                }else if (TextUtils.isEmpty(Password)){

                    binding.SPswrd.setError("Incorrect password");
                }

                else {

                    dialog.show();

                   AuthdataModel authDataModel = new AuthdataModel(Name,Email,UPIC,JOINDATE,NOTICE,UCOINS,spins,Scratcher,Dice,Withdraw,RewardAd,INTERAD);

                    auth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                dialog.dismiss();

                                database = FirebaseDatabase.getInstance();

                                reference = database.getReference().child(task.getResult().getUser().getUid()).setValue(authDataModel);

                                startActivity(new Intent(getContext(), MainActivity.class));
                            }


                            else {
                                dialog.dismiss();

                                Toast.makeText(getContext(), "Sorry Try Again Later", Toast.LENGTH_SHORT).show();
                            }


                        }
                    });






                }

            }
        });












        binding.SLoginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.Replacer,new LoginFrag());
                transaction.commit();
            }
        });

        binding.supportteamS.setOnClickListener(new View.OnClickListener() {
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