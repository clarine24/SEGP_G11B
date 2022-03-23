package com.example.rewire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class in_game_educationalpage extends AppCompatActivity implements View.OnClickListener {
    int page = 1;

    ImageView back;
    ImageView next;

    ImageView pageA;
    ImageView pageB;
    ImageView pageC;
    Drawable draw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_game_educationalpage);

        Header header = findViewById(R.id.header);
        header.initHeader();
        header.home_btn.setOnClickListener(v -> startActivity(header.toHomeMenu()));

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

        switchPage();
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
        else {
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
}