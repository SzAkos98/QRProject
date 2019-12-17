package com.example.asdlo.qrreadertest2.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.asdlo.qrreadertest2.R;
import com.example.asdlo.qrreadertest2.adaptors.AboutAdaptor;
import com.example.asdlo.qrreadertest2.model.AboutItem;
import com.example.asdlo.qrreadertest2.model.Footer;
import com.example.asdlo.qrreadertest2.model.Header;
import com.example.asdlo.qrreadertest2.model.RecyclerViewItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AboutFragment extends Fragment {

    private RequestQueue mQueue;

    List<RecyclerViewItem> recyclerViewItems = new ArrayList<>();
    private Context mContext;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_about,
                container, false);

        mContext = view.getContext();


        mQueue = Volley.newRequestQueue(mContext);

        String url = "https://jsonplaceholder.typicode.com/albums";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    if(i==0){
                        Header header = new Header("Welcome To Qr reader About Page");
                        recyclerViewItems.add(header);
                    }
                    try {
                        JSONObject album = response.getJSONObject(i);
                        String title = album.getString("title");
                        int userId = album.getInt("userId");
                        int id = album.getInt("id");
                        AboutItem aboutItem = new AboutItem(id, userId, title);
                        recyclerViewItems.add(aboutItem);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if (i == response.length()-1) {
                        Footer footer = new Footer("Made by Ákos Szabó");
                        recyclerViewItems.add(footer);
                    }
                }
                RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
                recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
                recyclerView.setAdapter(new AboutAdaptor(recyclerViewItems, view.getContext()));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);


        return view;
    }

}