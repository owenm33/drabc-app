package com.example.drabc.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.example.drabc.R;
import com.example.drabc.databinding.ActivityBreathingBinding;

public class BreathingActivity extends AppCompatActivity {

    private SharedPreferences userDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityBreathingBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_breathing);
        binding.setActivity(this);
        userDetails = getSharedPreferences("USER", MODE_PRIVATE);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    public void onAnswer(boolean answer) {
        SharedPreferences.Editor editor = userDetails.edit();
        editor.putBoolean("B", answer);
        editor.apply();
        if (answer) {
            startActivity(new Intent(BreathingActivity.this, DescriptionActivity.class));
        } else {
            startActivity(new Intent(BreathingActivity.this, CompressionsActivity.class));
        }
        finish();
    }

    public void onNext(View v) {
        startActivity(new Intent(BreathingActivity.this, CompressionsActivity.class));
        finish();
    }

    public void onPrevious(View v) {
        startActivity(new Intent(BreathingActivity.this, AirwaysActivity.class));
        finish();
    }

    public void onClose(View v) {
        finish();
    }
}
