package com.example.asdlo.qrreadertest2.dao;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.asdlo.qrreadertest2.model.History;

import java.util.List;

@Dao
public interface HistoryDao {
    @Query("SELECT * FROM history")
    LiveData<List<History>> loadAllHistory();

    @Insert
    void insertHistory(History history);

    @Update
    void updateHistory(History history);

    @Delete
    void deleteHistory(History history);
}
