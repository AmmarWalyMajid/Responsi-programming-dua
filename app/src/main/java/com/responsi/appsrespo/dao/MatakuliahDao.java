package com.responsi.appsrespo.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.responsi.appsrespo.database.Matakuliah;

import java.util.List;

@Dao
public interface MatakuliahDao {
    @Query("SELECT*FROM matakuliah")
    List<Matakuliah>getAll();

    @Query("SELECT * FROM matakuliah WHERE id LIKE :matakuliahId LIMIT 1")
    Matakuliah findById(int matakuliahId);

    @Update
    void update (Matakuliah matakuliah);

    @Insert
    void insertData (Matakuliah matakuliah);

    @Delete
    void delete (Matakuliah matakuliah);

}
