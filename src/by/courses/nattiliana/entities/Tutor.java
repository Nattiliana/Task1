package by.courses.nattiliana.entities;

import by.courses.nattiliana.exceptions.OutOfSelectionException;

import java.io.*;
import java.util.*;

/**
 * Created by Nataly on 13.10.2016.
 */
public class Tutor extends User implements Serializable {
    private static final String fileName = "D:\\Program\\Java Workspace\\NC\\Task1\\src\\by\\courses\\nattiliana\\files\\tutor.txt";
    private static final String answersFileName = "D:\\Program\\Java Workspace\\NC\\Task1\\src\\by\\courses\\nattiliana\\files\\answers.txt";

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

    public static void serialize(List<Quiz> list) {
        String serializeFileName = "D:\\Program\\Java Workspace\\NC\\Task1\\src\\by\\courses\\nattiliana\\files\\serialize.txt";
        try (FileOutputStream fileOutputStream = new FileOutputStream(serializeFileName, true);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            if (list.equals(null)) {
                throw new NullPointerException();
            } else {
                objectOutputStream.writeObject(list);
                System.out.println("Serialized");
            }
        } catch (IOException ex) {
            ex.getMessage();
        }
    }

    private static Question createQuestion(int questionNumber) throws IOException {
        boolean isChecked = false;
        int number = 0;
        int rightAnswer = 0;
        String questionText = null;
        Map<Integer, String> questionMap = new TreeMap<>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        do {
            System.out.println("Enter " + questionNumber + " question text: \"e\" - exit");
            try {
                questionText = bufferedReader.readLine();
                if (questionText.equals("")) {
                    throw new OutOfSelectionException();
                }
                if (questionText.equals("e")) {
                    return null;
                }
                isChecked = true;
            } catch (OutOfSelectionException e) {
                System.out.println("Please, enter a valid text.");
            }
        } while (!isChecked);
        isChecked = false;
        do {
            System.out.println("Enter the amount of answers: ");
            try {
                number = Integer.parseInt(bufferedReader.readLine());
                if (number <= 0) {
                    throw new OutOfSelectionException();
                }
                isChecked = true;
            } catch (NumberFormatException ex) {
                System.out.println("You should use numbers!!");
            } catch (OutOfSelectionException e) {
                System.out.println("Please, enter a valid number.");
            }
        }
        while (!isChecked);
        for (int i = 0; i < number; i++) {
            System.out.println("Enter " + (i + 1) + " answer: ");
            String answer = bufferedReader.readLine();
            questionMap.put(i + 1, answer);
        }
        isChecked = false;
        do {
            System.out.println("Enter right answer number for " + questionNumber + " question: ");
            try {
                rightAnswer = Integer.parseInt(bufferedReader.readLine());
                if (rightAnswer <= 0) {
                    throw new OutOfSelectionException();
                }
                if (rightAnswer > number) {
                    throw new OutOfSelectionException();
                }
                isChecked = true;
            } catch (NumberFormatException ex) {
                System.out.println("You should use numbers!");
            } catch (OutOfSelectionException e) {
                System.out.println("Please, enter a valid number of answer.");
            }
        } while (!isChecked);
        return new Question(questionNumber, questionText, questionMap, rightAnswer);
    }

    public static Quiz createQuiz() throws IOException {
        GregorianCalendar calendar = new GregorianCalendar();
        boolean isChecked = false;
        String subjectName = null;
        String quizName = null;
        int amountOfQuestions = 0;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        do {
            System.out.println("Enter subject name:  \"e\" - exit");
            try {
                subjectName = bufferedReader.readLine();
                if (subjectName.equals("")) {
                    throw new OutOfSelectionException();
                }
                if (subjectName.equals("e")) {
                    return null;
                }
                isChecked = true;
            } catch (OutOfSelectionException e) {
                System.out.println("Please, enter a valid subject.");
            }
        } while (!isChecked);
        Subject subject = new Subject(subjectName);
        isChecked = false;
        do {
            System.out.println("Enter quiz name: \"e\" - exit");
            try {
                quizName = bufferedReader.readLine();
                if (quizName.equals("")) {
                    throw new OutOfSelectionException();
                }
                if (quizName.equals("e")) {
                    return null;
                }
                isChecked = true;
            } catch (OutOfSelectionException e) {
                System.out.println("Please, enter a valid name of quiz.");
            }
        } while (!isChecked);
        isChecked = false;
        do {
            System.out.println("Enter the amount of questions in quiz: ");
            try {
                amountOfQuestions = Integer.parseInt(bufferedReader.readLine());
                if (amountOfQuestions <= 0) {
                    throw new OutOfSelectionException();
                }
                isChecked = true;
            } catch (NumberFormatException ex) {
                System.out.println("You should use numbers");
            } catch (OutOfSelectionException e) {
                System.out.println("Please, enter a valid number.");
            }
        } while (!isChecked);
        List<Question> list = new ArrayList<>();
        for (int i = 0; i < amountOfQuestions; i++) {
            Question question = createQuestion(i + 1);
            list.add(question);
        }
        Collections.sort(list, new Question());
        return new Quiz(quizName, subject, list, calendar.getTime());
    }

    public static List<Quiz> deleteQuiz() {
        String serializeFileName = "D:\\Program\\Java Workspace\\NC\\Task1\\src\\by\\courses\\nattiliana\\files\\serialize.txt";
        try (FileInputStream fileInputStream = new FileInputStream(serializeFileName);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            List<Quiz> list = (List<Quiz>) objectInputStream.readObject();
            if (!(list.isEmpty())) {
                System.out.println("Deserialize object");
                for (int i = 0; i < list.size(); i++) {
                    System.out.print((i + 1) + " quiz: ");
                    System.out.println(list.get(i).getQuizName() + list.get(i).getSubject() + list.get(i).getDateOfCreate());
                    for (int j = 0; j < list.get(i).getQuestionsList().size(); j++) {
                        System.out.println(list.get(i).getQuestionsList().get(j).toStringForStudent());
                    }
                    System.out.println("---------------------------------------------------");
                }
                boolean isChecked = false;
                int quizNumber;
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                do {
                    System.out.println("Choose quiz number that you want to delete: ");
                    try {
                        quizNumber = Integer.parseInt(bufferedReader.readLine());
                        if (quizNumber <= 0) {
                            throw new OutOfSelectionException();
                        }
                        if (quizNumber > list.size()) {
                            throw new OutOfSelectionException();
                        }
                        isChecked = true;
                        list.remove(quizNumber - 1);
                        Student.clearFile(serializeFileName);
                        return list;
                    } catch (NumberFormatException ex) {
                        System.out.println("You should use numbers");
                    } catch (OutOfSelectionException e) {
                        System.out.println("Please, enter a valid number.");
                    }
                }
                while (!isChecked);
            }
            else {
                throw new NullPointerException();
            }
        } catch (ClassNotFoundException e) {
            e.getMessage();
        } catch (IOException e) {
            e.getMessage();
        }
        return null;
    }
}
