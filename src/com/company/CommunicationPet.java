package com.company;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendAudio;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class CommunicationPet extends TelegramLongPollingBot {
    private String botToken;
    private Parser parsedObject;
    CommunicationPet(Parser p, String botToken){
        parsedObject=p;
        this.botToken = botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            String myStr=update.getMessage().getText();
            Long id=update.getMessage().getChatId();
            String parsedStr=parsedObject.getParsedString(myStr);
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
