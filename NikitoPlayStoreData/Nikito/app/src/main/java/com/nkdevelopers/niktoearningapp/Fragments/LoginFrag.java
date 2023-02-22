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
import com.nkdevelopers.niktoearningapp.MainActivity;
import com.nkdevelopers.niktoearningapp.R;
import com.nkdevelopers.niktoearningapp.databinding.FragmentLoginBinding;

public class LoginFrag extends Fragment {




    FragmentLoginBinding binding;
    FirebaseAuth auth;
    ProgressDialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater,container,false);

        dialog = new ProgressDialog(getContext());

        dialog.setMessage("Logging into your Account Please Wait!");



        auth = FirebaseAuth.getInstance();

        binding.SiginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Email = binding.LEmail.getText().toString();
                String Password = binding.LPswrd.getText().toString();

                if (TextUtils.isEmpty(Email)){

                    binding.LEmail.setError("Incorrect Email");
                }
                else if (TextUtils.isEmpty(Password)){

                    binding.LPswrd.setError("Incorrect Password");
                }
                else {

                    dialog.show();
                    auth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()){
                                dialog.dismiss();
                                Toast.makeText(getContext(), "Welcome Back", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getContext(), MainActivity.class));

                            }
                            else {
                                dialog.dismiss();
                                Toast.makeText(getContext(), task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

                }



            }
        });



        binding.LSingupText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.Replacer,new SingupFrag());
                transaction.commit();


            }
        });

        return binding.getRoot();

    }


}