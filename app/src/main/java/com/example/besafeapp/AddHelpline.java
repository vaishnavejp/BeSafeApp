package com.example.besafeapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.besafeapp.helpers.DBHelper;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class AddHelpline extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addhelpline);

        DBHelper db = new DBHelper(this);

        SharedPreferences sp = getSharedPreferences("phoneNo", MODE_PRIVATE);
        String phoneNo = sp.getString("phoneNo","");

        db.retrieveUser(phoneNo);

        Button add = findViewById(R.id.add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextInputEditText name = findViewById(R.id.name);
                TextInputEditText phno = findViewById(R.id.phno);

                String phnoS = phno.getText().toString();

                // TODO : insert values into names table when user logs in
                db.insertNames(phoneNo);
                db.insertPhno(phoneNo, phnoS, name.getText().toString());

                System.out.println("done");

                Intent i = new Intent(AddHelpline.this, AddEmergNo.class);
                startActivity(i);
            }
        });

    }
}