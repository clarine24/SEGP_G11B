package com.example.rewire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
        final ImageView hbg_menu = findViewById(R.id.hbg_menu);
        final ImageView hbg_menu_info = findViewById(R.id.hbg_menu_info);
        final ImageView hbg_menu_vol = findViewById(R.id.hbg_menu_vol);
        final ImageView hbg_menu_doc = findViewById(R.id.hbg_menu_doc);
        final ImageView hbg_menu_close = findViewById(R.id.hbg_menu_close);
        final ImageView home_btn = findViewById(R.id.bt_home_icon);

        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

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

        hbg_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hbg_menu.setVisibility(View.INVISIBLE);
                hbg_menu_info.setVisibility(View.VISIBLE);
                hbg_menu_doc.setVisibility(View.VISIBLE);
                hbg_menu_vol.setVisibility(View.VISIBLE);
                hbg_menu_close.setVisibility(View.VISIBLE);
            }
        });

        hbg_menu_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hbg_menu.setVisibility(View.VISIBLE);
                hbg_menu_info.setVisibility(View.INVISIBLE);
                hbg_menu_doc.setVisibility(View.INVISIBLE);
                hbg_menu_vol.setVisibility(View.INVISIBLE);
                hbg_menu_close.setVisibility(View.INVISIBLE);
            }
        });
    }
}
