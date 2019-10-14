package com.company;

public enum Gender {

    male("мальчик"),
    female("девочка"),
    trans ("трансгендерный лапушка");

    public String title;

    Gender(String title){
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

    @Override
    public String toString(){
        return title;
    }

    public static Gender getGender(String str){
        switch(str){
            case("трансгендер"):
                return Gender.trans;
            case("девочка"):
                return Gender.female;
            case("мальчик"):
                return Gender.male;
            default:
                throw new RuntimeException("BadArguments");
        }
    }
}
