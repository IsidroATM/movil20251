package com.example.helloandroid.daos;

import androidx.room.Dao;
import androidx.room.Entity;
import androidx.room.Query;

import com.example.helloandroid.entities.City;

import java.util.List;
@Dao
public interface CityDao {
    @Query("select * from cities")
    public List<City> getAll();
}
