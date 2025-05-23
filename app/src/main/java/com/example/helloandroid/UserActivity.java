package com.example.helloandroid;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.helloandroid.daos.UserDao;
import com.example.helloandroid.database.AppDatabase;
import com.example.helloandroid.entities.City;
import com.example.helloandroid.entities.User;
import com.example.helloandroid.repositories.UserRepositorio;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class UserActivity extends AppCompatActivity {
    private UserRepositorio userRepository;
    private RecyclerView recyclerView;
    private UserAdapter userAdapter;

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


        fillData();

        recyclerView = findViewById(R.id.userRecyclerView);
        
        // Configure RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        userAdapter = new UserAdapter(new ArrayList<>());
        recyclerView.setAdapter(userAdapter);
        
        // Load data
        List<User> users = userRepository.getAll();
        userAdapter.setUsers(users);

        //Log.i("MAIN_APP UserActivity", new Gson().toJson(db.userDao().getAll()));
    }

    public void fillData(){
        Log.d("MAIN_APP UserActivity", new Gson().toJson(userRepository.getAll()));

//        City city1 = new City();
//        city1.name = "City 1";
//
//        AppDatabase.getInstance(this).cityDao().insert(city1);

        // Method to fill data if needed
    }
}