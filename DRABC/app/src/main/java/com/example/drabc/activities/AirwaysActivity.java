package com.example.drabc.activities;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;

import com.example.drabc.R;
import com.example.drabc.databinding.ActivityAirwaysBinding;
import com.example.drabc.databinding.ActivityResponseBinding;

public class AirwaysActivity extends AppCompatActivity {

    private ActivityResponseBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_airways);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }
}
