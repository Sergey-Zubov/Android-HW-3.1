package com.szubov.healthmonitor;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
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
        try {
            float mWeight = Float.parseFloat(mEditWeight.getText().toString());
            int mSteps = Integer.parseInt(mEditSteps.getText().toString());

            Set<PatientVitals> patientVitalsSet = new HashSet<>();
            patientVitalsSet.add(new PatientVitals(mWeight, mSteps));

            mEditWeight.setText(null);
            mEditSteps.setText(null);

            Toast.makeText(this, "Жизненные показатели сохранены!", Toast.LENGTH_LONG).show();
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Неверный формат данных!!", Toast.LENGTH_LONG).show();
        }

    }
}
