package com.example.rewire;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
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
    }
}
