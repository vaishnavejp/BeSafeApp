package com.example.besafeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.besafeapp.helpers.DBHelper;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        NavBar fragment = (NavBar) getSupportFragmentManager().findFragmentById(R.id.NavBar);
        if (fragment != null) {
            fragment.updateButtonColor("home");
        }

        SharedPreferences sp = getSharedPreferences("phoneNo", MODE_PRIVATE);
        String phoneNo = sp.getString("phoneNo", "");

//        DBHelper DB = new DBHelper(getApplicationContext());
//        Cursor liked = DB.getLikedHelplines(phoneNo);
//        String likedNumbers = "";
//        while(liked.moveToNext()){
//            likedNumbers += liked.getString(0)+" ";
//        }
//        Toast.makeText(this, likedNumbers, Toast.LENGTH_SHORT).show();

        Button butt = findViewById(R.id.buttonacc);
        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, AccountSettings.class);
                startActivity(intent);
            }
        });
    }
}