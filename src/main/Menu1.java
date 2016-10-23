package main;

/**
 * Created by Nataly on 24.10.2016.
 * ${VERSION}
 */
public enum Menu1 {
    AUTHORIZE("1 - Authorize!!!"),
    WQ("2"),
    EXIT("3");

    private String message;

    Menu1(String message){
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
