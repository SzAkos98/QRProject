package com.example.asdlo.qrreadertest2.viewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.support.annotation.NonNull;

import com.example.asdlo.qrreadertest2.model.Profile;
import com.example.asdlo.qrreadertest2.repositories.ProfileRepository;

import java.util.List;

public class ProfileViewModel extends AndroidViewModel {
    private ProfileRepository profileRepository;
    private LiveData<List<Profile>> allProfiles;
    private LiveData<String> nameTransformed;

    public ProfileViewModel(@NonNull Application application) {
        super(application);
        profileRepository = new ProfileRepository(application);
        allProfiles = profileRepository.getAllProfiles();
        nameTransformed = Transformations.map(profileRepository.getFirstName(), name -> name +" transformed");
    }

    public void insert (Profile profile) {
        profileRepository.insert(profile);
    }

    public void update (Profile profile) {
        profileRepository.update(profile);
    }

    public void delete (Profile profile) {
        profileRepository.delete(profile);
    }

    public LiveData<List<Profile>> getAllProfiles() {
        return allProfiles;
    }
}
