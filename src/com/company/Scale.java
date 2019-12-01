package com.company;

import java.util.Calendar;
import java.util.TimerTask;
import java.util.Timer;

class Scale {
    private int size;
    private int value;

    Scale(int maxValue, int timeAmount){
        size = maxValue;
        value = maxValue;
        Timer t = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                DownValue();
            }
        };
        t.schedule(task, 0, timeAmount);
    }

    String getStringScale(){
        StringBuilder result = new StringBuilder();
        result.append("| ");
        for (int i = 0; i < size; i++){
            if (i < value)
                result.append("█ ");
            else
                result.append("    ");
        }
        result.append('|');
        return result.toString();
    }

    int getValue(){
        return value;
    }

    void upValue(){
        if (value < size) {
            value = value + 1;
        }
    }

    private void DownValue(){
        if (value > 0) {
            value = value - 1;
        }
    }
}
