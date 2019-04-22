package com.example.drabc.activities;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.drabc.R;
import com.example.drabc.classes.CustomDialogFragment;
import com.example.drabc.databinding.ActivityDangerBinding;

import static com.example.drabc.classes.Constants.MY_PERMISSIONS_REQUEST_CALL_PHONE;

public class DangerActivity extends AppCompatActivity implements CustomDialogFragment.CustomDialogListener {

    private ActivityDangerBinding binding;
    private SharedPreferences userDetails;
    private SharedPreferences.Editor editor;
    String currentNumber;
    CustomDialogFragment customDialog;
    Intent callIntent, responseIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        callIntent = new Intent(Intent.ACTION_CALL);
        responseIntent = new Intent(DangerActivity.this, ResponseActivity.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_danger);
        userDetails = getSharedPreferences("USER", MODE_PRIVATE);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

    }

    public void onClose(View v) {
        finish();
    }

    public void onNext(View v) {
        startActivity(new Intent(DangerActivity.this, ResponseActivity.class));
        finish();
    }

    public void onCall(String number) {
        currentNumber = number;
        callEmergency();
        customDialog.dismiss();
    }

    public void callEmergency() {
        if (ContextCompat.checkSelfPermission(DangerActivity.this, Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(DangerActivity.this, new String[]{Manifest.permission.CALL_PHONE},
                    MY_PERMISSIONS_REQUEST_CALL_PHONE);
        } else {
            callIntent.setData(Uri.parse(currentNumber));
            Intent[] intents = {responseIntent, callIntent};
            startActivities(intents);
            finish();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CALL_PHONE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    callEmergency();
                } else {
                    Toast.makeText(getApplicationContext(), "Call permission was denied", Toast.LENGTH_LONG).show();
                }
                break;
            }
        }
    }

    public void onDanger(View v) {
        editor = userDetails.edit();
        editor.putBoolean("D", true);
        editor.apply();
//        startActivity(new Intent(DangerActivity.this, DangerNumbersActivity.class));
//        finish();
        showCustomDialog();

    }

    public void onNoDanger(View v) {
        editor = userDetails.edit();
        editor.putBoolean("D", false);
        editor.apply();
        // respond to no danger being present
        startActivity(new Intent(DangerActivity.this, ResponseActivity.class));
        finish();
    }

    private void showCustomDialog() {
        FragmentManager fm = getSupportFragmentManager();
        View dView = this.getWindow().getDecorView();
        customDialog =
                CustomDialogFragment.newInstance(dView.getWidth(), dView.getHeight(), "Useful numbers\n (click to call)",
                        getResources().getString(R.string.danger_fragment_body), true);
        customDialog.setNewCustomDialogListener(this);
        customDialog.show(fm, "fragment_custom_dialog");
    }
}
