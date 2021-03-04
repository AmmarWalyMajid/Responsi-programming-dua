package com.responsi.appsrespo.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.responsi.appsrespo.dao.MatakuliahDao;

@Database(entities = {Matakuliah.class},version = 3)
public abstract class AppDataBase extends RoomDatabase {

    public abstract MatakuliahDao matakuliahDao();



}
