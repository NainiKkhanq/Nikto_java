package com.nkdevelopers.niktoearningapp.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.admanager.AdManagerAdRequest;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Context;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.nkdevelopers.niktoearningapp.Adapters.CategoryAdapter;
import com.nkdevelopers.niktoearningapp.MainActivity;
import com.nkdevelopers.niktoearningapp.ModelClasses.CategoryModel;
import com.nkdevelopers.niktoearningapp.R;
import com.nkdevelopers.niktoearningapp.databinding.FragmentCategoryBinding;

import java.util.ArrayList;

public class CategoryFrag extends Fragment {

    FirebaseUser user;
    DatabaseReference databasereference;
    FragmentCategoryBinding binding;
    FirebaseFirestore firebaseFirestore;
    Context context;
    String UID;
    String COINS;
    InterstitialAd mInterstitialAd;
    private String INTAD = "ca-app-pub-3940256099942544/1033173712";



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCategoryBinding.inflate(inflater, container, false);
        AdManagerAdRequest adRequest = new AdManagerAdRequest.Builder().build();


        // Admob Ad

        InterstitialAd.load(getContext(),INTAD, adRequest,

                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;


                        if (mInterstitialAd != null) {
                            InterstitialAd.load(getContext(),INTAD, adRequest,
                                    new InterstitialAdLoadCallback() {
                                        @Override
                                        public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                                            // The mInterstitialAd reference will be null until
                                            // an ad is loaded.
                                            mInterstitialAd = interstitialAd;

                                        }

                                        @Override
                                        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                                            // Handle the error

                                            mInterstitialAd = null;
                                        }
                                    });
                        } else {

                        }


                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error

                        mInterstitialAd = null;
                    }
                });



        firebaseFirestore = FirebaseFirestore.getInstance();

        // Getting Live User Coins From Firebase

        user = FirebaseAuth.getInstance().getCurrentUser();
        UID = user.getUid();


        databasereference = FirebaseDatabase.getInstance().getReference();

        databasereference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // We convert the upcoming coins int value from fierbase to String
                COINS = String.valueOf(snapshot.child(UID).child("coins").getValue(Integer.class));


                binding.UserCoins.setText(COINS);




                String Note = snapshot.child(UID).child("notice").getValue(String.class);
                binding.noticeforuser.setText(Note);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        // Category Loading From Database

        // Create ArrayList like Category Model Class and Initialized
        ArrayList<CategoryModel> CategoryArraylist = new ArrayList<>();
        CategoryAdapter adapter = new CategoryAdapter(context, CategoryArraylist);

        firebaseFirestore
                .collection("Categories")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {


                        CategoryArraylist.clear();

                        // By using value.getDocuments we get all documents from Categories Collection
                        for (DocumentSnapshot documentSnapshot : value.getDocuments()) {


                            CategoryModel categoryModel = documentSnapshot.toObject((CategoryModel.class));
                            categoryModel.setCategoryID(documentSnapshot.getId());
                            CategoryArraylist.add(categoryModel);

                        }

                        adapter.notifyDataSetChanged();


                    }
                });

        binding.CategoryRecycler.setLayoutManager(new GridLayoutManager(getContext(), 2));

        binding.CategoryRecycler.setAdapter(adapter);


        binding.WalletBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.MainReplacer, new WalletFrag());
                transaction.commit();

            }
        });

        binding.withdrawbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.MainReplacer, new WithdrawFrag());
                transaction.commit();


            }
        });


        binding.ProfileRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.MainReplacer, new profilefrag());
                transaction.commit();


            }
        });

        return binding.getRoot();
    }



}