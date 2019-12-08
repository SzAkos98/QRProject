package com.example.asdlo.qrreadertest2.datebase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.asdlo.qrreadertest2.model.History;

@Database(entities = History.class, version = 1)
public abstract class HistoryDatabase extends RoomDatabase {

    private static HistoryDatabase instance;

    public abstract HistoryDao historyDao();

    public static synchronized HistoryDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    HistoryDatabase.class, "history_database")
                    .fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}
