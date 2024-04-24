package org.example.Questions;

public class HTTPQuestion extends AbstractQuestion {
    private String[] methods = {"get", "post", "put", "patch", "delete"};

    public HTTPQuestion() {
        super("Перечислите основные методы HTTP-запросов через пробел в одну строку.");
    }

    @Override
    public boolean checkAnswer(String answer) {
        answer = answer.toLowerCase();
        for (String method : methods) {
            if (!answer.contains(method))
                return false;
        }
        return true;
    }
}
