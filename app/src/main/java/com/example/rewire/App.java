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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public abstract class App extends AppCompatActivity {
    //private static final String FILE_NAME = "data.txt";

    private static final String FILE_NAME = "data.txt";
    String filepath = "";
    String fileContent = "";

    Header header;
    Dialog infoDialog, docDialog, musicDialog;
    ImageView closeButton;
    TextView infoText;

    Music music = new Music(this);
    ImageView muteMusic;
    ImageView unmuteMusic;
    AutoCompleteTextView music_auto_complete_text;
    ArrayAdapter<String> soundtrackAdapter;

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
            music.muteMusic();
            music.setIsMute(true);
            muteMusic.setVisibility(View.VISIBLE);
            unmuteMusic.setVisibility(View.INVISIBLE);
        });

        muteMusic.setOnClickListener(v -> {
            music.unmuteMusic();
            music.setIsMute(false);
            muteMusic.setVisibility(View.INVISIBLE);
            unmuteMusic.setVisibility(View.VISIBLE);
        });

        String[] soundtracks = {"LOFI", "AMBIENCE", "CHILL"};

        music_auto_complete_text = musicDialog.findViewById(R.id.music_auto_complete_text);
        soundtrackAdapter = new ArrayAdapter<String>(this, R.layout.music_drop_down_layout, soundtracks);
        music_auto_complete_text.setAdapter(soundtrackAdapter);
        music_auto_complete_text.setHintTextColor(Color.BLACK);

        if (music.getSOUNDTRACK() == 1) {
            music_auto_complete_text.setHint("LOFI");
        }

        if (music.getSOUNDTRACK() == 2) {
            music_auto_complete_text.setHint("AMBIENCE");
        }

        if (music.getSOUNDTRACK() == 3) {
            music_auto_complete_text.setHint("CHILL");
        }


        if (music.getIsMute()) {
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
                music.setSOUNDTRACK(1);
                music.setMusicToLofi();
            }

            if (soundtrackSelected == "AMBIENCE") {
                music.setSOUNDTRACK(2);
                music.setMusicToAmbience();
            }
            if (soundtrackSelected == "CHILL") {
                music.setSOUNDTRACK(3);
                music.setMusicToChill();
            }


        });

    }

    void writeFile() {
        String level = String.valueOf(CBT_Game.level);
        String scene = String.valueOf(CBT_Sublevel.scene);

        File myExternalFile = new File(this.getExternalFilesDir(filepath), FILE_NAME);
                // Create an object of FileOutputStream for writing data to myFile.txt
        FileOutputStream fos = null;
        try {
                    // Instantiate the FileOutputStream object and pass myExternalFile in constructor
                    fos = new FileOutputStream(myExternalFile);
                    // Write to the file
                    fos.write(level.getBytes());
                    fos.write("\n".getBytes());
                    fos.write(scene.getBytes());
                    // Close the stream
                    fos.close();
        } catch (FileNotFoundException e) {
                    e.printStackTrace();
        } catch (IOException e) {
                    e.printStackTrace();
        }
                // Clear the EditText
                //etInput.setText("");
                // Show a Toast message to inform the user that the operation has been successfully completed.
                //Toast.makeText(MainActivity.this, "Information saved to SD card.", Toast.LENGTH_SHORT).show();
    }


    void readFile() {
        FileReader fr;
        File myExternalFile = new File(this.getExternalFilesDir(filepath), FILE_NAME);
        // Instantiate a StringBuilder object. This class is an alternative to String Class
        // and it is mutable, has methods such as append(), insert(), or replace() that allow to
        // modify strings. Hence it is more efficient.
        StringBuilder stringBuilder = new StringBuilder();
        try {
            String text;
            fr = new FileReader(myExternalFile);
            BufferedReader br = new BufferedReader(fr);

            // Next, call readLine() method on BufferedReader object to read a line of text.

            // Save last unlocked level
            text = br.readLine();
            CBT_Game.levelUnlock = Integer.valueOf(text);

            // Save last unlocked scene
            text = br.readLine();
            CBT_Sublevel.sceneUnlock = Integer.valueOf(text);

            // Use a while loop to read the entire file
            while(text != null){
                // Append the line read to StringBuilder object. Also, append a new-line
                stringBuilder.append(text).append('\n');
                // Again read the next line and store in variable line
                text = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Convert the StringBuilder content into String and add text "File contents\n"
            // at the beginning.
            //String fileContents = "File contents\n" + stringBuilder.toString();
            // Set the TextView with fileContents
            //tvLoad.setText(fileContents);
        }
    }
}