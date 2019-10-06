package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Parser {
    //private List<String> buttonsNames;
    private String conversation;
    private PetBot pet;
    private final List<String> genders=Arrays.asList("девочка", "мальчик", "трангендер");

    //сделать conversation классом
    //if (conversation.equals("name")) можно сделать выбор животного/породы
    //обработать все возможные ошибки пользователя
    //команды в enum
    //повторения return
    //уже сделанные разные клавиатуры(?)
    //создать объект парсера из строки и кнопок и хайда

    String GetAudio(String rawstr){
        if (conversation.equals("fullpet") && rawstr.equals("/caress")){
            return "http://d.zaix.ru/eWkq.mp3";
        }
        return "none";
    }

    List<String> GetButtonsNames(String rawstr){
        List<String> buttonsNames=new ArrayList<>();
        if (rawstr.equals("/start")){
            buttonsNames=genders;
        }//else if (conversation.equals("name"))
        //выставить флаг хайд
        return buttonsNames;
    }

    Parser(PetBot p){
        pet=p;
        conversation="notStarted";
        //buttonsNames=new ArrayList<>(){};
    }

    String GetParsedString(String rawstr){
        String parsedString = "Не могу вас понять";
        if (rawstr.equals("/help")){
            parsedString = "Добро пожаловать в чат общения с вашим питоцем./n" +
                    "Используйте команду /start, чтобы завести питомца/n" +
                    "А затем заботьтесь о нем командами /caress и /feed" +
                    "Если вы будете хорошим хозяином, то цвидите как он растет и учится новому)" +
                    "Не забывайте о нем, ведь без вас он погибнет!";
            return parsedString;
        }
        else{
            if (rawstr.equals("/rollback") && !conversation.equals("notStarted")){
                conversation="notStarted";
                return "используйте /start, чтобы завести питомца снова";
            }
        }
        switch (conversation){
            case "fullpet":{
                switch (rawstr){
                    case("/feed"):{
                        parsedString ="ням-ням";
                        return parsedString;
                    }
                    case("/admire"):{
                        parsedString ="Ваш питомец-"+pet.learnGender()+"!Его(ее) имя "+pet.getName();
                        return parsedString;
                    }
                    case("/caress"):{
                        parsedString ="муррр";
                        return parsedString;
                    }
                    case("/rename"):{
                        conversation="name";
                        parsedString ="Сейчас Вашего питомца зовут"+pet.getName()+". Введите новое имя";
                        //return parsedString;
                    }
                }
            }
            case "notStarted":{
                if (rawstr.equals("/start")){
                    conversation="genderChoice";
                    parsedString ="Выберите пол вашего питомца";
                    return parsedString;
                }
                break;
            }
            case "genderChoice":{
                pet.chooseGender(rawstr);
                parsedString ="Как будут звать вашего питомца?";
                conversation="name";
                return parsedString;
            }
            case "name":{
                pet.giveName(rawstr);
                conversation="fullpet";
                parsedString ="Теперь у вас есть питомец-"+pet.learnGender()+"!Его(ее) имя "+pet.getName();
                return parsedString;
            }
        }
        return parsedString;
    }

}
