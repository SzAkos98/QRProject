package com.example.asdlo.qrreadertest2.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asdlo.qrreadertest2.R;
import com.example.asdlo.qrreadertest2.model.History;
import com.example.asdlo.qrreadertest2.viewModels.HistoryViewModel;

public class EditHistoryFragment extends Fragment {

    private EditText editHistoryEditText;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_edit_history,
                container, false);
        final HistoryViewModel historyViewModel = new HistoryViewModel(getActivity().getApplication());

        editHistoryEditText = view.findViewById(R.id.editHistoryEditText);
        editHistoryEditText.setText(HistoryFragment.dbQrCode);

        Button addHistoryBtn = view.findViewById(R.id.update_history);

        addHistoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String qrCode = editHistoryEditText.getText().toString();

                if (qrCode.trim().isEmpty()) {
                    Toast.makeText(getContext(), "Please insert qrCode", Toast.LENGTH_LONG).show();
                } else {
                    History history = new History(qrCode);
                    history.setId(HistoryFragment.dbQrCodeId);
                    historyViewModel.update(history);
                    Toast.makeText(getContext(), "History saved", Toast.LENGTH_SHORT).show();
                }
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new HistoryFragment()).commit();
            }
        });

        Button backHistoryBtn = view.findViewById(R.id.back_to_history_edit);

        backHistoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new HistoryFragment()).commit();
            }
        });

        return view;
    }

}
