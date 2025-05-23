package com.example.helloandroid.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "cities")
public class City {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;

    public City(){}
    public City(String name) {
        this.name = name;
    }

}
