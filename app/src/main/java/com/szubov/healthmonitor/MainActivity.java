package com.szubov.healthmonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText mEditFioPatient;
    private EditText mEditAgePatient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditFioPatient = findViewById(R.id.editFioPatient);
        mEditAgePatient = findViewById(R.id.editAgePatient);

    }

    public void btnSaveFioAndAgeOnClick(View view) {
        short agePatient = Short.parseShort(mEditAgePatient.getText().toString());
        Patient patient = new Patient(mEditFioPatient.getText().toString(), agePatient);
        mEditFioPatient.setText(null);
        mEditAgePatient.setText(null);
    }


    public void btnPressureOnClick(View view) {
        Intent intent = new Intent(MainActivity.this, PressureActivity.class);
        startActivity(intent);
    }


    public void btnVitalsOnClick(View view) {
        Intent intent = new Intent(MainActivity.this, VitalsActivity.class);
        startActivity(intent);
    }
}