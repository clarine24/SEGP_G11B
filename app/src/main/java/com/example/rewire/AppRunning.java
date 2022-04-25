//No open source code or third-party libraries were used in this class.
//This class contains only original source code
package com.example.rewire;

import android.app.Dialog;
import android.widget.Button;

// Subclass of App for AppRunning
// Extends to App class as the exit to home is needed
public class AppRunning extends App {
    Dialog exitToHomeDialog;
    Button closeButton, exitButton;

    // Used to set the header, where each of the buttons inside the header are found and added
    @Override
    void setHeader() {
        super.setHeader();
        exitToHomeDialog = new Dialog(this);
    }
    // Declares what happens when the home menu is called
    // calls the exit to home pop up layout and the closeButton and exitButton id
    @Override
    void toHomeMenu() {
        initDialog(exitToHomeDialog,R.layout.exit_to_home_popup);
        initCloseButton(exitToHomeDialog,R.id.closeButton);
        initExitButton(exitToHomeDialog,R.id.exitButton);
    }
    // declares what happens when the CloseButton is pressed
    private void initCloseButton(Dialog dialog, int closeButtonID) {
        closeButton = dialog.findViewById(closeButtonID);
        closeButton.setOnClickListener(v -> closeDialog(dialog));
    }
    // Declares what happens when the ExitButton is pressed
    void initExitButton(Dialog dialog, int exitButtonID) {
        exitButton = dialog.findViewById(exitButtonID);
        exitButton.setOnClickListener(v -> exitOnClick(dialog));
    }
    // Sends the user back to the home menu
    void exitOnClick(Dialog dialog) {
        dialog.dismiss();
        super.toHomeMenu();
    }
}
