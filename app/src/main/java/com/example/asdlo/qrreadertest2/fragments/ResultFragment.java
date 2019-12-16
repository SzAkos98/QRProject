package com.example.asdlo.qrreadertest2.fragments;

import android.content.Intent;
import android.databinding.DataBindingUtil;
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
import com.example.asdlo.qrreadertest2.databinding.SearchFregmentBinder;

public class ResultFragment extends Fragment {

    private SearchFregmentBinder binding;
    String url;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_result, container, false);
        View view = binding.getRoot();

        binding.displayQRcodeTextView.setText("Your QR code is : \n" + SearchFragment.code);
        binding.goBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity1 ();
            }
        });
        binding.goUrlBtn.setOnClickListener(new View.OnClickListener() {
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