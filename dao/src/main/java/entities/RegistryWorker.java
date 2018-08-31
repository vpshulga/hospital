package entities;

import enums.Educations;
import enums.Sex;

public class RegistryWorker extends MedWorker {
    private int id;

    public RegistryWorker() {
    }

    public RegistryWorker(String firstName, String lastName, int age, Sex sex,
                          Educations education, int experience, Long userId) {
        super(firstName, lastName, age, sex, education, experience, userId);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
