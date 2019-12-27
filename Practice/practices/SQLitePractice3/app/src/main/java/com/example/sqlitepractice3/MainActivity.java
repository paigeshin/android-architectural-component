package com.example.sqlitepractice3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.sqlitepractice3.adapter.ClickListener;
import com.example.sqlitepractice3.adapter.ContactAdapter;
import com.example.sqlitepractice3.db.DatabaseHelper;
import com.example.sqlitepractice3.db.entity.Contact;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ClickListener {

    private DatabaseHelper db;
    private RecyclerView recyclerView;
    private ContactAdapter contactAdapter;
    private ArrayList<Contact> contacts = new ArrayList<>();

    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDB();
        initRecyclerView();
        contactAdapter.notifyDataSetChanged();
    }

    private void initRecyclerView(){
        recyclerView = findViewById(R.id.recyclerview);
        contactAdapter = new ContactAdapter(MainActivity.this, contacts, this);
        recyclerView.setAdapter(contactAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    private void initDB(){
        db = new DatabaseHelper(MainActivity.this);
        db.insertContact("kim", "kim@gmail.com");
        db.insertContact("lee", "lee@mail.com");
        db.insertContact("park", "park@haii.co.kr");
        contacts = db.getAllContacts();
        Log.d(TAG, "initDB contacts: " + contacts.size());
    }

    @Override
    protected void onPause() {
        super.onPause();
        db.deleteAll();
    }

    @Override
    public void clickListener(int position) {
        Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_LONG).show();
    }
}
