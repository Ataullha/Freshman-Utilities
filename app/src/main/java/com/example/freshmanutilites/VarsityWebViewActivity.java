package com.example.freshmanutilites;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class VarsityWebViewActivity extends AppCompatActivity {

    WebView VwebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_varsity_web_view);

        VwebView = findViewById(R.id.VarsityWebViewId);

        VwebView.loadUrl("https://www.sust.edu");
        VwebView.getSettings().setJavaScriptEnabled(true);
        VwebView.setWebViewClient(new WebViewClient());


    }

    @Override
    public void onBackPressed() {
        if (VwebView.canGoBack()){
            VwebView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}