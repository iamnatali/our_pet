package com.company;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //могу создавать сколько угодно подключений к базе или не стоит?
        ApiContextInitializer.init();
        System.out.println("Введите токен");
        Scanner scanner = new Scanner(System.in);
        String token = scanner.nextLine();
        TelegramBotsApi botsApi = new TelegramBotsApi();
        PetBot myPet = new PetBot();
        RunTimer timer = new RunTimer();
        timer.run(myPet.getHunger(), 1000*60);
        timer.run(myPet.getWealth(), 1000*60);
        Parser parser = new Parser(myPet);
        try {
            botsApi.registerBot(new CommunicationPet(parser, token));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
