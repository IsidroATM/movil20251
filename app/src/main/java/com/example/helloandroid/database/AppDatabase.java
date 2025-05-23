package com.example.helloandroid.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.helloandroid.daos.ProductDao;
import com.example.helloandroid.daos.UserDao;
import com.example.helloandroid.entities.City;
import com.example.helloandroid.entities.Product;
import com.example.helloandroid.entities.User;

@Database(entities = {User.class, Product.class, City.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public static AppDatabase appdatabase;
    public abstract UserDao userDao();
    public ProductDao productDao;
    public static AppDatabase getInstance(Context context){
        if (appdatabase == null){
            appdatabase = Room.databaseBuilder(context,
                    AppDatabase.class, "helloandroid-db1").allowMainThreadQueries().build();
        }
        return appdatabase;
    }
}
