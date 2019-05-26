package com.haymorg.drabc.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.haymorg.drabc.R;
import com.haymorg.drabc.databinding.ActivityCompressionsBinding;
import com.haymorg.drabc.classes.Constants.cprDemoState;

import java.io.IOException;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class CompressionsActivity extends AppCompatActivity {

    private ActivityCompressionsBinding binding;
    private MediaPlayer cprSoundMediaPlayer;
    private GifDrawable cprGif;
    private GifImageView cprImage;
    private TextView cprText;
    private cprDemoState uiState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_compressions);
        uiState = cprDemoState.DEMO;
        cprImage = binding.cprGif;
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        try {
            cprGif = new GifDrawable(getResources(), R.drawable.cpr_gif);
        } catch (IOException e) {
            e.printStackTrace();
        }
        cprText = binding.compressionsBody;
        cprSoundMediaPlayer = MediaPlayer.create(this, R.raw.play_cpr);
        cprSoundMediaPlayer.setLooping(true);
        cprGif.start();
    }

    public void onSteps(View v) {
        cprGif.stop();
        cprGif.setVisible(false, true);
        cprImage.setVisibility(View.GONE);
        cprText.setVisibility(View.VISIBLE);
    }

    public void onTempo(View v) {
        if (cprSoundMediaPlayer.isPlaying()) {
            cprSoundMediaPlayer.pause();
        } else {
            cprSoundMediaPlayer.seekTo(0);
            cprSoundMediaPlayer.start();
        }
    }

    public void onStop(View v) {
        if (cprSoundMediaPlayer.isPlaying()) {
            cprSoundMediaPlayer.pause();
        }

        if (cprGif.isRunning()) {
            cprGif.stop();
            cprGif.setVisible(false, true);
            cprImage.setVisibility(View.GONE);
            cprText.setVisibility(View.VISIBLE);
        }
    }

    public void onDemo(View v) {
        cprGif.setVisible(true, true);
        cprGif.start();
        cprImage.setVisibility(View.VISIBLE);
        cprText.setVisibility(View.GONE);
    }

    public void onClose(View v) {
        if (cprSoundMediaPlayer.isPlaying()) {
            cprSoundMediaPlayer.pause();
        }
        finish();
    }

    public void onPrevious(View v) {
        if (cprSoundMediaPlayer.isPlaying()) {
            cprSoundMediaPlayer.pause();
        }
        startActivity(new Intent(CompressionsActivity.this, BreathingActivity.class));
        finish();
    }

}
