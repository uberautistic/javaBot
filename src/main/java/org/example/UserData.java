package org.example;

public class UserData {
    private int questionNumber;
    private int counter;

    public UserData() {
        counter = 0;
        questionNumber = 0;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public int getCounter() {
        return counter;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
