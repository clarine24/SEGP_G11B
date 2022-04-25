// No open source code or third-party libraries were used in this class.
// This class contains only original source code.

package com.example.rewire;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

// A subclass of AppRunning for breathing exercise.
// It extends AppRunning class as the exit to home pop-up should appear during the breathing exercise.
public class BT_Main extends AppRunning {
    private Dialog startPrompt, endPrompt;
    private Button startButton, restartButton;
    private SeekBar seekBar;
    private TextView numberOfRoundsInput;
    private int roundsNum;

    private final int inhaleTime = 4, holdTime = 7, exhaleTime = 8;
    private StepTimer stepTimer;
    private TotalProgressTimer totalProgressTimer;
    TextView time, round, state, instruction;
    ProgressBar timerProgressBar, roundProgressBar;
    int timeType, maxRound, currentRound;
    boolean roundStart;
    static String stateStr, instructStr;

    // Called when breathing exercise activity page is starting
    // Initialise variables used by BT_Main class
    // Connect page to header
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        startPrompt = new Dialog(this);
        startPrompt.setCanceledOnTouchOutside(false);
        endPrompt = new Dialog(this);
        endPrompt.setCanceledOnTouchOutside(false);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.breathing_technique_main);

        setHeader();

        timerProgressBar = findViewById(R.id.timer_progress);
        time = findViewById(R.id.bt_seconds);
        state = findViewById(R.id.bt_state);
        instruction = findViewById(R.id.bt_instruction);
        stepTimer = new StepTimer(this);

        roundProgressBar = findViewById(R.id.total_progress);
        round = findViewById(R.id.round);
        totalProgressTimer = new TotalProgressTimer(this);
    }

    // Called when the breathing exercise activity page is displayed to users
    // Make start prompt appear and remove previous breathing exercise progress
    @Override
    protected void onStart() {
        super.onStart();
        setStartPrompt();
        stepTimer.resetProgressBar(1);
        totalProgressTimer.resetProgressBar(1);
        round.setText("");
    }

    // Display the start prompt
    // Close start prompt when cancel button clicked
    // Save the number of rounds input by users
    // Start the breathing exercise
    private void setStartPrompt() {
        startPrompt.setContentView(R.layout.breathing_technique_start_prompt);
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

    // Display end prompt
    void setEndPrompt() {
        endPrompt.setContentView(R.layout.breathing_technique_end_prompt);
        endPrompt.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        restartButton = endPrompt.findViewById(R.id.restart_btn);
        restartButton.setOnClickListener(view -> {
            endPrompt.dismiss();
            onStart();
        });

        initExitButton(endPrompt,R.id.exit_to_home_btn);
        endPrompt.show();
    }

    // Pause all timers when a pop-up window appears
    @Override
    void initDialog(Dialog dialog, int viewID) {
        pauseTimers();
        super.initDialog(dialog, viewID);
    }

    // Resume all timers when a pop-up window closes
    @Override
    void closeDialog(Dialog dialog) {
        super.closeDialog(dialog);
        resumeTimers();
    }

    // Close all pop-up windows and exit breathing exercise to home menu
    // Reset all progress bars and text
    @Override
    void exitOnClick(Dialog dialog) {
        stepTimer.resetProgressBar(1);
        totalProgressTimer.resetProgressBar(1);
        round.setText("");
        super.exitOnClick(dialog);
        finish();
    }

    // Start breathing exercise
    private void countdownStart() {
        timeType = 1;
        currentRound = 1;
        roundStart = false;

        setMaxRound();
        setNewCycle();
        setNewRound();
    }

    // Determine the phase of the breathing exercise and calls the relevant timer
    // Set text to be displayed for each phase of the breathing exercise
    public void setNewCycle() {
        if (timeType == 1) {
            stateStr = "Ready";
            instructStr = "";
            stepTimer.readyTimer();
        }
        else if (timeType == 3) {
            stateStr = "Hold";
            instructStr = "";
            stepTimer.holdTimer();
        }
        else if (timeType == 4) {
            stateStr = "Exhale";
            instructStr = "Through the Mouth";
            stepTimer.exhaleTimer();
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
            stepTimer.inhaleTimer();
        }
    }

    // Increment the time type (eg. from inhale to hold)
    public void setTimeType() {
        timeType++;
    }

    // Calculate the total number of seconds for the entire breathing exercise
    private void setMaxRound() {
        maxRound = roundsNum * (inhaleTime + holdTime + exhaleTime);
    }

    // Control the horizontal progress bar
    // Determine the total progress timer method to be called
    public void setNewRound() {
        if (!roundStart) {
            totalProgressTimer.waitTimer();
        }
        else {
            totalProgressTimer.roundTimer();
        }
    }

    // Pause all timers
    private void pauseTimers() {
        stepTimer.stopTimer();
        totalProgressTimer.stopTimer();
    }

    // Resume all timers
    private void resumeTimers() {
        stepTimer.resumeTimer();
        totalProgressTimer.resumeTimer();
    }
}
