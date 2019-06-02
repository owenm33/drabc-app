package com.haymorg.drabc.activities;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.haymorg.drabc.R;
import com.haymorg.drabc.classes.CustomDialogFragment;
import com.haymorg.drabc.databinding.ActivityAirwaysBinding;

import static com.haymorg.drabc.classes.Constants.MY_PERMISSIONS_REQUEST_CALL_PHONE;
import static com.haymorg.drabc.classes.Constants.MY_PERMISSIONS_REQUEST_CAMERA;

public class AirwaysActivity extends AppCompatActivity implements CustomDialogFragment.CustomDialogCloseListener {

    private SharedPreferences userDetails;
    private boolean flashLightStatus = true;
    CustomDialogFragment customDialog;
    Intent airwaysIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAirwaysBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_airways);
        binding.setActivity(this);
        userDetails = getSharedPreferences("USER", MODE_PRIVATE);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        airwaysIntent = getIntent();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void onAnswer(boolean answer) {
        SharedPreferences.Editor editor = userDetails.edit();
        editor.putBoolean("A", answer);
        editor.apply();
        if (answer) {
            startActivity(new Intent(AirwaysActivity.this, BreathingActivity.class));
            finish();
        } else {
            showCustomDialog();
        }
    }

    public void onTorch(View v) {
        if (ContextCompat.checkSelfPermission(AirwaysActivity.this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(AirwaysActivity.this, new String[]{Manifest.permission.CAMERA},
                    MY_PERMISSIONS_REQUEST_CALL_PHONE);
        } else {
            flashLight(flashLightStatus);
        }
    }

    public void flashLight(boolean turnOn) {
        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            cameraManager.setTorchMode(cameraManager.getCameraIdList()[0], turnOn);
            flashLightStatus = !flashLightStatus;
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CAMERA: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    flashLight(true);
                } else {
                    Toast.makeText(getApplicationContext(), "Could not turn on flashlight (permission was denied)", Toast.LENGTH_LONG).show();
                }
                break;
            }
        }
    }

    private void showCustomDialog() {
        FragmentManager fm = getSupportFragmentManager();
        View dView = this.getWindow().getDecorView();
        customDialog =
                CustomDialogFragment.newInstance(dView.getWidth(), dView.getHeight(), "Clear airways",
                        getResources().getString(R.string.airways_fragment_body), "airways");
        customDialog.show(fm, "fragment_custom_dialog");
    }

    public void onNext(View v) {
        startActivity(new Intent(AirwaysActivity.this, BreathingActivity.class));
        finish();
    }

    public void onPrevious(View v) {
        startActivity(new Intent(AirwaysActivity.this, ResponseActivity.class));
        finish();
    }

    public void onCloseDialog() {
        startActivity(airwaysIntent);
        customDialog.dismiss();
        finish();
    }

    public void onClose(View v) {
        finish();
    }

}
