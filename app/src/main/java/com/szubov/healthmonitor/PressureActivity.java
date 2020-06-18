package com.szubov.healthmonitor;

import android.os.Bundle;
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
                mCheckBoxTachycardiaYes.setChecked(false);
                mCheckBoxTachycardiaNo.setChecked(isChecked);

            }
        });

        mCheckBoxTachycardiaYes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCheckBoxTachycardiaNo.setChecked(false);
                mCheckBoxTachycardiaYes.setChecked(isChecked);
            }
        });
    }


    public void btnSavePressureOnClick(View view) {
        try {
            short mUpperPressure = Short.parseShort(mEditUpperPressure.getText().toString());
            short mLowerPressure = Short.parseShort(mEditLowerPressure.getText().toString());
            short mPulse = Short.parseShort(mEditPulse.getText().toString());
            boolean mTachycardia = false;

            if (!mCheckBoxTachycardiaNo.isChecked()) {
                mTachycardia = true;
            }

            DateFormat dateTimeFormat = new SimpleDateFormat("HH:mm yyyy-MM-dd",
                    Locale.getDefault());
            String dateTime = dateTimeFormat.format(new Date());

            Map<String, PatientPressure> patientPressureMap = new TreeMap<>();
            patientPressureMap.put(dateTime, (new PatientPressure(mUpperPressure, mLowerPressure,
                    mPulse, mTachycardia)));

            mEditUpperPressure.setText(null);
            mEditLowerPressure.setText(null);
            mEditPulse.setText(null);
            mCheckBoxTachycardiaNo.setChecked(true);

            Toast.makeText(this, "Показатели давления сохранены!",
                    Toast.LENGTH_LONG).show();

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Неверный формат данных!", Toast.LENGTH_LONG).show();
        }

    }
}
