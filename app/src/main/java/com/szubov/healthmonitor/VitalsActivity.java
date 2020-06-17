package com.szubov.healthmonitor;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import java.util.HashSet;
import java.util.Set;

public class VitalsActivity extends AppCompatActivity {

    private EditText mEditWeight;
    private EditText mEditSteps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vitals);

        mEditWeight = findViewById(R.id.editWeight);
        mEditSteps = findViewById(R.id.editSteps);

    }

    public void btnSaveWeightAndStepsOnClick(View view) {
        float mWeight = Float.parseFloat(mEditWeight.getText().toString());
        int mSteps = Integer.parseInt(mEditSteps.getText().toString());

        Set<PatientVitals> patientVitalsSet = new HashSet<>();
        patientVitalsSet.add(new PatientVitals(mWeight, mSteps));
    }
}
