package com.example.asdlo.qrreadertest2.datebase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.asdlo.qrreadertest2.dao.HistoryDao;
import com.example.asdlo.qrreadertest2.dao.ProfileDao;
import com.example.asdlo.qrreadertest2.model.History;
import com.example.asdlo.qrreadertest2.model.Profile;

@Database(entities = {History.class, Profile.class}, version = 2, exportSchema = false)
public abstract class QrReaderDatabase extends RoomDatabase {

    private static QrReaderDatabase instance;

    public abstract HistoryDao historyDao();

    public abstract ProfileDao profileDao();

    public static synchronized QrReaderDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    QrReaderDatabase.class, "qrreader_database")
                    .fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}
