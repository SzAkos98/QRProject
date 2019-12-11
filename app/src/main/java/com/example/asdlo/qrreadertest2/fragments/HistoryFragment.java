package com.example.asdlo.qrreadertest2.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.asdlo.qrreadertest2.R;
import com.example.asdlo.qrreadertest2.adaptors.HistoryAdaptor;
import com.example.asdlo.qrreadertest2.viewModels.HistoryViewModel;
import com.example.asdlo.qrreadertest2.model.History;

import java.util.List;

public class HistoryFragment extends Fragment {
    private HistoryViewModel historyViewModel;
    public static String dbQrCode;
    public static int dbQrCodeId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_history,
                container, false);

        FloatingActionButton addHistoryBtn = view.findViewById(R.id.addHistoryBtn);
        addHistoryBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new AddHistoryFragment()).commit();
            }
        });

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setHasFixedSize(true);

        final HistoryAdaptor adaptor = new HistoryAdaptor();
        recyclerView.setAdapter(adaptor);

        historyViewModel = ViewModelProviders.of(this).get(HistoryViewModel.class);
        historyViewModel.getAllHistories().observe(this, new Observer<List<History>>() {
            @Override
            public void onChanged(@Nullable List<History> histories) {
                adaptor.submitList(histories);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                historyViewModel.delete(adaptor.getHistoryAt(viewHolder.getAdapterPosition()));
                Toast.makeText(view.getContext(),"History deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        adaptor.setOnItemClickedListener(new HistoryAdaptor.OnItemClickListener() {
            @Override
            public void onItemClick(History history) {
                dbQrCodeId = history.getId();
                dbQrCode = history.getQrCode();
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new EditHistoryFragment()).commit();
            }
        });
        return view;
    }

}
