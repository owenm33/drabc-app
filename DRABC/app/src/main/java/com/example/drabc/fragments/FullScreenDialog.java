package com.example.drabc.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import com.example.drabc.R;

public class FullScreenDialog extends DialogFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        // Use Builder class for convenient dialog construction
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogStyle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.layout_full_screen_dialog, container, false);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
//        toolbar.setNavigationIcon(R.drawable.ic_close_white_24dp);
//        toolbar.setNavigationOnClickListener(view1 -> cancelUpload());
//        toolbar.setTitle("My Dialog");

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = (int) (ViewGroup.LayoutParams.MATCH_PARENT*0.8);
            int height = (int) (ViewGroup.LayoutParams.MATCH_PARENT*0.8);
            dialog.getWindow().setLayout(width, height);
        }
    }
}
