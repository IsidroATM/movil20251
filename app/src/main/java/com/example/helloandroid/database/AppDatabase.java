package com.example.helloandroid.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.helloandroid.daos.CityDao;
import com.example.helloandroid.daos.ProductDao;
import com.example.helloandroid.daos.UserDao;
import com.example.helloandroid.entities.City;
import com.example.helloandroid.entities.Product;
import com.example.helloandroid.entities.User;

@Database(entities = {User.class, Product.class, City.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase appDatabase;
    public abstract UserDao userDao();
    public abstract ProductDao productDao();
    public abstract CityDao cityDao();
    public static AppDatabase getInstance(Context context) {
        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(context,
                            AppDatabase.class, "helloandroid_db")
                    .allowMainThreadQueries()
                    .build();
        }

        return appDatabase;
    }

}
