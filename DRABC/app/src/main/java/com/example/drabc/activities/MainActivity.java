package com.example.drabc.activities;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.drabc.R;
import com.example.drabc.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private Button danger, response, airways, breathing, compressions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        danger = binding.dangerButton;
        response = binding.responseButton;
        airways = binding.airwaysButton;
        breathing = binding.breathingButton;
        compressions = binding.compressionsButton;
    }

    private void onDanger() {
        danger.setText("DANGER TEST!");
    }

    private void onResponse() {
        response.setText("RESPONSE TEST!");
    }

    private void onAirways() {
        airways.setText("AIRWAYS TEST!");
    }

    private void onBreathing() {
        breathing.setText("BREATHING TEST!");
    }

    private void onCompressions() {
        compressions.setText("COMPRESSIONS TEST!");
    }
}
