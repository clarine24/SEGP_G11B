//No open source code or third-party libraries were used in this class.
//This class contains only original source code
package com.example.rewire;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;

// Where all the classes to make the header is found
//
public class Header extends ConstraintLayout {
    public ImageView hbg_menu;
    public ImageView hbg_menu_info;
    public ImageView hbg_menu_music;
    public ImageView hbg_menu_doc;
    public ImageView hbg_menu_close;
    public ImageView home_btn;

    //
    public Header(Context context) {
        super(context);
    }

    //
    public Header(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    //
    public Header(Context context, AttributeSet attributeSet, int defStyle) {
        super(context, attributeSet, defStyle);
    }
    //calls the inflateHeader class
    public void initHeader() {
        inflateHeader();
    }

    // Takes the layout xml files and creates the view objects from it
    // Hbg menu is made visible here
    private void inflateHeader() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.header, this);

        hbg_menu = findViewById(R.id.hbg_menu);
        hbg_menu_info = findViewById(R.id.hbg_menu_info);
        hbg_menu_music = findViewById(R.id.music);
        hbg_menu_doc = findViewById(R.id.hbg_menu_doc);
        hbg_menu_close = findViewById(R.id.hbg_menu_close);
        home_btn = findViewById(R.id.bt_home_icon);

        hbg_menu.setOnClickListener(view -> {
            hbg_menu.setVisibility(INVISIBLE);
            hbg_menu_info.setVisibility(VISIBLE);
            hbg_menu_music.setVisibility(VISIBLE);
            hbg_menu_doc.setVisibility(VISIBLE);
            hbg_menu_close.setVisibility(VISIBLE);
        });

        hbg_menu_close.setOnClickListener(view -> {
            hbg_menu_info.setVisibility(INVISIBLE);
            hbg_menu_doc.setVisibility(INVISIBLE);
            hbg_menu_music.setVisibility(INVISIBLE);
            hbg_menu_close.setVisibility(INVISIBLE);
            hbg_menu.setVisibility(VISIBLE);
        });
    }
}