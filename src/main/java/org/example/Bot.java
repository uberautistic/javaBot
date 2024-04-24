package org.example;

import org.example.Questions.*;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.HashMap;

public class Bot extends TelegramLongPollingBot {

    private final HashMap<Long, UserData> userData;
    private ArrayList<AbstractQuestion> questions;

    public Bot() {
        userData = new HashMap<>();
        addQuestions();
    }

    @Override
    public String getBotUsername() {
        return "ZAYJava_bot";
    }

    @Override
    public String getBotToken() {
        return "6378236085:AAF6J55q7LAU3nqbJRVq16QXQ4D6Jloj_Vo";
    }

    public void sendText(Long who, String what) {
        SendMessage sm = SendMessage.builder()
                .chatId(who.toString())
                .text(what).build();
        try {
            execute(sm);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message msg = update.getMessage();
        String text = msg.getText();
        long uid = msg.getFrom().getId();
        if (text.equals("/start")) {
            sendText(uid, "Привет, это тестовый бот написанный на Java, приступим к тесту навыков по Java.");
            userData.put(uid, new UserData());
            sendText(uid, questions.get(0).getQuestionText());
        } else if (userData.get(uid).getQuestionNumber() > questions.size() - 1) {
            UserData data = userData.get(uid);
            int score = data.getCounter();
            int result = (int) (((double) score / questions.size()) * 100);
            sendText(uid, "Тест завершен!" +
                    "\nВаш результат: " + result + "%." +
                    "\nДля повторного прохождения теста отправьте /start.");
        } else {
            System.out.println(text);
            UserData data = userData.get(uid);
            int questionNumber = data.getQuestionNumber();
            boolean result = questions.get(questionNumber).checkAnswer(text);
            if (result)
                data.setCounter(data.getCounter() + 1);
            data.setQuestionNumber(data.getQuestionNumber() + 1);
            if (questionNumber + 1 == questions.size()) {
                int score = data.getCounter();
                int bresult = (int) (((double) score / questions.size()) * 100);
                sendText(uid, "Тест завершен!" +
                        "\nВаш результат: " + bresult + "%." +
                        "\nДля повторного прохождения теста отправьте /start.");
            } else
                sendText(uid, questions.get(data.getQuestionNumber()).getQuestionText());
        }
    }

    public void addQuestions() {
        questions = new ArrayList<>();
        questions.add(new JavaQuestion());
        questions.add(new SQLQuestion());
        questions.add(new GitQuestion());
        questions.add(new HTTPQuestion());
    }
}
