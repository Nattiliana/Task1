package main;

/**
 * Created by Nataly on 24.10.2016.
 * ${VERSION}
 */
public enum Item1 {
    AUTHORIZE_T(1, "Authorization"){
        @Override
        public String toString() {
            return ". Authorize as tutor";
        }
    },
    AUTHORIZE_S(2, "Authorization"){
        @Override
        public String toString() {
            return ". Authorize as student";
        }
    };

    private String message;
    private int index;

    Item1(int index, String message) {
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
