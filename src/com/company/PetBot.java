package com.company;

import java.util.Timer;
import java.util.TimerTask;

class PetBot {
    private String name="";
    private Gender genderType;
    private Boolean hungry = false;

    public Boolean IsHungry(){
        return hungry;
    }

    public void Feed(){
        hungry = false;
    }

     PetBot(){
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                hungry = true;
            }
        };

        timer.schedule(task, 20000, 20000);
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
