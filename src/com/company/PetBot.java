package com.company;

class PetBot {
    private String name="";
    private Gender genderType;
    private final Scale wealth;
    private final Scale hunger;

    PetBot(){
        wealth = new Scale(10);
        hunger = new Scale(10);
    }

    void care(){
        wealth.upValue();
    }

    Scale getWealth(){
        return wealth;
    }

    void feed(){
        hunger.upValue();
    }

    Scale getHunger(){return hunger;}

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
