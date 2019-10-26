package com.company;


class PetBot {
    private String name="";
    private Gender genderType;

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
