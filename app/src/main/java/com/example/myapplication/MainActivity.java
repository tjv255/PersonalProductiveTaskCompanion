package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.text.DecimalFormat;

public class MainActivity  extends AppCompatActivity
{
    private Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        model = (Model) getIntent().getSerializableExtra("model");
        if (model == null)
            model = new Model();

        equipItems();

        UpdateStats();
    }

    private void UpdateStats() {
        DecimalFormat df = new DecimalFormat("0.0");

        TextView currencyText = (TextView) findViewById(R.id.currencyText);
        currencyText.setText("Currency:\n$"+model.getCurrency());

        TextView fitnessLevel = (TextView) findViewById(R.id.fitness_level);
        fitnessLevel.setText("Level "+model.getFitnessLevel());
        TextView fitnessProgress = (TextView) findViewById(R.id.fitness_progress);
        fitnessProgress.setText(""+df.format(model.getFitnessProgressPercentage())+"% of Goal");
        ProgressBar fitnessProgressBar = (ProgressBar) findViewById(R.id.fitness_progress_bar);
        fitnessProgressBar.setProgress((int) model.getFitnessProgressPercentage());

        TextView informativityLevel = (TextView) findViewById(R.id.informativity_level);
        informativityLevel.setText("Level "+model.getInformativityLevel());
        TextView informativityProgress = (TextView) findViewById(R.id.informativity_progress);
        informativityProgress.setText(""+df.format(model.getInformativityProgressPercentage())+"% of Goal");
        ProgressBar informativityProgressBar = (ProgressBar) findViewById(R.id.informativity_progress_bar);
        informativityProgressBar.setProgress((int) model.getInformativityProgressPercentage());

        TextView productivityLevel = (TextView) findViewById(R.id.productivity_level);
        productivityLevel.setText("Level "+model.getProductivityLevel());
        TextView productivityProgress = (TextView) findViewById(R.id.productivity_progress);
        productivityProgress.setText(""+df.format(model.getProductivityProgressPercentage())+"% of Goal");
        ProgressBar productivityProgressBar = (ProgressBar) findViewById(R.id.productivity_progress_bar);
        productivityProgressBar.setProgress((int) model.getProductivityProgressPercentage());

        TextView restLevel = (TextView) findViewById(R.id.rest_level);
        restLevel.setText("Level "+model.getRestLevel());
        TextView restProgress = (TextView) findViewById(R.id.rest_progress);
        restProgress.setText(""+df.format(model.getRestProgressPercentage())+"% of Goal");
        ProgressBar restProgressBar = (ProgressBar) findViewById(R.id.rest_progress_bar);
        restProgressBar.setProgress((int) model.getRestProgressPercentage());
    }

    public void equipItems() {
        if(model.equippedItems.get("Headgear") != null) {
            ImageView hat = findViewById(R.id.avatar_hat);
            hat.setAlpha(1.0f);
        }
        if(model.equippedItems.get("Glasses") != null) {
            ImageView glasses = findViewById(R.id.avatar_glasses);
            glasses.setAlpha(1.0f);
        }
        if(model.equippedItems.get("Upper Body") != null) {
            ImageView shirt = findViewById(R.id.avatar_shirt);
            shirt.setAlpha(1.0f);
        }
        if(model.equippedItems.get("Legwear") != null) {
            ImageView legs = findViewById(R.id.avatar_leg_ware);
            legs.setAlpha(1.0f);
        }
        if(model.equippedItems.get("Footwear") != null) {
            ImageView leftFoot = findViewById(R.id.avatar_left_foot);
            ImageView rightFoot = findViewById(R.id.avatar_right_foot);
            leftFoot.setAlpha(1.0f);
            rightFoot.setAlpha(1.0f);
        }


    }

    public void startTaskTapped(View view) {
        Intent intent = new Intent(this, SelectTaskActivity.class);
        intent.putExtra("model", (Serializable) model);
        startActivity(intent);
    }

    public void shopTapped(View view) {
        Intent intent = new Intent(this, ShopActivity.class);
        intent.putExtra("model", (Serializable) model);
        startActivity(intent);
    }

    public void setGoalsTapped(View view) {
        Intent intent = new Intent(this, GoalSettingActivity.class);
        intent.putExtra("model", (Serializable) model);
        startActivity(intent);
    }

    private String formatTime(int seconds, int minutes, int hours)
    {
        return String.format("%02d", hours) + " : " + String.format("%02d", minutes) + " : " + String.format("%02d", seconds);
    }
}