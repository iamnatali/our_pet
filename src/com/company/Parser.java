package com.company;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Parser {
    private ConversationStates conversation;
    private PetBot pet;
    private final List<String> genders=Arrays.asList("девочка", "мальчик", "трансгендер");

    //обработать все возможные ошибки пользователя
    //команды в enum
    //повторения return
    //уже сделанные разные клавиатуры(?)

    void changeConversation(ConversationStates newconv){
        conversation=newconv;
    }

    ConversationStates getConversation(){
        return conversation;
    }

    String getAudio(String rawstr){
        if (conversation.equals(ConversationStates.fullpet)
                && rawstr.equals("/caress")){
            return "http://d.zaix.ru/eWkq.mp3";
        }
        return "";
    }

    List<String> getButtonsNames(String rawstr){
        List<String> buttonsNames=new ArrayList<>();
        if (rawstr.equals("/start")){
            buttonsNames=genders;
        }
        return buttonsNames;
    }

    Parser(PetBot p){
        pet=p;
        conversation=ConversationStates.notStarted;
    }

    String getParsedString(String rawstr){
        StringConst strConst=new StringConst();
        String defaultString=strConst.defaultstring;
        String parsedString = defaultString;
        if (rawstr.equals("/help")){
            parsedString = strConst.help;
            return parsedString;
        }
        else{
            if (rawstr.equals("/rollback")
                    && !conversation.equals(ConversationStates.notStarted)){
                conversation=ConversationStates.notStarted;
                return strConst.rollback;
            }
        }
        switch (conversation){
            case fullpet:{
                switch (rawstr){
                    case("/feed"):{
                        return "ням-ням";
                    }
                    case("/admire"):{
                        return "Ваш питомец-"+pet.learnGender()+"!Его(ее) имя "+pet.getName();
                    }
                    case("/caress"):{
                        return "муррр";
                    }
                    case("/rename"):{
                        conversation=ConversationStates.name;
                        parsedString ="Сейчас Вашего питомца зовут "+pet.getName()+". Введите новое имя";
                        return parsedString;
                        //не ошибка
                    }
                    default:{
                        return defaultString;
                    }
                }
            }
            case notStarted:{
                if (rawstr.equals("/start")){
                    conversation=ConversationStates.genderChoice;
                    return strConst.genderChoice;
                }
                break;
            }
            case genderChoice:{
                pet.chooseGender(rawstr);
                conversation=ConversationStates.name;
                return strConst.nameChoice;
            }
            case name:{
                pet.giveName(rawstr);
                conversation=ConversationStates.fullpet;
                parsedString ="Теперь у вас есть питомец-"+pet.learnGender()+"!Его(ее) имя "+pet.getName();
                return parsedString;
            }
            default:{
                throw new IllegalArgumentException("неправильный conversation state");
            }
        }
        return parsedString;
    }

}