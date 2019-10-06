package com.company;


class PetBot {
    private String name;
    private String genderType;

    void giveName(String str){
        name=str;
    }

    String getName(){
        return name;
    }

    void chooseGender(String str) {
        genderType=str;
    }

    String learnGender(){
        return genderType;
    }
}
