package com.szubov.healthmonitor;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.HashSet;
import java.util.Set;

public class VitalsActivity extends AppCompatActivity {

    private EditText mEditWeight;
    private EditText mEditSteps;
    private static final String TAG = "MyApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vitals);

        mEditWeight = findViewById(R.id.editWeight);
        mEditSteps = findViewById(R.id.editSteps);

    }

    public void btnSaveWeightAndStepsOnClick(View view) {
        Log.i(TAG, "User clicked save in VitalsActivity");

        if (Short.parseShort(mEditSteps.getText().toString()) >= 32767) {
            Toast.makeText(this, R.string.field_steps_contains_invalid_values,
                    Toast.LENGTH_LONG).show();
            Log.e(TAG, "Field steps contains too big number in VitalsActivity");
        }

        try {
            float mWeight = Float.parseFloat(mEditWeight.getText().toString());
            short mSteps = Short.parseShort(mEditSteps.getText().toString());

            Set<PatientVitals> patientVitalsSet = new HashSet<>();
            patientVitalsSet.add(new PatientVitals(mWeight, mSteps));

            mEditWeight.setText(null);
            mEditSteps.setText(null);

            Toast.makeText(this, R.string.btn_save_vitals_activity, Toast.LENGTH_LONG).show();
        } catch (NumberFormatException ex) {
            Toast.makeText(this, R.string.btn_save_exception, Toast.LENGTH_LONG).show();
            Log.e(TAG, "Btn save exception in VitalsActivity", ex);
        }

    }
}
