package by.courses.nattiliana.menu;

/**
 * Created by Nataly on 24.10.2016.
 * ${VERSION}
 */
abstract public class MenuItem {
    private String title;

    public MenuItem(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public abstract void run();
}
