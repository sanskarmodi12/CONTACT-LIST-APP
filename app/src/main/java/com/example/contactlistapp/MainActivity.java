package com.example.contactlistapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<ContactsModel> contactsList;
    RecyclerView recyclerView;
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;
//    private String [] mColumnProjection=new String[]{
//            ContactsContract.Contacts.DISPLAY_NAME_PRIMARY,
//            ContactsContract.CommonDataKinds.Phone.NUMBER,
//            ContactsContract.Contacts.HAS_PHONE_NUMBER,
//
//    };


    @SuppressLint("Range")
    public  void getContacts()
    {
//        String name,contactId,phoneno = null,address = null,email = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);

        }
        else{
            contactsList =new ArrayList<>();
            ContentResolver contentResolver=getContentResolver();
            Cursor cursor=contentResolver.query(ContactsContract.Contacts.CONTENT_URI,
                    null,
                    null,
                    null,
                    null);
            if(cursor!=null && cursor.getCount()>0){

                while (cursor.moveToNext()){
                    String name,contactId,phoneno = null,address = null,email = null;

                  name=cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                  contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                  Cursor phoneCursor = getContentResolver().query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{contactId},
                            null);
                    if (phoneCursor.moveToNext()) {
                        phoneno = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        phoneCursor.close();


                    }

                    Cursor emailCursor = getContentResolver().query(
                            ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?",
                            new String[]{contactId},
                            null);
                    if (emailCursor.moveToNext()) {
                        email = emailCursor.getString(emailCursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
                        emailCursor.close();


                    }

                    Cursor addressCursor = getContentResolver().query(
                            Uri.parse(String.valueOf(ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_URI)),
                            null,
                            ContactsContract.CommonDataKinds.StructuredPostal.CONTACT_ID + " = ?",
                            new String[]{contactId},
                            null);
                    if (addressCursor.moveToNext()) {
                        address = addressCursor.getString(addressCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.FORMATTED_ADDRESS));
                        addressCursor.close();


                    }
                    ContactsModel contactsModel=new ContactsModel(name,phoneno,email,address);



                    contactsList.add(contactsModel);





                }
                cursor.close();
            }
            ContactsAdapter contactsAdapter=new ContactsAdapter(contactsList,this);
            recyclerView.setAdapter(contactsAdapter);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);





        }

    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted
                getContacts();
            } else {
                Toast.makeText(this, "Until you grant the permission, we canot display the names", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=(RecyclerView) findViewById(R.id.recyclerView);
        getContacts();



    }



}