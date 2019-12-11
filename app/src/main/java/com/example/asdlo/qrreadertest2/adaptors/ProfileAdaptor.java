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
import com.example.asdlo.qrreadertest2.model.Profile;


public class ProfileAdaptor extends ListAdapter<Profile, ProfileAdaptor.ProfileHolder> {

    private OnItemClickListener listener;

    public ProfileAdaptor() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Profile> DIFF_CALLBACK = new DiffUtil.ItemCallback<Profile>() {
        @Override
        public boolean areItemsTheSame(@NonNull Profile oldItem, @NonNull Profile newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Profile oldItem, @NonNull Profile newItem) {
            return oldItem.getName().equals(newItem.getName()) &&
                    oldItem.getAge() == newItem.getAge();
        }
    };

    @NonNull
    @Override
    public ProfileHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.profile_item, viewGroup, false);
        return new ProfileHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileHolder proflieHolder, int i) {
        Profile currentProfile = getItem(i);
        proflieHolder.textViewName.setText(currentProfile.getName());
        proflieHolder.textViewAge.setText(Integer.toString(currentProfile.getAge()));
        proflieHolder.textViewID.setText(String.valueOf(currentProfile.getId()));
    }

    public Profile getProfileAt(int position) {
        return getItem(position);
    }

    class ProfileHolder extends RecyclerView.ViewHolder {
        private TextView textViewName;
        private TextView textViewAge;
        private TextView textViewID;

        public ProfileHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.text_view_name);
            textViewAge = itemView.findViewById(R.id.text_view_age);
            textViewID = itemView.findViewById(R.id.text_view_id_profile);

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
        void onItemClick(Profile proflie);
    }

    public void setOnItemClickedListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
