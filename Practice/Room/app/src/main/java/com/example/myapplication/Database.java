package com.example.myapplication;

import androidx.room.RoomDatabase;

import com.example.myapplication.db.entity.Contact;

@androidx.room.Database(entities = {Contact.class}, version = 1)
public abstract class Database extends RoomDatabase {
    public abstract MyDao mydao();
}
