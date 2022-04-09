package com.example.rewire;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class BT_main extends AppCompatActivity {
    Button cancelButton;
    Button exitButton;
    Dialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mDialog = new Dialog(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breathing_technique_main);

        Header header = findViewById(R.id.header);
        header.initHeader();

        header.home_btn.setOnClickListener(view -> {
            mDialog.setContentView(R.layout.exit_to_home_popup);
            mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            cancelButton = mDialog.findViewById(R.id.homeButton);
            exitButton = mDialog.findViewById(R.id.exitToHomeButton);

            cancelButton.setOnClickListener(v -> mDialog.dismiss());
            exitButton.setOnClickListener(v -> startActivity(header.toHomeMenu()));

            mDialog.show();
        });
    }
}
