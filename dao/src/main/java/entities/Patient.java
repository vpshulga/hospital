package entities;

import enums.Sex;

import java.sql.Date;


public class Patient extends Person {
    private int id;
    private String city;
    private String street;
    private int house;
    private int apartment;
    private String complaint;
    private int doctorId;
    private String entranceDate;

    public Patient() {

    }

    public Patient(String firstName, String secondName, int age, Sex sex,
                   String cyty, String street, int house, int apartment, String complaint,
                   int doctorId, String entranceDate, Long userId) {
        super(firstName, secondName, age, sex, userId);
        this.city = cyty;
        this.street = street;
        this.house = house;
        this.apartment = apartment;
        this.complaint = complaint;
        this.doctorId = doctorId;
        this.entranceDate = entranceDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public String getEntranceDate() {
        return entranceDate;
    }

    public void setEntranceDate(String entranceDate) {
        this.entranceDate = entranceDate;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouse() {
        return house;
    }

    public void setHouse(int house) {
        this.house = house;
    }

    public int getApartment() {
        return apartment;
    }

    public void setApartment(int apartment) {
        this.apartment = apartment;
    }

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }


    @Override
    public String toString() {
        return this.getFirstName() + " " + getLastName() + " " + this.getAge() + " "
                + this.getSex() + " " + this.getCity() + " " + this.getStreet() + " "
                + this.getHouse() + " " + this.getApartment() + "(" + this.getComplaint() + ")";
    }
}
