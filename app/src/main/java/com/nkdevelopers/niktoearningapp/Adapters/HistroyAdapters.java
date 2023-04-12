package com.nkdevelopers.niktoearningapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nkdevelopers.niktoearningapp.ModelClasses.HMD;
import com.nkdevelopers.niktoearningapp.R;

public class HistroyAdapters extends FirebaseRecyclerAdapter<HMD,HistroyAdapters.ViewHolder> {


    String UPIC;
    Context context;
    public HistroyAdapters(@NonNull FirebaseRecyclerOptions<HMD> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull HMD model) {


        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("MY_USERS").child(FirebaseAuth.getInstance().getCurrentUser().getUid());



        DatabaseReference databaseReferencePIC = FirebaseDatabase.getInstance().getReference("MY_USERS").child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString());

        try{
            GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(holder.UPIC.getContext());
            holder.WithBalance.setText(model.getTOTAL_MONEY());
            holder.Dates.setText(model.getDATE());
            Glide.with(holder.UPIC.getContext()).load(account.getPhotoUrl().toString()).circleCrop().into(holder.UPIC);
        }catch (Exception e){

        }







    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.moneylist,parent,false);

        return new HistroyAdapters.ViewHolder(view);

    }

    class ViewHolder extends  RecyclerView.ViewHolder{


        ImageView UPIC;
        TextView WithBalance,Dates;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            UPIC = itemView.findViewById(R.id.UPIC);
            WithBalance = itemView.findViewById(R.id.wthbalance);
            Dates = itemView.findViewById(R.id.dates);

        }

    }
}



