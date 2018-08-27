package entities.cards;

import entities.Patient;

public class Diagnosys {
    private int id;
    private Patient patient;
    private String text;

    public Diagnosys() {
    }

    public Diagnosys(Patient patient, String text) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Diagnosys)) return false;

        Diagnosys diagnosys = (Diagnosys) o;

        return id == diagnosys.id && (text != null ? text.equals(diagnosys.text) : diagnosys.text == null);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (patient != null ? patient.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }
}
