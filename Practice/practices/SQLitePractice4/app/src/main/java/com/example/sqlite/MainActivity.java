package com.example.sqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sqlite.adapter.ContactAdapter;
import com.example.sqlite.adapter.ContactListener;
import com.example.sqlite.database.DatabaseHelper;
import com.example.sqlite.database.entity.Contact;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ContactListener {

    private FloatingActionButton btnAddContact;
    private RecyclerView recyclerView;
    private ContactAdapter contactAdapter;
    private ArrayList<Contact> contacts = new ArrayList<>();
    private DatabaseHelper db = new DatabaseHelper(MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contacts.addAll(db.getAllContacts());
        init();
        setRecyclerView();
        btnAddContactListener();
    }

    private void init(){
        btnAddContact = findViewById(R.id.btnFloat_add_contact);
    }

    private void setRecyclerView(){
        recyclerView = findViewById(R.id.recyclerView);
        contactAdapter = new ContactAdapter(MainActivity.this, contacts, this);
        recyclerView.setAdapter(contactAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    @Override
    public void contactListener(int position) {
        getContact(position);
    }

    @Override
    public void contactDeleteListener(int position) {
        Contact contact = contacts.get(position);
        deleteContact(contact);
    }

    private void btnAddContactListener(){
        btnAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddDialog();
            }
        });
    }

    private void showAddDialog(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        final LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
        View view = inflater.inflate(R.layout.custom_dialog_fragment, null, false);
        final EditText etName = view.findViewById(R.id.etName);
        final EditText etEmail = view.findViewById(R.id.etEmail);
        builder.setView(view);
        builder.setTitle("add contact");
        builder.create();
        builder
        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = etName.getText().toString();
                String email = etEmail.getText().toString();
                addContact(name, email);
                dialog.dismiss();
            }
        })
        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    private void addContact(String name, String email){
        db.addContact(name, email);
        Contact contact = new Contact();
        contact.setName(name);
        contact.setEmail(email);
        contacts.add(contact);
        contactAdapter.notifyDataSetChanged();
    }

    private void getContact(int position){
        Contact contact = contacts.get(position);
        Intent intent = new Intent(MainActivity.this, ContactDetail.class);
        intent.putExtra("id", contact.getId());
        startActivity(intent);
    }

    private void deleteContact(final Contact contact){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder
                .setMessage("삭제할까요?")
                .setPositiveButton("네", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db.deleteContact(contact);
                        contacts.remove(contact);
                        contactAdapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("아니요", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        contacts.clear();
        contacts.addAll(db.getAllContacts());
        contactAdapter.notifyDataSetChanged();
    }
}
