package com.example.meetofinal626;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

public class AlternativeTravel extends AppCompatActivity implements View.OnClickListener {
    Button buttonBack;
    Button buttonMFWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alternative_travel);
        buttonBack = findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(this);

        buttonMFWeb = findViewById(R.id.buttonMFWeb);
        buttonMFWeb.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if (buttonBack == view) {
            Intent mainIntent = new Intent(AlternativeTravel.this, UpcomingTrips.class);
        }
        if (buttonMFWeb == view) {
            WebView webView = new WebView(this);
            setContentView(webView);
            webView.loadUrl("https://www.michiganflyer.com/");
        }
    }
}
