package com.example.myapplication;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;
import java.util.Map;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

//    private String[] localDataSet;
    private LinkedList<Map<String, String>> localDataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        View myView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            myView = itemView;
        }

        public void setType(String type) {
            TextView mType = myView.findViewById(R.id.type);
            mType.setText(type);
        }

        public void setNote(String note) {
            TextView mNote = myView.findViewById(R.id.note);
            if (mNote == null) System.out.println("Its null");
            else System.out.println("its not null...");
            mNote.setText(note);
        }

        public void setPrice(String price) {
            TextView mPrice = myView.findViewById(R.id.amount);
            mPrice.setText(price);
        }

        public void setImage(String imageString) {
            ImageView mImage = myView.findViewById(R.id.image);
            mImage.setImageResource(itemView.getContext().getResources().getIdentifier(imageString, "drawable", itemView.getContext().getPackageName()));
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     */
    public CustomAdapter(LinkedList<Map<String,String>> dataSet) {
        localDataSet = dataSet;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_data, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
