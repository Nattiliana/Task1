package by.courses.nattiliana.entities;

import java.io.Serializable;

/**
 * Created by Nataly on 13.10.2016.
 */
public class Subject implements Serializable {

    private String subjectName;

    /**
     * Instantiates a new Subject.
     *
     * @param subjectName the subject name
     */
    public Subject(String subjectName) {
        this.subjectName = subjectName;
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
        return "\nSubject name: " + subjectName + "\n";
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}
