package com.haymorg.drabc.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import com.haymorg.drabc.R;
import com.haymorg.drabc.databinding.ActivityCompressionsBinding;

public class CompressionsActivity extends AppCompatActivity {

    private ActivityCompressionsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_compressions);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    public void onClose(View v) {
        finish();
    }

    public void onPrevious(View v) {
        startActivity(new Intent(CompressionsActivity.this, BreathingActivity.class));
        finish();
    }
}
