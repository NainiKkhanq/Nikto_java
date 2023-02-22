package com.nkdevelopers.niktoearningapp.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.database.FirebaseDatabase;
import com.nkdevelopers.niktoearningapp.Auth.SingupFrag;
import com.nkdevelopers.niktoearningapp.MainActivity;
import com.nkdevelopers.niktoearningapp.ModelClasses.AuthdataModel;
import com.nkdevelopers.niktoearningapp.R;
import com.nkdevelopers.niktoearningapp.databinding.FragmentGLoginBinding;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GLogin extends Fragment {


    FragmentGLoginBinding binding;
    GoogleSignInOptions GSO;
    GoogleSignInClient GSC;
    Task<Void> databaseReference;
    AuthdataModel authDataModel;
    FirebaseDatabase firebaseDatabase;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                        View.SYSTEM_UI_FLAG_FULLSCREEN);
        // Inflate the layout for this fragment
        binding = FragmentGLoginBinding.inflate(getLayoutInflater(),container,false);


        binding.GSIGNUP.setOnClickListener(new View.OnClickListener() {
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