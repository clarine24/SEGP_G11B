package com.example.rewire;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    ImageView cancelButton;
    Dialog infoDialog;
    Dialog musicDialog;
    MediaPlayer musicPlayer;
    Music musicObject = new Music();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        infoDialog = new Dialog(this);
        musicDialog = new Dialog(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Header header = findViewById(R.id.header);
        header.initHeader();
        header.home_btn.setVisibility(View.INVISIBLE);

        final Button cbt_btn = findViewById(R.id.cbt_btn);
        final Button breathing_btn = findViewById(R.id.breathing_btn);

        cbt_btn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CBT_Game.class);
            startActivity(intent);
        });

        breathing_btn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, BT_start_prompt.class);
            startActivity(intent);
        });

        header.hbg_menu_info.setOnClickListener(view -> {
            infoDialog.setContentView(R.layout.about_pop_up);
            infoDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            cancelButton = infoDialog.findViewById(R.id.exitbuttoninfo);
            cancelButton.setOnClickListener(v -> infoDialog.dismiss());

            infoDialog.show();
        });

        header.hbg_menu_vol.setOnClickListener(view -> {
            musicDialog.setContentView(R.layout.music_pop_up);
            musicDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            cancelButton = musicDialog.findViewById(R.id.exitbuttonmusic);
            cancelButton.setOnClickListener(v -> musicDialog.dismiss());

            musicDialog.show();
        });

        musicPlayer = MediaPlayer.create(this, R.raw.onion);

        musicPlayer.setLooping(true);
        musicPlayer.setVolume(70, 70);

        musicPlayer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        musicPlayer.stop();
        musicPlayer.release();
    }
}