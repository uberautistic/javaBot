package org.example.Questions;

public class JavaQuestion extends AbstractQuestion {
    public JavaQuestion() {
        super(" Сколько в ЯП Java есть примитивов?");
    }

    @Override
    public boolean checkAnswer(String answer) {
        return answer.equals("8");
    }
}
