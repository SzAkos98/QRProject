package com.example.asdlo.qrreadertest2.viewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.asdlo.qrreadertest2.repositories.HistoryRepository;
import com.example.asdlo.qrreadertest2.model.History;

import java.util.List;

public class HistoryViewModel extends AndroidViewModel {
    private HistoryRepository historyRepository;
    private LiveData<List<History>> allHistories;

    public HistoryViewModel(@NonNull Application application) {
        super(application);
        historyRepository = new HistoryRepository(application);
        allHistories = historyRepository.getAllHistories();
    }

    public void insert (History history) {
        historyRepository.insert(history);
    }

    public void update (History history) {
        historyRepository.update(history);
    }

    public void delete (History history) {
        historyRepository.delete(history);
    }

    public LiveData<List<History>> getAllHistories() {
        return allHistories;
    }
}
