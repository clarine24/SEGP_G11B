package com.example.rewire;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

public class AppRunning extends App {
    Dialog exitToHomeDialog;

    @Override
    void setHeader() {
        super.setHeader();
        exitToHomeDialog = new Dialog(this);
    }

    @Override
    void toHomeMenu() {
        exitToHomeDialog.setContentView(R.layout.exit_to_home_popup);
        exitToHomeDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        closeButton = exitToHomeDialog.findViewById(R.id.closeButton);
        exitButton = exitToHomeDialog.findViewById(R.id.exitButton);

        closeButton.setOnClickListener(v -> setCloseButton());
        exitButton.setOnClickListener(v -> setExitButton());

        exitToHomeDialog.show();
    }

    void setCloseButton() {
        exitToHomeDialog.dismiss();
    }

    void setExitButton() {
        setCloseButton();
        super.toHomeMenu();
    }
}
