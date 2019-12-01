package com.company;

import java.util.Date;
import java.util.Calendar;

class PetBot {
    private String name="";
    private Gender genderType;
    private Calendar timeToEat;
    private final Scale wealth;

    PetBot(){
        timeToEat = Calendar.getInstance();
        feed();
        System.err.println("New PetBot");
        wealth = new Scale(10, 1000 * 3);
    }

    Calendar GetTimeToEat(){
        return timeToEat;
    }

    public void setWealth(int val){
        wealth.SetValue(val);
    }

    public void care(){
        wealth.UpValue();
    }

    public int getWealth(){
        return wealth.GetValue();
    }

    void feed(){
            timeToEat.setTime(new Date());
            timeToEat.add(Calendar.MINUTE, 1);
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
