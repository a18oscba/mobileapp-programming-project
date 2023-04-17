package com.example.project;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity {
    private WebView about;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_activity);

        about = (WebView) findViewById(R.id.aboutView);
        about.setWebViewClient(new WebViewClient());

        about.loadUrl("file:///android_asset/about.html");
    }
}
