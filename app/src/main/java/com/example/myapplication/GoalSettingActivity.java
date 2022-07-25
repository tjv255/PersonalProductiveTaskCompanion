package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

public class GoalSettingActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner fitnessSpinnerHours;
    Spinner fitnessSpinnerMinutes;
    Spinner studySpinnerHours;
    Spinner studySpinnerMinutes;
    Spinner productivitySpinnerHours;
    Spinner productivitySpinnerMinutes;
    Spinner restSpinnerHours;
    Spinner restSpinnerMinutes;

    Model model;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal_setting);

        model = (Model) getIntent().getSerializableExtra("model");
        if (model == null)
            model = new Model();

        fitnessSpinnerHours = findViewById(R.id.fitness_spinner_hours);
        ArrayAdapter<CharSequence> hoursAdapter = ArrayAdapter.createFromResource(this, R.array.hours, android.R.layout.simple_spinner_item);
        hoursAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fitnessSpinnerHours.setAdapter(hoursAdapter);
        fitnessSpinnerHours.setOnItemSelectedListener(this);

        fitnessSpinnerMinutes = findViewById(R.id.fitness_spinner_minutes);
        ArrayAdapter<CharSequence> minutesAdapter = ArrayAdapter.createFromResource(this, R.array.minutes, android.R.layout.simple_spinner_item);
        minutesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fitnessSpinnerMinutes.setAdapter(minutesAdapter);
        fitnessSpinnerMinutes.setOnItemSelectedListener(this);

        studySpinnerHours = findViewById(R.id.study_spinner_hours);
        studySpinnerHours.setAdapter(hoursAdapter);
        studySpinnerHours.setOnItemSelectedListener(this);

        studySpinnerMinutes = findViewById(R.id.study_spinner_minutes);
        studySpinnerMinutes.setAdapter(minutesAdapter);
        studySpinnerMinutes.setOnItemSelectedListener(this);

        productivitySpinnerHours = findViewById(R.id.productivity_spinner_hours);
        productivitySpinnerHours.setAdapter(hoursAdapter);
        productivitySpinnerHours.setOnItemSelectedListener(this);

        productivitySpinnerMinutes = findViewById(R.id.productivity_spinner_minutes);
        productivitySpinnerMinutes.setAdapter(minutesAdapter);
        productivitySpinnerMinutes.setOnItemSelectedListener(this);

        restSpinnerHours = findViewById(R.id.rest_spinner_hours);
        restSpinnerHours.setAdapter(hoursAdapter);
        restSpinnerHours.setOnItemSelectedListener(this);

        restSpinnerMinutes = findViewById(R.id.rest_spinner_minutes);
        restSpinnerMinutes.setAdapter(minutesAdapter);
        restSpinnerMinutes.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
//        TextView textView = (TextView) view;
//        textView.setTextColor(0xFFFFFF);
        //Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void submitButtonTapped(View view) {
        System.out.println(getValue(restSpinnerHours.getSelectedItem().toString()));

        int fitnessGoalHours = Integer.parseInt(getValue(fitnessSpinnerHours.getSelectedItem().toString()));
        int fitnessGoalMinutes = Integer.parseInt(getValue(fitnessSpinnerMinutes.getSelectedItem().toString()));

        int studyGoalHours = Integer.parseInt(getValue(studySpinnerHours.getSelectedItem().toString()));
        int studyGoalMinutes = Integer.parseInt(getValue(studySpinnerMinutes.getSelectedItem().toString()));

        int productivityGoalHours = Integer.parseInt(getValue(productivitySpinnerHours.getSelectedItem().toString()));
        int productivityGoalMinutes = Integer.parseInt(getValue(productivitySpinnerMinutes.getSelectedItem().toString()));

        int restGoalHours = Integer.parseInt(getValue(restSpinnerHours.getSelectedItem().toString()));
        int restGoalMinutes = Integer.parseInt(getValue(restSpinnerMinutes.getSelectedItem().toString()));

        model.setFitnessGoal(3600*fitnessGoalHours + 60*fitnessGoalMinutes);
        model.setInformativityGoal(3600*studyGoalHours + 60*studyGoalMinutes);
        model.setProductivityGoal(3600*productivityGoalHours + 60*productivityGoalMinutes);
        model.setRestGoal(3600*restGoalHours + 60*restGoalMinutes);

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("model", (Serializable) model);
        startActivity(intent);
    }

    private String getValue(String string) {
        String[] tokens = string.split(" ");
        return tokens[0];
    }
}