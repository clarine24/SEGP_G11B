package com.example.rewire;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BT_prompt extends AppCompatActivity {

    SeekBar bt_input_seekBar;
    TextView bt_numberOfRoundsInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breathing_technique_prompt);

        Header header = findViewById(R.id.header);
        header.initHeader();
        header.home_btn.setOnClickListener(v -> startActivity(header.toHomeMenu()));

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
    }
}
