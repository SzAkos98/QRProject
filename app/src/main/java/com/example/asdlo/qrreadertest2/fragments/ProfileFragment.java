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
import com.example.asdlo.qrreadertest2.adaptors.ProfileAdaptor;
import com.example.asdlo.qrreadertest2.viewModels.ProfileViewModel;
import com.example.asdlo.qrreadertest2.model.Profile;

import java.util.List;

public class ProfileFragment extends Fragment {
    private ProfileViewModel profileViewModel;
    public static String name;
    public static int age;
    public static int profileId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_profile,
                container, false);

        FloatingActionButton addProfileBtn = view.findViewById(R.id.addProfileBtn);
        addProfileBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new AddProfileFragment()).commit();
            }
        });

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_profile);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setHasFixedSize(true);

        final ProfileAdaptor adaptor = new ProfileAdaptor();
        recyclerView.setAdapter(adaptor);

        profileViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
        profileViewModel.getAllProfiles().observe(this, new Observer<List<Profile>>() {
            @Override
            public void onChanged(@Nullable List<Profile> profiles) {
                adaptor.submitList(profiles);
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
                profileViewModel.delete(adaptor.getProfileAt(viewHolder.getAdapterPosition()));
                Toast.makeText(view.getContext(),"Profile deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        adaptor.setOnItemClickedListener(new ProfileAdaptor.OnItemClickListener() {
            @Override
            public void onItemClick(Profile profile) {
                profileId = profile.getId();
                name = profile.getName();
                age = profile.getAge();
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new EditProfileFragment()).commit();
            }
        });
        return view;
    }

}
