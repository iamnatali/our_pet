package com.company;

import java.util.Date;
import java.util.Calendar;

class PetBot {
    private String name="";
    private Gender genderType;
    private Calendar timeToEat;

    Calendar GetTimeToEat(){
        return timeToEat;
    }

    void Feed(){
            timeToEat.setTime(new Date());
            timeToEat.add(Calendar.MINUTE, 1);
    }

     PetBot(){
            timeToEat = Calendar.getInstance();
            Feed();
     }

    void giveName(String str){
        name=str;
    }

    String getName(){
        return name;
    }

    void chooseGender(String str) {
        genderType=Gender.getGender(str);
    }

    String learnGender(){
        return genderType.toString();
    }
}
