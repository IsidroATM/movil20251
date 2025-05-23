package com.example.helloandroid;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import com.example.helloandroid.daos.UserDao;
import com.example.helloandroid.database.AppDatabase;
import com.example.helloandroid.entities.City;
import com.example.helloandroid.entities.User;
import com.example.helloandroid.repositories.UserRepositorio;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserActivity extends AppCompatActivity {

    UserRepositorio userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        userRepository = new UserRepositorio(AppDatabase.getInstance(this));


        for (User user: userRepository.getAll()) {
            Log.i("MAIN_APP UserActivity", user.email);
            Log.i("MAIN_APP UserActivity", user.city.name);
        }


        //Log.i("MAIN_APP UserActivity", new Gson().toJson(db.userDao().getAll()));

    }

    public void fillData(){
//        db.cityDao().insert(new City("Lima"));
//        db.cityDao().insert(new City("Cajamarca"));


    }
}