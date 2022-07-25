package com.example.myapplication;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

public class StatsView extends LinearLayout implements ModelListener {

    public StatsView(Context aContext) {
        super(aContext);

    }

    @Override
    public void modelChanged() {

    }
}
