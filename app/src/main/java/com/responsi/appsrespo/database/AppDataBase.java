package com.responsi.appsrespo.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(entities = {Matakuliah.class},version = 2)
public abstract class AppDataBase extends RoomDatabase {

    public abstract MatakuliahDao matakuliahDao();



}
