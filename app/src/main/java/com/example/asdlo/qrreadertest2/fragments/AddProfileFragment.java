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

public class AddProfileFragment extends Fragment {


    private EditText addProfileEditText_name;
    private EditText addProfileEditText_age;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_add_profile,
                container, false);
        final ProfileViewModel profileViewModel = new ProfileViewModel(getActivity().getApplication());

        addProfileEditText_name = view.findViewById(R.id.addProfileEditText_name);
        addProfileEditText_age = view.findViewById(R.id.addProfileEditText_age);

        Button addProfileBtn = view.findViewById(R.id.save_profile);

        addProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = addProfileEditText_name.getText().toString();
                int age = Integer.parseInt(addProfileEditText_age.getText().toString());

                if (name.trim().isEmpty()) {
                    Toast.makeText(getContext(), "Please insert qrCode", Toast.LENGTH_LONG).show();
                } else {
                    Profile profile = new Profile(name, age);
                    profileViewModel.insert(profile);
                    Toast.makeText(getContext(), "Profile saved", Toast.LENGTH_SHORT).show();
                }
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfileFragment()).commit();
            }
        });

        Button backProfileBtn = view.findViewById(R.id.back_to_profile);

        backProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfileFragment()).commit();
            }
        });

        return view;
    }

}
