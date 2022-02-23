package com.example.rewire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button cbt_btn = findViewById(R.id.cbt_btn);
        final Button breathing_btn = findViewById(R.id.breathing_btn);
        final ImageView hbg_menu = findViewById(R.id.hbg_menu);
        final ImageView hbg_menu_info = findViewById(R.id.hbg_menu_info);
        final ImageView hbg_menu_vol = findViewById(R.id.hbg_menu_vol);
        final ImageView hbg_menu_doc = findViewById(R.id.hbg_menu_doc);
        final ImageView hbg_menu_close = findViewById(R.id.hbg_menu_close);

        cbt_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CBT_Game.class);
                startActivity(intent);
            }
        });

        breathing_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BT_prompt.class);
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