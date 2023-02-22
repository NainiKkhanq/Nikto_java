package com.nkdevelopers.niktoearningapp.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.CountDownTimer;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.nkdevelopers.niktoearningapp.R;
import com.nkdevelopers.niktoearningapp.databinding.FragmentOfferwallsfragBinding;

import java.util.Random;

import io.grpc.Server;

public class offerwallsfrag extends Fragment {



    ProgressDialog dialog;
    FragmentOfferwallsfragBinding binding;
    String U11,U22,U33,U44,U55,U66,U77,U88,U99,U100;
    DatabaseReference databaseReference;
    int T_GN_PTC;
    int T_PTWATCH;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentOfferwallsfragBinding.inflate(getLayoutInflater(),container,false);
        getActivity().getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                        View.SYSTEM_UI_FLAG_FULLSCREEN);


        // TPC JOB
        databaseReference = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getCurrentUser().getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                T_GN_PTC = snapshot.child("t_PTC").getValue(Integer.class);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        // FETCHING_URLS

        databaseReference = FirebaseDatabase.getInstance().getReference("PTCADS");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dialog = new ProgressDialog(getContext());
                dialog.setMessage("Loading Please Wait!");

                dialog.show();
                Random random = new Random();
                int RND = random.nextInt(10);


                U11 = snapshot.child("U1").getValue(String.class);
                U22 = snapshot.child("U2").getValue(String.class);
                U33 = snapshot.child("U3").getValue(String.class);
                U44 = snapshot.child("U4").getValue(String.class);
                U55 = snapshot.child("U5").getValue(String.class);
                U66 = snapshot.child("U6").getValue(String.class);
                U77 = snapshot.child("U7").getValue(String.class);
                U88 = snapshot.child("U8").getValue(String.class);
                U99 = snapshot.child("U9").getValue(String.class);
                U100 = snapshot.child("U10").getValue(String.class);




                // RDJOB_Database_LOGICS

                if (RND == 1){

                    binding.WebView.getSettings().setJavaScriptEnabled(true);
                    binding.WebView.loadUrl(U11);
                    binding.WebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
                    binding.WebView.setWebViewClient(new WebViewClient());
                    binding.WebView.getSettings().setJavaScriptEnabled(true);

                    databaseReference = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getCurrentUser().getUid());
                    databaseReference.child("ptc_WATCH").setValue(ServerValue.increment(1));
                    if (T_GN_PTC>0){

                        databaseReference.child("t_PTC").setValue(ServerValue.increment(-1));

                    }else {}



                    // PGRSBR JOB
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {


                            dialog.dismiss();


                        }
                    },4000);

                    // TM JOB
                    CountDownTimer countDownTimer = new CountDownTimer(40000, 1000) {

                        public void onTick(long millisUntilFinished) {
                            binding.Timer.setText("" + millisUntilFinished / 1000);

                            // logic to set the EditText could go here
                        }

                        public void onFinish() {
                            binding.Timer.setText("Finish!");
                            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getCurrentUser().getUid());
//                            databaseReference.child("TOTAL_PTC_WATCH").setValue(ServerValue.increment(1));
                            databaseReference.child("coins").setValue(ServerValue.increment(10));
                            binding.CLOSEAD.setVisibility(View.VISIBLE);
                        }

                    }.start();

                }else if (RND ==2){

                    binding.WebView.getSettings().setJavaScriptEnabled(true);
                    binding.WebView.loadUrl(U22);
                    binding.WebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
                    binding.WebView.getSettings().setJavaScriptEnabled(true);
                    binding.WebView.setWebViewClient(new WebViewClient());
                    databaseReference = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getCurrentUser().getUid());
                    databaseReference.child("ptc_WATCH").setValue(ServerValue.increment(1));
                    if (T_GN_PTC>0){

                        databaseReference.child("t_PTC").setValue(ServerValue.increment(-1));

                    }else {}
                    // PGRSBR JOB
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {


                            dialog.dismiss();


                        }
                    },4000);

                    // TM JOB
                    CountDownTimer countDownTimer = new CountDownTimer(40000, 1000) {

                        public void onTick(long millisUntilFinished) {
                            binding.Timer.setText("" + millisUntilFinished / 1000);

                            // logic to set the EditText could go here
                        }

                        public void onFinish() {
                            binding.Timer.setText("Finish!");
                            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getCurrentUser().getUid());
                            databaseReference.child("coins").setValue(ServerValue.increment(10));
                            binding.CLOSEAD.setVisibility(View.VISIBLE);
                        }

                    }.start();
                }else if (RND == 3){

                    binding.WebView.getSettings().setJavaScriptEnabled(true);
                    binding.WebView.loadUrl(U33);
                    binding.WebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
                    binding.WebView.setWebViewClient(new WebViewClient());
                    binding.WebView.getSettings().setJavaScriptEnabled(true);
                    databaseReference = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getCurrentUser().getUid());
                    databaseReference.child("ptc_WATCH").setValue(ServerValue.increment(1));
                    if (T_GN_PTC>0){

                        databaseReference.child("t_PTC").setValue(ServerValue.increment(-1));

                    }else {}

                    // PGRSBR JOB
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {


                            dialog.dismiss();


                        }
                    },4000);

                    // TM JOB
                    CountDownTimer countDownTimer = new CountDownTimer(40000, 1000) {

                        public void onTick(long millisUntilFinished) {
                            binding.Timer.setText("" + millisUntilFinished / 1000);

                            // logic to set the EditText could go here
                        }

                        public void onFinish() {
                            binding.Timer.setText("Finish!");
                            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getCurrentUser().getUid());
//                            databaseReference.child("TOTAL_PTC_WATCH").setValue(ServerValue.increment(1));
                            databaseReference.child("coins").setValue(ServerValue.increment(10));
                            binding.CLOSEAD.setVisibility(View.VISIBLE);
                        }

                    }.start();
                }else if (RND == 4){

                    binding.WebView.getSettings().setJavaScriptEnabled(true);
                    binding.WebView.loadUrl(U44);
                    binding.WebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
                    binding.WebView.getSettings().setJavaScriptEnabled(true);
                    binding.WebView.setWebViewClient(new WebViewClient());
                    databaseReference = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getCurrentUser().getUid());
                    databaseReference.child("ptc_WATCH").setValue(ServerValue.increment(1));
                    if (T_GN_PTC>0){

                        databaseReference.child("t_PTC").setValue(ServerValue.increment(-1));

                    }else {}

                    // PGRSBR JOB
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {


                            dialog.dismiss();


                        }
                    },4000);

                    // TM JOB
                    CountDownTimer countDownTimer = new CountDownTimer(40000, 1000) {

                        public void onTick(long millisUntilFinished) {
                            binding.Timer.setText("" + millisUntilFinished / 1000);

                            // logic to set the EditText could go here
                        }

                        public void onFinish() {
                            binding.Timer.setText("Finish!");
                            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getCurrentUser().getUid());
                            databaseReference.child("coins").setValue(ServerValue.increment(10));
                            binding.CLOSEAD.setVisibility(View.VISIBLE);
                        }

                    }.start();
                }else if (RND == 5){

                    binding.WebView.getSettings().setJavaScriptEnabled(true);
                    binding.WebView.loadUrl(U55);
                    binding.WebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
                    binding.WebView.getSettings().setJavaScriptEnabled(true);
                    binding.WebView.setWebViewClient(new WebViewClient());
                    databaseReference = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getCurrentUser().getUid());
                    databaseReference.child("ptc_WATCH").setValue(ServerValue.increment(1));
                    if (T_GN_PTC>0){

                        databaseReference.child("t_PTC").setValue(ServerValue.increment(-1));

                    }else {}

                    // PGRSBR JOB
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {


                            dialog.dismiss();


                        }
                    },4000);

                    // TM JOB
                    CountDownTimer countDownTimer = new CountDownTimer(40000, 1000) {

                        public void onTick(long millisUntilFinished) {
                            binding.Timer.setText("" + millisUntilFinished / 1000);

                            // logic to set the EditText could go here
                        }

                        public void onFinish() {
                            binding.Timer.setText("Finish!");
                            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getCurrentUser().getUid());
//                            databaseReference.child("TOTAL_PTC_WATCH").setValue(ServerValue.increment(1));
                            databaseReference.child("coins").setValue(ServerValue.increment(10));
                            binding.CLOSEAD.setVisibility(View.VISIBLE);
                        }

                    }.start();
                }else if (RND == 6){

                    binding.WebView.getSettings().setJavaScriptEnabled(true);
                    binding.WebView.loadUrl(U66);
                    binding.WebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
                    binding.WebView.setWebViewClient(new WebViewClient());
                    binding.WebView.getSettings().setJavaScriptEnabled(true);
                    databaseReference = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getCurrentUser().getUid());
                    databaseReference.child("ptc_WATCH").setValue(ServerValue.increment(1));
                    if (T_GN_PTC>0){

                        databaseReference.child("t_PTC").setValue(ServerValue.increment(-1));

                    }else {}

                    // PGRSBR JOB
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {


                            dialog.dismiss();


                        }
                    },4000);

                    // TM JOB
                    CountDownTimer countDownTimer = new CountDownTimer(40000, 1000) {

                        public void onTick(long millisUntilFinished) {
                            binding.Timer.setText("" + millisUntilFinished / 1000);

                            // logic to set the EditText could go here
                        }

                        public void onFinish() {
                            binding.Timer.setText("Finish!");
                            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getCurrentUser().getUid());
//                            databaseReference.child("TOTAL_PTC_WATCH").setValue(ServerValue.increment(1));
                            databaseReference.child("coins").setValue(ServerValue.increment(10));
                            binding.CLOSEAD.setVisibility(View.VISIBLE);
                        }

                    }.start();
                }else if (RND == 7){

                    binding.WebView.getSettings().setJavaScriptEnabled(true);
                    binding.WebView.loadUrl(U77);
                    binding.WebView.setWebViewClient(new WebViewClient());
                    binding.WebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
                    binding.WebView.getSettings().setJavaScriptEnabled(true);
                    databaseReference = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getCurrentUser().getUid());
                    databaseReference.child("ptc_WATCH").setValue(ServerValue.increment(1));
                    if (T_GN_PTC>0){

                        databaseReference.child("t_PTC").setValue(ServerValue.increment(-1));

                    }else {}


                    // PGRSBR JOB
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {


                            dialog.dismiss();


                        }
                    },4000);

                    // TM JOB
                    CountDownTimer countDownTimer = new CountDownTimer(40000, 1000) {

                        public void onTick(long millisUntilFinished) {
                            binding.Timer.setText("" + millisUntilFinished / 1000);

                            // logic to set the EditText could go here
                        }

                        public void onFinish() {
                            binding.Timer.setText("Finish!");
                            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getCurrentUser().getUid());
//                            databaseReference.child("TOTAL_PTC_WATCH").setValue(ServerValue.increment(1));
                            databaseReference.child("coins").setValue(ServerValue.increment(10));
                            binding.CLOSEAD.setVisibility(View.VISIBLE);
                        }

                    }.start();
                }else if (RND == 8){

                    binding.WebView.getSettings().setJavaScriptEnabled(true);
                    binding.WebView.loadUrl(U88);
                    binding.WebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
                    binding.WebView.setWebViewClient(new WebViewClient());
                    binding.WebView.getSettings().setJavaScriptEnabled(true);
                    databaseReference = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getCurrentUser().getUid());
                    databaseReference.child("ptc_WATCH").setValue(ServerValue.increment(1));
                    if (T_GN_PTC>0){

                        databaseReference.child("t_PTC").setValue(ServerValue.increment(-1));

                    }else {}

                    // PGRSBR JOB
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {


                            dialog.dismiss();


                        }
                    },4000);

                    // TM JOB
                    CountDownTimer countDownTimer = new CountDownTimer(40000, 1000) {

                        public void onTick(long millisUntilFinished) {
                            binding.Timer.setText("" + millisUntilFinished / 1000);

                            // logic to set the EditText could go here
                        }

                        public void onFinish() {
                            binding.Timer.setText("Finish!");
                            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getCurrentUser().getUid());
//                            databaseReference.child("TOTAL_PTC_WATCH").setValue(ServerValue.increment(1));
                            databaseReference.child("coins").setValue(ServerValue.increment(30));
                            binding.CLOSEAD.setVisibility(View.VISIBLE);
                        }

                    }.start();
                }else if (RND == 9){

                    binding.WebView.getSettings().setJavaScriptEnabled(true);
                    binding.WebView.loadUrl(U99);
                    binding.WebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
                    binding.WebView.getSettings().setJavaScriptEnabled(true);
                    binding.WebView.setWebViewClient(new WebViewClient());
                    databaseReference = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getCurrentUser().getUid());
                    databaseReference.child("ptc_WATCH").setValue(ServerValue.increment(1));
                    if (T_GN_PTC>0){

                        databaseReference.child("t_PTC").setValue(ServerValue.increment(-1));

                    }else {}

                    // PGRSBR JOB
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {


                            dialog.dismiss();


                        }
                    },4000);

                    // TM JOB
                    CountDownTimer countDownTimer = new CountDownTimer(40000, 1000) {

                        public void onTick(long millisUntilFinished) {
                            binding.Timer.setText("" + millisUntilFinished / 1000);

                            // logic to set the EditText could go here
                        }

                        public void onFinish() {
                            binding.Timer.setText("Finish!");
                            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getCurrentUser().getUid());
//                            databaseReference.child("TOTAL_PTC_WATCH").setValue(ServerValue.increment(1));
                            databaseReference.child("coins").setValue(ServerValue.increment(10));
                            binding.CLOSEAD.setVisibility(View.VISIBLE);
                        }

                    }.start();
                }else if (RND == 10){

                    // PGRSBR JOB
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {


                            dialog.dismiss();


                        }
                    },4000);

                    binding.WebView.getSettings().setJavaScriptEnabled(true);
                    binding.WebView.loadUrl(U100);
                    binding.WebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
                    binding.WebView.getSettings().setJavaScriptEnabled(true);
                    binding.WebView.setWebViewClient(new WebViewClient());
                    databaseReference = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getCurrentUser().getUid());
                    databaseReference.child("ptc_WATCH").setValue(ServerValue.increment(1));
                    if (T_GN_PTC>0){

                        databaseReference.child("t_PTC").setValue(ServerValue.increment(-1));

                    }else {}
                    // TM JOB
                    CountDownTimer countDownTimer = new CountDownTimer(40000, 1000) {

                        public void onTick(long millisUntilFinished) {
                            binding.Timer.setText("" + millisUntilFinished / 1000);

                            // logic to set the EditText could go here
                        }

                        public void onFinish() {
                            binding.Timer.setText("Finish!");
                            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getCurrentUser().getUid());
//                            databaseReference.child("TOTAL_PTC_WATCH").setValue(ServerValue.increment(1));
                            databaseReference.child("coins").setValue(ServerValue.increment(10));
                            binding.CLOSEAD.setVisibility(View.VISIBLE);
                        }

                    }.start();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });







        binding.CLOSEAD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.MainReplacer, new CategoryFrag());
                transaction.commit();
            }
        });

        return binding.getRoot();


    }


}