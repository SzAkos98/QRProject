package com.example.asdlo.qrreadertest2.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.asdlo.qrreadertest2.datebase.HistoryDao;
import com.example.asdlo.qrreadertest2.datebase.HistoryDatabase;
import com.example.asdlo.qrreadertest2.model.History;

import java.util.List;

public class HistoryRepository {
    private HistoryDao historyDao;
    private LiveData<List<History>> allHistories;

    public HistoryRepository(Application application) {
        HistoryDatabase database = HistoryDatabase.getInstance(application);
        historyDao = database.historyDao();
        allHistories = historyDao.loadAllHistory();
    }

    public void insert(History history) {
        new InsertHistoryAsyncTask(historyDao).execute(history);
    }

    public void update (History history) {
        new UpdateHistoryAsyncTask(historyDao).execute(history);
    }

    public void  delete( History history) {
        new DeleteHistoryAsyncTask(historyDao).execute(history);
    }

    public LiveData<List<History>> getAllHistories() {
        return allHistories;
    }

    private static class InsertHistoryAsyncTask extends AsyncTask<History, Void ,Void> {
        private HistoryDao historyDao;
        private InsertHistoryAsyncTask(HistoryDao historyDao) {
            this.historyDao = historyDao;
        }

        @Override
        protected Void doInBackground(History... histories) {
            historyDao.insertHistory(histories[0]);
            return null;
        }
    }

    private static class UpdateHistoryAsyncTask extends AsyncTask<History, Void ,Void> {
        private HistoryDao historyDao;
        private UpdateHistoryAsyncTask(HistoryDao historyDao) {
            this.historyDao = historyDao;
        }

        @Override
        protected Void doInBackground(History... histories) {
            historyDao.updateHistory(histories[0]);
            return null;
        }
    }

    private static class DeleteHistoryAsyncTask extends AsyncTask<History, Void ,Void> {
        private HistoryDao historyDao;
        private DeleteHistoryAsyncTask(HistoryDao historyDao) {
            this.historyDao = historyDao;
        }

        @Override
        protected Void doInBackground(History... histories) {
            historyDao.deleteHistory(histories[0]);
            return null;
        }
    }

}
