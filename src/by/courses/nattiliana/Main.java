package by.courses.nattiliana;

import by.courses.nattiliana.entities.*;
import by.courses.nattiliana.tools.Reader;
import by.courses.nattiliana.enums.MenuItems;
import by.courses.nattiliana.menu.Menu;
import by.courses.nattiliana.menu.MenuItem;

import java.io.*;
import java.util.*;

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
                            List<Question> questionsList = new ArrayList<>();
                            questionsList.add(Tutor.createQuestion(2));
                            questionsList.add(Tutor.createQuestion(1));
                            questionsList.add(Tutor.createQuestion(3));
                            System.out.println("count: " + Question.count);
                            Quiz quiz = Tutor.createQuiz(questionsList);
                            System.out.println("Your quiz: " + quiz);
                            Tutor.serialize(quiz);
                        } catch (IOException e) {
                            e.getMessage();
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
                        Student.deserialize();
                        try {
                            Student.passTheQuiz();
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


