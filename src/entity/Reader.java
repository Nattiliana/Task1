package entity;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * Created by Nataly on 23.10.2016.
 * ${VERSION}
 */
public class Reader {

    public static String readLoginFromFile() throws IOException {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader("logins.txt"));
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

    public static String readPassFromFile() throws IOException {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader("logins.txt"));
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

    public static String readNameFromFile() throws IOException {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader("logins.txt"));
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

    public static String readSurnameFromFile() throws IOException {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader("logins.txt"));
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
}
