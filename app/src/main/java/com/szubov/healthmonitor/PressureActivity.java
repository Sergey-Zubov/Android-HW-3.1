package com.szubov.healthmonitor;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

public class PressureActivity extends AppCompatActivity {

    private EditText mEditUpperPressure;
    private EditText mEditLowerPressure;
    private EditText mEditPulse;
    private CheckBox mCheckBoxTachycardiaNo;
    private CheckBox mCheckBoxTachycardiaYes;
    private static final String TAG = "MyApp";
    Map<String, PatientPressure> patientPressureMap = new TreeMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pressure);

        mEditUpperPressure = findViewById(R.id.editUpperPressure);
        mEditLowerPressure = findViewById(R.id.editLowerPressure);
        mEditPulse = findViewById(R.id.editPulse);
        mCheckBoxTachycardiaNo = findViewById(R.id.checkBoxTachycardiaNo);
        mCheckBoxTachycardiaYes = findViewById(R.id.checkBoxTachycardiaYes);

        mCheckBoxTachycardiaNo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                Log.i(TAG, "CheckBoxNo flag has changed in PressureActivity");
                try {
                    mCheckBoxTachycardiaYes.setChecked(false);
                    mCheckBoxTachycardiaNo.setChecked(isChecked);
                } catch (Exception ex) {
                    Log.e(TAG, "CheckBoxNo exception in PressureActivity", ex);
                }


            }
        });

        mCheckBoxTachycardiaYes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.i(TAG, "CheckBoxYes flag has changed in PressureActivity");
                try {
                    mCheckBoxTachycardiaNo.setChecked(false);
                    mCheckBoxTachycardiaYes.setChecked(isChecked);
                } catch (Exception ex) {
                    Log.e(TAG, "CheckBoxYes exception in PressureActivity", ex);
                }

            }
        });
    }


    public void btnSavePressureOnClick(View view) {
        Log.i(TAG, "User clicked save in PressureActivity");

        while (true) {
            if (mEditUpperPressure.length() < 1 || mEditLowerPressure.length() < 1 ||
                    mEditPulse.length() < 1) {
                Toast.makeText(this, R.string.field_is_empty, Toast.LENGTH_LONG).show();
                Log.e(TAG, "Not all fields are filled in PressureActivity");
                break;
            } else if (mEditUpperPressure.length() > 3 || mEditLowerPressure.length() > 3 ||
                    mEditPulse.length() > 3) {
                Toast.makeText(this, R.string.field_contains_too_big_value,
                        Toast.LENGTH_LONG).show();
                Log.e(TAG, "Field contains too big number in PressureActivity");
                break;
            } else {
                try {
                    short mUpperPressure = Short.parseShort(mEditUpperPressure.getText()
                            .toString());
                    short mLowerPressure = Short.parseShort(mEditLowerPressure.getText()
                            .toString());
                    short mPulse = Short.parseShort(mEditPulse.getText().toString());
                    boolean mTachycardia = false;

                    if (!mCheckBoxTachycardiaNo.isChecked()) {
                        mTachycardia = true;
                    }

                    DateFormat dateTimeFormat = new SimpleDateFormat(getString
                            (R.string.date_time_pattern),
                            Locale.getDefault());
                    String dateTime = dateTimeFormat.format(new Date());

                    patientPressureMap.put(dateTime, new PatientPressure(mUpperPressure,
                            mLowerPressure, mPulse, mTachycardia, dateTime));

                    mEditUpperPressure.setText(null);
                    mEditLowerPressure.setText(null);
                    mEditPulse.setText(null);
                    mCheckBoxTachycardiaNo.setChecked(true);

                    Toast.makeText(this, R.string.btn_save_pressure_activity,
                            Toast.LENGTH_LONG).show();
                } catch (NumberFormatException ex) {
                    Toast.makeText(this, R.string.btn_save_exception,
                            Toast.LENGTH_LONG).show();
                    Log.e(TAG, "Btn save exception in PressureActivity", ex);
                }
            }
            break;
        }
    }

    public void btnMainFromPressureActivityOnClick(View view) {
        Log.i(TAG, "User clicked btn MainActivity in PressureActivity");

        try {
            Intent intent = new Intent(PressureActivity.this, MainActivity.class);
            startActivity(intent);
        } catch (Exception ex) {
            Log.e(TAG, "Exception in PressureActivity, transition btn to MainActivity", ex);
        }
    }

    public void btnVitalsFromPressureActivityOnClick(View view) {
        Log.i(TAG, "User clicked btn VitalsActivity in PressureActivity");

        try {
            Intent intent = new Intent(PressureActivity.this, VitalsActivity.class);
            startActivity(intent);
        } catch (Exception ex) {
            Log.e(TAG, "Exception in PressureActivity, transition btn to VitalsActivity", ex);
        }
    }
}
