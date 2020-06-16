package com.szubov.healthmonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText mFioPatient;
    private EditText mAgePatient;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFioPatient = findViewById(R.id.enter_fio_patient);
        mAgePatient = findViewById(R.id.enter_age_patient);

    }

    public void btnSaveFioAndAgeOnClick(View view) {

    }


    public void btnPressureOnClick(View view) {

    }


    public void btnVitalsOnClick(View view) {

    }
}