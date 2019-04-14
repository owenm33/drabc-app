package com.example.drabc.activities;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.drabc.R;
import com.example.drabc.databinding.ActivityMainBinding;
import com.example.drabc.fragments.FullScreenDialog;

// test
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Button danger, response, airways, breathing, compressions;
    PopupWindow popUp;
    RelativeLayout popupLayout, mainLayout;
    TextView tv;
    RelativeLayout.LayoutParams params;
    boolean click = true;
    FullScreenDialog dialog = new FullScreenDialog();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        popUp = new PopupWindow(this);
        popupLayout = new RelativeLayout(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
//        setContentView(R.layout.activity_main);
        danger = binding.dangerButton;
        response = binding.responseButton;
        airways = binding.airwaysButton;
        breathing = binding.breathingButton;
        compressions = binding.compressionsButton;
    }
//
//    private void onDanger() {
//        danger.setText("DANGER TEST!");
//        dialog.show(ft, FullScreenDialog.TAG);
//    }

    private void onResponse() {
        response.setText("RESPONSE TEST!");
        onClick();
    }

    private void onAirways() {
        airways.setText("AIRWAYS TEST!");
        onClick();
    }

    private void onBreathing() {
        breathing.setText("BREATHING TEST!");
        onClick();
    }

    private void onCompressions() {
        compressions.setText("COMPRESSIONS TEST!");
        onClick();
    }

    private void onClick() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        dialog.show();
    }

//    private showPopup(final Activity, Point p) {
//        // Inflate the popup_layout.xml
//        LinearLayout viewGroup = (LinearLayout) context.findViewById(R.id.info_popup);
//        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemServicie(Context.LAYOUT_INFLATER_SERVICE);
//    }
}
