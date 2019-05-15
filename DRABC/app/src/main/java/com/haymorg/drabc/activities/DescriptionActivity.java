package com.haymorg.drabc.activities;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v4.app.FragmentManager;
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
import com.haymorg.drabc.classes.CustomDialogFragment;
import com.haymorg.drabc.databinding.ActivityDescriptionBinding;
import com.haymorg.drabc.models.ConditionsResponse;
import com.haymorg.drabc.models.Treatment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DescriptionActivity extends AppCompatActivity {

    private ActivityDescriptionBinding binding;
    private String problemDescription;
    ArrayAdapter<String> description_adapter;
    AutoCompleteTextView descriptionAutoText;
    private boolean got_locations = false;
    ArrayList<String> diagnoses = new ArrayList<>();
    ArrayList<String> diagnosis_id = new ArrayList<>();
    String condition, treatmentID, treatmentDescription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_description);

    }

    @Override
    protected void onStart() {
        super.onStart();
        descriptionAutoText = binding.descriptionAutoComplete;
        description_adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, diagnoses);
        descriptionAutoText.setAdapter(description_adapter);
        suggestConditions();

        descriptionAutoText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long rowId) {
                if (view != null) {
                    condition = (String) parent.getItemAtPosition(position);
                    treatmentID = diagnosis_id.get(diagnoses.indexOf(condition));
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
                    descriptionAutoText.clearFocus();
                    binding.buttonHelp.setEnabled(true);
                }
            }
        });
    }

    public void onHelp(View v) {
        problemDescription = descriptionAutoText.getText().toString();

        if (problemDescription.trim().isEmpty() || problemDescription.equals("Description of medical issue")) {
            Toast.makeText(getApplicationContext(), "Please enter a brief description of the problem", Toast.LENGTH_LONG).show();
        } else {
//            Toast.makeText(getApplicationContext(), "This is wheree another API call will be made, using the ID of " + problemDescription + " to get its treatment", Toast.LENGTH_LONG).show();
            getTreatment(treatmentID);
        }
    }

    private void showCustomDialog(String condition, String treatment) {
        FragmentManager fm = getSupportFragmentManager();
        View dView = this.getWindow().getDecorView();
        CustomDialogFragment customDialog =
                CustomDialogFragment.newInstance(dView.getWidth(), dView.getHeight(), condition,
                        treatment, false);
        customDialog.show(fm, "fragment_custom_dialog");
    }

    private void getTreatment(String id) {
        Call<Treatment> call = RetrofitClient
                .getInstance()
                .getApiInterface()
                .getTreatment(id);

        call.enqueue(new Callback<Treatment>() {
            @Override
            public void onResponse(Call<Treatment> call, Response<Treatment> response) {
                int statusCode = response.code();
                if (statusCode == 200) {
                    treatmentDescription = response.body().getTreatment();
                    showCustomDialog(condition, treatmentDescription);
                } else {
                    Toast.makeText(getApplicationContext(), "Couldn't retrieve treatment suggestions; please check your internet connection", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Treatment> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void suggestConditions() {
        Call<ArrayList<ConditionsResponse>> call = RetrofitClient
                .getInstance()
                .getApiInterface()
                .getConditions();

        call.enqueue(new Callback<ArrayList<ConditionsResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<ConditionsResponse>> call, Response<ArrayList<ConditionsResponse>> response) {
                int statusCode = response.code();
                if (statusCode == 200) {
                    ArrayList<ConditionsResponse> conditions = response.body();
                    int listSize = conditions.size();
                    for (int i = 0; i < listSize; i++) {
                        description_adapter.add(response.body().get(i).getName());
                        diagnosis_id.add(response.body().get(i).getId());
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Couldn't retrieve condition list; please check your internet connection", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ConditionsResponse>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


}
