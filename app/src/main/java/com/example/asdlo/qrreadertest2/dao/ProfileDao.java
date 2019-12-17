package com.example.asdlo.qrreadertest2.dao;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.asdlo.qrreadertest2.model.History;
import com.example.asdlo.qrreadertest2.model.Profile;

import java.util.List;

@Dao
public interface ProfileDao {
    @Query("SELECT * FROM profile")
    LiveData<List<Profile>> loadAllProfile();

    @Query("SELECT name FROM profile ORDER BY id LIMIT 1")
    LiveData<String> firstName();

    @Insert
    void insertProfile(Profile profile);

    @Update
    void updateProfile(Profile profile);

    @Delete
    void deleteProfile(Profile profile);
}
