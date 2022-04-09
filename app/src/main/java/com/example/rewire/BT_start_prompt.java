package com.example.rewire;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BT_start_prompt extends AppCompatActivity {
    SeekBar bt_input_seekBar;
    TextView bt_numberOfRoundsInput;
    Button cancelButton;
    Button exitButton;
    Dialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mDialog = new Dialog(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breathing_technique_start_prompt);

        Header header = findViewById(R.id.header);
        header.initHeader();

        final Button bt_ok_btn = findViewById(R.id.bt_ok_button);

        bt_ok_btn.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), BT_main.class);
            startActivity(intent);
        });

        bt_input_seekBar = (SeekBar) findViewById(R.id.bt_input_seekBar);
        bt_numberOfRoundsInput = (TextView) findViewById(R.id.bt_numberOfRoundsInput);

        bt_input_seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                bt_numberOfRoundsInput.setText(" " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        header.home_btn.setOnClickListener(view -> {
            mDialog.setContentView(R.layout.exit_to_home_popup);
            mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            cancelButton = mDialog.findViewById(R.id.homeButton);
            exitButton = mDialog.findViewById(R.id.exitToHomeButton);

            cancelButton.setOnClickListener(v1 -> mDialog.dismiss());
            exitButton.setOnClickListener(v1 -> {
                mDialog.dismiss();
                startActivity(header.toHomeMenu());
            });

            mDialog.show();
        });
    }

}
