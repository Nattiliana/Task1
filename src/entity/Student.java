package entity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Nataly on 13.10.2016.
 */
public class Student extends User implements Loginable {

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

    public Student(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof Student)) {
            Student otherStudent = (Student) obj;
            if (otherStudent.login.equals(login)) {
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

    @Override
    public boolean login() throws IOException {
        if ((login.equals(Reader.readLoginFromFile("student.txt"))) && (password.equals(Reader.readPassFromFile("student.txt")))) {
            return true;
        } else {
            return false;
        }
    }

    public static void authorize() {
        boolean isChecked = false;
        do {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter login");
            String login = null;
            try {
                login = bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Enter password");
            String pass = null;
            try {
                pass = bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Student student = new Student(login, pass);
            try {
                if (student.login()) {
                    student.setName(entity.Reader.readNameFromFile("student.txt"));
                    student.setSurname(entity.Reader.readSurnameFromFile("student.txt"));
                    System.out.println("Welcome, " + student.getName() + " " + student.getSurname());
                    isChecked = true;
                } else {
                    System.out.println("Try again!");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        while (!isChecked);
    }
}
