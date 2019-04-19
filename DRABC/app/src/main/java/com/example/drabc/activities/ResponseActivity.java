package com.example.drabc.activities;

import android.content.Intent;
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
    private ImageButton close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_response);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        close = binding.closeResponse;
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
}
