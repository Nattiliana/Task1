package main;

/**
 * Created by Nataly on 24.10.2016.
 * ${VERSION}
 */
public enum Item2 {
    CREATE_QUIZ(1, "Creating quiz"){
        @Override
        public String toString() {
            return ". Create quiz";
        }
    },
    ANSWER_QUIZ(1, "Answer the questions below"){
        @Override
        public String toString() {
            return ". Pass the quiz";
        }
    };

    private String message;
    private int index;

    Item2(int index, String message) {
        this.index = index;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public int getIndex() {
        return index;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
