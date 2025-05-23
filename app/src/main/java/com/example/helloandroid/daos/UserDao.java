package com.example.helloandroid.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.helloandroid.entities.User;
import java.util.List;
@Dao
public interface UserDao {
    //Select Todos
    //Select * from users
    @Query("select * from users")
    List<User> getAll();

    @Query("select * from users where id = :id")
    User findById(int id);

    @Insert
    void insert(User users);
}
