package main;

import entity.OutOfSelectionException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nataly on 24.10.2016.
 * ${VERSION}
 */
public class Menu {
    private List<MenuItem> items = new ArrayList<>();
    private boolean isExit = false;

    public Menu() {
        items.add(new MenuItem(". Exit") {
            @Override
            public void run() {
                isExit = true;
            }
        });
    }

    public void run() {
        while (!isExit) {
            printMenu();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                int choice = Integer.parseInt(reader.readLine());
                MenuItem item = items.get(choice - 1);
                item.run();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                System.out.println("You should use numbers!");
            } catch (IndexOutOfBoundsException e){
                System.out.println("Please, enter a valid selection.");
            }
        }
    }

    public Menu addEntry(MenuItem item) {
        int index = items.size() - 1;
        items.add(index, item);
        return this;
    }

    private void printMenu() {
        System.out.println("Menu:");
        for (int i = 0; i < items.size(); i++) {
            MenuItem item = items.get(i);
            System.out.println((i + 1) + item.getTitle());
        }
    }
}
