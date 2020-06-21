package com.szubov.healthmonitor;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class VitalsActivity extends AppCompatActivity {

    private EditText mEditWeight;
    private EditText mEditSteps;
    private static final String TAG = "MyApp";
    List<PatientVitals> patientVitalsSet = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vitals);

        mEditWeight = findViewById(R.id.editWeight);
        mEditSteps = findViewById(R.id.editSteps);

    }

    public void btnSaveWeightAndStepsOnClick(View view) {
        Log.i(TAG, "User clicked save in VitalsActivity");

        while (true) {
            if (mEditWeight.length() < 1 || mEditSteps.length() < 1) {
                Toast.makeText(this, R.string.field_is_empty, Toast.LENGTH_LONG).show();
                Log.e(TAG, "Not all fields are filled in VitalsActivity");
                break;
            } else if (mEditWeight.length() >6 || mEditSteps.length() > 5) {
                Toast.makeText(this, R.string.field_contains_too_big_value,
                        Toast.LENGTH_LONG).show();
                Log.e(TAG, "Field contains too big number in VitalsActivity");
                break;
            } else if (Short.parseShort(mEditSteps.getText().toString()) >= 32767) { //необходимо
                // уточнение насчет кол-ва шагов (за день, в кабинете у врача) для выбора
                // ограничения и типа поля
                Toast.makeText(this, R.string.field_steps_contains_invalid_values,
                        Toast.LENGTH_LONG).show();
                Log.e(TAG, "Field steps contains too big number in VitalsActivity");
                break;
            } else {
                try {
                    float mWeight = Float.parseFloat(mEditWeight.getText().toString());
                    short mSteps = Short.parseShort(mEditSteps.getText().toString());

                    patientVitalsSet.add(new PatientVitals(mWeight, mSteps));

                    mEditWeight.setText(null);
                    mEditSteps.setText(null);

                    Toast.makeText(this, R.string.btn_save_vitals_activity,
                            Toast.LENGTH_LONG).show();
                } catch (NumberFormatException ex) {
                    Toast.makeText(this, R.string.btn_save_exception,
                            Toast.LENGTH_LONG).show();
                    Log.e(TAG, "Btn save exception in VitalsActivity", ex);
                }
            }
            break;
        }
    }

    public void btnMainFromVitalsActivityOnClick(View view) {
        Log.i(TAG, "User clicked btn MainActivity in VitalsActivity");

        try {
            Intent intent = new Intent(VitalsActivity.this, MainActivity.class);
            startActivity(intent);
        } catch (Exception ex) {
            Log.e(TAG, "Exception in VitalsActivity, transition btn to MainActivity", ex);
        }
    }

    public void btnPressureFromVitalsActivityOnClick(View view) {
        Log.i(TAG, "User clicked btn PressureActivity in VitalsActivity");

        try {
            Intent intent = new Intent(VitalsActivity.this, PressureActivity.class);
            startActivity(intent);
        } catch (Exception ex) {
            Log.e(TAG, "Exception in VitalsActivity, transition btn to PressureActivity", ex);
        }
    }
}
