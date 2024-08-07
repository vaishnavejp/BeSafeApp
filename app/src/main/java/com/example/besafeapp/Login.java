package com.example.besafeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.besafeapp.helpers.DBHelper;

public class Login extends AppCompatActivity {

    EditText phoneNoEditText, passwordEditText;
    Button loginButton;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        phoneNoEditText = findViewById(R.id.phonenoEditText);
        passwordEditText = findViewById(R.id.passwordEditText);

        loginButton = findViewById(R.id.login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNo = phoneNoEditText.getText().toString();
                String enteredPassword = passwordEditText.getText().toString();

                DBHelper DB = new DBHelper(getApplicationContext());

                if(DB.isOldUser(phoneNo)==true){
                    String actualPassword = DB.getPassword(phoneNo);

                    if(enteredPassword.equals(actualPassword)){
                        sp = getSharedPreferences("logged",MODE_PRIVATE);
                        sp.edit().putBoolean("logged", true).apply();
                        sp = getSharedPreferences("phoneNo",MODE_PRIVATE);
                        sp.edit().putString("phoneNo",phoneNo).apply();

                        Toast.makeText(Login.this, "Authenticated", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(Login.this, GetStartedPage.class);
                        startActivity(intent);

                        DB.insertNames(phoneNo);

                    }

                    else{
                        passwordEditText.setText("");
                        Toast.makeText(Login.this, "Invalid password!", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(Login.this, "ACCOUNT DOESN'T EXIST, SIGN UP!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}