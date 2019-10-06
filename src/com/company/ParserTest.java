package com.company;

import org.junit.After;
import org.junit.Assert;

class ParserTest {
    private PetBot pet=new PetBot();
    private Parser p=new Parser(pet);

    @After
    public void initConversation(){
        p.changeConversation(ConversationStates.notStarted);
    }

    @org.junit.jupiter.api.Test
    void getAudiowithEverythingRight() {
        p.changeConversation(ConversationStates.fullpet);
        String test=p.getAudio("/caress");
        Assert.assertEquals(test,"http://d.zaix.ru/eWkq.mp3");
    }

    //тесты пачкой
    @org.junit.jupiter.api.Test
    void getAudiowithEverythingWrong() {
        String test=p.getAudio("/feed");
        Assert.assertEquals(test,"http://d.zaix.ru/eWkq.mp3");
    }

    @org.junit.jupiter.api.Test
    void getButtonsNames() {
    }

    @org.junit.jupiter.api.Test
    void getParsedString() {
    }
}