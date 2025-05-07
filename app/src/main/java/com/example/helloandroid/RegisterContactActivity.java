package com.example.helloandroid;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.helloandroid.entities.Contact;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import androidx.appcompat.app.AppCompatActivity;


import com.google.firebase.database.FirebaseDatabase;

public class RegisterContactActivity extends AppCompatActivity {

    private EditText etName, etPhone, etGender, etAddress;
    private Button btnSave;
    private ImageView imgProfile;
    private com.google.firebase.database.DatabaseReference dbRef;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_contact);

        etName = findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPhone);
        etGender = findViewById(R.id.etGender);
        etAddress = findViewById(R.id.etAddress);
        imgProfile = findViewById(R.id.imgProfile);
        btnSave = findViewById(R.id.btnSave);

        dbRef = FirebaseDatabase.getInstance().getReference("contacts");

        btnSave.setOnClickListener(v -> saveContact());
    }

    private void saveContact() {
        String id = dbRef.push().getKey();
        String name = etName.getText().toString();
        String phone = etPhone.getText().toString();
        String gender = etGender.getText().toString();
        String address = etAddress.getText().toString();

        Contact contact = new Contact(id, name, phone, gender, address);
        dbRef.child(id).setValue(contact)
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(this, "Contacto guardado", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterContactActivity.this, ContactListActivity.class);
                    startActivity(intent);
                    finish();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }
}