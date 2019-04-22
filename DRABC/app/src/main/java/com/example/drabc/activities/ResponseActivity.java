package com.example.drabc.activities;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.drabc.R;
import com.example.drabc.classes.PhoneDialogFragment;
import com.example.drabc.databinding.ActivityResponseBinding;

import static com.example.drabc.classes.Constants.MY_PERMISSIONS_REQUEST_CALL_PHONE;

public class ResponseActivity extends AppCompatActivity {

    private ActivityResponseBinding binding;
    private SharedPreferences userDetails;
    private SharedPreferences.Editor editor;
    Intent callIntent, airwaysIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        callIntent = new Intent(Intent.ACTION_CALL).setData(Uri.parse("tel:0415155516"));
        airwaysIntent = new Intent(ResponseActivity.this, AirwaysActivity.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_response);
        userDetails = getSharedPreferences("USER", MODE_PRIVATE);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
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

    public void onResponse(View v) {
        editor = userDetails.edit();
        editor.putBoolean("R", true);
        editor.apply();
        startActivity(new Intent(ResponseActivity.this, AirwaysActivity.class));
        finish();
    }

    public void onNoResponse(View v) {
        editor = userDetails.edit();
        editor.putBoolean("R", false);
        editor.apply();
        showCallAlert();
    }

    public void showCallAlert() {
        new AlertDialog.Builder(this)
                .setTitle("Call 000")
                .setMessage("Do you need an ambulance?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        callEmergency();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "WARNING: if the person is unresponsive, it is highly recommended that you contact emergency services", Toast.LENGTH_LONG);

                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CALL_PHONE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    callEmergency();
                } else {
                    Toast.makeText(getApplicationContext(), "WARNING: if the person is unresponsive, it is highly recommended that you contact emergency services", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    public void callEmergency() {
        if (ContextCompat.checkSelfPermission(ResponseActivity.this, Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ResponseActivity.this, new String[]{Manifest.permission.CALL_PHONE},
                    MY_PERMISSIONS_REQUEST_CALL_PHONE);
        } else {
            Intent[] intents = {airwaysIntent, callIntent};
            startActivities(intents);
            finish();
        }
    }

}
