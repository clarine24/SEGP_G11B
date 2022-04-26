//No open source code or third-party libraries were used in this class.
//This class contains only original source code

package com.example.rewire;

import android.app.Dialog;
import android.widget.Button;

// Subclass of App for AppRunning
// Extends AppRunning class when the exit to home pop-up is needed
public class AppRunning extends App {
    Dialog exitToHomeDialog;
    Button closeButton, exitButton;

    // Activate the header
    // Connect the information, documentation, and music to their respective dialogs
    @Override
    void setHeader() {
        super.setHeader();
        exitToHomeDialog = new Dialog(this);
    }

    // Display the exit to home pop-up window
    // Switch the interface to main menu
    @Override
    void toHomeMenu() {
        initDialog(exitToHomeDialog,R.layout.exit_to_home_popup);
        initCloseButton(exitToHomeDialog,R.id.closeButton);
        initExitButton(exitToHomeDialog,R.id.exitButton);
    }

    // Initialize the close button for the dialog
    private void initCloseButton(Dialog dialog, int closeButtonID) {
        closeButton = dialog.findViewById(closeButtonID);
        closeButton.setOnClickListener(v -> closeDialog(dialog));
    }

    // Initialize the exit button for the dialog
    void initExitButton(Dialog dialog, int exitButtonID) {
        exitButton = dialog.findViewById(exitButtonID);
        exitButton.setOnClickListener(v -> exitOnClick(dialog));
    }

    // Switch the interface to main menu
    void exitOnClick(Dialog dialog) {
        dialog.dismiss();
        super.toHomeMenu();
    }
}
