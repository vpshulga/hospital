package entities.cards;

import entities.Patient;
import enums.AppointmentsType;

public class Appointment {
    private int id;
    private Patient patient;
    private AppointmentsType type;
    private String text;

    public Appointment() {
    }

    public Appointment(Patient patient, AppointmentsType type, String text) {
        this.patient = patient;
        this.text = text;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public AppointmentsType getType() {
        return type;
    }

    public void setType(AppointmentsType type) {
        this.type = type;
    }
}
