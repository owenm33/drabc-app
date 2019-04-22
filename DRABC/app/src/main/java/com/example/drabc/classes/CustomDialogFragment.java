package com.example.drabc.classes;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.widget.TextViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.drabc.R;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static com.example.drabc.classes.Constants.PHONE_NUMBERS;

public class CustomDialogFragment extends DialogFragment {


    public interface CustomDialogListener {
        public void onCall(String number);
    }

    private static Window dWindow;
    private TextView mTitle, mBody;
    private CustomDialogListener mListener;

    public CustomDialogFragment() {

    }

    public static CustomDialogFragment newInstance(int width, int height, String title, String body, boolean clickableText) {
        CustomDialogFragment frag = new CustomDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("body", body);
        args.putInt("width", 7*width/8);
        args.putInt("height", 7*height/8);
        args.putBoolean("clickable", clickableText);
        frag.setArguments(args);
        return frag;
    }

    public void setNewCustomDialogListener(CustomDialogListener listener) {
        this.mListener = listener;
    }

//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        this.mListener = (CustomDialogListener) activity;
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_custom_dialog, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dWindow = getDialog().getWindow();
        mTitle = (TextView) getView().findViewById(R.id.dialog_title);
        mBody = (TextView) getView().findViewById(R.id.dialog_body);
        String title = getArguments().getString("title", "DialogFragment title");
        String body = getArguments().getString("body", "DialogFragment body");
        getDialog().setTitle(title);
        mTitle.setText(title);
        mBody.setText(body);
//        dWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        if (getArguments().getBoolean("clickable")) {
            setClickableText(mBody);
        }
    }

    private void setClickableText(TextView body) {
        TextView cloned = body;
//        Iterator it = PHONE_NUMBERS.entrySet().iterator();
        LinearLayout linearLayout = (LinearLayout) getView().findViewById(R.id.dialog_body_view);
//        Map.Entry pair = (Map.Entry) it.next();
//        body.setClickable(true);
//        body.setText((String) pair.getKey());
        int id = 0;
//        it.remove();

        for (Map.Entry<Integer, List<String>> entry : PHONE_NUMBERS.entrySet()) {
            cloned.setClickable(true);
            cloned.setId(id);
            cloned.setOnClickListener(callClickListener);
//            Map.Entry newPair = (Map.Entry) it.next();
//            cloned.setId(id);
//            cloned.setText((String) newPair.getKey());
            cloned.setText(entry.getValue().get(0));
            cloned.setLayoutParams(body.getLayoutParams());
            TextViewCompat.setTextAppearance(cloned, R.style.CustomTextView);
            if (id != 0) {
                linearLayout.addView(cloned);
            }
            cloned = new TextView(getActivity());
            id++;
        }

    }

    private View.OnClickListener callClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            onNumberSet(PHONE_NUMBERS.get(v.getId()).get(1));
        }
    };

    public void onNumberSet(String phoneNumber) {
        this.mListener.onCall(phoneNumber);
    }

    @Override
    public void onResume() {
        super.onResume();
        dWindow.setLayout(getArguments().getInt("width"), getArguments().getInt("height"));
    }
}
