package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nataly on 18.10.2016.
 */
public class RegistrationList {

    private Student student;
    private List<Quiz> quizList = new ArrayList<>();

    public RegistrationList(Student student, List<Quiz> quizList) {
        this.student = student;
        this.quizList = quizList;
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
        return "\nStudent: " + student + " Subjects list: " + quizList;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<Quiz> getQuizList() {
        return quizList;
    }

    public void setQuizList(List<Quiz> subjectsList) {
        this.quizList = quizList;
    }
}
