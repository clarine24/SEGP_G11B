// No open source code or third-party libraries were used in this class.
// This class contains only original source code.

package com.example.rewire;

import android.os.CountDownTimer;
import android.widget.ProgressBar;

// Timer class is an abstract class.
public abstract class Timer {
    CountDownTimer timer;
    BT_Main bt_main;
    ProgressBar progressBar;
    int interval;
    long remainTime;

    // Create a Timer instance with the provided breathing exercise activity and progress bar
    public Timer(BT_Main bt_main, ProgressBar progressBar) {
        this.bt_main = bt_main;
        this.progressBar = progressBar;
    }

    // Abstract method used to create timers
    abstract void createTimer(int totalTime, int countDownInterval);

    // Creates a new timer and remove previous progress
    public void newTimer(int totalTime, int countDownInterval) {
        resetProgressBar(totalTime);
        createTimer(totalTime, countDownInterval);
    }

    // Start timer
    public void startTimer() {
        timer.start();
    }

    // Stop/pause timer
    public void stopTimer() {
        timer.cancel();
    }

    // Resume timer
    public void resumeTimer() {
        createTimer((int) remainTime, interval);
        startTimer();
    }

    // Reset progress bar to 0
    void resetProgressBar(int max) {
        progressBar.setMax(max);
        progressBar.setProgress(0);
    }

    // Update the progress bar
    void setProgressBar(long remainTime) {
        progressBar.setProgress((int) (progressBar.getMax() - remainTime));
    }
}
