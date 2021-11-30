package com.example.rewire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class BT_prompt extends AppCompatActivity {

    SeekBar bt_input_seekBar;
    TextView bt_numberOfRoundsInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breathing_technique_prompt);

        final Button bt_ok_btn = findViewById(R.id.bt_ok_button);

        bt_ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BT_main.class);
                startActivity(intent);
            }
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
    }
}
