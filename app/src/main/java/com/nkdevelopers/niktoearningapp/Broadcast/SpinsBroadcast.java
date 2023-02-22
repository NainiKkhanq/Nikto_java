package com.nkdevelopers.niktoearningapp.Broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.nkdevelopers.niktoearningapp.R;

public class SpinsBroadcast extends BroadcastReceiver {

    DatabaseReference databaseReference;


    @Override
    public void onReceive(Context context, Intent intent) {




            Toast.makeText(context, "Reminder End", Toast.LENGTH_SHORT).show();
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "notifyme1")
                    .setSmallIcon(R.drawable.money)
                    .setContentTitle("Rminder")
                    .setContentText("Come back and earn more Money")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);


            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);

            notificationManagerCompat.notify(201,builder.build());

            databaseReference = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getUid());



        SharedPreferences sharedPreferences = context.getSharedPreferences("Spins",Context.MODE_PRIVATE);

        SharedPreferences.Editor myEdit = sharedPreferences.edit();

// Storing the key and its value as the data fetched from edittext
        myEdit.putInt("SPINS", 20);
        myEdit.commit();


        SharedPreferences sh = context.getSharedPreferences("Spins", Context.MODE_PRIVATE);
        int a = sh.getInt("SPINS", 0);

        if (a ==20){

            databaseReference.child("spins").setValue(ServerValue.increment(20));

        }


        }

}
