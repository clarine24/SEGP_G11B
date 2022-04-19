package com.example.rewire;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public abstract class App extends AppCompatActivity {
    Header header;
    Dialog infoDialog, docDialog, musicDialog;
    ImageView cancelButton;
    Button exitButton, closeButton;
    MediaPlayer musicPlayer;
    ImageView muteMusic;
    ImageView unmuteMusic;
    AutoCompleteTextView music_auto_complete_text;
    ArrayAdapter<String> soundtrackAdapter;
    private final static int MAX_VOLUME = 100;

    void setHeader() {
        header = findViewById(R.id.header);
        header.initHeader();

        header.home_btn.setOnClickListener(view -> toHomeMenu());

        infoDialog = new Dialog(this);
        header.hbg_menu_info.setOnClickListener(view -> setInfoDialog());

        docDialog = new Dialog(this);
        header.hbg_menu_doc.setOnClickListener(view -> setDocDialog());

        musicDialog = new Dialog(this);
        header.hbg_menu_music.setOnClickListener(view -> setMusicDialog());

    }

    void toHomeMenu() {
        initButtonView();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    void setInfoDialog() {
        initButtonView();
        infoDialog.setContentView(R.layout.about_pop_up);
        infoDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        cancelButton = infoDialog.findViewById(R.id.exitbuttoninfo);
        cancelButton.setOnClickListener(v -> cancelButton_InfoDialog());
        infoDialog.show();
    }

    void cancelButton_InfoDialog() {
        infoDialog.dismiss();
    }

    void setDocDialog() {
        initButtonView();
        docDialog.setContentView(R.layout.documentation_pop_up);
        docDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        cancelButton = docDialog.findViewById(R.id.exitbuttondoc);
        cancelButton.setOnClickListener(v -> cancelButton_DocDialog());
        docDialog.show();
    }

    void cancelButton_DocDialog() {
        docDialog.dismiss();
    }

    void setMusicDialog() {
        initButtonView();

        musicDialog.setContentView(R.layout.music_pop_up);
        musicDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        cancelButton = musicDialog.findViewById(R.id.exitbuttonmusic);
        muteMusic = musicDialog.findViewById(R.id.mute_music);
        unmuteMusic = musicDialog.findViewById(R.id.unmute_music);

        cancelButton.setOnClickListener(v -> musicDialog.dismiss());

        muteMusic.setOnClickListener(v -> {
            muteMusic();
            muteMusic.setVisibility(View.INVISIBLE);
            unmuteMusic.setVisibility(View.VISIBLE);
        });

        unmuteMusic.setOnClickListener(v -> {
            unmuteMusic();
            muteMusic.setVisibility(View.VISIBLE);
            unmuteMusic.setVisibility(View.INVISIBLE);
        });

        String[] soundtracks = {"LOFI", "AMBIENCE", "CHILL"};

        music_auto_complete_text = musicDialog.findViewById(R.id.music_auto_complete_text);
        soundtrackAdapter = new ArrayAdapter<String>(this, R.layout.music_drop_down_layout, soundtracks);
        music_auto_complete_text.setAdapter(soundtrackAdapter);

        music_auto_complete_text.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String soundtrackSelected = parent.getItemAtPosition(position).toString();
                if (soundtrackSelected == "LOFI") {
                    setMusicToLofi();
                }
                if (soundtrackSelected == "AMBIENCE") {
                    setMusicToAmbience();
                }
                if (soundtrackSelected == "CHILL") {
                    setMusicToChill();
                }
            }
        });

        musicDialog.show();

    }

    void cancelButton_MusicDialog() {
        musicDialog.dismiss();
    }

    @Override
    protected void onStart() {
        initButtonView();
        super.onStart();
    }

    void initButtonView() {
        header.hbg_menu_info.setVisibility(INVISIBLE);
        header.hbg_menu_doc.setVisibility(INVISIBLE);
        header.hbg_menu_music.setVisibility(INVISIBLE);
        header.hbg_menu_close.setVisibility(INVISIBLE);
        header.hbg_menu.setVisibility(VISIBLE);
    }

    private void defaultMusicSetting() {
        musicPlayer.setLooping(true);
        unmuteMusic();
        musicPlayer.start();
    }

    void playMusic() {
        musicPlayer = MediaPlayer.create(this, R.raw.lofi);
        defaultMusicSetting();
    }

    private void setMusicToLofi() {
        musicPlayer.stop();
        musicPlayer = MediaPlayer.create(this, R.raw.lofi);
        defaultMusicSetting();
    }

    private void setMusicToAmbience() {
        musicPlayer.stop();
        musicPlayer = MediaPlayer.create(this, R.raw.ambience);
        defaultMusicSetting();
    }

    private void setMusicToChill() {
        musicPlayer.stop();
        musicPlayer = MediaPlayer.create(this, R.raw.chill);
        defaultMusicSetting();
    }

    private void muteMusic() {
        final float volume = (float) (1 - (Math.log(MAX_VOLUME - 0) / Math.log(MAX_VOLUME)));
        musicPlayer.setVolume(volume, volume);
    }

    private void unmuteMusic() {
        final float volume = (float) (1 - (Math.log(MAX_VOLUME - 90) / Math.log(MAX_VOLUME)));
        musicPlayer.setVolume(volume, volume);
    }


}
