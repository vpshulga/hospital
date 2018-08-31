package entities;

import enums.Educations;
import enums.Sex;

public abstract class MedWorker extends Person {
    private Educations education;
    private int experience;

    public MedWorker() {
    }

    public MedWorker(String firstName, String secondName, int age, Sex sex,
                     Educations education, int experience, Long userId) {
        super(firstName, secondName, age, sex, userId);
        this.education = education;
        this.experience = experience;
    }

    public Educations getEducation() {
        return education;
    }

    public void setEducation(Educations education) {
        this.education = education;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }
}
