package com.szubov.healthmonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText mEditFioPatient;
    private EditText mEditAgePatient;
    private static final String TAG = "MyApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditFioPatient = findViewById(R.id.editFioPatient);
        mEditAgePatient = findViewById(R.id.editAgePatient);

    }

    public void btnSaveFioAndAgeOnClick(View view) {
        Log.i(TAG, "User clicked save in MainActivity");

        while (true) {
            if (mEditFioPatient.length() < 1 || mEditAgePatient.length() < 1) {
                Toast.makeText(this, R.string.field_is_empty, Toast.LENGTH_LONG).show();
                Log.e(TAG, "Not all fields are filled in MainActivity");
                break;

            } else if (!mEditFioPatient.getText().toString().
                    matches(String.valueOf(R.string.regex_for_fio_main_activity))) {
                Toast.makeText(this, R.string.field_fio_contains_invalid_values,
                        Toast.LENGTH_LONG).show();
                Log.e(TAG, "Field FIO contains invalid values in MainActivity");
                break;
            } else if (mEditFioPatient.length() > 70 || mEditAgePatient.length() > 3) {
                Toast.makeText(this, R.string.field_contains_too_big_value,
                        Toast.LENGTH_LONG).show();
                Log.e(TAG, "Field contains too big value in MainActivity");
                break;

            } else {
                try {
                    short agePatient = Short.parseShort(mEditAgePatient.getText().toString());
                    Patient patient = new Patient(mEditFioPatient.getText().toString(),
                            agePatient);

                    mEditFioPatient.setText(null);
                    mEditAgePatient.setText(null);

                    Toast.makeText(this, R.string.btn_save_main_activity_yes,
                            Toast.LENGTH_LONG).show();
                } catch (NumberFormatException ex) {
                    Toast.makeText(this, R.string.btn_save_exception,
                            Toast.LENGTH_LONG).show();
                    Log.e(TAG, "Btn save exception in MainActivity", ex);
                }
            }
            break;
        }
    }


    public void btnPressureOnClick(View view) {
        Log.i(TAG, "User clicked btn PressureActivity in MainActivity");

        try {
            Intent intent = new Intent(MainActivity.this, PressureActivity.class);
            startActivity(intent);
        } catch (Exception ex) {
            Log.e(TAG, "Exception in MainActivity, transition btn to PressureActivity", ex);
        }

    }


    public void btnVitalsOnClick(View view) {
        Log.i(TAG, "User clicked btn VitalsActivity in MainActivity");

        try {
            Intent intent = new Intent(MainActivity.this, VitalsActivity.class);
            startActivity(intent);
        } catch (Exception ex) {
            Log.e(TAG, "Exception in MainActivity, transition btn to VitalsActivity", ex);
        }

    }
}