package com.example.rewire;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenu extends App {
    Button cbt, bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        readFile();
        //writeFile();

        setHeader();
        header.home_btn.setVisibility(View.INVISIBLE);

        music.playMusic();

        cbt = findViewById(R.id.cbt_btn);
        bt = findViewById(R.id.breathing_btn);
        cbt.setOnClickListener(v -> startCBT());
        bt.setOnClickListener(v -> startBT());
    }

    private void startCBT() {
        Intent intent = new Intent(MainMenu.this, CBT_Level.class);
        startActivity(intent);
    }

    private void startBT() {
        Intent intent = new Intent(MainMenu.this, BT_Main.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        music.destroyMusic();
    }
}