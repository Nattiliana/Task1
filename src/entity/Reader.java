package entity;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by Nataly on 23.10.2016.
 * ${VERSION}
 */
public class Reader {

    public static String readLoginFromFile(String fileName) throws IOException {
        BufferedReader bufferedReader = null;
        try {
            File file = new File(fileName);
            bufferedReader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line;
        String loginFromFile = null;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                StringTokenizer stringTokenizer = new StringTokenizer(line);
                while (stringTokenizer.hasMoreTokens()) {
                    if (stringTokenizer.nextToken().contains("Login")) {
                        loginFromFile = stringTokenizer.nextToken();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loginFromFile;
    }

    public static String readPassFromFile(String fileName) throws IOException {
        BufferedReader bufferedReader = null;
        try {
            File file = new File(fileName);
            bufferedReader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line;
        String passFromFile = null;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                StringTokenizer stringTokenizer = new StringTokenizer(line);
                while (stringTokenizer.hasMoreTokens()) {
                    if (stringTokenizer.nextToken().contains("Pass")) {
                        passFromFile = stringTokenizer.nextToken();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return passFromFile;
    }

    public static String readNameFromFile(String fileName) throws IOException {
        BufferedReader bufferedReader = null;
        try {
            File file = new File(fileName);
            bufferedReader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line;
        String nameFromFile = null;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                StringTokenizer stringTokenizer = new StringTokenizer(line);
                while (stringTokenizer.hasMoreTokens()) {
                    if (stringTokenizer.nextToken().contains("Name")) {
                        nameFromFile = stringTokenizer.nextToken();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nameFromFile;
    }

    public static String readSurnameFromFile(String fileName) throws IOException {
        BufferedReader bufferedReader = null;
        try {
            File file = new File(fileName);
            bufferedReader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line;
        String surnameFromFile = null;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                StringTokenizer stringTokenizer = new StringTokenizer(line);
                while (stringTokenizer.hasMoreTokens()) {
                    if (stringTokenizer.nextToken().contains("Surname")) {
                        surnameFromFile = stringTokenizer.nextToken();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return surnameFromFile;
    }

    public static List<String> readQuizFromFile(String fileName) throws IOException {
        BufferedReader bufferedReader = null;
        try {
            File file = new File(fileName);
            bufferedReader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line;
        List<String> list = new ArrayList<>();
        try {
            while ((line = bufferedReader.readLine()) != null) {
                list.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<Integer> readAnswersFromFile(String fileName) throws IOException {
        BufferedReader bufferedReader = null;
        try {
            File file = new File(fileName);
            bufferedReader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line;
        List<Integer> list = new ArrayList<>();
        try {
            while ((line = bufferedReader.readLine()) != null) {
                StringTokenizer stringTokenizer = new StringTokenizer(line);
                while (stringTokenizer.hasMoreTokens()) {
                    if (stringTokenizer.nextToken().contains("Right answer:")) {
                        list.add(Integer.parseInt(stringTokenizer.nextToken()));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
