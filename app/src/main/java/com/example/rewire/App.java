package com.example.rewire;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public abstract class App extends AppCompatActivity {
    Header header;
    Dialog infoDialog, docDialog, musicDialog;
    ImageView closeButton;
    TextView infoText;

    MediaPlayer musicPlayer;
    ImageView muteMusic;
    ImageView unmuteMusic;
    AutoCompleteTextView music_auto_complete_text;
    ArrayAdapter<String> soundtrackAdapter;
    private final static int MAX_VOLUME = 100;

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

    void initDialog(Dialog dialog, int viewID) {
        initButtonView();
        dialog.setContentView(viewID);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    private void initCloseImageView(Dialog dialog, int closeButtonID) {
        closeButton = dialog.findViewById(closeButtonID);
        closeButton.setOnClickListener(v -> closeDialog(dialog));
    }

    private void initInfoText(Dialog dialog, int infoID) {
        infoText = dialog.findViewById(infoID);
        infoText.setMovementMethod(LinkMovementMethod.getInstance());
        infoText.setLinkTextColor(getResources().getColor(R.color.teal_700));
    }

    void closeDialog(Dialog dialog) {
        dialog.dismiss();
    }

    void setInfoDialog() {
        initDialog(infoDialog,R.layout.about_pop_up);
        initCloseImageView(infoDialog,R.id.exitbuttoninfo);
        initInfoText(infoDialog,R.id.about_info);
    }

    private void setDocDialog() {
        initDialog(docDialog,R.layout.documentation_pop_up);
        initCloseImageView(docDialog,R.id.exitbuttondoc);
        initInfoText(docDialog,R.id.documentationInfo);
    }

    void setMusicDialog() {
        initDialog(musicDialog,R.layout.music_pop_up);
        initCloseImageView(musicDialog,R.id.exitbuttonmusic);

        muteMusic = musicDialog.findViewById(R.id.mute_music);
        unmuteMusic = musicDialog.findViewById(R.id.unmute_music);

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

        music_auto_complete_text.setOnItemClickListener((parent, view, position, id) -> {
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
        });
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
