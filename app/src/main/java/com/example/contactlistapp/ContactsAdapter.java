package com.example.contactlistapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.viewHolder> {
    ArrayList<ContactsModel> list;
    Context context;

    public ContactsAdapter(ArrayList<ContactsModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ContactsAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=  LayoutInflater.from(context).inflate(R.layout.contact_card_view,parent,false);
        return new viewHolder(view);

    }

    @Override
    public void onBindViewHolder( ContactsAdapter.viewHolder holder, int position) {
        ContactsModel contactsModel=list.get(position);

        holder.name.setText(contactsModel.getName());
        holder.phone.setText(contactsModel.getPhoneno());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // START NEW ACTIVITY

                Intent intent =new Intent(context,UserContactDetails.class);
                intent.putExtra("name",contactsModel.getName());
                intent.putExtra("phone",contactsModel.getPhoneno());
                intent.putExtra("email",contactsModel.getEmail());
                intent.putExtra("address",contactsModel.getAddress());



                context.startActivity(intent);

            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        private TextView name,phone;


        public viewHolder(@NonNull View itemView) {
            super(itemView);
           name= itemView.findViewById(R.id.tv1_name);
           phone=itemView.findViewById(R.id.tv1_phone);
        }
    }
}
