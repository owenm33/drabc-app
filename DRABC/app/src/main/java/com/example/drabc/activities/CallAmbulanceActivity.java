package com.example.drabc.activities;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.drabc.R;
import com.example.drabc.databinding.ActivityCallAmbulanceBinding;

import static com.example.drabc.classes.Constants.MY_PERMISSIONS_REQUEST_CALL_PHONE;

public class CallAmbulanceActivity extends AppCompatActivity {

    private ActivityCallAmbulanceBinding binding;
    Intent callIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        callIntent = new Intent(Intent.ACTION_CALL).setData(Uri.parse("tel:0415155516"));
        binding = DataBindingUtil.setContentView(this, R.layout.activity_call_ambulance);
    }

    public void onCall(View v) {
        if (ContextCompat.checkSelfPermission(CallAmbulanceActivity.this, Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(CallAmbulanceActivity.this, new String[]{Manifest.permission.CALL_PHONE},
                    MY_PERMISSIONS_REQUEST_CALL_PHONE);
        }
    }

    public void onRefused(View v) {
        finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CALL_PHONE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startActivity(callIntent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Permission denied; please call emergency services ASAP", Toast.LENGTH_LONG);
                }
            }
        }
    }

}

