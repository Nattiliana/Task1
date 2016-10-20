package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nataly on 13.10.2016.
 */
public class Subject {

    private String subjectName;
    private List<Quiz> quizList = new ArrayList<>();
    public static int count;

    /**
     * Instantiates a new Subject.
     *
     * @param subjectName the subject name
     * @param quizList    the quiz list
     */
    public Subject(String subjectName, List<Quiz> quizList) {
        this.subjectName = subjectName;
        this.quizList = quizList;
        count++;
    }

    public Subject() {
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof Subject)) {
            Subject otherSubject = (Subject) obj;
            if (otherSubject.subjectName.equals(subjectName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 31 + ((subjectName == null) ? 0 : subjectName.hashCode());
    }

    @Override
    public String toString() {
        return "\nSubject name: " + subjectName + " Quiz list: " + quizList;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public List<Quiz> getQuizList() {
        return quizList;
    }

    public void setQuizList(List<Quiz> quizList) {
        this.quizList = quizList;
    }
}
