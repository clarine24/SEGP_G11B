package com.example.rewire;

import android.os.CountDownTimer;
import android.widget.ProgressBar;

public abstract class Timer {
    CountDownTimer timer;
    BT_Main bt_main;
    ProgressBar progressBar;
    int interval;
    long remainTime;

    public Timer(BT_Main bt_main, ProgressBar progressBar) {
        this.bt_main = bt_main;
        this.progressBar = progressBar;
    }

    abstract void createTimer(int d, int i);

    public void newTimer(int d, int i) {
        resetProgressBar(d);
        createTimer(d,i);
    }

    public void startTimer() {
        timer.start();
    }

    public void stopTimer() {
        timer.cancel();
    }

    public void resumeTimer() {
        createTimer((int) remainTime, interval);
        startTimer();
    }

    void resetProgressBar(int t) {
        progressBar.setMax(t);
        progressBar.setProgress(0);
    }

    void setProgressBar(long l) {
        progressBar.setProgress((int) (progressBar.getMax() - l));
    }
}
