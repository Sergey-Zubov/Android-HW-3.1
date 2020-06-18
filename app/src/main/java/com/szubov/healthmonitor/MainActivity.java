package com.szubov.healthmonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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
        try {
            short agePatient = Short.parseShort(mEditAgePatient.getText().toString());
            Patient patient = new Patient(mEditFioPatient.getText().toString(), agePatient);

            mEditFioPatient.setText(null);
            mEditAgePatient.setText(null);

            Toast.makeText(this, "Пациент внесен в базу!", Toast.LENGTH_LONG).show();
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Неверный формат данных!", Toast.LENGTH_LONG).show();
        }

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