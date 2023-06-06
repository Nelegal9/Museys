package com.alekhin.museys.room;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MuseumRepository {
    private final MuseumDao museumDao;
    LiveData<List<Museum>> readAllData;

    MuseumRepository(@NonNull MuseumDao museumDao) {
        this.museumDao = museumDao;
        readAllData = museumDao.readAllData();
    }

    LiveData<List<Museum>> searchDatabase(String searchQuery) {
        return museumDao.searchDatabase(searchQuery);
    }
}