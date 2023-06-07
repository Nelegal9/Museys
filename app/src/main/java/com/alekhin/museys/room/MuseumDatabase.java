package com.alekhin.museys.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Museum.class}, version = 1, exportSchema = false)
public abstract class MuseumDatabase extends RoomDatabase {
    public abstract MuseumDao museumDao();
    private static volatile MuseumDatabase INSTANCE = null;

    synchronized static MuseumDatabase getDatabase(Context context) {
        if (INSTANCE == null) INSTANCE = Room.databaseBuilder(context.getApplicationContext(), MuseumDatabase.class, "museum_database").allowMainThreadQueries().fallbackToDestructiveMigration().createFromAsset("database/museum.db").build();
        return INSTANCE;
    }
}