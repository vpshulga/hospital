package entities;

import enums.Educations;
import enums.Sex;

public class Nurse extends MedWorker {
    private int id;

    public Nurse() {
    }

    public Nurse(String firstName, String secondName, int age, Sex sex, Educations education,
                 int experience, int id, Long userId) {
        super(firstName, secondName, age, sex, education, experience, userId);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
