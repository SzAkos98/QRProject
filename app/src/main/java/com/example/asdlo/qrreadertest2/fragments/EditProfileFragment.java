package com.example.asdlo.qrreadertest2.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asdlo.qrreadertest2.R;
import com.example.asdlo.qrreadertest2.model.Profile;
import com.example.asdlo.qrreadertest2.viewModels.ProfileViewModel;

public class EditProfileFragment extends Fragment {

    private EditText editProfileEditText_name;
    private EditText editProfileEditText_age;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_edit_profile,
                container, false);
        final ProfileViewModel profileViewModel = new ProfileViewModel(getActivity().getApplication());

        editProfileEditText_name = view.findViewById(R.id.editProileEditText_name);
        editProfileEditText_name.setText(ProfileFragment.name);

        editProfileEditText_age = view.findViewById(R.id.editProileEditText_age);
        editProfileEditText_age.setText(Integer.toString(ProfileFragment.age));


        Button addProfileBtn = view.findViewById(R.id.update_profile);

        addProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editProfileEditText_name.getText().toString();
                int age = Integer.parseInt(editProfileEditText_age.getText().toString());

                if (name.trim().isEmpty()) {
                    Toast.makeText(getContext(), "Please insert name and age", Toast.LENGTH_LONG).show();
                } else {
                    Profile profile = new Profile(name, age);
                    profile.setId(ProfileFragment.profileId);
                    profileViewModel.update(profile);
                    Toast.makeText(getContext(), "Profile saved", Toast.LENGTH_SHORT).show();
                }
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfileFragment()).commit();
            }
        });

        Button backProfileBtn = view.findViewById(R.id.back_to_profile_edit);

        backProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfileFragment()).commit();
            }
        });

        return view;
    }

}
