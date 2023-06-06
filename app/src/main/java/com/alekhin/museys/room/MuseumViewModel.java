package com.alekhin.museys.room;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MuseumViewModel extends AndroidViewModel {
    public final LiveData<List<Museum>> readAllData;
    private final MuseumRepository museumRepository;

    public MuseumViewModel(@NonNull Application application) {
        super(application);

        MuseumDao museumDao = MuseumDatabase.getDatabase(application).museumDao();
        museumRepository = new MuseumRepository(museumDao);
        readAllData = museumDao.readAllData();
    }

    public LiveData<List<Museum>> searchDatabase(String searchQuery) {
        return museumRepository.searchDatabase(searchQuery);
    }
}