package com.nkdevelopers.niktoearningapp.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
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

public class SingupFrag extends Fragment {


    FragmentSingupBinding binding;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth auth;
    FirebaseDatabase database;
    Task<Void> reference;
    ProgressDialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSingupBinding.inflate(inflater,container,false);

        // Getting Instances

        firebaseFirestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();


        binding.SignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Creating Authdata Model Class Object

                String Name,Email,Password;
                Name = binding.SName.getText().toString();
                Email = binding.SEmail.getText().toString();
                Password = binding.SPswrd.getText().toString();
                dialog = new ProgressDialog(getContext());
                dialog.setMessage("Creating Your Account Please Wait!");


                if (TextUtils.isEmpty(Name)){

                    binding.SName.setError("Incorrect Name");
                }else if(TextUtils.isEmpty(Email)){

                    binding.SEmail.setError("Incorrect Email");
                }else if (TextUtils.isEmpty(Password)){

                    binding.SPswrd.setError("Incorrect password");
                }else {

                    dialog.show();
                    AuthdataModel authdataModel = new AuthdataModel(Name,Email,Password,"No New Notice is Available :)","",0,0);

                    auth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                dialog.dismiss();

                                database = FirebaseDatabase.getInstance();

                                reference = database.getReference().child(task.getResult().getUser().getUid()).setValue(authdataModel);

                                startActivity(new Intent(getContext(), MainActivity.class));
                            }


                            else {
                                dialog.dismiss();

                                Toast.makeText(getContext(), "Sorry Try Again Later", Toast.LENGTH_SHORT).show();
                            }


                        }
                    });






                    // If Else Text Utils Statement End Here
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

        return binding.getRoot();

    }
}