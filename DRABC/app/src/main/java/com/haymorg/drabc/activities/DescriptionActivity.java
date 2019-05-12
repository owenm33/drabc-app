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
import com.haymorg.drabc.api.RetrofitClient;
import com.haymorg.drabc.databinding.ActivityDescriptionBinding;
import com.haymorg.drabc.models.ConditionsResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.haymorg.drabc.classes.Constants.SUGGESTED_ISSUES;

public class DescriptionActivity extends AppCompatActivity {

    private ActivityDescriptionBinding binding;
    private String problemDescription;
    ArrayAdapter<String> description_adapter;
    AutoCompleteTextView descriptionAutoText;
    private boolean got_locations = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_description);

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!suggestConditions()) {

        }
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

    private boolean suggestConditions() {
        Call<ArrayList<ConditionsResponse>> call = RetrofitClient
                .getInstance()
                .getApiInterface()
                .getConditions();

        call.enqueue(new Callback<ArrayList<ConditionsResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<ConditionsResponse>> call, Response<ArrayList<ConditionsResponse>> response) {
                int statusCode = response.code();
                if (statusCode == 200) {
                    Toast.makeText(getApplicationContext(), "Got locations", Toast.LENGTH_LONG).show();
                    got_locations = true;
//                    List<Condition> list =
                } else if (statusCode == 400 || statusCode == 402 || statusCode == 502) {
                    Toast.makeText(getApplicationContext(), "You failed hard", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ConditionsResponse>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        return got_locations;
    }


}
