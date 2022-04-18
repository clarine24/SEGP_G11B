package com.example.rewire;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class CBT_Game extends AppCompatActivity implements View.OnClickListener{
    public static int level = 0;

    ImageView cancelButton;
    Dialog infoDialog;
    Dialog docDialog;
    ImageView cancelButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        infoDialog = new Dialog(this);
        docDialog = new Dialog( this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cbt_game);

        Header header = findViewById(R.id.header);
        header.initHeader();
        header.home_btn.setOnClickListener(v -> startActivity(header.toHomeMenu()));

        header.hbg_menu_info.setOnClickListener(view -> {
            infoDialog.setContentView(R.layout.about_pop_up);
            infoDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            cancelButton = infoDialog.findViewById(R.id.exitbuttoninfo);
            cancelButton.setOnClickListener(v -> infoDialog.dismiss());

            infoDialog.show();
        });
        header.hbg_menu_doc.setOnClickListener(view -> {

            docDialog.setContentView(R.layout.documentation_pop_up);
            docDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            cancelButton2 = docDialog.findViewById(R.id.exitbuttondoc);

            cancelButton2.setOnClickListener(v -> docDialog.dismiss());

            docDialog.show();

        });

        final Button level1 = findViewById(R.id.level1);
        level1.setOnClickListener(this);

        final Button level2 = findViewById(R.id.level2);
        level2.setOnClickListener(this);

        final Button level3 = findViewById(R.id.level3);
        level3.setOnClickListener(this);

        final Button level4 = findViewById(R.id.level4);
        level4.setOnClickListener(this);

        final Button level5 = findViewById(R.id.level5);
        level5.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.level1:
                level = 1;
                break;
            case R.id.level2:
                level = 2;
                break;
            case R.id.level3:
                level = 3;
                break;
            case R.id.level4:
                level = 4;
                break;
            case R.id.level5:
                level = 5;
                break;
        }

        Intent intent = new Intent(CBT_Game.this, CBT_Sublevel.class);
        startActivity(intent);
    }
}