package com.example.rewire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class CBT_Game extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cbt_game);

        // buttons for each level
        final Button level1_btn = findViewById(R.id.level1);
        final Button level2_btn = findViewById(R.id.level2);
        final Button level3_btn = findViewById(R.id.level3);
        final Button level4_btn = findViewById(R.id.level4);
        final Button level5_btn = findViewById(R.id.level5);

        final ImageView hbg_menu = findViewById(R.id.hbg_menu);
        final ImageView hbg_menu_info = findViewById(R.id.hbg_menu_info);
        final ImageView hbg_menu_vol = findViewById(R.id.hbg_menu_vol);
        final ImageView hbg_menu_doc = findViewById(R.id.hbg_menu_doc);
        final ImageView hbg_menu_close = findViewById(R.id.hbg_menu_close);
        final ImageView home_btn = findViewById(R.id.home_icon);

        // function for each button
        level1_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CBT_Game.this, CBT_sublevel_1.class);
                startActivity(intent);
            }
        });

        level2_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CBT_Game.this, CBT_sublevel_2.class);
                startActivity(intent);
            }
        });

        level3_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CBT_Game.this, CBT_sublevel_3.class);
                startActivity(intent);
            }
        });

        level4_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CBT_Game.this, CBT_sublevel_4.class);
                startActivity(intent);
            }
        });

        level5_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CBT_Game.this, CBT_sublevel_5.class);
                startActivity(intent);
            }
        });


        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
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