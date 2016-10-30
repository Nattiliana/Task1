package by.courses.nattiliana;

import by.courses.nattiliana.entities.*;
import by.courses.nattiliana.enums.MenuItems;
import by.courses.nattiliana.exceptions.OutOfSelectionException;
import by.courses.nattiliana.menu.Menu;
import by.courses.nattiliana.menu.MenuItem;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nataly on 13.10.2016.
 */
class Main {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        String studentFileName = "D:\\Program\\Java Workspace\\NC\\Task1\\src\\by\\courses\\nattiliana\\files\\student.txt";

        Menu menu = new Menu();
        menu.addItem(new MenuItem(MenuItems.AUTHORIZE_T.toString()) {
            @Override
            public void run() {
                System.out.println(MenuItems.AUTHORIZE_T.getMessage());
                Tutor.authorize();
                Menu tutorMenu = new Menu();
                tutorMenu.addItem(new MenuItem(MenuItems.CREATE_QUIZ.toString()) {
                    @Override
                    public void run() {
                        try {
                            System.out.println(MenuItems.CREATE_QUIZ.getMessage());
                            boolean isCreated = false;
                            List<Quiz> quizList = new ArrayList<>();
                            do {
                                Quiz quiz = Tutor.createQuiz();
                                quizList.add(quiz);
                                System.out.println("Question's count: " + Question.count);
                                System.out.println("Your quiz: " + quiz);
                                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                                System.out.println("Do you want to create one more? (y/n)");
                                if (bufferedReader.readLine().equals("n")) {
                                    isCreated = true;
                                } else if (!(bufferedReader.readLine().equals("y"))) {
                                    throw new OutOfSelectionException();
                                }
                            } while (!isCreated);
                            Tutor.serialize(quizList);
                        } catch (NullPointerException ex) {
                            System.out.println("Quiz is empty!");
                        } catch (IOException e) {
                            e.getMessage();
                        } catch (OutOfSelectionException e) {
                            System.out.println("Please, enter a valid command.");
                        }
                    }
                });
                tutorMenu.addItem(new MenuItem(MenuItems.DELETE_QUIZ.toString()) {
                    @Override
                    public void run() {
                        try {
                            System.out.println(MenuItems.DELETE_QUIZ.getMessage());
                            List<Quiz> quizList = Tutor.deleteQuiz();
                            Tutor.serialize(quizList);
                        } catch (NullPointerException ex) {
                            System.out.println("There is no quiz to delete!");
                        }
                    }
                });
                tutorMenu.run();
            }
        });
        menu.addItem(new MenuItem(MenuItems.AUTHORIZE_S.toString()) {
            @Override
            public void run() {
                System.out.println(MenuItems.AUTHORIZE_S.getMessage());
                Student.authorize();
                Menu studentMenu = new Menu();
                studentMenu.addItem(new MenuItem(MenuItems.ANSWER_QUIZ.toString()) {
                    @Override
                    public void run() {
                        System.out.println(MenuItems.ANSWER_QUIZ.getMessage());
                        try {
                            Student student = new Student(by.courses.nattiliana.tools.Reader.readLoginFromFile(studentFileName),
                                    by.courses.nattiliana.tools.Reader.readPassFromFile(studentFileName),
                                    by.courses.nattiliana.tools.Reader.readNameFromFile(studentFileName),
                                    by.courses.nattiliana.tools.Reader.readSurnameFromFile(studentFileName));
                            int result = Student.passTheQuiz();
                            System.out.println(new RegistrationList(student, result));
                        } catch (NullPointerException ex) {
                            System.out.println("There is no quiz to delete!");
                        } catch (IOException e) {
                            e.getMessage();
                        }
                    }
                });
                studentMenu.run();
            }
        });
        menu.run();
    }
}


