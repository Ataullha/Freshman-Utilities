package com.example.freshmanutilites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ToolsActivity extends AppCompatActivity implements View.OnClickListener{

    private CardView cv_varsityWEB,cv_libraryWEB,cv_notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tools);

        cv_libraryWEB = findViewById(R.id.cv_library_website);
        cv_varsityWEB = findViewById(R.id.cv_varsity_website);
        cv_notification = findViewById(R.id.cv_notification);

        cv_libraryWEB.setOnClickListener(this);
        cv_varsityWEB.setOnClickListener(this);
        cv_notification.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.cv_varsity_website:
                startActivity(new Intent(ToolsActivity.this,VarsityWebViewActivity.class));
                break;
            case R.id.cv_library_website:
                startActivity(new Intent(ToolsActivity.this,LibraryWebViewActivity.class));
                break;
            case R.id.cv_notification:
                startActivity(new Intent(ToolsActivity.this,PushNotificationActivity.class));
                break;


        }


    }
}