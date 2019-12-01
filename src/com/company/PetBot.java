package com.company;

class PetBot {
    private String name="";
    private Gender genderType;
    private final Scale wealth;
    private final Scale hunger;

    PetBot(){
        wealth = new Scale(10, 1000 * 60);
        hunger = new Scale(10, 1000 * 60);
    }

    void care(){
        wealth.upValue();
    }

    String getStrWealth(){return wealth.getStringScale();}

    int getWealth(){
        return wealth.getValue();
    }

    void feed(){
        hunger.upValue();
    }

    String getStrHunger(){return hunger.getStringScale();}

    int getHunger(){return hunger.getValue();}

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
