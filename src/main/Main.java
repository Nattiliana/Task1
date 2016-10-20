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
        Tutor tutor = new Tutor("qwerty", "qwer", "John", "Snow");
        System.out.println(tutor.toString());
        Tutor tutor1 = new Tutor("ll", "qwe", "Johny", "Snow");
        System.out.println(tutor1.toString());
        System.out.println("Tutor's count: " + Tutor.count);

        try {
            FileOutputStream fileOutputStream = new FileOutputStream("./tutors.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(tutor);
            objectOutputStream.close();
            fileOutputStream.close();
            System.out.println("Serialized");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        System.out.println("-------------------------------------------------------------------");

        Tutor tutor2;
        try {
            FileInputStream fileInputStream = new FileInputStream("./tutors.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            tutor2 = (Tutor) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            return;
        } catch (ClassNotFoundException e) {
            System.out.println("Class Tutor not found!");
            e.printStackTrace();
            return;
        }
        System.out.println("Deserialize");
        System.out.println(tutor2);

        System.out.println("-------------------------------------------------------------------");
        Map<Integer, String> questionMap = new TreeMap<>();
        questionMap.put(1, "4");
        questionMap.put(2, "2");
        questionMap.put(3, "8");
        Question question = new Question(2, "5 + 3", questionMap, 3);
        Question question1 = new Question(1, "5 - 3", questionMap, 2);
        System.out.println(question.toString());
        List<Question> questionsList = new ArrayList<>();
        questionsList.add(question);
        questionsList.add(question1);

        ListIterator iterator = questionsList.listIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        Collections.sort(questionsList, new Question());

        for (Question q : questionsList) {
            System.out.println(q);
        }

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

        /*for (Menu menu : Menu.values()) {
            System.out.println(menu);
        }*/

    }

    /*public enum Menu {
        CREATE_TUTOR {
            public void create() {
                Tutor eTutor = new Tutor("ee", "eeee", "ee", "eeee");
            }
        },
        CREATE_STUDENT {
            public void create() {
                Student eStudent = new Student("ee", "eeee", "ee", "eeee");
                System.out.println(eStudent);
            }
        };

        public abstract void create();
    }*/
}
