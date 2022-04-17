package com.example.rewire;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Music extends AppCompatActivity {
    MediaPlayer musicPlayer = new MediaPlayer();

    public void playMusic() {
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
