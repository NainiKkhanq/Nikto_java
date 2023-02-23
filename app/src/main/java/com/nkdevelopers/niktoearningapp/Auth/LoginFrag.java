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
    String MG;
    String SPN;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater,container,false);

        dialog = new ProgressDialog(getContext());

        dialog.setMessage("Logging into your Account Please Wait!");



        auth = FirebaseAuth.getInstance();
        SPN = "+92 3185856591";
        MG = "Thanks for using Bitcoin Gaze! Please type your concern and we will reply you as soon as possible.!";
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

        binding.supportteamL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(
                        new Intent(Intent.ACTION_VIEW,
                                Uri.parse(
                                        String.format("https://api.whatsapp.com/send?phone=%s&text=%s",SPN,MG )
                                )
                        )
                );
            }
        });
        return binding.getRoot();

    }


}