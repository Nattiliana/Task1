package main;

import entity.*;
import entity.Reader;

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

        Menu menu = new Menu();
        menu.addEntry(new MenuItem(Item1.AUTHORIZE_T.toString()) {
            @Override
            public void run() {
                System.out.println(Item1.AUTHORIZE_T.getMessage());
                Tutor.authorize();
                Menu tutorMenu = new Menu();
                tutorMenu.addEntry(new MenuItem(Item2.CREATE_QUIZ.toString()) {
                    @Override
                    public void run() {
                        try {
                            System.out.println(Item2.CREATE_QUIZ.getMessage());
                            List<Question> questionsList = new ArrayList<>();
                            questionsList.add(createQuestion(2));
                            questionsList.add(createQuestion(1));
                            questionsList.add(createQuestion(3));
                            System.out.println("count: " + Question.count);
                            Quiz quiz = Main.createQuiz(questionsList);
                            System.out.println("Your quiz: " + quiz);
                            Main.serialize(quiz);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                tutorMenu.run();

            }
        });
        menu.addEntry(new MenuItem(Item1.AUTHORIZE_S.toString()) {
            @Override
            public void run() {
                System.out.println(Item1.AUTHORIZE_S.getMessage());
                Student.authorize();
                Menu studentMenu = new Menu();
                studentMenu.addEntry(new MenuItem(Item2.ANSWER_QUIZ.toString()) {
                    @Override
                    public void run() {
                        System.out.println(Item2.ANSWER_QUIZ.getMessage());
                        Main.deserialize();
                        for (int i = 0; i < 2; i++) {
                            try {
                                System.out.println("Enter the answer for " + (i + 1) + " question");
                                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                                int answer = Integer.parseInt(bufferedReader.readLine());
                                int correctAnswer = Reader.readAnswersFromFile("answers.txt").get(i);
                                if (answer == correctAnswer){
                                    System.out.println("Your answer is correct!");
                                }
                                else {
                                    System.out.println("Your answer is wrong!");
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
                studentMenu.run();
            }
        });
        menu.run();
    }

    public static void serialize(Quiz quiz) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("serialize.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(quiz);
            objectOutputStream.close();
            fileOutputStream.close();
            System.out.println("Serialized");
        } catch (
                IOException ex)

        {
            ex.printStackTrace();
        }
    }

    public static void deserialize() {
        Quiz quiz;
        try {
            FileInputStream fileInputStream = new FileInputStream("serialize.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            quiz = (Quiz) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            return;
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found!");
            e.printStackTrace();
            return;
        }
        System.out.println("Deserialize object" + quiz);
    }

    public static void writeAnswersToFile(String fileName, int answer) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(fileName, true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
        try {
            bufferedWriter.append("Right answer: " + answer);
            bufferedWriter.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                bufferedWriter.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            try {
                fileOutputStream.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private static Question createQuestion(int questionNumber) throws IOException {

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
                Main.writeAnswersToFile("answers.txt", rightAnswer);
                isChecked = true;
            } catch (NumberFormatException ex) {
                System.out.println("You should use numbers!");
            } catch (OutOfSelectionException e) {
                System.out.println("Please, enter a valid number of answer.");
            }
        } while (!isChecked);
        Question question = new Question(questionNumber, questionText, questionMap, rightAnswer);
        return question;
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
                e.printStackTrace();
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
                e.printStackTrace();
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
        Quiz quiz = new Quiz(quizName, subject, newList, calendar.getTime());
        return quiz;
    }

}


