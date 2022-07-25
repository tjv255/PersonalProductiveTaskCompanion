package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;

public class ShopActivity extends AppCompatActivity {

    //private Toolbar toolbar;
    private RecyclerView recyclerView;
    Model model;
    TextView currencyText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        //toolbar = findViewById(R.id.shop_toolbar);
       // setSupportActionBar(toolbar);
        //getSupportActionBar().setTitle("Shop");

        recyclerView=findViewById(R.id.recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        layoutManager.setStackFromEnd(true);
        layoutManager.setReverseLayout(true);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        model = (Model) getIntent().getSerializableExtra("model");

        currencyText = (TextView) findViewById(R.id.currency_text);
        currencyText.setText(""+model.getCurrency()+"$");
    }


    @Override
    protected void onStart() {
        super.onStart();
        Context context = this;

        CustomAdapter adapter = new CustomAdapter(model.getStoreItems()){
            @Override
            public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
                holder.setType(model.getStoreItems().get(position).get("name"));
                holder.setNote(model.getStoreItems().get(position).get("note"));
                holder.setPrice(model.getStoreItems().get(position).get("price"));
                holder.setImage(model.getStoreItems().get(position).get("image"));

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (model.getStoreItems().get(position).get("price").equals("purchased")) {
                            Toast.makeText(context, "The item is already purchased", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            int itemPrice = Integer.parseInt(Objects.requireNonNull(model.getStoreItems().get(position).get("price")));
                            if (itemPrice > model.getCurrency()) {
                                Toast.makeText(context, "Not enough currency", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            model.addCurrency(-itemPrice);
                            model.getStoreItems().get(position).put("price", "purchased");
                            model.equippedItems.put(model.getStoreItems().get(position).get("type"), model.getStoreItems().get(position).get("name"));
                            Intent intent = new Intent(context, MainActivity.class);
                            intent.putExtra("model", (Serializable) model);
                            intent.putExtra("itemToEquip", position);
                            startActivity(intent);
                        }

                    }
                });
            }
        };
        recyclerView.setAdapter(adapter);
    }

    public void leaveTapped(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("model", (Serializable) model);
        startActivity(intent);
    }

    public void getCurrency(View view) {
        model.addCurrency(3500);
        currencyText.setText(""+model.getCurrency()+"$");
    }
}