package com.example.sqlitepractice3.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlitepractice3.R;
import com.example.sqlitepractice3.db.entity.Contact;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactRow> {

    private Context context;
    private ArrayList<Contact> contacts;
    private ClickListener clickListener;

    public ContactAdapter(Context context, ArrayList<Contact> contacts, ClickListener clickListener) {
        this.context = context;
        this.contacts = contacts;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ContactRow onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview, parent, false);
        return new ContactRow(itemView, clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactRow holder, int position) {
        Contact contact = contacts.get(position);
        holder.tvName.setText(contact.getName());
        holder.tvEmail.setText(contact.getEmail());
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    class ContactRow extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tvName;
        private TextView tvEmail;
        private ClickListener clickListener;

        ContactRow(View itemView, ClickListener clickListener) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName_itemview);
            tvEmail = itemView.findViewById(R.id.tvEmail_itemview);
            this.clickListener = clickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.clickListener(getAdapterPosition());
        }
    }

}
