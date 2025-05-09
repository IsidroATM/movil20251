package com.example.helloandroid;

import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;

import com.example.helloandroid.databinding.ActivityLoginBinding;
import com.example.helloandroid.entities.User;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;

    private User userInput = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setupBinding();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());

            int basePadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics());

            v.setPadding(systemBars.left + basePadding, systemBars.top + basePadding, systemBars.right + basePadding, systemBars.bottom + basePadding);
            return insets;
        });

        setupUI();
    }

    private void setupBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.setUser(userInput);
    }

    private void setupUI() {
        binding.loginBtn.setOnClickListener(view ->{
            Log.i("MAIN_APP LoginActivity", "Login button clicked");
            Log.i("MAIN_APP LoginActivity", "User email: " + userInput.email);
            Log.i("MAIN_APP LoginActivity", "User password: " + userInput.password);
        });
    }
}