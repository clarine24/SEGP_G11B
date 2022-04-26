// No open source code or third-party libraries were used in this class.
// This class contains only original source code.

package com.example.rewire;

import android.content.Context;
import android.media.MediaPlayer;

// Music class controls the background music
public class Music {
    private Context context;
    private static MediaPlayer musicPlayer;
    private static boolean isMute = false;
    private static int SOUNDTRACK = 1;
    private final static int MAX_VOLUME = 100;

    // Constructor of Music class
    public Music(Context context) {
        this.context = context;
    }

    // Keep the music looping
    // Play the music
    private void defaultMusicSetting() {
        musicPlayer.setLooping(true);
        if (isMute) {
            muteMusic();
        }
        else
            unmuteMusic();
        musicPlayer.start();
    }

    // Play LOFI as a default background music
    // Called when the application is starting
    public void playMusic() {
        musicPlayer = MediaPlayer.create(context, R.raw.lofi);
        defaultMusicSetting();
    }

    // Switch the background music to LOFI
    public void setMusicToLofi() {
        musicPlayer.stop();
        musicPlayer = MediaPlayer.create(context, R.raw.lofi);
        defaultMusicSetting();
    }

    // Switch the background music to AMBIENCE
    public void setMusicToAmbience() {
        musicPlayer.stop();
        musicPlayer = MediaPlayer.create(context, R.raw.ambience);
        defaultMusicSetting();
    }

    // Switch the background music to CHILL
    public void setMusicToChill() {
        musicPlayer.stop();
        musicPlayer = MediaPlayer.create(context, R.raw.chill);
        defaultMusicSetting();
    }

    // Mute the background music
    public void muteMusic() {
        final float volume = (float) (1 - (Math.log(MAX_VOLUME - 0) / Math.log(MAX_VOLUME)));
        musicPlayer.setVolume(volume, volume);
    }

    // Unmute the background music
    public void unmuteMusic() {
        final float volume = (float) (1 - (Math.log(MAX_VOLUME - 99) / Math.log(MAX_VOLUME)));
        musicPlayer.setVolume(volume, volume);
    }

    // Destroy the music
    // Called when the application is closing
    public void destroyMusic() {
        musicPlayer.stop();
        musicPlayer.release();
    }

    // Setter of the mute music indicator
    public static void setIsMute(boolean isMute) {
        Music.isMute = isMute;
    }

    // Getter of the mute music indicator
    public static boolean getIsMute() {
        return isMute;
    }

    // Getter of the current background music
    public static int getSOUNDTRACK() {
        return SOUNDTRACK;
    }

    // Setter of the current background music
    public static void setSOUNDTRACK(int SOUNDTRACK) {
        Music.SOUNDTRACK = SOUNDTRACK;
    }

}
