package com.company;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    private String parsedString;
    private List<String> buttonsNames;

    public String GetParsedString(){
        return parsedString;
    }

    public List<String> GetButtonsNames(){
        return buttonsNames;
    }

    public Parser(String rawstr){
        buttonsNames=new ArrayList<>(){};
        String help = "Добро пожаловать в чат общения с вашим(и) питоцем(ами).Помощь консультанта-/help";
        if (rawstr.equals("/help")){
            parsedString= help;
        }
        if (rawstr.equals("/start")){
            //хранить массив питомцев?(спрашивать есть ли питомец)
            buttonsNames.add("Девочка");
            buttonsNames.add("Мальчик");
            buttonsNames.add("Трангендер");
            parsedString="Выберите пол вашего питомца";
        }
    }

}
