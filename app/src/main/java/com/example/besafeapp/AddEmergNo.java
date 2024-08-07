package com.example.besafeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.besafeapp.helpers.DBHelper;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.besafeapp.helpers.DBHelper;

public class AddEmergNo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_emerg_no);

        DBHelper db = new DBHelper(this);


        SharedPreferences sp = getSharedPreferences("phoneNo", MODE_PRIVATE);
        String phno = sp.getString("phoneNo","");

        // TODO change hardoded number
        int total = db.getTotal(phno);
        String p2 = "I am in danger! SOS! Help me!";

        NavBar fragment = (NavBar) getSupportFragmentManager().findFragmentById(R.id.NavBar);
        if (fragment != null) {
            fragment.updateButtonColor("call");
        }

        Button add = findViewById(R.id.addEm);

        if(total >= 4) {
            add.setVisibility(View.INVISIBLE);
        }
        else add.setVisibility(View.VISIBLE);

        // TODO change harcoded number
        Cursor cursor = db.retrieveUser(phno);
        Cursor namesCursor = db.retrieveNames(phno);
        String en1 = "0", en2 = "0", en3 ="0", en4 = "0";
        String n1 = "", n2 = "", n3 = "", n4 = "";
        LinearLayout l = findViewById(R.id.mainL);
        l.setOrientation(LinearLayout.VERTICAL);

        while(cursor != null && cursor.moveToNext()) {
            en1 = cursor.getString(5);
            en2 = cursor.getString(6);
            en3 = cursor.getString(7);
            en4 = cursor.getString(8);
        }

        while(namesCursor != null && namesCursor.moveToNext()) {
            n1 = namesCursor.getString(1);
            n2 = namesCursor.getString(2);
            n3 = namesCursor.getString(3);
            n4 = namesCursor.getString(4);
        }

        // TODO align phno button properly

        // em1
        if(!en1.equals("0")) {
            String em1 = String.valueOf(en1);

            LinearLayout l2 = new LinearLayout(this);
            l2.setOrientation(LinearLayout.VERTICAL);

            TextView name = new TextView(this);
            TextView ph = new TextView(this);

            name.setText(n1.toString());
            Typeface currentTypeface = name.getTypeface();
            name.setTypeface(Typeface.create(currentTypeface, Typeface.BOLD));
            name.setTextSize(20);

            ph.setText(em1);

            l2.addView(name);
            l2.addView(ph);

            Button b1 = new Button(this);

            b1.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.delete, 0, 0);

            b1.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            b1.setBackgroundColor(Color.TRANSPARENT);

            LinearLayout.LayoutParams buttonLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            buttonLayoutParams.setMargins(650, -30, 0, 0);
            b1.setLayoutParams(buttonLayoutParams);

            String e = en1;

            LinearLayout l3 = new LinearLayout(this);
            l3.setOrientation(LinearLayout.HORIZONTAL);
            l3.setPadding(10, 100, 10, 20);

            l3.addView(l2);
            l3.addView(b1);

            l.addView(l3);

            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    db.deleteEmergNo(phno, e);
                    l.removeView(l3);
                    add.setVisibility(View.VISIBLE);
                }
            });


        }

        //em2
        if(!en2.equals("0")) {
            String em2 = String.valueOf(en2);

            LinearLayout l2 = new LinearLayout(this);
            l2.setOrientation(LinearLayout.VERTICAL);

            TextView name = new TextView(this);
            TextView ph = new TextView(this);

            name.setText(n2.toString());
            Typeface currentTypeface = name.getTypeface();
            name.setTypeface(Typeface.create(currentTypeface, Typeface.BOLD));
            name.setTextSize(20);

            ph.setText(em2);

            l2.addView(name);
            l2.addView(ph);

            Button b1 = new Button(this);

            b1.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.delete, 0, 0);

            b1.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            b1.setBackgroundColor(Color.TRANSPARENT);

            LinearLayout.LayoutParams buttonLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            buttonLayoutParams.setMargins(650, -30, 0, 0);
            b1.setLayoutParams(buttonLayoutParams);

            String e = en2;
            LinearLayout l3 = new LinearLayout(this);
            l3.setOrientation(LinearLayout.HORIZONTAL);
            l3.setPadding(20, 100, 20, 20);

            l3.addView(l2);
            l3.addView(b1);

            l.addView(l3);

            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    db.deleteEmergNo(phno, e);
                    l.removeView(l3);
                    add.setVisibility(View.VISIBLE);
                }
            });
        }

        //em3
        if(!en3.equals("0")) {
            String em3 = String.valueOf(en3);

            LinearLayout l2 = new LinearLayout(this);
            l2.setOrientation(LinearLayout.VERTICAL);

            TextView name = new TextView(this);
            TextView ph = new TextView(this);

            name.setText(n3.toString());
            Typeface currentTypeface = name.getTypeface();
            name.setTypeface(Typeface.create(currentTypeface, Typeface.BOLD));
            name.setTextSize(20);

            ph.setText(em3);

            l2.addView(name);
            l2.addView(ph);

            Button b1 = new Button(this);

            b1.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.delete, 0, 0);

            b1.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            b1.setBackgroundColor(Color.TRANSPARENT);

            LinearLayout.LayoutParams buttonLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            buttonLayoutParams.setMargins(650, -30, 0, 0);
            b1.setLayoutParams(buttonLayoutParams);


            String e = String.valueOf(en3);
            LinearLayout l3 = new LinearLayout(this);
            l3.setOrientation(LinearLayout.HORIZONTAL);
            l3.setPadding(20, 100, 20, 20);

            l3.addView(l2);
            l3.addView(b1);

            l.addView(l3);

            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    db.deleteEmergNo(phno, e);
                    l.removeView(l3);
                    add.setVisibility(View.VISIBLE);
                }
            });
        }

        //em4
        if(!en4.equals("0")) {
            String em4 = String.valueOf(en4);

            LinearLayout l2 = new LinearLayout(this);
            l2.setOrientation(LinearLayout.VERTICAL);

            TextView name = new TextView(this);
            TextView ph = new TextView(this);

            name.setText(n4.toString());
            Typeface currentTypeface = name.getTypeface();
            name.setTypeface(Typeface.create(currentTypeface, Typeface.BOLD));
            name.setTextSize(20);

            ph.setText(em4);

            l2.addView(name);
            l2.addView(ph);

            Button b1 = new Button(this);

            b1.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.delete, 0, 0);

            b1.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            b1.setBackgroundColor(Color.TRANSPARENT);

            LinearLayout.LayoutParams buttonLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            buttonLayoutParams.setMargins(650, -30, 0, 0);
            b1.setLayoutParams(buttonLayoutParams);

//            int e1 = en4;
            String e = String.valueOf(en4);
            LinearLayout l3 = new LinearLayout(this);
            l3.setOrientation(LinearLayout.HORIZONTAL);
            l3.setPadding(20, 100, 20, 20);

            l3.addView(l2);
            l3.addView(b1);

            l.addView(l3);

            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    db.deleteEmergNo(phno, e);
                    l.removeView(l3);
                    add.setVisibility(View.VISIBLE);
                }
            });
        }




        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AddEmergNo.this, AddHelpline.class);
                startActivity(i);
            }
        });

        Button back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddEmergNo.this, EditAccount.class);
                startActivity(i);
            }
        });
    }
}