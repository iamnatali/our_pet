package com.company;


import javafx.util.Pair;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Parser {
    private ConversationStates conversation;
    PetBot pet;
    private final List<String> genders=Gender.getTitles();
    private PetDB db=new PetDB();
    //уже сделанные разные клавиатуры(?)

    void changeConversation(ConversationStates newconv){
        conversation=newconv;
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
        if (rawstr.equals("/start") && conversation.equals(ConversationStates.genderChoice)){
            buttonsNames=genders;
        }
        return buttonsNames;
    }

    Parser(PetBot petBot){
        pet=petBot;
        conversation=ConversationStates.notStarted;
    }

    String getParsedString(String rawstr, Long id){
        StringConst strConst=new StringConst();
        String defaultString=strConst.defaultstring;
        String parsedString = defaultString;
        try {
            Pair<PetBot,Boolean> resPair=db.getData(id);
            if (resPair.getValue()){
                pet=resPair.getKey();
                if (conversation!=ConversationStates.name) {
                    conversation = ConversationStates.fullpet;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (rawstr.equals("/help")){
            parsedString = strConst.help;
            return parsedString;
        }
        else{
            if (rawstr.equals("/rollback")
                    && !conversation.equals(ConversationStates.notStarted)){
                if (conversation.equals(ConversationStates.fullpet)){
                    try {
                        db.deleteData(id);
                    }catch (SQLException e){
                        e.printStackTrace();
                    }
                }
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
                try{
                    Pair<PetBot,Boolean> resPair=db.getData(id);
                    if (resPair.getValue()){
                        db.updateData(id,pet);
                    }
                    else{
                        db.setData(id, pet);
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }
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