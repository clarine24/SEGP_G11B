package com.example.rewire;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends App {
    Button cbt, bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setHeader();
        header.home_btn.setVisibility(View.INVISIBLE);

        playMusic();

        cbt = findViewById(R.id.cbt_btn);
        bt = findViewById(R.id.breathing_btn);
        cbt.setOnClickListener(v -> startCBT());
        bt.setOnClickListener(v -> startBT());
    }

    private void startCBT() {
        Intent intent = new Intent(MainActivity.this, CBT_Game.class);
        startActivity(intent);
    }

    private void startBT() {
        Intent intent = new Intent(MainActivity.this, BT_main.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        musicPlayer.stop();
        musicPlayer.release();
    }
}