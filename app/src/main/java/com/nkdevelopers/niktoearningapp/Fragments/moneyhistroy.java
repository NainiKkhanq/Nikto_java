package com.nkdevelopers.niktoearningapp.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nkdevelopers.niktoearningapp.Adapters.HistroyAdapters;
import com.nkdevelopers.niktoearningapp.ModelClasses.HistroyModels;
import com.nkdevelopers.niktoearningapp.R;
import com.nkdevelopers.niktoearningapp.databinding.FragmentMoneyhistroyBinding;

public class moneyhistroy extends Fragment {

   FragmentMoneyhistroyBinding binding;
    HistroyAdapters histroyAdapters;
    HistroyModels histroyModels;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentMoneyhistroyBinding.inflate(getLayoutInflater(),container,false);

        getActivity().getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                        View.SYSTEM_UI_FLAG_FULLSCREEN);

      DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("PAYMENTINFO");
        FirebaseRecyclerOptions<HistroyModels>
                options= new FirebaseRecyclerOptions.Builder<HistroyModels>()
                .setQuery(databaseReference,HistroyModels.class).build();


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
}