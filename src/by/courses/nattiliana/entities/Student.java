package by.courses.nattiliana.entities;

import by.courses.nattiliana.tools.Reader;

import java.io.*;

/**
 * Created by Nataly on 13.10.2016.
 */
public class Student extends User {
    private static final String studentFileName = "D:\\Program\\Java Workspace\\NC\\Task1\\src\\by\\courses\\nattiliana\\files\\student.txt";
    private static final String answersFileName = "D:\\Program\\Java Workspace\\NC\\Task1\\src\\by\\courses\\nattiliana\\files\\answers.txt";

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

    private String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    private String getSurname() {
        return surname;
    }

    private void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public boolean login() throws IOException {
        return (login.equals(by.courses.nattiliana.tools.Reader.readLoginFromFile(studentFileName))) && (password.equals(by.courses.nattiliana.tools.Reader.readPassFromFile(studentFileName)));
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
                e.getMessage();
            }
            System.out.println("Enter password");
            String pass = null;
            try {
                pass = bufferedReader.readLine();
            } catch (IOException e) {
                e.getMessage();
            }
            Student student = new Student(login, pass);
            try {
                if (student.login()) {
                    student.setName(by.courses.nattiliana.tools.Reader.readNameFromFile(studentFileName));
                    student.setSurname(by.courses.nattiliana.tools.Reader.readSurnameFromFile(studentFileName));
                    System.out.println("Welcome, " + student.getName() + " " + student.getSurname());
                    isChecked = true;
                } else {
                    System.out.println("Try again, please!");
                }
            } catch (IOException e) {
                e.getMessage();
            }
        }
        while (!isChecked);
    }

    public static void deserialize() {
        String serializeFileName = "D:\\Program\\Java Workspace\\NC\\Task1\\src\\by\\courses\\nattiliana\\files\\serialize.txt";
        try (FileInputStream fileInputStream = new FileInputStream(serializeFileName);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            while ((fileInputStream.available()) > 0 ) {
                Quiz quiz = (Quiz) objectInputStream.readObject();
                System.out.println("Deserialize object");
                System.out.println(quiz.getQuizName() + quiz.getSubject() + quiz.getDateOfCreate());
                for (int i =0; i < quiz.getQuestionsList().size(); i++){
                    System.out.println(quiz.getQuestionsList().get(i).toStringForStudent());
                }
            }
        } catch (IOException ex) {
            ex.getMessage();
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found!");
            e.getMessage();
        }
    }

    public static int passTheQuiz() throws IOException {
        int count = 0;
        for (int i = 0; i < Question.count; i++) {
            try {
                System.out.println("Enter the answer for " + (i + 1) + " question");
                BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
                int answer = Integer.parseInt(bf.readLine());
                int correctAnswer = Reader.readAnswersFromFile(answersFileName).get(i);
                if (answer == correctAnswer) {
                    System.out.println("Your answer is correct!");
                    count++;
                } else {
                    System.out.println("Your answer is wrong!");
                }
            } catch (IndexOutOfBoundsException ex) {
                System.out.println("List is empty!");
            }
        }
        return count;
    }
}
