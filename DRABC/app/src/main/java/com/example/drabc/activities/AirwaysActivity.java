package com.example.drabc.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.example.drabc.R;
import com.example.drabc.databinding.ActivityAirwaysBinding;

public class AirwaysActivity extends AppCompatActivity {

    private ActivityAirwaysBinding binding;
    private SharedPreferences userDetails;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_airways);
        userDetails = getSharedPreferences("USER", MODE_PRIVATE);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    public void onClose(View v) {
        finish();
    }

    public void onNext(View v) {
        startActivity(new Intent(AirwaysActivity.this, BreathingActivity.class));
        finish();
    }

    public void onPrevious(View v) {
        startActivity(new Intent(AirwaysActivity.this, ResponseActivity.class));
        finish();
    }

    public void onAirways(View v) {
        editor = userDetails.edit();
        editor.putBoolean("A", true);
        editor.apply();
        startActivity(new Intent(AirwaysActivity.this, BreathingActivity.class));
        finish();
    }

    public void onNoAirways(View v) {
        editor = userDetails.edit();
        editor.putBoolean("A", false);
        editor.apply();
        startActivity(new Intent(AirwaysActivity.this, BreathingActivity.class));
        finish();
    }
}
