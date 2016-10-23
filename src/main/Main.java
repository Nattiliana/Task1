package main;

import entity.*;

import java.io.*;
import java.util.*;

/**
 * Created by Nataly on 13.10.2016.
 */
public class Main {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        int selection = 0;
        boolean isChosen = false;
        do {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Please Make a selection:");
            Main.printEnum(Menu.class);
            System.out.println("Selection: ");
            try {
                selection = Integer.parseInt(bufferedReader.readLine());
                if (selection == 3) {
                    break;
                }
                switch (selection) {

                    case 1:
                        System.out.println("Authorize");
                        Main.authorize();
                        isChosen = true;
                        break;

                    case 2:
                        System.out.println("Opening existing record");
                        isChosen = true;
                        break;

                    case 3:
                        isChosen = true;
                        break;

                    default:
                        System.out.println("Please, enter a valid selection.");

                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            catch (NumberFormatException ex) {
                System.out.println("You should use numbers!");
            }
        } while (!isChosen);


        Tutor tutor = new Tutor("qwerty", "qwer", "John", "Snow");
        Main.serialize(tutor);
        Main.deserialize();
        Tutor tutor1 = new Tutor("ll", "qwe", "Johny", "Snow");
        System.out.println(tutor1.toString());
        System.out.println("Tutor's count: " + Tutor.count);


        Map<Integer, String> questionMap = new TreeMap<>();
        questionMap.put(1, "4");
        questionMap.put(2, "2");
        questionMap.put(3, "8");
        Question question = new Question(2, "5 + 3", questionMap, 3);
        Question question1 = new Question(1, "5 - 3", questionMap, 2);
        List<Question> questionsList = new ArrayList<>();
        questionsList.add(question);
        questionsList.add(question1);

        ListIterator iterator = questionsList.listIterator();
        while (iterator.hasNext())
        {
            System.out.println(iterator.next());
        }

        Collections.sort(questionsList, new Question());

        System.out.println("Sorted question list: ");
        questionsList.forEach(System.out::println);

        GregorianCalendar calendar = new GregorianCalendar();
        Quiz quiz = new Quiz("quiz", questionsList, calendar.getTime());
        System.out.println(quiz.toString());
        List<Quiz> quizList = new ArrayList<>();
        quizList.add(quiz);

        Subject subject = new Subject("EMP", quizList);
        System.out.println("Subject's count: " + Subject.count);
        System.out.println(subject.toString());

        List<Subject> studentSubjectList = new ArrayList<>();
        studentSubjectList.add(subject);


        Student student = new Student("qw", "qwee", "James", "Fr");
        System.out.println(student.toString());

        RegistrationList registrationList = new RegistrationList(student, studentSubjectList);
        System.out.println(registrationList);

        /*System.out.println("Menu");
        for (Menu menu : Menu.values()) {
            System.out.println(menu);
        }

        System.out.println("Choose menu item");
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            Menu.runCommand(Integer.parseInt(bufferedReader.readLine()));
        }
        catch (IOException ex){
            ex.printStackTrace();
        }

        Map<Integer, Menu> enumMap = new HashMap<>();
        enumMap.put(1, Menu.CREATE_STUDENT);
        for (Map.Entry entry : enumMap.entrySet()){
            System.out.print(entry.getKey());
            System.out.println(entry.getValue());
        }

    }

    private enum Menu {
        CREATE_TUTOR(1, "Create tutor"),
        CREATE_STUDENT(2, "Create student"),
        CREATE_QUESTION(3, "Create question"),
        CREATE_QUIZ(4, "Create quiz"),
        CREATE_SUBJECT(5, "Create subject"),
        CREATE_LIST(6, "Create registration list"),
        EXIT(7, "Exit");

        private final String message;
        private final int code;

        public static Menu get(int code) {
            for (Menu c : Menu.values()) {
                if (code == c.code) {
                    return c;
                }
            }
            return null;
        }

        Menu(int code, String message) {
            this.code = code;
            this.message = message;
        }

        public int getCode() {
            return this.code;
        }

        public String getMessage() {
            return this.message;
        }

        protected static void runCommand(int choice) {
            Menu command = Menu.get(choice);
            System.out.println("You Chose '" + command.getMessage() + "'\n\n");
        }*/
    }

    private static void authorize() {
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
            Tutor tutor5 = new Tutor(login, pass);
            try {
                if (tutor5.login()) {
                    tutor5.setName(entity.Reader.readNameFromFile());
                    tutor5.setSurname(entity.Reader.readSurnameFromFile());
                    System.out.println("Welcome, " + tutor5.getName() + " " + tutor5.getSurname());
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

    private static void serialize(Object object) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("./tutors.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(object);
            objectOutputStream.close();
            fileOutputStream.close();
            System.out.println("Serialized");
        } catch (
                IOException ex)

        {
            ex.printStackTrace();
        }
    }

    private static void deserialize() {
        Object object;
        try {
            FileInputStream fileInputStream = new FileInputStream("./tutors.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            object = objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (
                IOException ex)

        {
            ex.printStackTrace();
            return;
        } catch (
                ClassNotFoundException e)

        {
            System.out.println("Class not found!");
            e.printStackTrace();
            return;
        }
        System.out.println("Deserialize object" + object);
    }

    public static <T extends Enum<T>> void printEnum(Class<T> enumClass) {
        for (Enum<T> item : enumClass.getEnumConstants()) {
            System.out.println(item.toString());
        }
    }
}


