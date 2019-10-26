package com.company;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        PetDB db=new PetDB();
        ApiContextInitializer.init();
        System.out.println("Введите токен");
        Scanner scaner = new Scanner(System.in);
        String token = scaner.nextLine();
        TelegramBotsApi botsApi = new TelegramBotsApi();
        PetBot myPet = new PetBot();
        Parser parser = new Parser(myPet);
        try {
            botsApi.registerBot(new CommunicationPet(parser, token, db));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
