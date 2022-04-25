// No open source code or third-party libraries were used in this class.
// This class contains only original source code.

package com.example.rewire;

import android.os.CountDownTimer;
import android.os.Handler;
import android.widget.TextView;

// StepTimer is a subclass of Timer
// It is used to keep track of every phase of the breathing exercise
public class StepTimer extends Timer {
    private Handler timerHandler;
    private Runnable timerRunnable;
    private TextView time, state, instruction;

    // Create a StepTimer instance with the given breathing exercise activity
    public StepTimer(BT_Main bt_main) {
        super(bt_main,bt_main.timerProgressBar);

        time = bt_main.time;
        state = bt_main.state;
        instruction = bt_main.instruction;

        timerHandler = new Handler();
    }

    // Implement the createTimer method in Timer class
    // Create a new countdown timer based on the given total time and countdown interval
    @Override
    void createTimer(int totalTime, int countDownInterval) {
        interval = countDownInterval;
        state.setText(BT_Main.stateStr);
        instruction.setText(BT_Main.instructStr);

        timer = new CountDownTimer(totalTime, countDownInterval) {
            // Update the progress bar and timer text at every tick
            // Display the remaining time for each phase
            @Override
            public void onTick(long l) {
                remainTime = l;
                time.setText(Long.toString(l/1000 + 1));
                setProgressBar(l);
            }

            // Clear the progress bar and start a new step/phase of the breathing exercise
            @Override
            public void onFinish() {
                setProgressBar(0);
                bt_main.setTimeType();
                bt_main.setNewCycle();
            }
        };
    }

    // Create and start the timer for the ready phase of the breathing exercise
    public void readyTimer() {
        timerRunnable = () -> {
            newTimer(3000,20);
            startTimer();
        };
        timerHandler.post(timerRunnable);
    }

    // Create and start the timer for the inhaling phase of the breathing exercise
    public void inhaleTimer() {
        timerRunnable = () -> {
            newTimer(4000,20);
            startTimer();
        };
        timerHandler.post(timerRunnable);
    }

    // Create and start the timer for the holding phase of the breathing exercise
    public void holdTimer() {
        timerRunnable = () -> {
            newTimer(7000,20);
            startTimer();
        };
        timerHandler.post(timerRunnable);
    }

    // Create and start the timer for the exhaling phase of the breathing exercise
    public void exhaleTimer() {
        timerRunnable = () -> {
            newTimer(8000,20);
            startTimer();
        };
        timerHandler.post(timerRunnable);
    }

    // Reset the thread to resume the timer
    @Override
    public void resumeTimer() {
        timerRunnable = () -> super.resumeTimer();
        timerHandler.post(timerRunnable);
    }
}
