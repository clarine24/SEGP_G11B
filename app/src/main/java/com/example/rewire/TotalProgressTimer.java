// No open source code or third-party libraries were used in this class.
// This class contains only original source code.

package com.example.rewire;

import android.os.CountDownTimer;
import android.os.Handler;
import android.widget.TextView;

// TotalProgressTimer is a subclass of Timer
// It is used to keep track of the entire progress of the breathing exercise
public class TotalProgressTimer extends Timer {
    private Handler roundHandler;
    private Runnable roundRunnable;
    private TextView round;

    // Create a TotalProgressTimer instance with the given breathing exercise activity
    public TotalProgressTimer(BT_Main bt_main) {
        super(bt_main, bt_main.roundProgressBar);
        round = bt_main.round;
        roundHandler = new Handler();
    }

    // Create and start the timer for the waiting phase of the breathing exercise
    public void waitTimer() {
        roundRunnable = () -> {
            newWaitTimer(3000,20);
            startTimer();
        };
        roundHandler.post(roundRunnable);
    }

    // Creates a new timer for the waiting phase
    // Resets the horizontal progress bar
    private void newWaitTimer(int totalTime, int countDownInterval) {
        resetProgressBar(totalTime);
        createWaitTimer(totalTime, countDownInterval);
    }

    // Create a new countdown timer used by the waiting phase
    private void createWaitTimer(int totalTime, int countDownInterval) {
        interval = countDownInterval;
        timer = new CountDownTimer(totalTime, countDownInterval) {
            @Override
            public void onTick(long l) {
                remainTime = l;
                round.setText("");
            }

            // Indicate the starting of the breathing exercise
            // Start the new timer that keeps track of the total progress
            @Override
            public void onFinish() {
                bt_main.roundStart = true;
                bt_main.setNewRound();
            }
        };
    }

    // Resume the wait timer
    public void resumeWaitTimer() {
        createWaitTimer((int) remainTime, interval);
        startTimer();
    }

    // Create and start the timer to keep track of the total progress of the breathing exercise
    public void roundTimer() {
        roundRunnable = () -> {
            newTimer(bt_main.maxRound * 1000,20);
            startTimer();
        };
        roundHandler.post(roundRunnable);
    }

    // Implement the createTimer method in Timer class
    // Create a new countdown timer
    @Override
    void createTimer(int totalTime, int countDownInterval) {
        interval = countDownInterval;
        timer = new CountDownTimer(totalTime, countDownInterval) {
            // Update the progress bar and timer text at every tick
            // Display the round number
            @Override
            public void onTick(long l) {
                remainTime = l;
                round.setText("Round " + String.valueOf(bt_main.currentRound));
                setProgressBar(l);
            }

            // Set progress bar to 100%
            // Display the end prompt
            @Override
            public void onFinish() {
                setProgressBar(0);
                bt_main.setEndPrompt();
            }
        };
    }

    // Reset the thread to resume the timer
    @Override
    public void resumeTimer() {
        roundRunnable = () -> {
            if (!bt_main.roundStart) {
                resumeWaitTimer();
            }
            else {
                super.resumeTimer();
            }
        };
        roundHandler.post(roundRunnable);
    }
}
