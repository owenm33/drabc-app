package com.haymorg.drabc.activities;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.haymorg.drabc.R;
import com.haymorg.drabc.databinding.ActivityResponseBinding;
import com.haymorg.drabc.classes.Constants;

public class ResponseActivity extends AppCompatActivity {

    private SharedPreferences userDetails;
    Intent callIntent, airwaysIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        callIntent = new Intent(Intent.ACTION_CALL).setData(Uri.parse("tel:0415155516"));
        airwaysIntent = new Intent(ResponseActivity.this, AirwaysActivity.class);
        ActivityResponseBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_response);
        binding.setActivity(this);
        userDetails = getSharedPreferences("USER", MODE_PRIVATE);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    public void onAnswer(boolean answer) {
        SharedPreferences.Editor editor = userDetails.edit();
        editor.putBoolean("R", answer);
        editor.apply();
        if (answer) {
            showCallAlert("Consider calling 000", "Ask casualty if they would like an ambulance. What did they say?", "Yes", "No");
        } else {
            showCallAlert("Please call 000", "Calling an ambulance is strongly recommended", "OK", "We'll be fine");
        }
    }

    public void showCallAlert(String title, String message, String pos, String neg) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setNegativeButton(neg, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (!userDetails.getBoolean("R", false)) {
                            Toast.makeText(getApplicationContext(), getResources().getString(R.string.call_emergency_warning), Toast.LENGTH_LONG).show();
                        } else {
                            startActivity(airwaysIntent);
                            finish();
                        }
                    }
                })
                .setPositiveButton(pos, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        callEmergency();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public void callEmergency() {
        if (ContextCompat.checkSelfPermission(ResponseActivity.this, Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ResponseActivity.this, new String[]{Manifest.permission.CALL_PHONE},
                    Constants.MY_PERMISSIONS_REQUEST_CALL_PHONE);
        } else {
            Intent[] intents = {airwaysIntent, callIntent};
            startActivities(intents);
            finish();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Constants.MY_PERMISSIONS_REQUEST_CALL_PHONE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    callEmergency();
                } else {
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.call_emergency_warning), Toast.LENGTH_LONG).show();
                }
                break;
            }
        }
    }

    public void onNext(View v) {
        startActivity(new Intent(ResponseActivity.this, AirwaysActivity.class));
        finish();
    }

    public void onPrevious(View v) {
        startActivity(new Intent(ResponseActivity.this, DangerActivity.class));
        finish();
    }

    public void onClose(View v) {
        finish();
    }

}
