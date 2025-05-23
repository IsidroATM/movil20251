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
import com.example.helloandroid.entities.User;
import com.google.gson.Gson;

import java.util.List;

public class UserAcitivy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_acitivy);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        AppDatabase db = AppDatabase.getInstance(this);

        UserDao userDao = db.userDao();
//        User user1 = new User();
//        user1.email = "email1@gmail.com";
//        user1.password = "123456";
//        User user2 = new User();
//        user2.email = "email2@gmail.com";
//        user2.password = "123456";
//
//        userDao.insert(user1);
//        userDao.insert(user2);

        List<User> users = userDao.getAll();

        Log.i("MAIN_APP UserActivity", new Gson().toJson(users));
    }
}