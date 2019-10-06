package com.company;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Main {

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();
        PetBot mypet=new PetBot();
        Parser parser=new Parser(mypet);
        try {
            botsApi.registerBot(new NewPet(parser));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
