package com.company;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Main {

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();
        PetBot myPet=new PetBot();
        Parser parser=new Parser(myPet);
        try {
            botsApi.registerBot(new CommunicationPet(parser));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
