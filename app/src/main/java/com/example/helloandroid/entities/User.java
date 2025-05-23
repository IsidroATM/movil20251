package com.example.helloandroid.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "users", indices = {@Index(value = {"email"}, unique = true)})
public class User {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String email;
    public String password;
    @ColumnInfo(name = "city_id")
    public int cityId;

    @Ignore
    public City city;
}
