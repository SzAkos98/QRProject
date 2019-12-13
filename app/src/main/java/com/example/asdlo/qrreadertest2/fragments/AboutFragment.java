package com.example.asdlo.qrreadertest2.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asdlo.qrreadertest2.R;
import com.example.asdlo.qrreadertest2.adaptors.AboutAdaptor;
import com.example.asdlo.qrreadertest2.model.AboutItem;
import com.example.asdlo.qrreadertest2.model.Footer;
import com.example.asdlo.qrreadertest2.model.Header;
import com.example.asdlo.qrreadertest2.model.RecyclerViewItem;

import java.util.ArrayList;
import java.util.List;

public class AboutFragment extends Fragment {


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_about,
                container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(new AboutAdaptor(createDummyList(),view.getContext()));

        return view;
    }

    private List<RecyclerViewItem> createDummyList() {
        List<RecyclerViewItem> recyclerViewItems = new ArrayList<>();
        Header header = new Header("Welcome To Qr reader About Page");
        recyclerViewItems.add(header);

        String[] titles = {"Összeadás",
                "Kivonás",
                "Szorzás",
                "Osztás",
                "1",
                "2",
                "3",
                "4",
                "5",
                "6",
                "7",
                "8"};
        String[] descriptions = {"1+1=2",
                "2-1=1",
                "3*3=6",
                "6/2=3",
                "test1",
                "test2",
                "test3",
                "test3",
                "test4",
                "test5",
                "test6",
                "test7"};
        for (int i = 0; i < titles.length; i++) {
            System.out.println(i);
            System.out.println(titles[i] +"   "+ descriptions[i]);
            AboutItem aboutItem = new AboutItem(titles[i], descriptions[i]);
            recyclerViewItems.add(aboutItem);

        }

        Footer footer = new Footer("Made by Ákos Szabó");
        recyclerViewItems.add(footer);
        return recyclerViewItems;
    }


}