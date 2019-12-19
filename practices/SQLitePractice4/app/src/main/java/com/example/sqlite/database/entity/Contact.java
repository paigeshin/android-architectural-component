package com.example.sqlite.database.entity;

public class Contact {

    public static final String TABLE_NAME = "contacts";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_EMAIL = "email";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + Contact.TABLE_NAME + " (" +
                    Contact.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    Contact.COLUMN_NAME + " CHAR(30)," +
                    Contact.COLUMN_EMAIL + " TEXT " +
                    ")";

    private String id;
    private String name;
    private String email;

    public Contact(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Contact() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
