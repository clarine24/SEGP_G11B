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

public class cbt_qa extends AppCompatActivity {

    ImageView cancelButton;
    Button exitButton;
    Dialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mDialog = new Dialog(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cbt_question_answer);

        Header header = findViewById(R.id.header);
        header.initHeader();
        header.home_btn.setOnClickListener(view -> {

            mDialog.setContentView(R.layout.tohomemenu_exit);
            mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            cancelButton = mDialog.findViewById(R.id.exitbutton);
            exitButton = mDialog.findViewById(R.id.exitohomemenu);

            cancelButton.setOnClickListener(v1 -> mDialog.dismiss());
            exitButton.setOnClickListener(v1 -> {
                mDialog.dismiss();
                startActivity(header.toHomeMenu());
            });

            mDialog.show();

        });

        final Button optionA = findViewById(R.id.optionA);
        final Button optionB = findViewById(R.id.optionB);
        final Button optionC = findViewById(R.id.optionC);
        final ImageView optionA_ans = findViewById(R.id.optionA_ans);
        final ImageView optionB_ans = findViewById(R.id.optionB_ans);
        final ImageView optionC_ans = findViewById(R.id.optionC_ans);

        //assume OPTION A is the correct answer
        char correct = 'A';

        optionA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                optionA_ans.setVisibility(View.VISIBLE);

                if (correct == 'A')
                    optionA.setBackgroundColor(getResources().getColor(R.color.dark_green));
                else
                    optionA.setBackgroundColor(getResources().getColor(R.color.dark_red));
            }

        });

        optionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                optionB_ans.setVisibility(View.VISIBLE);

                if (correct == 'B')
                    optionB.setBackgroundColor(getResources().getColor(R.color.dark_green));
                else
                    optionB.setBackgroundColor(getResources().getColor(R.color.dark_red));
            }
        });

        optionC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                optionC_ans.setVisibility(View.VISIBLE);

                if (correct == 'C')
                    optionC.setBackgroundColor(getResources().getColor(R.color.dark_green));
                else
                    optionC.setBackgroundColor(getResources().getColor(R.color.dark_red));
            }
        });

    }



}
