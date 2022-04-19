package com.example.rewire;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

public class BT_main extends AppRunning {
    private Dialog startPrompt, endPrompt;
    private Button startButton, restartButton, homeButton;
    private SeekBar seekBar;
    private TextView numberOfRoundsInput;
    private int roundsNum;

    private final int inhaleTime = 4, holdTime = 7, exhaleTime = 8;
    private TimerProgress timerProgress;
    private RoundProgress roundProgress;
    TextView time, round, state, instruction;
    ProgressBar timerProgressBar, roundProgressBar;
    int timeType, maxRound;
    int currentRound;
    boolean roundStart;
    static String stateStr, instructStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        startPrompt = new Dialog(this);
        startPrompt.setCanceledOnTouchOutside(false);
        endPrompt = new Dialog(this);
        endPrompt.setCanceledOnTouchOutside(false);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breathing_technique_main);

        setHeader();

        timerProgressBar = findViewById(R.id.timer_progress);
        time = findViewById(R.id.bt_seconds);
        state = findViewById(R.id.bt_state);
        instruction = findViewById(R.id.bt_instruction);
        timerProgress = new TimerProgress(this);

        roundProgressBar = findViewById(R.id.total_progress);
        round = findViewById(R.id.round);
        roundProgress = new RoundProgress(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        setStartPrompt();
        timerProgress.resetProgressBar(1);
        roundProgress.resetProgressBar(1);
        round.setText("");
    }

    private void setStartPrompt() {
        startPrompt.setContentView(R.layout.activity_breathing_technique_start_prompt);
        startPrompt.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        seekBar = startPrompt.findViewById(R.id.bt_input_seekBar);
        numberOfRoundsInput = startPrompt.findViewById(R.id.bt_numberOfRoundsInput);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                numberOfRoundsInput.setText("" + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        startButton = startPrompt.findViewById(R.id.okButton);
        startButton.setOnClickListener(view -> {
            roundsNum = seekBar.getProgress();
            startPrompt.dismiss();
            countdownStart();
        });

        startPrompt.show();
    }

    void setEndPrompt() {
        endPrompt.setContentView(R.layout.activity_breathing_technique_end_prompt);
        endPrompt.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        restartButton = endPrompt.findViewById(R.id.restart_btn);
        restartButton.setOnClickListener(view -> {
            endPrompt.dismiss();
            onStart();
        });

        homeButton = endPrompt.findViewById(R.id.exit_to_home_btn);
        homeButton.setOnClickListener(view -> {
            endPrompt.dismiss();
            finish();
        });

        endPrompt.show();
    }

    @Override
    void toHomeMenu() {
        pauseTimers();
        super.toHomeMenu();
    }

    @Override
    void initDialog(Dialog dialog, int viewID, int cancelButtonID) {
        pauseTimers();
        super.initDialog(dialog, viewID, cancelButtonID);
    }

    @Override
    void closeDialog(Dialog dialog) {
        super.closeDialog(dialog);
        resumeTimers();
    }

    @Override
    void setCloseButton() {
        super.setCloseButton();
        resumeTimers();
    }

    @Override
    void setExitButton() {
        super.setCloseButton();
        initButtonView();
        finish();
    }

    @Override
    void setMusicDialog() {
        pauseTimers();
        super.setMusicDialog();
    }

    private void countdownStart() {
        timeType = 1;
        currentRound = 1;
        roundStart = false;

        setMaxRound();
        setNewCycle();
        setNewRound();
    }

    public void setNewCycle() {
        if (timeType == 1) {
            stateStr = "Ready";
            instructStr = "";
            timerProgress.readyTimer();
        }
        else if (timeType == 3) {
            stateStr = "Hold";
            instructStr = "";
            timerProgress.holdTimer();
        }
        else if (timeType == 4) {
            stateStr = "Exhale";
            instructStr = "Through the Mouth";
            timerProgress.exhaleTimer();
        }
        else if (timeType == 5) {
            currentRound++;

            if (currentRound <= roundsNum) {
                timeType = 2;
            }
            else {
                time.setText(Integer.toString(0));
            }
        }

        if (timeType == 2) {
            stateStr = "Inhale";
            instructStr = "Through the Nose";
            timerProgress.inhaleTimer();
        }
    }

    public void setTimeType() {
        timeType++;
    }

    private void setMaxRound() {
        maxRound = roundsNum * (inhaleTime + holdTime + exhaleTime);
    }

    public void setNewRound() {
        if (!roundStart) {
            roundProgress.waitTimer();
        }
        else {
            roundProgress.roundTimer();
        }
    }

    private void pauseTimers() {
        timerProgress.stopTimer();
        roundProgress.stopTimer();
    }

    private void resumeTimers() {
        timerProgress.resumeTimer();
        roundProgress.resumeTimer();
    }
}
