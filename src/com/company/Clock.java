package com.company;

import java.util.Timer;
import java.util.TimerTask;

public class Clock {
    private int secondsPassed;

    Timer timer;
    TimerTask timerTask;

    public void Clock(){

        this.secondsPassed = 0;
        this.timer = new Timer();
        this.timerTask = new TimerTask() {
            public void run() {
                secondsPassed++;
                System.out.println("Second passed: " + secondsPassed);
            }
        };
    }

    public void clockStart() {
        timer.scheduleAtFixedRate(timerTask, 1000, 1000);
    }

    public int getSecondsPassed(){return secondsPassed;};
}
