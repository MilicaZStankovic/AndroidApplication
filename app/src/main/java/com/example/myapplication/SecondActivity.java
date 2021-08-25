package com.example.myapplication;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity  extends AppCompatActivity {
@Override
    public void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_second);
    WebView webView = findViewById(R.id.webview);
    webView.loadUrl("https://www.youtube.com/watch?v=3KFvoDDs0XM&ab_channel=RoyOrbison-Topic");

}

}
