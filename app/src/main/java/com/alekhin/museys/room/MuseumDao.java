package com.alekhin.museys.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MuseumDao {

    @Query("SELECT * FROM museum ORDER BY id ASC")
    LiveData<List<Museum>> readAllData();

    @Query("SELECT * FROM museum WHERE museumTitle LIKE :searchQuery")
    LiveData<List<Museum>> searchDatabase(String searchQuery);
}