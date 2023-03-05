package com.nkdevelopers.niktoearningapp.Adapters;

import static java.security.AccessController.getContext;

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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nkdevelopers.niktoearningapp.ModelClasses.HistroyModels;
import com.nkdevelopers.niktoearningapp.R;
import com.squareup.picasso.Picasso;

public class HistroyAdapters extends FirebaseRecyclerAdapter<HistroyModels,HistroyAdapters.ViewHolder> {


    String UPIC;
    Context context;
    public HistroyAdapters(@NonNull FirebaseRecyclerOptions<HistroyModels> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull HistroyModels model) {


        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getCurrentUser().getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                UPIC = snapshot.child("upic").getValue(String.class);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        holder.WithBalance.setText(model.getTOTAL_MONEY());
        holder.Dates.setText(model.getDATE());
        Glide.with(holder.UPIC.getContext()).load(R.drawable.coins).circleCrop().into(holder.UPIC);




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



