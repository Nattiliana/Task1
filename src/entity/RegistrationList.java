package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nataly on 18.10.2016.
 */
public class RegistrationList {

    private Student student;
    private List<Subject> subjectsList = new ArrayList<>();

    public RegistrationList(Student student, List<Subject> subjectsList) {
        this.student = student;
        this.subjectsList = subjectsList;
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
        return "\nStudent: " + student + " Subjects list: " + subjectsList;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<Subject> getSubjectsList() {
        return subjectsList;
    }

    public void setSubjectsList(List<Subject> subjectsList) {
        this.subjectsList = subjectsList;
    }
}
