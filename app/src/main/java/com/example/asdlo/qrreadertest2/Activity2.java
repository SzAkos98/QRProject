package com.example.asdlo.qrreadertest2;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {

    TextView textView;
    Button goBackBtn, goUrlBtn;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        textView = (TextView) findViewById(R.id.displayQRcode);
        textView.setText("Your QR code is : \n" + SearchFragment.code);
        goBackBtn = (Button) findViewById(R.id.goBackBtn);
        goBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity1 ();
            }
        });
        goUrlBtn = (Button) findViewById(R.id.goUrlBtn);
        goUrlBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                url = SearchFragment.code;
                if  (!url.startsWith("http://") && !url.startsWith("https://"))
                    url = "http://" + url;
                Uri uriUrl = Uri.parse(url);
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
            }
        });

    }


    public void openActivity1() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
