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

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public abstract class App extends AppCompatActivity {
    private static final String FILE_NAME = "data.txt";
    Header header;
    Dialog infoDialog, docDialog, musicDialog;
    ImageView closeButton;
    TextView infoText;

    static MediaPlayer musicPlayer;
    ImageView muteMusic;
    ImageView unmuteMusic;
    AutoCompleteTextView music_auto_complete_text;
    ArrayAdapter<String> soundtrackAdapter;
    static int SOUNDTRACK = 1;
    private final static int MAX_VOLUME = 100;
    static boolean isMute = false;

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

    private void initText(Dialog dialog, int infoID) {
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
        initText(infoDialog,R.id.about_info);
    }

    private void setDocDialog() {
        initDialog(docDialog,R.layout.documentation_pop_up);
        initCloseImageView(docDialog,R.id.exitbuttondoc);
        initText(docDialog,R.id.documentationInfo);
    }

    void setMusicDialog() {
        initDialog(musicDialog,R.layout.music_pop_up);
        initCloseImageView(musicDialog,R.id.exitbuttonmusic);

        muteMusic = musicDialog.findViewById(R.id.mute_music);
        unmuteMusic = musicDialog.findViewById(R.id.unmute_music);

        unmuteMusic.setOnClickListener(v -> {
            muteMusic();
            isMute = true;
            muteMusic.setVisibility(View.VISIBLE);
            unmuteMusic.setVisibility(View.INVISIBLE);
        });

        muteMusic.setOnClickListener(v -> {
            unmuteMusic();
            isMute = false;
            muteMusic.setVisibility(View.INVISIBLE);
            unmuteMusic.setVisibility(View.VISIBLE);
        });

        String[] soundtracks = {"LOFI", "AMBIENCE", "CHILL"};

        music_auto_complete_text = musicDialog.findViewById(R.id.music_auto_complete_text);
        soundtrackAdapter = new ArrayAdapter<String>(this, R.layout.music_drop_down_layout, soundtracks);
        music_auto_complete_text.setAdapter(soundtrackAdapter);
        music_auto_complete_text.setHintTextColor(Color.BLACK);

        if (SOUNDTRACK == 1) {
            music_auto_complete_text.setHint("LOFI");
        }

        if (SOUNDTRACK == 2) {
            music_auto_complete_text.setHint("AMBIENCE");
        }

        if (SOUNDTRACK == 3) {
            music_auto_complete_text.setHint("CHILL");
        }

        if (isMute) {
            muteMusic.setVisibility(VISIBLE);
            unmuteMusic.setVisibility(INVISIBLE);
        }
        else {
            muteMusic.setVisibility(INVISIBLE);
            unmuteMusic.setVisibility(VISIBLE);
        }

        music_auto_complete_text.setOnItemClickListener((parent, view, position, id) -> {
            String soundtrackSelected = parent.getItemAtPosition(position).toString();
            if (soundtrackSelected == "LOFI") {
                SOUNDTRACK = 1;
                setMusicToLofi();
            }
            if (soundtrackSelected == "AMBIENCE") {
                SOUNDTRACK = 2;
                setMusicToAmbience();
            }
            if (soundtrackSelected == "CHILL") {
                SOUNDTRACK = 3;
                setMusicToChill();
            }
        });
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
        final float volume = (float) (1 - (Math.log(MAX_VOLUME - 99) / Math.log(MAX_VOLUME)));
        musicPlayer.setVolume(volume, volume);
    }


    void writeFile() {
        String level = String.valueOf(CBT_Game.level);
        String scene = String.valueOf(CBT_Sublevel.scene);

        FileOutputStream file = null;
        try {
            file = openFileOutput(FILE_NAME, MODE_PRIVATE);
            file.write(level.getBytes());
            file.write("\n".getBytes());
            file.write(scene.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (file != null) {
                try {
                    file.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    void readFile() {
        FileInputStream file = null;
        try {
            file = openFileInput(FILE_NAME);
            InputStreamReader inputStreamReader = new InputStreamReader(file);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String text;

            // Save last unlocked level
            text = bufferedReader.readLine();
            CBT_Game.levelUnlock = Integer.valueOf(text);

            // Save last unlocked scene
            text = bufferedReader.readLine();
            CBT_Sublevel.sceneUnlock = Integer.valueOf(text);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (file != null) {
                try {
                    file.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
