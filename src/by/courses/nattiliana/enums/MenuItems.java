package by.courses.nattiliana.enums;

/**
 * Created by Nataly on 24.10.2016.
 * ${VERSION}
 */
public enum MenuItems {
    AUTHORIZE_T("Authorization"){
        @Override
        public String toString() {
            return ". Authorize as tutor";
        }
    },
    AUTHORIZE_S("Authorization"){
        @Override
        public String toString() {
            return ". Authorize as student";
        }
    },
    CREATE_QUIZ("Creating quiz"){
        @Override
        public String toString() {
            return ". Create quiz";
        }
    },
    ANSWER_QUIZ("Answer the questions below"){
        @Override
        public String toString() {
            return ". Pass the quiz";
        }
    };

    private String message;

    MenuItems(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
