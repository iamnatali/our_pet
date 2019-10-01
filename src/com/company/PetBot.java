package com.company;

import java.util.HashMap;

public class PetBot {
    private HashMap<GenderType, String> gendersString;
    public String greet="Добро пожаловать в наш зоомагазин. Выбирайте питомца(бла-бла)";
    private String name;
    private GenderType genderType;
    public PetBot(){
        gendersString = new HashMap<>();
        gendersString.put(GenderType.male, "джентельмен");
        gendersString.put(GenderType.female,"леди");
        gendersString.put(GenderType.trans,"трансгендерный лапушка");
    }

    void giveName(String str){
        name=str;
    }

    String getName(){
        return name;
    }

    void chooseGender(String str) throws IllegalArgumentException {
        str=str.toUpperCase();
        if (str.equals(""))
            throw new IllegalArgumentException("Введите М/Ж/Т");
        char ch=str.charAt(0);
        switch (ch) {
            case ('Т'):
                genderType = GenderType.trans;
            case ('Ж'):
                genderType = GenderType.female;
                break;
            case ('М'):
                genderType = GenderType.male;
                break;
        }
    }
    String learnGender(){
        return gendersString.get(genderType);
    }
}
