package com.example.myapplication;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myapplication.db.entity.Contact;

import java.util.List;

public interface MyDao {

    @Insert
    public void addContact(Contact contact);

    @Query("SELECT * FROM contacts")
    public List<Contact> getContacts();

    @Update
    public void updateContact(Contact contact);

    @Delete
    public void deleteContact(Contact contact);

}
