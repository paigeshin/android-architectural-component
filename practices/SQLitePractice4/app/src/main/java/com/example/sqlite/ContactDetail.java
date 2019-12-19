package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sqlite.database.DatabaseHelper;
import com.example.sqlite.database.entity.Contact;

import org.w3c.dom.Text;

public class ContactDetail extends AppCompatActivity {

    private EditText etName;
    private EditText etEmail;
    private Button btnUpdate;

    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);
        init();
        setDetail();
    }

    private void init(){
        db = new DatabaseHelper(ContactDetail.this);
        etName = findViewById(R.id.etName_from_detail);
        etEmail = findViewById(R.id.etEmail_from_detail);
        btnUpdate = findViewById(R.id.btnUpdate);
    }

    private void setDetail(){
        Intent intent = getIntent();
        final String id = intent.getStringExtra("id");
        Contact contact = db.getContact(Long.parseLong(id));
        etName.setText(contact.getName());
        etEmail.setText(contact.getEmail());

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.updateContact(new Contact(id, etName.getText().toString(), etEmail.getText().toString()));
            }
        });

    }



}
