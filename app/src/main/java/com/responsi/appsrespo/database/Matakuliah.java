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

    @ColumnInfo(name = "nama")
    private String nama ="";

    @ColumnInfo(name = "email")
    private String email ="";

    @ColumnInfo(name = "password")
    private String password ="";

    @ColumnInfo(name = "image")
    private String image="";

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



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
