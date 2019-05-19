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

import java.io.IOException;

import pl.droidsonroids.gif.GifDrawable;

public class CompressionsActivity extends AppCompatActivity {

    private ActivityCompressionsBinding binding;
    private MediaPlayer cprSoundMediaPlayer;
    private GifDrawable cprGif;
    private TextView cprText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_compressions);
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
//        final Button playPlay_Cpr = (Button) this.findViewById(R.id.play_cpr);
    }

    public void onSteps(View v) {
        binding.compressionsBody.setVisibility(View.VISIBLE);
        cprGif.setVisible(false, true);
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

        if (cprGif.isPlaying()) {
            cprGif.stop();
            cprGif.setVisible(false, true);
            cprText.setVisibility(View.VISIBLE);
        }
    }

    public void onDemo(View v) {
        if (cprGif.isPlaying()) {
            cprGif.stop();
            cprGif.setVisible(false, true);
        } else {
            cprGif.setVisible(true, false);
            cprGif.start();
            cprText.setVisibility(View.GONE);
        }
    }

    public void onClose(View v) {
        finish();
    }

    public void onPrevious(View v) {
        startActivity(new Intent(CompressionsActivity.this, BreathingActivity.class));
        finish();
    }

}
