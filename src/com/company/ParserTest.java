package com.company;

import org.junit.After;
import org.junit.Assert;

import java.util.Arrays;
import java.util.List;

class ParserTest {
    private PetBot pet=new PetBot();
    private Parser p=new Parser(pet);

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
        Assert.assertEquals(test, Arrays.asList("девочка", "мальчик", "трансгендер"));
    }

    @org.junit.jupiter.api.Test
    void getButtonsNamesEverythingWrong() {
        List<String> test=p.getButtonsNames("/feed");
        Assert.assertNotEquals(test, Arrays.asList("девочка", "мальчик", "трансгендер"));
    }

    @org.junit.jupiter.api.Test
    void getParsedStringRollbackCheck() {
        p.changeConversation(ConversationStates.fullpet);
        String test=p.getParsedString("/rollback");
        Assert.assertEquals(test,"используйте /start, чтобы завести питомца снова");
    }

    @org.junit.jupiter.api.Test
    void getParsedStringFullpetFeed() {
        p.changeConversation(ConversationStates.fullpet);
        String test=p.getParsedString("/feed");
        Assert.assertEquals(test,"ням-ням");
    }

    @org.junit.jupiter.api.Test
    void getParsedStringFullpetAdmire() {
        p.changeConversation(ConversationStates.fullpet);
        String test=p.getParsedString("/admire");
        Assert.assertEquals(test, "Ваш питомец-"+pet.learnGender()+"!Его(ее) имя "+pet.getName());
    }

    @org.junit.jupiter.api.Test
    void getParsedStringFullpetCaress() {
        p.changeConversation(ConversationStates.fullpet);
        String test=p.getParsedString("/caress");
        Assert.assertEquals(test,"муррр");
    }

    @org.junit.jupiter.api.Test
    void getParsedStringFullpetRename() {
        p.changeConversation(ConversationStates.fullpet);
        String test=p.getParsedString("/rename");
        Assert.assertEquals(test, "Сейчас Вашего питомца зовут "+pet.getName()+". Введите новое имя");
    }

    @org.junit.jupiter.api.Test
    void getParsedStringNotStartedRight() {
        p.changeConversation(ConversationStates.notStarted);
        String test=p.getParsedString("/start");
        Assert.assertEquals(test,"Выберите пол вашего питомца");
    }

    @org.junit.jupiter.api.Test
    void getParsedStringNotStartedWrong() {
        String newLine = System.getProperty("line.separator");
        String defaultString="Не могу вас понять("+newLine+
                "Возможно вы используете команды до полноценного создания питомца"+newLine+
                "начните с команды /start";
        p.changeConversation(ConversationStates.notStarted);
        String test=p.getParsedString("/feed");
        Assert.assertEquals(test, defaultString);
    }

    @org.junit.jupiter.api.Test
    void getParsedStringGenderChoice() {
        p.changeConversation(ConversationStates.genderChoice);
        String test=p.getParsedString("мальчик");
        Assert.assertEquals(test,"Как будут звать вашего питомца?");
    }

    @org.junit.jupiter.api.Test
    void getParsedStringName() {
        p.changeConversation(ConversationStates.name);
        String test=p.getParsedString("суртур");
        Assert.assertEquals(test, "Теперь у вас есть питомец-"
                +pet.learnGender()+"!Его(ее) имя "+pet.getName());
    }
}