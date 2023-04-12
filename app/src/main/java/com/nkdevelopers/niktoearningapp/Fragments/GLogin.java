package com.nkdevelopers.niktoearningapp.Fragments;

import android.app.ProgressDialog;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.nkdevelopers.niktoearningapp.ModelClasses.ATBCD;
import com.nkdevelopers.niktoearningapp.R;
import com.nkdevelopers.niktoearningapp.databinding.FragmentGLoginBinding;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GLogin extends Fragment {


    FragmentGLoginBinding binding;
    GoogleSignInOptions GSO;
    GoogleSignInClient GSC;
    ATBCD authDataModel;
    FirebaseDatabase firebaseDatabase;
    ProgressDialog dialog ;

    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                        View.SYSTEM_UI_FLAG_FULLSCREEN);
        // Inflate the layout for this fragment
        binding = FragmentGLoginBinding.inflate(getLayoutInflater(),container,false);

        // Google Signin
        GSO = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                        .requestEmail()
                                .build();
        // Using GSC Google Signin Client

        GSC = GoogleSignIn.getClient(getContext(),GSO);
       dialog = new ProgressDialog(getContext());
        dialog.setMessage("Please Wait!");


        binding.GSIGNUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Method Calling
                GoogleSignin();


            }
        });

        return binding.getRoot();
    }

    private void GoogleSignin() {

        Intent intent = GSC.getSignInIntent();
        startActivityForResult(intent,60);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 60) {

            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account.getIdToken());
            }catch (Exception e){

                Toast.makeText(getContext(), "Please Try Different Account!", Toast.LENGTH_SHORT).show();
            }

        }

    }

    private void firebaseAuthWithGoogle(String IDTOKEN){

        AuthCredential credential = GoogleAuthProvider.getCredential(IDTOKEN,null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        dialog.show();

                        if (task.isSuccessful()){
                            DatabaseReference databaseReferencef = FirebaseDatabase.getInstance().getReference();
                            // Check is User is Already Registered or Not

                            databaseReferencef.child("MY_USERS").child(mAuth.getCurrentUser().getUid().toString())
                                    .addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                                            if (snapshot.exists()){
                                                Toast.makeText(getContext(), "Welcome Back " + mAuth.getCurrentUser().getDisplayName() , Toast.LENGTH_SHORT).show();
//                                                startActivity(new Intent(getContext(), MainActivity.class));

                                                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                                                transaction.replace(R.id.MainReplacer,new CategoryFrag());
                                                transaction.commit();
                                                dialog.dismiss();

                                            }else {
                                                // Perform Model Class Work
                                                // Creating Authdata Model Class Object

                                                // We Will use Google Signin Account because there is no old user occur so we use Google Signin to fetch
                                                // UserGmail data than get other name,email from gmail
                                                firebaseDatabase = FirebaseDatabase.getInstance();

                                                GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getContext());


                                                String Name,Email;
                                                Name = account.getDisplayName();
                                                Email = account.getEmail();

                                                String UPIC = account.getPhotoUrl().toString();
                                                String JOINDATE = getDateTime();
                                                String NOTICE = "100% Legit! Collect 800+ Coins to Get your First Withdraw";
                                                int UCOINS = 0;
                                                int spins = 5;
                                                int Scratcher = 5;
                                                int Withdraw = 0;
                                                int Dice = 5;
                                                int RewardAd = 0;
                                                int INTERAD = 0;

                                                ATBCD ATBCD =  new ATBCD(Name,Email,UPIC,JOINDATE,NOTICE,UCOINS,Scratcher, spins, Dice,Withdraw,RewardAd,INTERAD);

                                                DatabaseReference databaseReference1 = FirebaseDatabase.getInstance()
                                                        .getReference();
                                                databaseReference1.child("MY_USERS")
                                                        .child(mAuth.getCurrentUser().getUid())
                                                        .setValue(ATBCD);
                                                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                                                transaction.replace(R.id.MainReplacer,new CategoryFrag());
                                                transaction.commit();
                                                dialog.dismiss();

                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                            Toast.makeText(getContext(), "Contact With Support Team or Try Again!", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        }
                    }
                });
    }

    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

}