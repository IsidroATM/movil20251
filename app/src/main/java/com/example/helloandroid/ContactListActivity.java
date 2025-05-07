package com.example.helloandroid;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.helloandroid.adpaters.ContactAdapter;
import com.example.helloandroid.entities.Contact;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ContactListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    ArrayList<Contact> contactList;
    ContactAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        contactList = new ArrayList<>();
        adapter = new ContactAdapter(contactList);
        recyclerView.setAdapter(adapter);

        // Búsqueda
        EditText searchField = findViewById(R.id.searchField);
        searchField.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.filter(s.toString());
            }
            @Override public void afterTextChanged(Editable s) {}
        });

        // Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference("contacts");
        databaseReference.limitToLast(100) // paginación simple
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        ArrayList<Contact> loadedContacts = new ArrayList<>();
                        for (DataSnapshot contactSnapshot : snapshot.getChildren()) {
                            Contact contact = contactSnapshot.getValue(Contact.class);
                            if (contact != null) loadedContacts.add(contact);
                        }
                        contactList.clear();
                        contactList.addAll(loadedContacts);
                        adapter.updateData(loadedContacts);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(ContactListActivity.this, "Error al cargar contactos", Toast.LENGTH_SHORT).show();
                    }
                });
        Button btnAddContact = findViewById(R.id.btnAddContact);
        btnAddContact.setOnClickListener(v -> {
            Intent intent = new Intent(ContactListActivity.this, RegisterContactActivity.class);
            startActivity(intent);
        });
    }
}