package com.responsi.appsrespo.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Matakuliah {

    @PrimaryKey(autoGenerate = true)
    private int id=0;

    @ColumnInfo(name = "makul")
    private String makul ="";

    @ColumnInfo(name = "semester")
    private String semester="";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMakul() {
        return makul;
    }

    public void setMakul(String makul) {
        this.makul = makul;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
}
