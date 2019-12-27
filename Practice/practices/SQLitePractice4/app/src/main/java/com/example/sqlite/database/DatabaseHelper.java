package com.example.sqlite.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sqlite.database.entity.Contact;

import java.util.ArrayList;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "contact_db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Contact.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Contact.TABLE_NAME);
        onCreate(db);
    }

    //read one, read all
    public Contact getContact(long id){
         SQLiteDatabase db = this.getReadableDatabase();
         Cursor cursor = db.query(
                 Contact.TABLE_NAME,
                 new String[]{Contact.COLUMN_ID, Contact.COLUMN_NAME, Contact.COLUMN_EMAIL},
                 Contact.COLUMN_ID + " = ?",
                 new String[]{String.valueOf(id)},
                 null, null, null);
         if(cursor.moveToFirst()){
             Contact contact = new Contact();
             contact.setId(cursor.getString(cursor.getColumnIndex(Contact.COLUMN_ID)));
             contact.setName(cursor.getString(cursor.getColumnIndex(Contact.COLUMN_NAME)));
             contact.setEmail(cursor.getString(cursor.getColumnIndex(Contact.COLUMN_EMAIL)));
             cursor.close();
             return contact;
         }
         cursor.close();
        return null;
    }

    public ArrayList<Contact> getAllContacts(){
        ArrayList<Contact> contacts = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Contact.TABLE_NAME, null);
        if(cursor.moveToFirst()){
            do {
                Contact contact = new Contact();
                contact.setId(cursor.getString(cursor.getColumnIndex(Contact.COLUMN_ID)));
                contact.setName(cursor.getString(cursor.getColumnIndex(Contact.COLUMN_NAME)));
                contact.setEmail(cursor.getString(cursor.getColumnIndex(Contact.COLUMN_EMAIL)));
                contacts.add(contact);
            } while(cursor.moveToNext());
        }
        cursor.close();
        return contacts;
    }

    public long addContact(String name, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Contact.COLUMN_NAME, name);
        contentValues.put(Contact.COLUMN_EMAIL, email);
        long index = db.insert(Contact.TABLE_NAME, null, contentValues);
        db.close();
        return index;
    }

    public long updateContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Contact.COLUMN_ID, contact.getId());
        contentValues.put(Contact.COLUMN_NAME, contact.getName());
        contentValues.put(Contact.COLUMN_EMAIL, contact.getEmail());
        long index = db.update(Contact.TABLE_NAME, contentValues, Contact.COLUMN_ID + " = ?", new String[]{String.valueOf(contact.getId())});
        db.close();
        return index;
    }

    public void deleteContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Contact.TABLE_NAME, Contact.COLUMN_ID + " = ?", new String[]{String.valueOf(contact.getId())});
        db.close();
    }

}
