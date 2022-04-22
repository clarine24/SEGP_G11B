package com.example.rewire;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

public class CBT_EducationalPage extends AppRunning {
    TextView eduTitle, eduInfo;
    static String level, scene, id;
    ImageView next;

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

    @Override
    protected void onStart() {
        super.onStart();
        level = String.valueOf(CBT_Level.level);
        scene = String.valueOf(CBT_Sublevel.scene);
        id = "Level" + level + "_Scene" + scene;
        displayText();
    }

    private void displayText() {
        int titleID = this.getResources().getIdentifier(id + "_Title", "string", this.getPackageName());
        int infoID = this.getResources().getIdentifier(id + "_Info", "string", this.getPackageName());
        eduTitle.setText(titleID);
        eduInfo.setText(infoID);
        eduInfo.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void toScenario() {
        Intent intent = new Intent(CBT_EducationalPage.this, CBT_Question.class);
        startActivity(intent);
    }
}