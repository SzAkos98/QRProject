package com.example.asdlo.qrreadertest2.fragments;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.asdlo.qrreadertest2.MainActivity;
import com.example.asdlo.qrreadertest2.R;

public class ResultFragment extends Fragment {

    TextView textView;
    Button goBackBtn, goUrlBtn;
    String url;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_result,
                container, false);
        textView = (TextView) view.findViewById(R.id.displayQRcode);
        textView.setText("Your QR code is : \n" + SearchFragment.code);
        goBackBtn = (Button) view.findViewById(R.id.goBackBtn);
        goBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity1 ();
            }
        });
        goUrlBtn = (Button) view.findViewById(R.id.goUrlBtn);
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
        return view;
    }


    public void openActivity1() {
        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);
    }
}
