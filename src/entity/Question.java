package entity;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Nataly on 13.10.2016.
 */
public class Question implements Comparator<Question> {

    private int questionNumber;
    private String question;
    private Map<Integer, String> answerMap = new TreeMap<>();
    private int rightAnswer;

    /**
     * Instantiates a new Question.
     *
     * @param questionNumber the question number
     * @param question       the question
     * @param answerMap      the answer map
     * @param rightAnswer    the right answer
     */
    public Question(int questionNumber, String question, Map<Integer, String> answerMap, int rightAnswer) {
        this.questionNumber = questionNumber;
        this.question = question;
        this.answerMap = answerMap;
        this.rightAnswer = rightAnswer;
    }

    public Question() {
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Question)) {
            return false;
        }
        Question other = (Question) obj;
        if (question == null) {
            if (other.question != null) {
                return false;
            } else if (!question.equals(other.question)) {
                return false;
            }
        }
        if (questionNumber != other.questionNumber) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return 31 * questionNumber + ((question == null) ? 0 : question.hashCode());
    }

    @Override
    public String toString() {
        return "\nQuestion number: " + questionNumber + " Question: " + question
                + " Answers: " + answerMap + " Right answer: " + rightAnswer;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Map<Integer, String> getAnswerMap() {
        return answerMap;
    }

    public void setAnswerMap(Map<Integer, String> answerMap) {
        this.answerMap = answerMap;
    }

    public int getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(int rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    @Override
    public int compare(Question q1, Question q2) {
        return q1.questionNumber - q2.questionNumber;
    }
}
