package entity;

/**
 * Created by Nataly on 13.10.2016.
 */
public class Student extends User {

    /**
     * Instantiates a new Student.
     *
     * @param login    the login
     * @param password the password
     * @param name     the name
     * @param surname  the surname
     */
    public Student(String login, String password, String name, String surname) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof Tutor)) {
            Tutor otherTutor = (Tutor) obj;
            if (otherTutor.login.equals(login)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 31 + ((login == null) ? 0 : login.hashCode());
    }

    @Override
    public String toString() {
        return "\nlogin: " + login + " Name: " + name + " Surname: " + surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
