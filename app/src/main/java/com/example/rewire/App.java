// No open source code or third-party libraries were used in this class.
// This class contains only original source code.

package com.example.rewire;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

// App class is a subclass of AppCompatActivity
// It is used to initialize the dialogs, as well as to store the progress of CBT
public class App extends AppCompatActivity {
    private static final String FILE_NAME = "data.txt";
    String filepath = "";

    Header header;
    Dialog infoDialog, docDialog, musicDialog;
    ImageView closeButton;
    TextView infoText;

    Music music = new Music(this);
    ImageView muteMusic;
    ImageView unmuteMusic;
    AutoCompleteTextView music_auto_complete_text;
    ArrayAdapter<String> soundtrackAdapter;

    // Called when any of the dialogs is starting
    // Show the hamburger menu icon and hide the information, documentation, music, and close icon
    @Override
    protected void onStart() {
        initButtonView();
        super.onStart();
    }

    // Show the hamburger menu icon and hide the information, documentation, music, and close icon
    void initButtonView() {
        header.hbg_menu_info.setVisibility(INVISIBLE);
        header.hbg_menu_doc.setVisibility(INVISIBLE);
        header.hbg_menu_music.setVisibility(INVISIBLE);
        header.hbg_menu_close.setVisibility(INVISIBLE);
        header.hbg_menu.setVisibility(VISIBLE);
    }

    // Activate the header
    // Connect the information, documentation, and music to their respective dialogs
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

    // Switch the interface to main menu
    void toHomeMenu() {
        initButtonView();
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }

    // Initialize the dialog
    // Show the hamburger menu icon and hide the information, documentation, music, and close icon
    // Set the background of the dialog to transparent
    void initDialog(Dialog dialog, int viewID) {
        initButtonView();
        dialog.setContentView(viewID);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    // Initialize the close button for the dialog
    private void initCloseImageView(Dialog dialog, int closeButtonID) {
        closeButton = dialog.findViewById(closeButtonID);
        closeButton.setOnClickListener(v -> closeDialog(dialog));
    }

    // Write a text to the dialog
    private void initText(Dialog dialog, int infoID) {
        infoText = dialog.findViewById(infoID);
        infoText.setMovementMethod(LinkMovementMethod.getInstance());
        infoText.setLinkTextColor(getResources().getColor(R.color.teal_700));
    }

    // Dismiss the dialog
    void closeDialog(Dialog dialog) {
        dialog.dismiss();
    }

    // Initialize the information dialog
    void setInfoDialog() {
        initDialog(infoDialog,R.layout.about_popup);
        initCloseImageView(infoDialog,R.id.exitbuttoninfo);
        initText(infoDialog,R.id.about_info);
    }

    // Initialize the documentation dialog
    private void setDocDialog() {
        initDialog(docDialog,R.layout.documentation_popup);
        initCloseImageView(docDialog,R.id.exitbuttondoc);
        initText(docDialog,R.id.documentationInfo);
    }

    // Initialize the music dialog
    // Set button listener for the mute icon
    void setMusicDialog() {
        initDialog(musicDialog,R.layout.music_popup);
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

        // Create a drop down menu
        String[] soundtracks = {"LOFI", "AMBIENCE", "CHILL"};

        music_auto_complete_text = musicDialog.findViewById(R.id.music_auto_complete_text);
        soundtrackAdapter = new ArrayAdapter<String>(this, R.layout.music_drop_down, soundtracks);
        music_auto_complete_text.setAdapter(soundtrackAdapter);
        music_auto_complete_text.setHintTextColor(Color.BLACK);

        // Set the hint text of the drop down menu to the current soundtrack
        if (music.getSOUNDTRACK() == 1) {
            music_auto_complete_text.setHint("LOFI");
        }

        if (music.getSOUNDTRACK() == 2) {
            music_auto_complete_text.setHint("AMBIENCE");
        }

        if (music.getSOUNDTRACK() == 3) {
            music_auto_complete_text.setHint("CHILL");
        }

        // update the music icon according to the mute music indicator
        if (music.getIsMute()) {
            muteMusic.setVisibility(VISIBLE);
            unmuteMusic.setVisibility(INVISIBLE);
        }
        else {
            muteMusic.setVisibility(INVISIBLE);
            unmuteMusic.setVisibility(VISIBLE);
        }

        // Set the background music according to the drop down menu
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

    // Write the progress of CBT to a text file
    void writeFile() {
        String level = String.valueOf(CBT_Level.level);
        String scene = String.valueOf(CBT_Sublevel.scene);

        File myExternalFile = new File(this.getExternalFilesDir(filepath), FILE_NAME);
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
    }

    // Read the previous progress of CBT from a text file
    void readFile() {
        FileReader fr;
        File myExternalFile = new File(this.getExternalFilesDir(filepath), FILE_NAME);
        try {
            String text;
            fr = new FileReader(myExternalFile);
            BufferedReader br = new BufferedReader(fr);

            // Next, call readLine() method on BufferedReader object to read a line of text.

            // Save last unlocked level
            text = br.readLine();
            CBT_Level.levelUnlock = Integer.valueOf(text);

            // Save last unlocked scene
            text = br.readLine();
            CBT_Sublevel.sceneUnlock = Integer.valueOf(text);

            fr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}