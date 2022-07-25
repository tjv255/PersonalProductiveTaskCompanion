package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

public class TaskTimerActivity extends AppCompatActivity
{
    Model model;

    private TextView timerText;
    private Button startStopButton;
    private Button finishButton;
    private TextView currencyText;
    private ProgressBar progressBar;
    private TextView progressPercentage;
    private TextView timeLeftText;

    String taskName;
    float taskProgress;
    TextView taskLabel;
    Timer timer;
    TimerTask timerTask;
    int time;
    int goal;
    private DecimalFormat df;



    boolean timerStarted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_timer);

        model = (Model) getIntent().getSerializableExtra("model");
        System.out.println(model.getFitnessLevel());

        //AvatarView avatarView;
        taskLabel = (TextView) findViewById(R.id.taskName);
        timerText = (TextView) findViewById(R.id.timerText);
        startStopButton = (Button) findViewById(R.id.startStopButton);
        finishButton = (Button) findViewById(R.id.finishButton);
        currencyText = (TextView) findViewById(R.id.currencyText);

        currencyText.setText("Currency:\n$"+model.getCurrency());

        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        progressPercentage = (TextView) findViewById(R.id.progress_percent);
        timeLeftText = (TextView) findViewById(R.id.time_left);

//

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            taskName = null;
        }
        else {
            taskName = extras.getString("task");
            taskLabel.setText(taskName);
        }
        System.out.println(taskName);
        timer = new Timer();
        df = new DecimalFormat("0.0");

        // Function returns the progress we want to update from in the model
        float[] a = model.addProgress(taskName, 0);
        progressBar.setProgress((int)a[1]);
        progressPercentage.setText(""+df.format(a[1])+"% of Goal");
        timeLeftText.setText(getTimeLeftText());

        retrieveTaskTime();
    }

    private void retrieveTaskTime() {
        time = 0;
        if (model != null)  {
            System.out.println(model.getFitnessProgress());
            time = model.getFitnessProgress();
        }
        switch (taskName) {
            case "Exercise":
                time = model.getFitnessProgress();
                goal = model.getFitnessGoal();
                break;
            case "Study":
                time = model.getInformativityProgress();
                goal = model.getInformativityGoal();
                break;
            case "Productive":
                time = model.getProductivityProgress();
                goal = model.getProductivityGoal();
                break;
            case "Rest":
                time = model.getRestProgress();
                goal = model.getRestGoal();
                break;
        }
        timerText.setText(getTimerText());
        timeLeftText.setText(getTimeLeftText());
    }

    public void resetTimer(View view)
    {
        if (timerTask != null)
        {
            timerTask.cancel();
            setButtonUI("START", R.color.white);
            time = 0;
            timerStarted = false;
            timerText.setText(formatTime(0, 0, 0));
        }
    }

    public void startStopTapped(View view)
    {
        if (!timerStarted)
        {
            // Change status label and start timer
            timerStarted = true;
            setButtonUI("STOP", R.color.red);
            switch (taskName) {
                case "Exercise":
                    taskLabel.setText("Exercising");
                    taskProgress = model.getFitnessProgress();
                    break;
                case "Study":
                    taskLabel.setText("Studying");
                    taskProgress = model.getInformativityProgress();
                    break;
                case "Productive":
                    taskLabel.setText("Being Productive!");
                    taskProgress = model.getInformativityProgress();
                    break;
                case "Rest":
                    taskLabel.setText("Resting");
                    taskProgress = model.getRestProgress();
                    break;
            }
            finishButton.setEnabled(false);
            startTimer();
        }
        else
        {
            timerStarted = false;
            setButtonUI("START", R.color.white);
            taskLabel.setText(taskName);
            finishButton.setEnabled(true);
            timerTask.cancel();
        }
    }

    public void finishTapped(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("model", (Serializable) model);
        startActivity(intent);
    }

    private void setButtonUI(String start, int color)
    {
        startStopButton.setText(start);
        startStopButton.setTextColor(ContextCompat.getColor(this, color));
    }

    private void startTimer()
    {
        timerTask = new TimerTask()
        {
            @Override
            public void run()
            {
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        time++;

                        if(time%5 == 0){
                            model.addCurrency(1);
                            currencyText.setText("Currency:\n$"+model.getCurrency());
                        }


                        float[] a = model.addProgress(taskName, 1);
                        taskProgress+=1;



                        progressBar.setProgress((int)a[1]);
                        progressPercentage.setText(""+df.format(a[1])+"% of Goal");
                        timeLeftText.setText(getTimeLeftText());


                        // Once progress equals goal, add bonus currency as a reward
                        if (taskProgress == a[0]) {
                            model.addCurrency(50);
                            currencyText.setText("Currency:\n$"+model.getCurrency());
                        }

                        timerText.setText(getTimerText());
                    }
                });
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0 ,1000);
    }

    private String getTimeLeftText() {
        int rounded = (int) Math.max(Math.round(goal - time), 0);
        int minutes = ((rounded % 89400) % 3600) / 60;
        int hours = ((rounded % 89400) / 3600);

        return (String.format("%02d", hours) + " : " + String.format("%02d", minutes)+" left.");
    }

    private String getTimerText()
    {
        int rounded = (int) Math.round(time);
        int seconds = ((rounded % 89400) % 3600) % 60;
        int minutes = ((rounded % 89400) % 3600) / 60;
        int hours = ((rounded % 89400) / 3600);

        return formatTime(seconds, minutes, hours);
    }

    private String formatTime(int seconds, int minutes, int hours)
    {
        return String.format("%02d", hours) + " : " + String.format("%02d", minutes) + " : " + String.format("%02d", seconds);
    }
}