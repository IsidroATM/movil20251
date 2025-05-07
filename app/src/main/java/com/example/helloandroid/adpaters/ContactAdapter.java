package com.example.helloandroid.adpaters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.helloandroid.R;
import com.example.helloandroid.entities.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private List<Contact> originalList;
    private List<Contact> displayedList;

    public static class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvPhone, tvGender;

        public ContactViewHolder(View view) {
            super(view);
            tvName = view.findViewById(R.id.tvName);
            tvPhone = view.findViewById(R.id.tvPhone);
            tvGender = view.findViewById(R.id.tvGender);
        }
    }

    public ContactAdapter(List<Contact> contactList) {
        this.originalList = contactList;
        this.displayedList = new ArrayList<>(contactList);
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_contact, parent, false);
        return new ContactViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Contact contact = displayedList.get(position);
        holder.tvName.setText(contact.getName());
        holder.tvPhone.setText(contact.getPhone());
        holder.tvGender.setText(contact.getGender());
    }

    @Override
    public int getItemCount() {
        return displayedList.size();
    }

    public void filter(String query) {
        displayedList.clear();
        if (query == null || query.isEmpty()) {
            displayedList.addAll(originalList);
        } else {
            for (Contact c : originalList) {
                if (c.getName().toLowerCase().contains(query.toLowerCase())) {
                    displayedList.add(c);
                }
            }
        }
        notifyDataSetChanged();
    }

    public void updateData(List<Contact> newList) {
        originalList = new ArrayList<>(newList);
        filter(""); // reset filter
    }

}

