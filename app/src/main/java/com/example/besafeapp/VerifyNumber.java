package com.example.besafeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.besafeapp.helpers.DBHelper;

import java.util.Random;

public class VerifyNumber extends AppCompatActivity {

    Button verifyButton, backButton;
    EditText otpEditText, phoneEditText;
    TextView sendAgain;

    String phoneNo;
    int generatedOTP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_number);

        Intent intent = getIntent();
        phoneNo = intent.getStringExtra("phoneNo");
        String name = intent.getStringExtra("name");
        String password = intent.getStringExtra("password");

        verifyButton = findViewById(R.id.verifyButton);
        backButton = findViewById(R.id.backButton);
        otpEditText = findViewById(R.id.otpEditText);
        phoneEditText = findViewById(R.id.phoneEditText);
        sendAgain = findViewById(R.id.sendAgain);

        phoneEditText.setText(phoneNo);
        phoneEditText.setEnabled(false);

        generateOTP();

        if(ContextCompat.checkSelfPermission(VerifyNumber.this, android.Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
            sendMessage();
        }
        else {
            ActivityCompat.requestPermissions(VerifyNumber.this, new String[]{Manifest.permission.SEND_SMS}, 100);
            sendMessage();
        }

        verifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int otpEntered = Integer.valueOf(otpEditText.getText().toString());
                if(generatedOTP == otpEntered){
                    Toast.makeText(VerifyNumber.this, "verified", Toast.LENGTH_SHORT).show();
                    DBHelper DB = new DBHelper(getApplicationContext());
                    DB.insertUser(phoneNo, name, password);

                    SharedPreferences sp = getSharedPreferences("logged", MODE_PRIVATE);
                    sp = getSharedPreferences("logged",MODE_PRIVATE);
                    sp.edit().putBoolean("logged", true).apply();

                    sp = getSharedPreferences("phoneNo",MODE_PRIVATE);
                    sp.edit().putString("phoneNo",phoneNo).apply();

                    Toast.makeText(VerifyNumber.this, "Number verified", Toast.LENGTH_SHORT).show();

                    //GO TO WELCOME PAGE
                    Intent intent1 = new Intent(VerifyNumber.this, GetStartedPage.class);
                    startActivity(intent1);
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(VerifyNumber.this, SignUp.class);
                startActivity(intent1);
            }
        });

        sendAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateOTP();
                sendMessage();
            }
        });
    }

    void generateOTP(){
        Random rand = new Random();
        generatedOTP = 1000 + rand.nextInt(8999);
        //sendSms(Double.valueOf(phoneNo), generatedOTP);
    }

    public void sendMessage() {
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phoneNo, null, String.valueOf(generatedOTP)+ " is your OTP for logging into Be Safe App", null, null);
    }
}