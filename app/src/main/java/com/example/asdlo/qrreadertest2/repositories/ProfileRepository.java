package com.example.asdlo.qrreadertest2.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.asdlo.qrreadertest2.dao.ProfileDao;
import com.example.asdlo.qrreadertest2.datebase.QrReaderDatabase;
import com.example.asdlo.qrreadertest2.model.Profile;

import java.util.List;

public class ProfileRepository {
    private ProfileDao profileDao;
    private LiveData<List<Profile>> allProfiles;
    private LiveData<String> firstName;

    public ProfileRepository(Application application) {
        QrReaderDatabase database = QrReaderDatabase.getInstance(application);
        profileDao = database.profileDao();
        allProfiles = profileDao.loadAllProfile();
        firstName = profileDao.firstName();
    }

    public void insert(Profile profile) {
        new InsertProfileAsyncTask(profileDao).execute(profile);
    }

    public void update (Profile profile) {
        new UpdateProfileAsyncTask(profileDao).execute(profile);
    }

    public void  delete( Profile profile) {
        new DeleteProfileAsyncTask(profileDao).execute(profile);
    }

    public LiveData<List<Profile>> getAllProfiles() {
        return allProfiles;
    }

    public LiveData<String> getFirstName() {
        return firstName;
    }

    private static class InsertProfileAsyncTask extends AsyncTask<Profile, Void ,Void> {
        private ProfileDao profileDao;
        private InsertProfileAsyncTask(ProfileDao profileDao) {
            this.profileDao = profileDao;
        }

        @Override
        protected Void doInBackground(Profile... profiles) {
            profileDao.insertProfile(profiles[0]);
            return null;
        }
    }

    private static class UpdateProfileAsyncTask extends AsyncTask<Profile, Void ,Void> {
        private ProfileDao profileDao;
        private UpdateProfileAsyncTask(ProfileDao profileDao) {
            this.profileDao = profileDao;
        }

        @Override
        protected Void doInBackground(Profile... profiles) {
            profileDao.updateProfile(profiles[0]);
            return null;
        }
    }

    private static class DeleteProfileAsyncTask extends AsyncTask<Profile, Void ,Void> {
        private ProfileDao profileDao;
        private DeleteProfileAsyncTask(ProfileDao profileDao) {
            this.profileDao = profileDao;
        }

        @Override
        protected Void doInBackground(Profile... profiles) {
            profileDao.deleteProfile(profiles[0]);
            return null;
        }
    }

}
