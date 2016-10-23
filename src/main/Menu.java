package main;

/**
 * Created by Nataly on 24.10.2016.
 * ${VERSION}
 */
public enum Menu {
    AUTHORIZE("1 - Authorize"),
    SM("2"),
    EXIT("3");

    private String message;

    Menu(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
