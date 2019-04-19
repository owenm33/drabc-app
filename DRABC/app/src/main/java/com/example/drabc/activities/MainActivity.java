package com.example.drabc.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.drabc.R;
import com.example.drabc.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Button danger, response, airways, breathing, compressions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        danger = binding.dangerButton;
        response = binding.responseButton;
        airways = binding.airwaysButton;
        breathing = binding.breathingButton;
        compressions = binding.compressionsButton;
    }

    public void onDanger(View v) {
        danger.setText("DANGER TEST!");
        startActivity(new Intent(MainActivity.this, DangerActivity.class));
    }

    public void onResponse(View v) {
        response.setText("RESPONSE TEST!");
        startActivity(new Intent(MainActivity.this, ResponseActivity.class));
    }

    public void onAirways(View v) {
        airways.setText("AIRWAYS TEST!");
        startActivity(new Intent(MainActivity.this, AirwaysActivity.class));
    }

    public void onBreathing(View v) {
        breathing.setText("BREATHING TEST!");
        startActivity(new Intent(MainActivity.this, BreathingActivity.class));
    }

    public void onCompressions(View v) {
        compressions.setText("COMPRESSIONS TEST!");
        startActivity(new Intent(MainActivity.this, CompressionsActivity.class));
    }

}
