package com.nkdevelopers.niktoearningapp.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.core.Context;
import com.nkdevelopers.niktoearningapp.ModelClasses.CategoryModel;
import com.nkdevelopers.niktoearningapp.R;
import com.nkdevelopers.niktoearningapp.WorkRoom;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewsHolder>{


    Context mcontext;




    // We create two objects Context and Arraylist
    ArrayList<CategoryModel> CategoryArraylist; // Arraylist Like CategoryModel


    public CategoryAdapter(Context mcontext, ArrayList<CategoryModel> categoryArraylist) {
        this.mcontext = mcontext;
        CategoryArraylist = categoryArraylist;
    }
    // Empty Constructor
    public CategoryAdapter() {
    }



    // Main methods
    @NonNull
    @Override
    public ViewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categorydesign,null);
        return new ViewsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewsHolder holder, int position) {


        // Creating Category Model Class Object
        CategoryModel categoryModel;
        // Now we Get the Category Array list position
        categoryModel = CategoryArraylist.get(position);

        holder.itemText.setText(categoryModel.getCategoryName());

        Picasso.get().load(categoryModel.getCategoryImage()).into(holder.itemImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(view.getContext(), WorkRoom.class);
                intent.putExtra("CategoryID" ,categoryModel.getCategoryID());
                view.getContext().startActivity(intent);





            }
        });




    }

    @Override
    public int getItemCount() {
        return CategoryArraylist.size();
    }

// Views Holder Class

    public class ViewsHolder extends RecyclerView.ViewHolder{

        ImageView itemImage;
        TextView itemText;

        public ViewsHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.itemImage);
            itemText = itemView.findViewById(R.id.itemText);


        }
    }
}