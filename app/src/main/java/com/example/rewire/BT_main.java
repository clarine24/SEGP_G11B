package com.example.rewire;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;


public class BT_main extends AppCompatActivity {

    ImageView cancelButton;
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

            //exitButton = (Button) findViewById(R.id.exitohomemenu);
            mDialog.setContentView(R.layout.tohomemenu_exit);
            mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            cancelButton = mDialog.findViewById(R.id.exitbutton);
            exitButton = mDialog.findViewById(R.id.exitohomemenu);

            cancelButton.setOnClickListener(v -> mDialog.dismiss());
            exitButton.setOnClickListener(v -> startActivity(header.toHomeMenu()));

            mDialog.show();

        });


    }

}
