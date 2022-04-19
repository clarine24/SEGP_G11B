package com.example.rewire;

import android.os.CountDownTimer;
import android.os.Handler;
import android.widget.TextView;

public class RoundProgress extends Timer {
    private Handler roundHandler;
    private Runnable roundRunnable;
    private TextView round;

    public RoundProgress(BT_main bt_main) {
        super(bt_main, bt_main.roundProgressBar);
        round = bt_main.round;
        roundHandler = new Handler();
    }

    public void waitTimer() {
        roundRunnable = () -> {
            newWaitTimer(3000,20);
            startTimer();
        };
        roundHandler.post(roundRunnable);
    }

    private void newWaitTimer(int d, int i) {
        resetProgressBar(d);
        createWaitTimer(d,i);
    }

    private void createWaitTimer(int d, int i) {
        interval = i;
        timer = new CountDownTimer(d,i) {
            @Override
            public void onTick(long l) {
                remainTime = l;
                round.setText("");
            }

            @Override
            public void onFinish() {
                bt_main.roundStart = true;
                bt_main.setNewRound();
            }
        };
    }

    public void resumeWaitTimer() {
        createWaitTimer((int) remainTime, interval);
        startTimer();
    }

    public void roundTimer() {
        roundRunnable = () -> {
            newTimer(bt_main.maxRound * 1000,20);
            startTimer();
        };
        roundHandler.post(roundRunnable);
    }

    @Override
    void createTimer(int d, int i) {
        interval = i;
        timer = new CountDownTimer(d,i) {
            @Override
            public void onTick(long l) {
                remainTime = l;
                round.setText("Round " + String.valueOf(bt_main.currentRound));
                setProgressBar(l);
            }

            @Override
            public void onFinish() {
                setProgressBar(0);
                bt_main.setEndPrompt();
            }
        };
    }

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
