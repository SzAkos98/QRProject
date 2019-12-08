package com.example.asdlo.qrreadertest2.adaptors;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.asdlo.qrreadertest2.R;
import com.example.asdlo.qrreadertest2.model.History;

import java.util.ArrayList;
import java.util.List;


public class HistoryAdaptor extends RecyclerView.Adapter<HistoryAdaptor.HistoryHolder> {

    private List<History> histories = new ArrayList<>();

    @NonNull
    @Override
    public HistoryHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.history_item, viewGroup, false);
        return new HistoryHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryHolder historyHolder, int i) {
        History currentHistory = histories.get(i);
        historyHolder.textViewQrCode.setText(currentHistory.getQrCode());
        historyHolder.textViewID.setText(String.valueOf(currentHistory.getId()));
    }

    @Override
    public int getItemCount() {
        return histories.size();
    }

    public void setHistories(List<History> histories) {
        this.histories = histories;
        notifyDataSetChanged();
    }

    public History getHistoryAt(int position) {
        return histories.get(position);
    }

    class HistoryHolder extends RecyclerView.ViewHolder {
        private TextView textViewQrCode;
        private TextView textViewID;

        public HistoryHolder(@NonNull View itemView) {
            super(itemView);
            textViewQrCode = itemView.findViewById(R.id.text_view_qrCode);
            textViewID = itemView.findViewById(R.id.text_view_id);
        }
    }
}
