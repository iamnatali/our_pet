package com.company;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendAudio;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.SQLException;
import java.util.*;

public class CommunicationPet extends TelegramLongPollingBot {
    private String botToken;
    private Parser parsedObject;
    private Map<Long, Parser> dict;

    CommunicationPet(Parser p, String botToken){
        parsedObject=p;
        this.botToken = botToken;
        dict = new HashMap<Long, Parser>();
        Timer tm = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                for (Long id : dict.keySet()) {
                    Parser parser = dict.get(id);
                    SendMessage msg = new SendMessage()
                            .setChatId(id)
                            .setText("");
                    if (parser.pet.getHunger() == 0) {
                        msg.setText("Я голодный!");
                    }
                    if (parser.pet.getWealth() == 0) {
                        msg.setText("Мне грустно!");
                    }
                    if (!msg.getText().equals(""))
                    {
                        try {
                            execute(msg);
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
        };
        tm.schedule(task, 10000, 1000);
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (! (update.hasMessage() && update.getMessage().hasText()))
            return;
        Long id=update.getMessage().getChatId();
        if(!dict.containsKey(id))
            dict.put(id, parsedObject);
        String myStr=update.getMessage().getText();
        //передаем mystr и id 3 раза(нехорошо, но парсер сделать инцциализирующимися mystr и id не можем)
        //может 1 метод, вызывающий 3 и возвращающий тройку?
        String parsedStr=parsedObject.getParsedString(myStr, id);
        String audioStr=parsedObject.getAudio(myStr);
        List<String> parsedBut=parsedObject.getButtonsNames(myStr);
        SendMessage message = new SendMessage()
                .setChatId(update.getMessage().getChatId())
                .setText(parsedStr);
        if (!parsedBut.isEmpty()) {
            setButtons(message, parsedBut);
        }

        if (!audioStr.equals("")){
            SendAudio audio=new SendAudio();
            audio.setAudio(audioStr);
            audio.setTitle("мурррррр");
            audio.setChatId(id);
            audio.setCaption("муррр");
            try {
                execute(audio);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "@BreadPetBot";
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    private synchronized void setButtons(SendMessage sendMessage, List<String> bnames)
    {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        List<KeyboardRow> keyboard = new ArrayList<>();

        for (String name:bnames) {
            KeyboardRow keyboardRow = new KeyboardRow();
            keyboardRow.add(new KeyboardButton(name));
            keyboard.add(keyboardRow);
        }

        replyKeyboardMarkup.setKeyboard(keyboard);
    }
}
