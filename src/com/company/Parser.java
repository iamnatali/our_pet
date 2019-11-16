package com.company;


import java.util.HashMap;
import java.util.Map;

import java.sql.SQLException;
import java.util.ArrayList;
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
            HashMap<PetBot,Boolean> resMap=db.getData(id);
            PetBot resKey = null;
            Boolean resValue = false;

            for ( Map.Entry<PetBot, Boolean> entry : resMap.entrySet()
                 ) {
                resKey = entry.getKey();
                resValue = entry.getValue();
            }
            if (resValue){
                pet=resKey;
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
        else if (rawstr.equals("/rollback") && !conversation.equals(ConversationStates.notStarted)){
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

        switch (conversation){
            case fullpet:{
                switch (rawstr){
                    case("/feed"):{
                        pet.Feed();
                        return strConst.feed;
                    }
                    case("/admire"):{
                        return strConst.getAdmireString(pet.getName(), pet.learnGender());
                    }
                    case("/caress"):{
                        return "муррр";
                    }
                    case("/rename"):{
                        conversation=ConversationStates.name;
                        parsedString=strConst.getRenameString(pet.getName());
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
                    HashMap<PetBot,Boolean> resMap=db.getData(id);
                    Boolean resValue = false;
                    for (Map.Entry<PetBot, Boolean> entry: resMap.entrySet()){
                        resValue = entry.getValue();
                    }
                    if (resValue){
                        db.updateData(id,pet);
                    }
                    else{
                        db.setData(id, pet);
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }
                conversation=ConversationStates.fullpet;
                parsedString = strConst.getFullPetString(pet.getName(), pet.learnGender());
                return parsedString;
            }
            default:{
                throw new IllegalArgumentException("неправильный conversation state");
            }
        }
        return parsedString;
    }

}