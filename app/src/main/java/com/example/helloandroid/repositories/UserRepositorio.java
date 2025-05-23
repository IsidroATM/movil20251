package com.example.helloandroid.repositories;

import com.example.helloandroid.database.AppDatabase;
import com.example.helloandroid.entities.City;
import com.example.helloandroid.entities.User;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UserRepositorio {
    AppDatabase db;
    public UserRepositorio(AppDatabase db) {
        this.db = db;
    }
    public List<User> getAll() {
        List<User> users = db.userDao().getAll();



            List<Integer> cityIds = users.stream()
                    .map(u -> u.cityId).distinct()
                    .collect(Collectors.toList());

            List<City> cities = db.cityDao().getByIds(cityIds);

            for (User user : users) {
                user.city = cities.stream()
                        .filter(o -> o.id == user.cityId)
                        .findFirst()
                        .orElse(null);
            }


        return users;
    }
}
