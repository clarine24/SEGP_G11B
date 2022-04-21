package com.example.rewire;

import android.content.Context;
import android.media.MediaPlayer;

import androidx.appcompat.app.AppCompatActivity;

public class Music {
    private Context context;
    private static MediaPlayer musicPlayer;
    private static boolean isMute = false;
    private static int SOUNDTRACK = 1;
    private final static int MAX_VOLUME = 100;

    public Music(Context context) {
        this.context = context;
    }

    private void defaultMusicSetting() {
        musicPlayer.setLooping(true);
        if (isMute) {
            muteMusic();
        }
        else
            unmuteMusic();
        musicPlayer.start();
    }

    public void playMusic() {
        musicPlayer = MediaPlayer.create(context, R.raw.lofi);
        defaultMusicSetting();
    }

    public void setMusicToLofi() {
        musicPlayer.stop();
        musicPlayer = MediaPlayer.create(context, R.raw.lofi);
        defaultMusicSetting();
    }

    public void setMusicToAmbience() {
        musicPlayer.stop();
        musicPlayer = MediaPlayer.create(context, R.raw.ambience);
        defaultMusicSetting();
    }

    public void setMusicToChill() {
        musicPlayer.stop();
        musicPlayer = MediaPlayer.create(context, R.raw.chill);
        defaultMusicSetting();
    }

    public void muteMusic() {
        final float volume = (float) (1 - (Math.log(MAX_VOLUME - 0) / Math.log(MAX_VOLUME)));
        musicPlayer.setVolume(volume, volume);
    }

    public void unmuteMusic() {
        final float volume = (float) (1 - (Math.log(MAX_VOLUME - 99) / Math.log(MAX_VOLUME)));
        musicPlayer.setVolume(volume, volume);
    }

    public void destroyMusic() {
        musicPlayer.stop();
        musicPlayer.release();
    }

    public static void setIsMute(boolean isMute) {
        Music.isMute = isMute;
    }

    public static boolean getIsMute() {
        return isMute;
    }

    public static int getSOUNDTRACK() {
        return SOUNDTRACK;
    }

    public static void setSOUNDTRACK(int SOUNDTRACK) {
        Music.SOUNDTRACK = SOUNDTRACK;
    }

}
