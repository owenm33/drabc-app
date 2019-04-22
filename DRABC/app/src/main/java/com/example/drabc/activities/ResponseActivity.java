package com.example.drabc.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.drabc.R;
import com.example.drabc.databinding.ActivityResponseBinding;

public class ResponseActivity extends AppCompatActivity {

    private ActivityResponseBinding binding;
    private SharedPreferences userDetails;
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_response);
        userDetails = getSharedPreferences("USER", MODE_PRIVATE);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    public void onClose(View v) {
        finish();
    }

    public void onNext(View v) {
        startActivity(new Intent(ResponseActivity.this, AirwaysActivity.class));
        finish();
    }

    public void onPrevious(View v) {
        startActivity(new Intent(ResponseActivity.this, DangerActivity.class));
        finish();
    }

    public void onResponse(View v) {
        editor = userDetails.edit();
        editor.putBoolean("R", false);
        editor.apply();
        startActivity(new Intent(ResponseActivity.this, AirwaysActivity.class));

    }

    public void onNoResponse(View v) {
        editor = userDetails.edit();
        editor.putBoolean("R", true);
        editor.apply();
        startActivity(new Intent(ResponseActivity.this, CallAmbulanceActivity.class));


    }
}
