// No open source code or third-party libraries were used in this class.
// This class contains only original source code.


package com.example.rewire;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

// A subclass of AppRunning for Educational Info.
// It extends AppRunning class as the exit to home pop-up should appear on the educational info page frame.
public class CBT_EducationalPage extends AppRunning {
    TextView eduTitle, eduInfo;
    static String level, scene, id;
    ImageView next;


    // Called when CBT educational page activity is starting
    // Initialise variables used by CBT_EducationalPage class
    // Connect page to header
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cbt_educationalpage);

        setHeader();

        eduTitle = findViewById(R.id.edu_title);
        eduInfo = findViewById(R.id.edu_info);

        next = findViewById(R.id.edu_next);
        next.setOnClickListener(view -> toScenario());
    }

    // Called when the CBT educational page is displayed to users
    // Gets the proper educational info of a specific scene by ID.
    @Override
    protected void onStart() {
        super.onStart();
        level = String.valueOf(CBT_Level.level);
        scene = String.valueOf(CBT_Sublevel.scene);
        id = "Level" + level + "_Scene" + scene;
        displayText();
    }

    // Called by onStart to display text
    // gets the desired educational info text by IDs of the cbt_string.xml file in resource folder
    private void displayText() {
        int titleID = this.getResources().getIdentifier(id + "_Title", "string", this.getPackageName());
        int infoID = this.getResources().getIdentifier(id + "_Info", "string", this.getPackageName());
        eduTitle.setText(titleID);
        eduInfo.setText(infoID);
        eduInfo.setMovementMethod(LinkMovementMethod.getInstance());
    }

    // Starts the scenario activity and dismisses the educational info activity
    private void toScenario() {
        Intent intent = new Intent(CBT_EducationalPage.this, CBT_Question.class);
        startActivity(intent);
    }
}