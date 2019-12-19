package com.example.sqlite.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlite.R;
import com.example.sqlite.database.entity.Contact;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.RowCell> {

    private Context context;
    private ArrayList<Contact> contacts;
    private ContactListener contactListener;

    public ContactAdapter(Context context, ArrayList<Contact> contacts, ContactListener contactListener) {
        this.context = context;
        this.contacts = contacts;
        this.contactListener = contactListener;
    }

    @NonNull
    @Override
    public RowCell onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview, parent, false);
        return new RowCell(view, contactListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RowCell holder, int position) {
        Contact contact = contacts.get(position);
        holder.tvName.setText(contact.getName());
        holder.tvEmail.setText(contact.getEmail());
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    class RowCell extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tvName;
        private TextView tvEmail;
        private ImageView ivDelete;
        private ContactListener contactListener;

        RowCell(View view, ContactListener contactListener) {
            super(view);
            tvName = view.findViewById(R.id.tvName);
            tvEmail = view.findViewById(R.id.tvEmail);
            ivDelete = view.findViewById(R.id.ivCancel);
            this.contactListener = contactListener;
            itemView.setOnClickListener(this);
            ivDelete.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            switch (v.getId()){
                case R.id.ivCancel:
                    contactListener.contactDeleteListener(position);
                    break;
                default:
                    contactListener.contactListener(position);
                    break;
            }

        }
    }
}
