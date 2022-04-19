package com.example.rewire;

import android.app.Dialog;
import android.widget.Button;

public class AppRunning extends App {
    Dialog exitToHomeDialog;
    Button closeButton, exitButton;

    @Override
    void setHeader() {
        super.setHeader();
        exitToHomeDialog = new Dialog(this);
    }

    @Override
    void toHomeMenu() {
        initDialog(exitToHomeDialog,R.layout.exit_to_home_popup);
        initCloseButton(exitToHomeDialog,R.id.closeButton);
        initExitButton(exitToHomeDialog,R.id.exitButton);
    }

    private void initCloseButton(Dialog dialog, int closeButtonID) {
        closeButton = dialog.findViewById(closeButtonID);
        closeButton.setOnClickListener(v -> closeDialog(dialog));
    }

    void initExitButton(Dialog dialog, int exitButtonID) {
        exitButton = dialog.findViewById(exitButtonID);
        exitButton.setOnClickListener(v -> exitOnClick(dialog));
    }

    void exitOnClick(Dialog dialog) {
        dialog.dismiss();
        super.toHomeMenu();
    }
}
