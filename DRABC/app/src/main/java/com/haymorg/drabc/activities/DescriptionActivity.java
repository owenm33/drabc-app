package com.haymorg.drabc.activities;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.haymorg.drabc.R;
import com.haymorg.drabc.databinding.ActivityDescriptionBinding;

import static com.haymorg.drabc.classes.Constants.SUGGESTED_ISSUES;

public class DescriptionActivity extends AppCompatActivity {

    private ActivityDescriptionBinding binding;
    private String problemDescription;
    ArrayAdapter<String> description_adapter;
    AutoCompleteTextView descriptionAutoText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_description);

    }

    @Override
    protected void onStart() {
        super.onStart();
        description_adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, SUGGESTED_ISSUES);
        descriptionAutoText = binding.descriptionAutoComplete;
        descriptionAutoText.setAdapter(description_adapter);

        descriptionAutoText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long rowId) {
                if (view != null) {
//                    problemDescription = (String) parent.getItemAtPosition(position);
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
                    descriptionAutoText.clearFocus();
                }
            }
        });
    }

    public void onHelp(View v) {
        problemDescription = descriptionAutoText.getText().toString();

        if (problemDescription == null || problemDescription.trim().isEmpty() || problemDescription.equals("Description of medical issue")) {
            Toast.makeText(getApplicationContext(), "Please enter a brief description of the problem", Toast.LENGTH_LONG).show();
        } else {
//            Toast.makeText(getApplicationContext(), problemDescription, Toast.LENGTH_LONG).show();
        }
    }
}
