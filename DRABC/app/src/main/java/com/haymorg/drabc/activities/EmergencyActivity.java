package com.haymorg.drabc.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.haymorg.drabc.R;
import com.haymorg.drabc.databinding.ActivityMainBinding;

public class EmergencyActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Button danger, response, airways, breathing, compressions;
    private SharedPreferences userDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userDetails = getSharedPreferences("USER", MODE_PRIVATE);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_emergency);

    }



    /* Should we have @Override protected void onNewIntent(Intent intent) here?
     ** And set android:launchMode="singleTop" for EmergencyActivity in the manifest?
     **/

    public void onDanger(View v) {
        startActivity(new Intent(EmergencyActivity.this, DangerActivity.class));
    }

    public void onResponse(View v) {
        startActivity(new Intent(EmergencyActivity.this, ResponseActivity.class));
    }

    public void onAirways(View v) {
        startActivity(new Intent(EmergencyActivity.this, AirwaysActivity.class));
    }

    public void onBreathing(View v) {
        startActivity(new Intent(EmergencyActivity.this, BreathingActivity.class));
    }

    public void onCompressions(View v) {
        startActivity(new Intent(EmergencyActivity.this, CompressionsActivity.class));
    }

}
