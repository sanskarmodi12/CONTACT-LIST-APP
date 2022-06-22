package com.example.contactlistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class UserContactDetails extends AppCompatActivity {
    TextView nametv,emailtv,phonetv,countrytv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_contact_details);
        Intent intent=getIntent();
        String name=intent.getStringExtra("name");
        String email=intent.getStringExtra("email");
        String phone=intent.getStringExtra("phone");
        String country=intent.getStringExtra("address");
        nametv=findViewById(R.id.tv2_name);
        emailtv=findViewById(R.id.tv2_email);
        phonetv=findViewById(R.id.tv2_phone);
       countrytv=findViewById(R.id.tv2_country);
       nametv.setText(name);
       emailtv.setText(email);
       phonetv.setText(phone);
       countrytv.setText(country);






    }
}