package com.responsi.appsrespo.database;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class DataBaseMigrations {

    public static final Migration MIGRATION_1_TO_2 = new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE matakuliah ADD COLUMN gambar TEXT DEFAULT''");
        }
    };

}
