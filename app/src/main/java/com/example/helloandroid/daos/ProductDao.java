package com.example.helloandroid.daos;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.helloandroid.entities.User;

import java.util.List;

@Dao
public interface ProductDao {
    @Query("select * from product")
    List<User> getAll();
}
