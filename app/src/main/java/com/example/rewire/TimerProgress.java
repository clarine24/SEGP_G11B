package com.example.rewire;

import android.os.CountDownTimer;
import android.os.Handler;
import android.widget.TextView;

public class TimerProgress extends Timer {
    private Handler timerHandler;
    private Runnable timerRunnable = () -> {
        newTimer(4000,20);
        startTimer();
    };
    private TextView time, state, instruction;

    public TimerProgress(BT_main bt_main) {
        super(bt_main,bt_main.timerProgressBar);

        time = bt_main.time;
        state = bt_main.state;
        instruction = bt_main.instruction;

        timerHandler = new Handler();
    }

    @Override
    void createTimer(int d, int i) {
        interval = i;
        state.setText(BT_main.stateStr);
        instruction.setText(BT_main.instructStr);

        timer = new CountDownTimer(d,i) {
            @Override
            public void onTick(long l) {
                remainTime = l;
                time.setText(Long.toString(l/1000 + 1));
                setProgressBar(l);
            }

            @Override
            public void onFinish() {
                setProgressBar(0);
                bt_main.setTimeType();
                bt_main.setNewCycle();
            }
        };
    }

    public void readyTimer() {
        timerRunnable = () -> {
            newTimer(3000,20);
            startTimer();
        };
        timerHandler.post(timerRunnable);
    }

    public void inhaleTimer() {
        timerRunnable = () -> {
            newTimer(4000,20);
            startTimer();
        };
        timerHandler.post(timerRunnable);
    }

    public void holdTimer() {
        timerRunnable = () -> {
            newTimer(7000,20);
            startTimer();
        };
        timerHandler.post(timerRunnable);
    }

    public void exhaleTimer() {
        timerRunnable = () -> {
            newTimer(8000,20);
            startTimer();
        };
        timerHandler.post(timerRunnable);
    }

    @Override
    public void resumeTimer() {
        timerRunnable = () -> super.resumeTimer();
        timerHandler.post(timerRunnable);
    }
}
