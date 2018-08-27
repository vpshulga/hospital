package entities.cards;

import entities.Patient;

public class Complaint {
    private int id;
    private Patient patient;
    private String text;

    public Complaint() {
    }

    public Complaint(Patient patient, String text) {
        this.patient = patient;
        this.text = text;
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
}
