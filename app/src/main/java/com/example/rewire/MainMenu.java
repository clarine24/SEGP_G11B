// No open source code or third-party libraries were used in this class.
// This class contains only original source code.

package com.example.rewire;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

// MainMenu class is a subclass of App
// This is the class used to implement the main menu
public class MainMenu extends App {
    Button cbt, bt;

    // Called when main menu activity is created
    // Read file to restore previous progress if it exists
    // Activate the header
    // Play background music
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        readFile();

        setHeader();
        header.home_btn.setVisibility(View.INVISIBLE);

        music.playMusic();

        cbt = findViewById(R.id.cbt_btn);
        bt = findViewById(R.id.breathing_btn);
        cbt.setOnClickListener(v -> startCBT());
        bt.setOnClickListener(v -> startBT());
    }

    // Switch the interface to CBT
    private void startCBT() {
        Intent intent = new Intent(MainMenu.this, CBT_Level.class);
        startActivity(intent);
    }

    // Switch the interface to breathing technique
    private void startBT() {
        Intent intent = new Intent(MainMenu.this, BT_Main.class);
        startActivity(intent);
    }

    // Called when the application is closing
    // Destroy the music
    @Override
    protected void onDestroy() {
        super.onDestroy();
        music.destroyMusic();
    }
}