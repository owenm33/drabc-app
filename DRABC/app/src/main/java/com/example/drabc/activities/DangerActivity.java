package com.example.drabc.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import com.example.drabc.R;
import com.example.drabc.databinding.ActivityDangerBinding;

public class DangerActivity extends AppCompatActivity {

    private ActivityDangerBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_danger);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    public void onClose(View v) {
        finish();
    }

    public void onNext(View v) {
        startActivity(new Intent(DangerActivity.this, ResponseActivity.class));
        finish();
    }

    public void onDanger(View v) {
        // respond to danger being present
    }

    public void onNoDanger(View v) {
        // respond to no danger being present
    }
}
