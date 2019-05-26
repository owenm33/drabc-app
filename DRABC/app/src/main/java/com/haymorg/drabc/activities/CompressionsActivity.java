package com.haymorg.drabc.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import com.haymorg.drabc.R;
import com.haymorg.drabc.databinding.ActivityCompressionsBinding;

import pl.droidsonroids.gif.GifDrawable;

public class CompressionsActivity extends AppCompatActivity {

    private ActivityCompressionsBinding binding;
//    private MediaPlayer cprSoundMediaPlayer;
    private GifDrawable cprGif;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_compressions);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        cprGif = binding.cprGif;

    }

    @Override
    protected void onStart() {
        super.onStart();
        cprGif.startAnimation();
    }

    @Override
    protected void onResume() {
        super.onResume();
        cprGif.startAnimation();
    }
    public void onPlay(View v) {

    }
    public void onDemo(View v) {
        binding.cprgif.setVisibility(View.VISIBLE);
        binding.compressionsBody.setVisibility(View.INVISIBLE);
    }
    public void onStop(View v) {

    }
    public void onClose(View v) {
        finish();
    }

    public void onPrevious(View v) {
        startActivity(new Intent(CompressionsActivity.this, BreathingActivity.class));
        finish();
    }
}
