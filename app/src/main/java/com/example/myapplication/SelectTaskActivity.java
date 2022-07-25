package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;


public class SelectTaskActivity extends AppCompatActivity {

//    private Button chooseExerciseButton;
//    private Button goBackButton;
    private Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_task);

        // Get model from previous activity
        model = (Model) getIntent().getSerializableExtra("model");
        System.out.println(model.getFitnessLevel());

//        chooseExerciseButton = (Button) findViewById(R.id.chooseExerciseButton);
//        goBackButton = (Button) findViewById(R.id.goBackButton);
    }


    public void chooseExerciseTapped (View view)
    {
        Intent intent = new Intent(this, TaskTimerActivity.class);
        String taskName = "Exercise";
        intent.putExtra("task", taskName);
        intent.putExtra("model", (Serializable) model);
        // intent.putExtra("model", (Serializable) model);
        startActivity(intent);
    }
    public void chooseStudyTapped (View view)
    {
        Intent intent = new Intent(this, TaskTimerActivity.class);
        String taskName = "Study";
        intent.putExtra("task", taskName);
        intent.putExtra("model", (Serializable) model);
        startActivity(intent);
    }
    public void chooseProductivityTapped (View view)
    {
        Intent intent = new Intent(this, TaskTimerActivity.class);
        String taskName = "Productive";
        intent.putExtra("task", taskName);
        intent.putExtra("model", (Serializable) model);
        startActivity(intent);
    }
    public void chooseRestTapped (View view)
    {
        Intent intent = new Intent(this, TaskTimerActivity.class);
        String taskName = "Rest";
        intent.putExtra("task", taskName);
        intent.putExtra("model", (Serializable) model);
        startActivity(intent);
    }

    public void goBackTapped(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("model", (Serializable) model);
        startActivity(intent);
    }
}