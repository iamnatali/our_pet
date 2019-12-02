package com.company;

import java.util.Timer;
import java.util.TimerTask;

public class RunTimer {
    public void run(Scale scale, int timeAmount){
        Timer t = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                scale.DownValue();
                System.out.println("downValue");
            }
        };
        t.schedule(task, 0, timeAmount);
    }

}
