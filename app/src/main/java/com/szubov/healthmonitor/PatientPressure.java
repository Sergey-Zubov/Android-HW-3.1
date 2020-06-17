package com.szubov.healthmonitor;

import androidx.annotation.Nullable;
import java.util.Objects;

public class PatientPressure {
    private short upperPressure;
    private short lowerPressure;
    private short pulse;
    private boolean tachycardia;
    private String dateTimeMeasurement;

    public String getDateTimeMeasurement() {
        return dateTimeMeasurement;
    }

    public PatientPressure(short upperPressure, short lowerPressure, short pulse, boolean tachycardia, String dateTimeMeasurement) {
        this.upperPressure = upperPressure;
        this.lowerPressure = lowerPressure;
        this.pulse = pulse;
        this.tachycardia = tachycardia;
        this.dateTimeMeasurement = dateTimeMeasurement;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateTimeMeasurement);
    }

    @Override
    public boolean equals(@Nullable Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatientPressure patientPressure = (PatientPressure) o;
        return dateTimeMeasurement == patientPressure.dateTimeMeasurement || (dateTimeMeasurement != null && dateTimeMeasurement.equals(patientPressure.getDateTimeMeasurement()));
    }
}
