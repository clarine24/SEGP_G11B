package com.example.rewire;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class cbt_qa extends AppCompatActivity {
    Button cancelButton;
    Button exitButton;
    Dialog exitToHomeDialog, infoDialog;
    ImageView cancelButton1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        exitToHomeDialog = new Dialog(this);
        infoDialog = new Dialog(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cbt_question_answer);

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

        final Button optionA = findViewById(R.id.optionA);
        final Button optionB = findViewById(R.id.optionB);
        final Button optionC = findViewById(R.id.optionC);
        final ImageView optionA_icon = findViewById(R.id.optionA_icon);
        final ImageView optionB_icon = findViewById(R.id.optionB_icon);
        final ImageView optionC_icon = findViewById(R.id.optionC_icon);

        //assume OPTION A is the correct answer
        char correct = 'A';

        optionA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (correct == 'A') {
                    showCorrectAnswer(optionA, optionA_icon);
                }
                else {
                    showIncorrectAnswer(optionA, optionA_icon);
                }
            }

        });

        optionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (correct == 'B')
                    showCorrectAnswer(optionB, optionB_icon);
                else
                    showIncorrectAnswer(optionB, optionB_icon);
            }
        });

        optionC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (correct == 'C')
                    showCorrectAnswer(optionC, optionC_icon);
                else
                    showIncorrectAnswer(optionC, optionC_icon);
            }
        });

    }

    private void showCorrectAnswer(Button option, ImageView icon) {
        option.setClickable(false);
        icon.setVisibility(View.VISIBLE);
        option.setBackgroundColor(getResources().getColor(R.color.dark_green));
        option.postDelayed(new Runnable() {
            @Override
            public void run() {
                option.setClickable(true);
                option.setBackgroundColor(getResources().getColor(R.color.dark_pink));
                icon.setVisibility(View.INVISIBLE);
            }
        }, 2000);
    }

    private void showIncorrectAnswer(Button option, ImageView icon) {
        option.setClickable(false);
        icon.setVisibility(View.VISIBLE);
        option.setBackgroundColor(getResources().getColor(R.color.dark_red));
        option.postDelayed(new Runnable() {
            @Override
            public void run() {
                option.setClickable(true);
                option.setBackgroundColor(getResources().getColor(R.color.dark_pink));
                icon.setVisibility(View.INVISIBLE);
            }
        }, 2000);
    }
}
