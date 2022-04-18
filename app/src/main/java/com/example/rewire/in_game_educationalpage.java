package com.example.rewire;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class in_game_educationalpage extends AppCompatActivity implements View.OnClickListener {
    int page = 1;

    ImageView back;
    ImageView next;
    ImageView pageA;
    ImageView pageB;
    ImageView pageC;
    Drawable draw;

    Button cancelButton;
    Button exitButton;
    Dialog exitToHomeDialog, infoDialog;
    ImageView cancelButton1;
    Dialog docDialog;
    ImageView cancelButton2;

    InputStream inputStream;
    BufferedReader bufferedReader;
    StringBuffer stringBuffer;
    TextView eduTitle, eduInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        exitToHomeDialog = new Dialog(this);
        infoDialog = new Dialog(this);
        docDialog = new Dialog( this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_game_educationalpage);

        Header header = findViewById(R.id.header);
        header.initHeader();

        header.home_btn.setOnClickListener(view -> {
            exitToHomeDialog.setContentView(R.layout.exit_to_home_popup);
            exitToHomeDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            cancelButton = exitToHomeDialog.findViewById(R.id.homeButton);
            exitButton = exitToHomeDialog.findViewById(R.id.exitToHomeButton);

            cancelButton.setOnClickListener(v1 -> exitToHomeDialog.dismiss());
            exitButton.setOnClickListener(v1 -> {
                exitToHomeDialog.dismiss();
                startActivity(header.toHomeMenu());
            });

            exitToHomeDialog.show();
        });

        header.hbg_menu_info.setOnClickListener(view -> {
            infoDialog.setContentView(R.layout.about_pop_up);
            infoDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            cancelButton1 = infoDialog.findViewById(R.id.exitbuttoninfo);
            cancelButton1.setOnClickListener(v -> infoDialog.dismiss());

            infoDialog.show();
        });
        header.hbg_menu_doc.setOnClickListener(view -> {

            docDialog.setContentView(R.layout.documentation_pop_up);
            docDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            cancelButton2 = docDialog.findViewById(R.id.exitbuttondoc);

            cancelButton2.setOnClickListener(v -> docDialog.dismiss());

            docDialog.show();

        });

        eduTitle = findViewById(R.id.edu_title);
        eduInfo = findViewById(R.id.edu_info);
        //displayText();

        back = findViewById(R.id.edu_back);
        next = findViewById(R.id.edu_next);

        pageA = findViewById(R.id.edu_pageA);
        pageB = findViewById(R.id.edu_pageB);
        pageC = findViewById(R.id.edu_pageC);

        back.setOnClickListener(this);
        next.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.edu_back:
                page--;
                break;
            case R.id.edu_next:
                page++;
                break;
        }

        if (page > 3) {
            Intent intent = new Intent(in_game_educationalpage.this, cbt_qa.class);
            startActivity(intent);
        }

        if (page < 1) {
            page = 1;
        }

        backButton();
        switchPage();
    }

    private void backButton() {
        if (page == 1) {
            back.setVisibility(View.INVISIBLE);
        }
        else {
            back.setVisibility(View.VISIBLE);
        }
    }

    private void switchPage() {
        if (page == 1) {
            drawBlack(pageA);

            drawWhite(pageB);
            drawWhite(pageC);
        }
        else if (page == 2) {
            drawBlack(pageB);

            drawWhite(pageA);
            drawWhite(pageC);
        }
        else if (page == 3){
            drawBlack(pageC);

            drawWhite(pageA);
            drawWhite(pageB);
        }
    }

    private void drawBlack(ImageView page) {
        String black = "@drawable/bt_circle_black_fill";
        int blackImage = getResources().getIdentifier(black, null, getPackageName());
        draw = getResources().getDrawable(blackImage);

        page.setImageDrawable(draw);
    }

    private void drawWhite(ImageView page) {
        String white = "@drawable/bt_circle_white_fill";
        int whiteImage = getResources().getIdentifier(white, null, getPackageName());
        draw = getResources().getDrawable(whiteImage);

        page.setImageDrawable(draw);
    }

    private void displayText() {
        /*inputStream = this.getResources().openRawResource(R.raw.testing);
        bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        stringBuffer = new StringBuffer();

        String text = "";

        if (inputStream != null) {
            try {
                while ((text = bufferedReader.readLine()) != null) {
                    stringBuffer.append(text + "\n");
                }

                eduInfo.setText(stringBuffer);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }*/
    }
}