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

public class CompressionsActivity extends AppCompatActivity {

    private ActivityCompressionsBinding binding;
    private MediaPlayer cprSoundMediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_compressions);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        cprSoundMediaPlayer = MediaPlayer.create(this, R.raw.play_cpr);
        cprSoundMediaPlayer.setLooping(true);
//        final Button playPlay_Cpr = (Button) this.findViewById(R.id.play_cpr);
    }

    public void onPlay(View v) {
        cprSoundMediaPlayer.start();
    }
    public void onStop(View v) {
        cprSoundMediaPlayer.stop();
    }
    public void onClose(View v) {
        finish();
    }

    public void onPrevious(View v) {
        startActivity(new Intent(CompressionsActivity.this, BreathingActivity.class));
        finish();
    }
}
