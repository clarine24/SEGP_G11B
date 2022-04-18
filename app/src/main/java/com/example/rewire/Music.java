package com.example.rewire;

import android.media.MediaPlayer;

import androidx.appcompat.app.AppCompatActivity;

public class Music extends AppCompatActivity {
    MediaPlayer musicPlayer = new MediaPlayer();

    public void defaultMusicSettings() {
        musicPlayer.setLooping(true);
        musicPlayer.setVolume(70, 70);
    }

    public void playMusic() {
        musicPlayer = MediaPlayer.create(this, R.raw.lofi);
        defaultMusicSettings();
        musicPlayer.start();
    }

    public void muteMusic() {
        musicPlayer.setVolume(0, 0);
    }

    public void unmuteMusic() {
        musicPlayer.setVolume(70, 70);
    }

    public void setMusicToLofi() {
        musicPlayer.stop();

        musicPlayer = MediaPlayer.create(this, R.raw.lofi);
        defaultMusicSettings();
        musicPlayer.start();
    }

    public void setMusicToAmbience() {
        musicPlayer.stop();

        musicPlayer = MediaPlayer.create(this, R.raw.ambience);
        defaultMusicSettings();
        musicPlayer.start();
    }

    public void setMusicToChill() {
        musicPlayer.stop();

        musicPlayer = MediaPlayer.create(this, R.raw.chill);
        defaultMusicSettings();
        musicPlayer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        musicPlayer.stop();
        musicPlayer.release();
    }
}
