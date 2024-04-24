package org.example.Questions;

public abstract class AbstractQuestion {
    private String questionText;

    public AbstractQuestion(String _questionText) {
        questionText = _questionText;
    }

    public String getQuestionText() {
        return questionText;
    }

    public abstract boolean checkAnswer(String answer);
}
