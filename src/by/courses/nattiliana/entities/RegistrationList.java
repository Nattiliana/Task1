package by.courses.nattiliana.entities;

/**
 * Created by Nataly on 18.10.2016.
 */
public class RegistrationList {

    private Student student;
    private int amountOfRightAnswers;

    public RegistrationList(Student student, int amountOfRightAnswers) {
        this.student = student;
        this.amountOfRightAnswers = amountOfRightAnswers;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof RegistrationList)) {
            RegistrationList other = (RegistrationList) obj;
            if (other.student.equals(student)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 31 + ((student == null) ? 0 : student.hashCode());
    }

    @Override
    public String toString() {
        return "\nStudent: " + student + " Amount of right answers: " + amountOfRightAnswers;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getAmountOfRightAnswers() {
        return amountOfRightAnswers;
    }

    public void setAmountOfRightAnswers(int amountOfRightAnswers) {
        this.amountOfRightAnswers = amountOfRightAnswers;
    }
}
