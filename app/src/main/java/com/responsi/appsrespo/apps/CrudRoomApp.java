package com.responsi.appsrespo.apps;

import android.app.Application;

import androidx.room.Room;

import com.responsi.appsrespo.database.AppDataBase;
import com.responsi.appsrespo.database.DataBaseMigrations;

public class CrudRoomApp extends Application {

    private static CrudRoomApp INSTANCE;
    private AppDataBase dataBase;

    public static CrudRoomApp getInstance(){
        return INSTANCE;
    }

    @Override
    public void onCreate() {
         super.onCreate();
         dataBase = Room.databaseBuilder(this,AppDataBase.class,"database matakuliah")
                 .addMigrations(DataBaseMigrations.MIGRATION_1_TO_2)
                 .allowMainThreadQueries()
                 .build();

         INSTANCE =this;
    }
    public AppDataBase getDataBase(){
        return dataBase;
    }
}
