package com.example.helloandroid.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.helloandroid.entities.City;

import java.util.List;

@Dao
public interface CityDao {
    @Query("select * from cities")
    public List<City> getAll();

    @Query("select * from cities where id = :id")
    public City findById(int id);

    @Query("select * from cities where id in (:ids)")
    public List<City> getByIds(List<Integer> ids);

    @Insert
    public void insert(City city);
}
