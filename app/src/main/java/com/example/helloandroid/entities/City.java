package com.example.helloandroid.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "cities")
public class City {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;
}
