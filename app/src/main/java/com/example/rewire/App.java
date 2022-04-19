package com.example.rewire;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public abstract class App extends AppCompatActivity {
    Header header;
    Dialog infoDialog, docDialog, musicDialog;
    ImageView cancelButton;
    Button exitButton, closeButton;

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
}
