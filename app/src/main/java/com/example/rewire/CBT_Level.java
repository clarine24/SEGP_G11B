// No open source code or third-party libraries were used in this class.
// This class contains only original source code.

package com.example.rewire;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

// Extends App class that is used to store progress
public class CBT_Level extends App implements View.OnClickListener{
    static int level = 1, levelUnlock = 1;
    private Button level1, level2, level3, level4, level5;
    private ImageButton lock2, lock3, lock4, lock5;

    // Called when CBT level page activity is starting
    // Initialise variables used by CBT_Level class
    // Connect page to header
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cbt_level);

        setHeader();

        level1 = findViewById(R.id.level1);
        level2 = findViewById(R.id.level2);
        level3 = findViewById(R.id.level3);
        level4 = findViewById(R.id.level4);
        level5 = findViewById(R.id.level5);

        level1.setOnClickListener(this);
        level2.setOnClickListener(this);
        level3.setOnClickListener(this);
        level4.setOnClickListener(this);
        level5.setOnClickListener(this);
        
        lock2 = findViewById(R.id.level2_lock);
        lock3 = findViewById(R.id.level3_lock);
        lock4 = findViewById(R.id.level4_lock);
        lock5 = findViewById(R.id.level5_lock);
    }

    // Called when the CBT Level page is displayed to users
    // Gets the proper levels to be unlocked and reads progress
    @Override
    protected void onStart() {
        readFile();
        unlockLevel();
        super.onStart();
    }

    // Unlocks levels according to the switch statement
    private void unlockLevel() {
        switch (levelUnlock) {
            case 5:
                lock5.setVisibility(View.INVISIBLE);
                level5.setVisibility(View.VISIBLE);
            case 4:
                lock4.setVisibility(View.INVISIBLE);
                level4.setVisibility(View.VISIBLE);
            case 3:
                lock3.setVisibility(View.INVISIBLE);
                level3.setVisibility(View.VISIBLE);
            case 2:
                lock2.setVisibility(View.INVISIBLE);
                level2.setVisibility(View.VISIBLE);
        }
    }

    // Assigns level to be viewed to the level variable
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.level1:
                level = 1;
                break;
            case R.id.level2:
                level = 2;
                break;
            case R.id.level3:
                level = 3;
                break;
            case R.id.level4:
                level = 4;
                break;
            case R.id.level5:
                level = 5;
                break;
        }

        // Starts the CBT_sublevel activity when a level is pressed and dismisses the CBT_Level
        Intent intent = new Intent(CBT_Level.this, CBT_Sublevel.class);
        startActivity(intent);
    }
}