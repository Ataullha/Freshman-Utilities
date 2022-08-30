package com.example.freshmanutilites;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class LibraryWebViewActivity extends AppCompatActivity {

    WebView LwebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library_web_view);

        LwebView = findViewById(R.id.LibraryWebViewId);

        LwebView.loadUrl("http://library.sust.edu/");
        LwebView.getSettings().setJavaScriptEnabled(true);
        LwebView.setWebViewClient(new WebViewClient());


    }

    @Override
    public void onBackPressed() {
        if (LwebView.canGoBack()){
            LwebView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}