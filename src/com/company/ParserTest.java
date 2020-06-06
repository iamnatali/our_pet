package com.company;

import org.junit.After;
import org.junit.Assert;

import java.util.List;

class ParserTest {
    private StringConst strConst;
    private PetBot pet=new PetBot();
    private Parser p=new Parser(pet);

    ParserTest(){
        strConst=new StringConst();
    }

    @After
    public void initConversation(){
        p.changeConversation(ConversationStates.notStarted);
    }

    @org.junit.jupiter.api.Test
    void getAudioEverythingRight() {
        p.changeConversation(ConversationStates.fullpet);
        String test=p.getAudio("/caress");
        Assert.assertEquals(test,"http://d.zaix.ru/eWkq.mp3");
    }

    //тесты пачкой
    @org.junit.jupiter.api.Test
    void getAudioEverythingWrong() {
        String test=p.getAudio("/feed");
        Assert.assertNotEquals(test,"http://d.zaix.ru/eWkq.mp3");
    }

    @org.junit.jupiter.api.Test
    void getButtonsNamesEverythingRight() {
        List<String> test=p.getButtonsNames("/start");
        Assert.assertEquals(test, Gender.getTitles());
    }

    @org.junit.jupiter.api.Test
    void getButtonsNamesEverythingWrong() {
        List<String> test=p.getButtonsNames("/feed");
        Assert.assertNotEquals(test, Gender.getTitles());
    }

    @org.junit.jupiter.api.Test
    void getParsedStringRollbackCheck() {
        p.changeConversation(ConversationStates.fullpet);
        String test=p.getParsedString("/rollback", (long) 123);
        Assert.assertEquals(test, strConst.rollback);
    }

    @org.junit.jupiter.api.Test
    void getParsedStringFullpetFeed() {
        p.changeConversation(ConversationStates.fullpet);
        String test=p.getParsedString("/feed", (long) 123);
        Assert.assertEquals(test,"ням-ням");
    }

    @org.junit.jupiter.api.Test
    void getParsedStringFullpetAdmire() {
        p.changeConversation(ConversationStates.fullpet);
        String test=p.getParsedString("/admire", (long) 123);
        Assert.assertEquals(test, "Ваш питомец-"+pet.learnGender()+"!Его(ее) имя "+pet.getName());
    }

    @org.junit.jupiter.api.Test
    void getParsedStringFullpetCaress() {
        p.changeConversation(ConversationStates.fullpet);
        String test=p.getParsedString("/caress", (long) 123);
        Assert.assertEquals(test,"муррр");
    }

    @org.junit.jupiter.api.Test
    void getParsedStringFullpetRename() {
        p.changeConversation(ConversationStates.fullpet);
        String test=p.getParsedString("/rename", (long) 123);
        Assert.assertEquals(test, "Сейчас Вашего питомца зовут "+pet.getName()+". Введите новое имя");
    }

    @org.junit.jupiter.api.Test
    void getParsedStringNotStartedRight() {
        p.changeConversation(ConversationStates.notStarted);
        String test=p.getParsedString("/start", (long) 123);
        Assert.assertEquals(test,strConst.genderChoice);
    }

    @org.junit.jupiter.api.Test
    void getParsedStringNotStartedWrong() {
        String defaultString=strConst.defaultString;
        p.changeConversation(ConversationStates.notStarted);
        String test=p.getParsedString("/feed", (long) 123);
        Assert.assertEquals(test, defaultString);
    }

    @org.junit.jupiter.api.Test
    void getParsedStringGenderChoice() {
        p.changeConversation(ConversationStates.genderChoice);
        String test=p.getParsedString("мальчик", (long) 123);
        Assert.assertEquals(test,strConst.nameChoice);
    }

    @org.junit.jupiter.api.Test
    void getParsedStringName() {
        p.changeConversation(ConversationStates.name);
        String test=p.getParsedString("суртур", (long) 123);
        Assert.assertEquals(test, "Теперь у вас есть питомец-"
                +pet.learnGender()+"!Его(ее) имя "+pet.getName());
    }
}