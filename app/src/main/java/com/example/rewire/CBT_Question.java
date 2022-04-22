package com.example.rewire;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class CBT_Question extends AppRunning {
    private Dialog completeDialog;
    private TextView question;
    private Button optionA, optionB, optionC, toLevelMenu;
    private ImageView optionA_correct, optionB_correct, optionC_correct;
    private ImageView optionA_wrong, optionB_wrong, optionC_wrong;
    private int questionID, optionAID, optionBID, optionCID;
    private String ans, select, ori;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cbt_question_answer);

        completeDialog = new Dialog(this);
        completeDialog.setCanceledOnTouchOutside(false);

        setHeader();

        question = findViewById(R.id.question);
        optionA = findViewById(R.id.optionA);
        optionB = findViewById(R.id.optionB);
        optionC = findViewById(R.id.optionC);

        optionA_correct = findViewById(R.id.optionA_correct);
        optionB_correct = findViewById(R.id.optionB_correct);
        optionC_correct = findViewById(R.id.optionC_correct);

        optionA_wrong = findViewById(R.id.optionA_wrong);
        optionB_wrong = findViewById(R.id.optionB_wrong);
        optionC_wrong = findViewById(R.id.optionC_wrong);

        optionA.setOnClickListener(view -> optionAClick());
        optionB.setOnClickListener(view -> optionBClick());
        optionC.setOnClickListener(view -> optionCClick());
    }

    @Override
    protected void onStart() {
        super.onStart();
        int ansID = this.getResources().getIdentifier(CBT_EducationalPage.id + "_Ans", "string", this.getPackageName());
        ans = getResources().getString(ansID);
        displayQuestion();
        displayOptions();
    }

    private void displayQuestion() {
        questionID = this.getResources().getIdentifier(CBT_EducationalPage.id + "_Question", "string", this.getPackageName());
        question.setText(questionID);
    }

    private void displayOptions() {
        optionAID = this.getResources().getIdentifier(CBT_EducationalPage.id + "_OptionA", "string", this.getPackageName());
        optionBID = this.getResources().getIdentifier(CBT_EducationalPage.id + "_OptionB", "string", this.getPackageName());
        optionCID = this.getResources().getIdentifier(CBT_EducationalPage.id + "_OptionC", "string", this.getPackageName());

        optionA.setText(optionAID);
        optionB.setText(optionBID);
        optionC.setText(optionCID);
    }

    private void optionAClick() {
        select = "A";
        if (ans.equals(select)) {
            showCorrectAnswer(optionA, optionA_correct);
        }
        else {
            showIncorrectAnswer(optionA, optionA_wrong);
        }
    }

    private void optionBClick() {
        select = "B";
        if (ans.equals(select)) {
            showCorrectAnswer(optionB, optionB_correct);
        }
        else {
            showIncorrectAnswer(optionB, optionB_wrong);
        }
    }

    private void optionCClick() {
        select = "C";
        if (ans.equals(select)) {
            showCorrectAnswer(optionC, optionC_correct);
        }
        else {
            showIncorrectAnswer(optionC, optionC_wrong);
        }
    }

    private void showAnswer(Button option, ImageView icon) {
        ori = String.valueOf(option.getText());
        changeText(option, "");
        option.setClickable(false);
        icon.setVisibility(View.VISIBLE);
    }

    private void showCorrectAnswer(Button option, ImageView icon) {
        showAnswer(option, icon);
        option.setBackgroundColor(getResources().getColor(R.color.dark_green));
        option.postDelayed(() -> {
            showCompleteDialog();
            unlockNextLevel();
        }, 700);
    }

    private void showIncorrectAnswer(Button option, ImageView icon) {
        showAnswer(option, icon);
        option.setBackgroundColor(getResources().getColor(R.color.dark_red));
        option.postDelayed(() -> {
            afterClick(option, icon);
            option.setBackgroundColor(getResources().getColor(R.color.dark_pink));
        }, 1000);
    }

    private void afterClick(Button option, ImageView icon) {
        changeText(option, ori);
        option.setClickable(true);
        icon.setVisibility(View.INVISIBLE);
    }

    private void changeText(Button option, String string) {
        option.setText(string);
    }

    private void showCompleteDialog() {
        initDialog(completeDialog, R.layout.level_complete_popup);

        toLevelMenu = completeDialog.findViewById(R.id.selectlevel);
        toLevelMenu.setOnClickListener(view -> {
            completeDialog.dismiss();
            Intent intent = new Intent(CBT_Question.this, CBT_Level.class);
            startActivity(intent);
        });

        exitButton = completeDialog.findViewById(R.id.closeButton);
        exitButton.setOnClickListener(view -> {
            completeDialog.dismiss();
            Intent intent = new Intent(CBT_Question.this, MainMenu.class);
            startActivity(intent);
        });
    }

    private void unlockNextLevel() {
        if (CBT_Sublevel.scene > 2) {
            CBT_Level.level++;
            CBT_Sublevel.scene = 1;
        }
        else {
            CBT_Sublevel.scene++;
        }

        writeFile();
    }
}
