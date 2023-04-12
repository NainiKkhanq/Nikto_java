package com.nkdevelopers.niktoearningapp.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nkdevelopers.niktoearningapp.Adapters.HistroyAdapters;
import com.nkdevelopers.niktoearningapp.ModelClasses.HMD;
import com.nkdevelopers.niktoearningapp.R;
import com.nkdevelopers.niktoearningapp.databinding.FragmentMoneyhistroyBinding;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class moneyhistroy extends Fragment {

   FragmentMoneyhistroyBinding binding;
    HistroyAdapters histroyAdapters;
    HMD HMD;

    String COINK,UPIC;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentMoneyhistroyBinding.inflate(getLayoutInflater(),container,false);

        getActivity().getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                        View.SYSTEM_UI_FLAG_FULLSCREEN);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("MY_USERS").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                UPIC = snapshot.child("upic").getValue(String.class);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        HMD = new HMD(getDateTime(),COINK,UPIC);
      DatabaseReference databaseReference12 = FirebaseDatabase.getInstance().getReference().child("MY_USERS").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("PAYMENTINFO");
        FirebaseRecyclerOptions<HMD>
                options= new FirebaseRecyclerOptions.Builder<HMD>()
                .setQuery(databaseReference12, HMD.class).build();


        HistroyAdapters histroyAdapters = new HistroyAdapters (options);
        histroyAdapters.startListening();
        binding.RecyclerViews.setLayoutManager(new LinearLayoutManager(getContext()));

        binding.RecyclerViews.setAdapter(histroyAdapters);




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

    @Override
    public void onStart() {
        super.onStart();
//        histroyAdapters.startListening();
    }


    @Override
    public void onStop() {
        super.onStop();
//        mainAdapterForData.stopListening();
    }
    @Override
    public void onResume() {
        super.onResume();



    }
    @Override
    public void onDestroy() {

        super.onDestroy();
    }

    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}