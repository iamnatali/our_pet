package com.company;

import java.util.Calendar;
import java.util.TimerTask;
import java.util.Timer;

public class Scale {
    private int size;
    private int value;

    public Scale(int maxValue, int timeAmount){
        size = maxValue;
        value = maxValue;
        Timer t = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println(value);
                DownValue();
            }
        };

        t.schedule(task, 0, timeAmount);
    }

    public void SetValue(int val){
        this.value=val;
    }

    public int GetValue(){
        return value;
    }

    public void UpValue(){
        if (value < size) {
            value = value + 1;
        }
    }

    public void DownValue(){
        if (value > 0) {
            value = value - 1;
            System.out.println("down value");
        }
    }
}
