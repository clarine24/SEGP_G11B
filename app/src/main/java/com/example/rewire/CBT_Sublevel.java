package com.example.rewire;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class CBT_Sublevel extends App implements View.OnClickListener {
    static int scene = 1, sceneUnlock = 1;
    private Button scene_1, scene_2, scene_3;
    private ImageButton lock2, lock3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cbt_sublevel);

        setHeader();

        TextView title = findViewById(R.id.title);
        title.setText("LEVEL " + CBT_Level.level);

        scene_1 = findViewById(R.id.scenario1);
        scene_2 = findViewById(R.id.scenario2);
        scene_3 = findViewById(R.id.scenario3);

        scene_1.setOnClickListener(this);
        scene_2.setOnClickListener(this);
        scene_3.setOnClickListener(this);

        lock2 = findViewById(R.id.scenario2_lock);
        lock3 = findViewById(R.id.scenario3_lock);
    }

    @Override
    protected void onStart() {
        unlockScene();
        super.onStart();
    }

    private void unlockScene() {
        // All scenes unlocked for completed levels
        if (CBT_Level.level < CBT_Level.levelUnlock) {
            sceneUnlock = 3;
        }

        switch (sceneUnlock) {
            case 3:
                lock3.setVisibility(View.INVISIBLE);
                scene_3.setVisibility(View.VISIBLE);
            case 2:
                lock2.setVisibility(View.INVISIBLE);
                scene_2.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.scenario1:
                scene = 1;
                break;
            case R.id.scenario2:
                scene = 2;
                break;
            case R.id.scenario3:
                scene = 3;
                break;
        }

        Intent intent = new Intent(CBT_Sublevel.this, CBT_EducationalPage.class);
        startActivity(intent);
    }
}