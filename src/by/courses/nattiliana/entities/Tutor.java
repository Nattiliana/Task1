package by.courses.nattiliana.entities;

import by.courses.nattiliana.exceptions.OutOfSelectionException;

import java.io.*;
import java.util.*;

/**
 * Created by Nataly on 13.10.2016.
 */
public class Tutor extends User implements Serializable {
    private static final String fileName = "D:\\Program\\Java Workspace\\NC\\Task1\\src\\by\\courses\\nattiliana\\files\\tutor.txt";


    /**
     * Instantiates a new Tutor.
     *
     * @param login    the login
     * @param password the password
     * @param name     the name
     * @param surname  the surname
     */
    public Tutor(String login, String password, String name, String surname) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }

    public Tutor(String login, String password) {
        this.login = login;
        this.password = password;
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
        return "\nLogin: " + login + " Name: " + name + " Surname: " + surname;
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
        return (login.equals(by.courses.nattiliana.tools.Reader.readLoginFromFile(fileName))) && (password.equals(by.courses.nattiliana.tools.Reader.readPassFromFile(fileName)));
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
            Tutor tutor = new Tutor(login, pass);
            try {
                if (tutor.login()) {
                    tutor.setName(by.courses.nattiliana.tools.Reader.readNameFromFile(fileName));
                    tutor.setSurname(by.courses.nattiliana.tools.Reader.readSurnameFromFile(fileName));
                    System.out.println("Welcome, " + tutor.getName() + " " + tutor.getSurname());
                    isChecked = true;
                } else {
                    System.out.println("Try again!");
                }
            } catch (IOException e) {
                e.getMessage();
            }
        }
        while (!isChecked);
    }

    public static void serialize(Quiz quiz) {
        String serializeFileName = "D:\\Program\\Java Workspace\\NC\\Task1\\src\\by\\courses\\nattiliana\\files\\serialize.txt";
        try (FileOutputStream fileOutputStream = new FileOutputStream(serializeFileName);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(quiz);
            System.out.println("Serialized");
        } catch (IOException ex) {
            ex.getMessage();
        }
    }

    private static void writeAnswersToFile(String fileName, int answer, int number) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName, true);
             BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream))) {
            bufferedWriter.append("Question " + number + " Right answer: " + answer + "\n");
            bufferedWriter.flush();
        } catch (IOException ex) {
            ex.getMessage();
        }
    }

    public static Question createQuestion(int questionNumber) throws IOException {

        boolean isChecked = false;
        int number = 0;
        int rightAnswer = 0;
        Map<Integer, String> questionMap = new TreeMap<>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter question text: ");
        String questionText = bufferedReader.readLine();
        do {
            System.out.println("Enter the number of answers: ");
            try {
                number = Integer.parseInt(bufferedReader.readLine());
                if (number < 0) {
                    throw new OutOfSelectionException();
                }
                isChecked = true;
            } catch (NumberFormatException ex) {
                System.out.println("You should use numbers!");
            } catch (OutOfSelectionException e) {
                System.out.println("Please, enter a valid number.");
            }
        }
        while (!isChecked);
        System.out.println("Enter answers: ");
        for (int i = 0; i < number; i++) {
            String answer = bufferedReader.readLine();
            questionMap.put(i + 1, answer);
        }
        isChecked = false;
        do {
            System.out.println("Enter right answer number: ");
            try {
                rightAnswer = Integer.parseInt(bufferedReader.readLine());
                if (rightAnswer < 0) {
                    throw new OutOfSelectionException();
                }
                if (rightAnswer > number) {
                    throw new OutOfSelectionException();
                }
                String answersFileName = "D:\\Program\\Java Workspace\\NC\\Task1\\src\\by\\courses\\nattiliana\\files\\answers.txt";
                Tutor.writeAnswersToFile(answersFileName, rightAnswer, questionNumber);
                isChecked = true;
            } catch (NumberFormatException ex) {
                System.out.println("You should use numbers!");
            } catch (OutOfSelectionException e) {
                System.out.println("Please, enter a valid number of answer.");
            }
        } while (!isChecked);
        return new Question(questionNumber, questionText, questionMap, rightAnswer);
    }

    public static Quiz createQuiz(List<Question> list) throws IOException {
        GregorianCalendar calendar = new GregorianCalendar();
        boolean isChecked = false;
        String subjectName = null;
        String quizName = null;
        int number = 0;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        do {
            System.out.println("Enter subject name: ");
            try {
                subjectName = bufferedReader.readLine();
                if (subjectName.equals(null)) {
                    throw new OutOfSelectionException();
                }
                isChecked = true;
            } catch (IOException e) {
                e.getMessage();
            } catch (OutOfSelectionException e) {
                System.out.println("Please, enter a valid subject.");
            }
        } while (!isChecked);
        Subject subject = new Subject(subjectName);
        isChecked = false;
        do {
            System.out.println("Enter quiz name: ");
            try {
                quizName = bufferedReader.readLine();
                if (quizName.equals(null)) {
                    throw new OutOfSelectionException();
                }
                isChecked = true;
            } catch (IOException e) {
                e.getMessage();
            } catch (OutOfSelectionException e) {
                System.out.println("Please, enter a valid name of quiz.");
            }
        } while (!isChecked);
        System.out.println("Choose 2 questions");
        Collections.sort(list, new Question());
        ListIterator iterator = list.listIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        isChecked = false;
        List<Question> newList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            do {
                System.out.println("Enter " + (i + 1) + " question number : ");
                try {
                    number = Integer.parseInt(bufferedReader.readLine());
                    if (number < 0) {
                        throw new OutOfSelectionException();
                    }
                    if (number > list.size()) {
                        throw new OutOfSelectionException();
                    }
                    isChecked = true;
                } catch (NumberFormatException ex) {
                    System.out.println("You should use numbers!");
                } catch (OutOfSelectionException e) {
                    System.out.println("Please, enter a valid number of answer.");
                }
            } while (!isChecked);
            newList.add(list.get(number - 1));
        }
        return new Quiz(quizName, subject, newList, calendar.getTime());
    }
}
