package com.example.besafeapp;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.besafeapp.helpers.DBHelper;

import org.w3c.dom.Text;

public class HomePage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        SharedPreferences sp = getSharedPreferences("phoneNo", MODE_PRIVATE);
        String phoneNo = sp.getString("phoneNo", "");

        Uri alarmrt1 = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        Ringtone ringtone = RingtoneManager.getRingtone(this, alarmrt1);

        DBHelper db = new DBHelper(this);

        NavBar fragment = (NavBar) getSupportFragmentManager().findFragmentById(R.id.NavBar);
        if (fragment != null) {
            fragment.updateButtonColor("home");
        }

        Cursor user = db.retrieveUser(phoneNo);
        String name = "User";
        while (user.moveToNext()){
            name = user.getString(1);
        }

        Button alarm = findViewById(R.id.alarm);
        Button sms = findViewById(R.id.sms);
        Button help = findViewById(R.id.lHelplines);
        Button sos = findViewById(R.id.sos);
        Button place = findViewById(R.id.place);

        place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomePage.this, SafePlaces.class);
                startActivity(i);
            }
        });

        TextView homeName = findViewById(R.id.homeName);
        homeName.setText("Hello, "+ name +" ");

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomePage.this, Helpline.class);
                startActivity(i);
            }
        });

        alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ringtone != null && ringtone.isPlaying()) {
                    alarm.setText("Alarm On");
                    ringtone.stop();
                }
                else {
                    alarm.setText("Alarm Off");
                    ringtone.play();
                }
            }
        });

        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ContextCompat.checkSelfPermission(HomePage.this, android.Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {

                    Cursor cursor = db.retrieveUser(phoneNo);
                    System.out.println(cursor);

                    String en1 = "0", en2 = "0", en3 = "0", en4 = "0";
                    String p2 = "I am in danger! SOS! Help me!";

                    while(cursor != null && cursor.moveToNext()) {
                        en1 = cursor.getString(5);
                        en2 = cursor.getString(6);
                        en3 = cursor.getString(7);
                        en4 = cursor.getString(8);
                    }

                    System.out.println(String.valueOf(en1));

                    if(!en1.equals("0")) {
                        String em = String.valueOf(en1);
                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(em, null, p2, null, null);
                    }

                    if(!en2.equals("0")) {
                        String em = String.valueOf(en2);
                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(em, null, p2, null, null);
                    }

                    if(!en3.equals("0")) {
                        String em = String.valueOf(en3);
                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(em, null, p2, null, null);
                    }

                    if(!en4.equals("0")) {
                        String em = String.valueOf(en4);
                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(em, null, p2, null, null);
                    }

                }
                else {
                    ActivityCompat.requestPermissions(HomePage.this, new String[]{Manifest.permission.SEND_SMS}, 100);
                }
            }
        });

        sos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Thread t1, t2;

                t1 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(ContextCompat.checkSelfPermission(HomePage.this, android.Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {

                                    Cursor cursor = db.retrieveUser(phoneNo);

                                    String en1 = "0", en2 = "0", en3 = "0", en4 = "0";
                                    String p2 = "I am in danger! SOS! Help me!";

                                    while(cursor != null && cursor.moveToNext()) {
                                        en1 = cursor.getString(5);
                                        en2 = cursor.getString(6);
                                        en3 = cursor.getString(7);
                                        en4 = cursor.getString(8);
                                    }

                                    if(!en1.equals("0")) {
                                        String em = String.valueOf(en1);
                                        SmsManager smsManager = SmsManager.getDefault();
                                        smsManager.sendTextMessage(em, null, p2, null, null);
                                    }

                                    if(!en2.equals("0")) {
                                        String em = String.valueOf(en2);
                                        SmsManager smsManager = SmsManager.getDefault();
                                        smsManager.sendTextMessage(em, null, p2, null, null);
                                    }

                                    if(!en3.equals("0")) {
                                        String em = String.valueOf(en3);
                                        SmsManager smsManager = SmsManager.getDefault();
                                        smsManager.sendTextMessage(em, null, p2, null, null);
                                    }

                                    if(!en4.equals("0")) {
                                        String em = String.valueOf(en4);
                                        SmsManager smsManager = SmsManager.getDefault();
                                        smsManager.sendTextMessage(em, null, p2, null, null);
                                    }

                                }
                                else {
                                    ActivityCompat.requestPermissions(HomePage.this, new String[]{Manifest.permission.SEND_SMS}, 100);
                                }
                            }
                        });
                    }
                });

                t2 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                int phno = db.retrieveSosNumber(phoneNo);
                                System.out.println(phno);
                                Intent callIntent = new Intent(Intent.ACTION_CALL);
                                callIntent.setData(Uri.parse("tel:"+phno));

                                if (ActivityCompat.checkSelfPermission(HomePage.this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                    ActivityCompat.requestPermissions(HomePage.this, new String[]{android.Manifest.permission.CALL_PHONE}, 1);
                                } else {
                                    startActivity(callIntent);
                                }
                            }
                        });
                    }
                });

                t1.start();
                t2.start();
            }
        });
    }
}
