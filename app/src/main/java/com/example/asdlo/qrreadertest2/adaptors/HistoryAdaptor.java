package com.example.asdlo.qrreadertest2.adaptors;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.asdlo.qrreadertest2.R;
import com.example.asdlo.qrreadertest2.model.History;

import java.util.ArrayList;
import java.util.List;


public class HistoryAdaptor extends ListAdapter<History, HistoryAdaptor.HistoryHolder> {


    private OnItemClickListener listener;

    public HistoryAdaptor() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<History> DIFF_CALLBACK = new DiffUtil.ItemCallback<History>() {
        @Override
        public boolean areItemsTheSame(@NonNull History oldItem, @NonNull History newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull History oldItem, @NonNull History newItem) {
            return oldItem.getQrCode().equals(newItem.getQrCode());
        }
    };

    @NonNull
    @Override
    public HistoryHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.history_item, viewGroup, false);
        return new HistoryHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryHolder historyHolder, int i) {
        History currentHistory = getItem(i);
        historyHolder.textViewQrCode.setText(currentHistory.getQrCode());
        historyHolder.textViewID.setText(String.valueOf(currentHistory.getId()));
    }

    public History getHistoryAt(int position) {
        return getItem(position);
    }

    class HistoryHolder extends RecyclerView.ViewHolder {
        private TextView textViewQrCode;
        private TextView textViewID;

        public HistoryHolder(@NonNull View itemView) {
            super(itemView);
            textViewQrCode = itemView.findViewById(R.id.text_view_qrCode);
            textViewID = itemView.findViewById(R.id.text_view_id);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(History history);
    }

    public void setOnItemClickedListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
