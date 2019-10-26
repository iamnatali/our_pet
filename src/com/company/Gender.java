package com.company;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Gender {

    male("мальчик"),
    female("девочка"),
    trans ("трансгендер");

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

    public static List<String> getTitles(){
        List<String> result = new ArrayList<>();
        for (Gender gender: Gender.values()) {
            result.add(gender.toString());
        }
        return result;
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
